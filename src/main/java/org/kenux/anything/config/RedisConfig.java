package org.kenux.anything.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory(host, port);
//    }

    public RedisConnectionFactory createLettuceConnectionFactory(int dbIndex) {
        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setDatabase(dbIndex);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(host);
//        redisStandaloneConfiguration.setPort(port);
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        return redisTemplate;
//    }
//
//    @Bean
//    @Qualifier("stringRedisTemplate")
//    public StringRedisTemplate stringRedisTemplate() {
//        final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
//        final int database = redisConnectionFactory().getDatabase();
//        System.out.println("database = " + database);
//        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
//        return stringRedisTemplate;
//    }
//
//    @Bean
//    @Qualifier("stringRedisTemplate2")
//    public StringRedisTemplate stringRedisTemplate2() {
//        final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
////        redisConnectionFactory().setDatabase(2);
//        final int database = redisConnectionFactory2().getDatabase();
//        System.out.println("database = " + database);
//        stringRedisTemplate.setConnectionFactory(redisConnectionFactory2());
//        return stringRedisTemplate;
//    }
}