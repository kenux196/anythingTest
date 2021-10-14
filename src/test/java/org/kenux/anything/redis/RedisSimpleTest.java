package org.kenux.anything.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kenux.anything.domain.dto.RedisUserDto;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataRedisTest
//@ContextConfiguration(classes = RedisConfig.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED) // 실제 DB 사용하고 싶을때 NONE 사용
class RedisSimpleTest {

    private RedisTemplate<String, Object> redisTemplate;
    private RedisTemplate<String, RedisUserDto> redisUserDtoRedisTemplate;
    private StringRedisTemplate stringRedisTemplate;
    private List<RedisUserDto> redisUserDtoList;

    @BeforeEach
    void init() {
        final RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("localhost");
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration);
        lettuceConnectionFactory.setDatabase(0); // optional
        lettuceConnectionFactory.afterPropertiesSet();

        redisUserDtoRedisTemplate = new RedisTemplate<>();
        redisUserDtoRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisUserDtoRedisTemplate.setKeySerializer(new StringRedisSerializer());
        redisUserDtoRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisUserDto.class));
        redisUserDtoRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisUserDtoRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RedisUserDto.class));
        redisUserDtoRedisTemplate.afterPropertiesSet();

        stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        stringRedisTemplate.afterPropertiesSet();

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisUserDto.class));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RedisUserDto.class));
        redisTemplate.afterPropertiesSet();

        redisUserDtoList = createData();
    }

    private List<RedisUserDto> createData() {
        List<RedisUserDto> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String id = "kenux-" + i;
            String name = "kenux yun " + i;
            RedisUserDto redisUserDto = RedisUserDto.builder()
                    .id(id)
                    .pw("rotkfrn196")
                    .name(name)
                    .age(i + 10)
                    .adult(true)
                    .build();
            list.add(redisUserDto);
        }
        return list;
    }

    @Test
    void stringRedisTemplateTest() {
        String key = "stringRedisTemplateTest-1";
        String value = "This value is type of String";
        stringRedisTemplate.opsForValue().set(key, value);
        final String result = stringRedisTemplate.opsForValue().get(key);
        assertThat(result).isEqualTo(value);
        System.out.println("result = " + result);
    }

    @Test
    void redisExpireTest() throws InterruptedException {
        String key = "redisExpireTest-1";
        String value = "This value will expire after 5seconds";
        long timeout = 5;

        final ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
        final Boolean expire = stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        final Long time = stringRedisTemplate.getExpire(key);
        System.out.println("time = " + time);
        Thread.sleep(6000);
        final String s = valueOperations.get(key);
        assertThat(expire).isTrue();
        assertThat(s).isNull();
    }

    @Test
    void redisInsertObject() {
        RedisUserDto redisUserDto = RedisUserDto.builder()
                .id("kenux.yun@gamil.com")
                .pw("rotkfrn196")
                .name("kenux")
                .age(44)
                .adult(true)
                .build();

        redisTemplate.opsForValue().set(redisUserDto.getId(), redisUserDto);

        final RedisUserDto result = (RedisUserDto) redisTemplate.opsForValue().get(redisUserDto.getId());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(redisUserDto.getId());
        assertThat(result.getPw()).isEqualTo(redisUserDto.getPw());
        System.out.println("result = " + result);
    }

    @Test
    void redisHashTest() {
        final String key = "redisHashTest#";

        final HashOperations<String, Object, Object> hashOperations = redisUserDtoRedisTemplate.opsForHash();
        for (RedisUserDto redisUserDto : redisUserDtoList) {
            hashOperations.put(key, redisUserDto.getId(), redisUserDto);
        }

        final RedisUserDto getOne = (RedisUserDto) hashOperations.get(key, redisUserDtoList.get(0).getId());
        assertThat(getOne).isNotNull();
        assertThat(getOne.getId()).isEqualTo(redisUserDtoList.get(0).getId());
        assertThat(getOne.getPw()).isEqualTo(redisUserDtoList.get(0).getPw());
        System.out.println("result = " + getOne);

        getOne.setName("dragon");
        redisTemplate.opsForHash().put(key, getOne.getId(), getOne);
        RedisUserDto result = (RedisUserDto) redisTemplate.opsForHash().get(key, getOne.getId());
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(getOne.getName());
    }

    @Test
    void redisHashGetMultiTest() {
        final String key = "redisHashTest#";

        final HashOperations<String, Object, Object> hashOperations = redisUserDtoRedisTemplate.opsForHash();
        for (RedisUserDto redisUserDto : redisUserDtoList) {
            hashOperations.put(key, redisUserDto.getId(), redisUserDto);
        }

        final List<RedisUserDto> results = hashOperations.multiGet(key, redisUserDtoList.stream()
                        .map(RedisUserDto::getId)
                        .collect(Collectors.toList())
                ).stream()
                .map(o -> (RedisUserDto) o)
                .collect(Collectors.toList());

        assertThat(results).isNotNull().isNotEmpty();
        assertThat(results.get(0).getId()).isEqualTo(redisUserDtoList.get(0).getId());
        assertThat(results.get(0).getName()).isEqualTo(redisUserDtoList.get(0).getName());
        for (RedisUserDto result : results) {
            System.out.println("result = " + result);
        }
    }

    @Test
    void redisTestForZSet() {
        final String key = "redisTestForZSet#";

        final ZSetOperations<String, RedisUserDto> zSetOperations = redisUserDtoRedisTemplate.opsForZSet();

        for (int i = 0; i < redisUserDtoList.size(); i++) {
            zSetOperations.add(key, redisUserDtoList.get(i), i);
        }
        final Set<RedisUserDto> result = zSetOperations.range(key, 0, redisUserDtoList.size());
        assertThat(result).isNotNull().isNotEmpty();
        for (RedisUserDto redisUserDto : result) {
            System.out.println("redisUserDto = " + redisUserDto);
        }
    }

    @Test
    void redisTestForList() {
        final String key = "redisTestForList#";
        final ListOperations<String, RedisUserDto> listOperations = redisUserDtoRedisTemplate.opsForList();
        for (RedisUserDto redisUserDto : redisUserDtoList) {
            listOperations.rightPush(key, redisUserDto);
        }

        final List<RedisUserDto> results = listOperations.range(key, 0, redisUserDtoList.size());
        assertThat(results).isNotNull().isNotEmpty();
        for (RedisUserDto result : results) {
            System.out.println("result = " + result);
        }
    }
}