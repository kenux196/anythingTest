package org.kenux.anything.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kenux.anything.web.dto.RedisUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataRedisTest
//@ContextConfiguration(classes = RedisConfig.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED) // 실제 DB 사용하고 싶을때 NONE 사용
public class RedisBasicTest {

    @Autowired
    RedisTemplate defaultRedisTemplate;

//    @Autowired
//    private RedisTemplate<String, Object> defaultRedisTemplate;

    @Autowired
//    @Qualifier("defaultStringRedisTemplate")
//    @Resource(name = "defaultStringRedisTemplate")
    StringRedisTemplate defaultStringRedisTemplate;

    @Autowired
//    @Qualifier("deviceControlStringRedisTemplate")
//    @Resource(name = "deviceControlStringRedisTemplate")
    StringRedisTemplate deviceControlStringRedisTemplate;

    private LettuceConnectionFactory lettuceConnectionFactory;
    private RedisTemplate<String, RedisUserDto> testRedisTemplate;
    private StringRedisTemplate testStringRedisTemplate;

    @BeforeEach
    void init() {
        final RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("localhost");
        lettuceConnectionFactory = new LettuceConnectionFactory(configuration);
        lettuceConnectionFactory.setDatabase(2);
        lettuceConnectionFactory.afterPropertiesSet();

        testRedisTemplate = new RedisTemplate<>();
        testRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        testRedisTemplate.setKeySerializer(new StringRedisSerializer());
        testRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(RedisUserDto.class));
        testRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        testRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RedisUserDto.class));
        testRedisTemplate.afterPropertiesSet();

        testStringRedisTemplate = new StringRedisTemplate();
        testStringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        testStringRedisTemplate.afterPropertiesSet();
    }

    @Test
    void customTemplateTest() {
        testRedisTemplate.opsForValue().set("test-1", new RedisUserDto());
        testStringRedisTemplate.opsForValue().set("test-2", "This is String");
    }

    @Test
    void redisConnectionTest() {
        final String key = "a";
        final String data = "1";

        final ValueOperations<String, String> valueOperations = defaultStringRedisTemplate.opsForValue();
        valueOperations.set(key, data);

        final String s = valueOperations.get(key);
        assertThat(s).isEqualTo(data);
    }

    @Test
    void redisExpireTest() throws InterruptedException {
        final String key = "a";
        final String data = "1";

        final ValueOperations<String, String> valueOperations = defaultStringRedisTemplate.opsForValue();
        valueOperations.set(key, data);
        final Boolean expire = defaultStringRedisTemplate.expire(key, 5, TimeUnit.SECONDS);
        final Long time = defaultStringRedisTemplate.getExpire(key);
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
                .name("kenux")
                .age(44)
                .build();

        defaultRedisTemplate.opsForValue().set(redisUserDto.getId(), redisUserDto);

        final RedisUserDto result = (RedisUserDto) defaultRedisTemplate.opsForValue().get(redisUserDto.getId());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(redisUserDto.getId());
        System.out.println("result = " + result);
    }

    @Test
    void redisInsertObjectToHash() {
        RedisUserDto redisUserDto = RedisUserDto.builder()
                .id("kenux.yun@gamil.com")
                .name("kenux")
                .age(44)
                .build();

        final String hashKey = "kenux:hashTest#";

        defaultRedisTemplate.opsForHash().put(hashKey, redisUserDto.getId(), redisUserDto);
        RedisUserDto result = (RedisUserDto) defaultRedisTemplate.opsForHash().get(hashKey, redisUserDto.getId());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(redisUserDto.getId());
        System.out.println("result = " + result);

        redisUserDto.setName("dragon");
        defaultRedisTemplate.opsForHash().put(hashKey, redisUserDto.getId(), redisUserDto);
        result = (RedisUserDto) defaultRedisTemplate.opsForHash().get(hashKey, redisUserDto.getId());
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(redisUserDto.getName());
    }

    @Test
    void redisTestForZSet() {
        final String key = "redisTestForZSet#";
        final List<RedisUserDto> data = createData();

        for (int i = 0; i < data.size(); i++) {
            defaultRedisTemplate.opsForZSet().add(key, data.get(i), i);
        }
        final Set range = defaultRedisTemplate.opsForZSet().range(key, 1, 5);
        final Set<RedisUserDto> range1 = defaultRedisTemplate.opsForZSet().range(key, 1, 5);
        for (Object o : range) {
            System.out.println("o = " + o);
        }
        for (RedisUserDto redisUserDto : range1) {
            System.out.println("redisUserDto = " + redisUserDto);
        }
    }

    @Test
    void redisTestForList() {
        // TODO : 테스트 실패다. 사용법 다시 확인. - skyun 2021-10-14
        final String key = "redisTestForList#";
        final List<RedisUserDto> data = createData();

        for (int i = 0; i < data.size(); i++) {
//            defaultRedisTemplate.opsForList().set(key + i, i, data.get(i));
            defaultRedisTemplate.opsForList().rightPush(key, data.get(i));
        }
        final List<RedisUserDto> results = defaultRedisTemplate.opsForList().range(key, 0, data.size());
        for (RedisUserDto result : results) {
            System.out.println("result = " + result);
        }
    }

    @Test
    void redisTestForValue() {
        final String key = "redisTestForValue#";
        final List<RedisUserDto> data = createData();
        defaultRedisTemplate.opsForValue().set(key, data);

        final String o = (String) defaultRedisTemplate.opsForValue().get(key);
        System.out.println("o = " + o);
    }

    @Test
    void stringRedisTemplateTest() {
        final ValueOperations<String, String> valueOperations = defaultStringRedisTemplate.opsForValue();
        final String keyPrefix = "ruleengine:device-control-ruleId:";
        final String value = "true";
        for (int i = 0; i < 100; i++) {
            String key = keyPrefix + i;
            valueOperations.set(key, value);
            defaultStringRedisTemplate.expire(key, i, TimeUnit.SECONDS);
        }
    }

    @Test
    void redisMultiDatabaseTest() {
        final ValueOperations<String, String> valueOperations = deviceControlStringRedisTemplate.opsForValue();
        final LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) deviceControlStringRedisTemplate.getConnectionFactory();
        final int database = connectionFactory.getDatabase();
        System.out.println(" test database = " + database);
        final String keyPrefix = "ruleengine:device-control-ruleId:";
        final String value = "true";
        for (int i = 0; i < 100; i++) {
            String key = keyPrefix + i;
            valueOperations.set(key, value);
            deviceControlStringRedisTemplate.expire(key, i, TimeUnit.SECONDS);
        }
    }

    @Test
    @DisplayName("양쪽 다 데이터 저장되어야 한다.")
    void redisMultiDatabaseTest2() {
        final ValueOperations<String, String> valueOperations = defaultStringRedisTemplate.opsForValue();
        final String keyPrefix = "ruleengine:cache-ruleId:";
        final String value = "true";
        for (int i = 0; i < 100; i++) {
            String key = keyPrefix + i;
            valueOperations.set(key, value);
            defaultStringRedisTemplate.expire(key, i, TimeUnit.SECONDS);
        }


        final ValueOperations<String, String> deviceControlValueOperations = deviceControlStringRedisTemplate.opsForValue();
        final LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) deviceControlStringRedisTemplate.getConnectionFactory();
        final int database = connectionFactory.getDatabase();
        System.out.println(" test database = " + database);
        final String keyPrefix2 = "ruleengine:device-control-ruleId:";
        final String value2 = "true";
        for (int i = 0; i < 100; i++) {
            String key = keyPrefix2 + i;
            deviceControlValueOperations.set(key, value2);
            deviceControlStringRedisTemplate.expire(key, i, TimeUnit.SECONDS);
        }
    }

    private List<RedisUserDto> createData() {
        List<RedisUserDto> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String id = "kenux-" + i;
            String name = "kenux yun " + i;
            RedisUserDto redisUserDto = RedisUserDto.builder()
                    .id(id)
                    .name(name)
                    .age(i + 10)
                    .build();
            list.add(redisUserDto);
        }
        return list;
    }
}