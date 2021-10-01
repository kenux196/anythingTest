package org.kenux.anything.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class DeviceControlRedisConfig extends RedisConfig {

    @Bean
    public RedisConnectionFactory deviceControlRedisConnectionFactory() {
        return createLettuceConnectionFactory(2);
    }
//
//    @Bean(name = "deviceControlRedisTemplate")
//    public RedisTemplate<String, Object> deviceControlRedisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setConnectionFactory(deviceControlRedisConnectionFactory());
//        return redisTemplate;
//    }

//    @Bean(name = "deviceControlRedisTemplate")
    @Bean
    @Qualifier("deviceControlRedisTemplate")
    public RedisTemplate<String, Object>  deviceControlRedisTemplate() {
        RedisTemplate<String, Object>  template = new RedisTemplate<>();
        template.setConnectionFactory(deviceControlRedisConnectionFactory());
//        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "deviceControlStringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate() {
        final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
//        final int database = deviceControlRedisConnectionFactory().getDatabase();
//        System.out.println("database = " + database);
        stringRedisTemplate.setConnectionFactory(deviceControlRedisConnectionFactory());
        return stringRedisTemplate;
    }
}