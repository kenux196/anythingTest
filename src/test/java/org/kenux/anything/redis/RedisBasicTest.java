package org.kenux.anything.redis;

import org.junit.jupiter.api.Test;
import org.kenux.anything.domain.dto.RedisUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@DataRedisTest
//@ContextConfiguration(classes = RedisConfig.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED) // 실제 DB 사용하고 싶을때 NONE 사용
public class RedisBasicTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void redisConnectionTest() {
        final String key = "a";
        final String data = "1";

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);

        final String s = valueOperations.get(key);
        assertThat(s).isEqualTo(data);
    }

    @Test
    void redisExpireTest() throws InterruptedException {
        final String key = "a";
        final String data = "1";

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);
        final Boolean expire = redisTemplate.expire(key, 5, TimeUnit.SECONDS);
        final Long time = redisTemplate.getExpire(key);
        System.out.println("time = " + time);
        Thread.sleep(6000);
        final String s = valueOperations.get(key);
        assertThat(expire).isTrue();
        assertThat(s).isNull();
    }

    @Test
    void redisInsertObject() {
        RedisUserDto redisUserDto = new RedisUserDto("kenux", "password");

        final ValueOperations<String, RedisUserDto> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(redisUserDto.getId(), redisUserDto);

        final RedisUserDto result = valueOperations.get(redisUserDto.getId());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(redisUserDto.getId());
        assertThat(result.getPw()).isEqualTo(redisUserDto.getPw());
        System.out.println("result = " + result);
    }

    @Test
    void stringRedisTemplateTest() {

        final ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
    }
}