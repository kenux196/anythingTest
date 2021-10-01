package org.kenux.anything.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class DefaultRedisConfig extends RedisConfig {

    @Bean
    @Primary
    public RedisConnectionFactory defaultRedisConnectionFactory() {
        return createLettuceConnectionFactory(0);
    }

    //
//    @Bean(name = "defaultRedisTemplate")
//    public RedisTemplate<String, Object> defaultRedisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setConnectionFactory(defaultRedisConnectionFactory());
//        return redisTemplate;
//    }
//    @Bean(name = "defaultRedisTemplate")
    @Bean
    @Qualifier("defaultRedisTemplate")
    public RedisTemplate<String, Object> defaultRedisTemplate() {
        RedisTemplate<String, Object>  template = new RedisTemplate<>();
        template.setConnectionFactory(defaultRedisConnectionFactory());
//        setSerializer(template);
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "defaultStringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate() {
        final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
//        final int database = defaultRedisConnectionFactory().getDatabase();
//        System.out.println("database = " + database);
        stringRedisTemplate.setConnectionFactory(defaultRedisConnectionFactory());
        return stringRedisTemplate;
    }
}