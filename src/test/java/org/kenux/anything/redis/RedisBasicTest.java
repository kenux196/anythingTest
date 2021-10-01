package org.kenux.anything.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kenux.anything.domain.dto.RedisUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
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
        RedisUserDto redisUserDto = new RedisUserDto("kenux", "password");

        final ValueOperations<String, RedisUserDto> valueOperations = defaultRedisTemplate.opsForValue();
        valueOperations.set(redisUserDto.getId(), redisUserDto);

        final RedisUserDto result = valueOperations.get(redisUserDto.getId());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(redisUserDto.getId());
        assertThat(result.getPw()).isEqualTo(redisUserDto.getPw());
        System.out.println("result = " + result);
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
}