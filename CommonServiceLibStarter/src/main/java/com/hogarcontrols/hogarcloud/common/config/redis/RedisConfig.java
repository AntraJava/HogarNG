package com.hogarcontrols.hogarcloud.common.config.redis;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
//@ConfigurationProperties
@ComponentScan("com.hogarcontrols.hogarcloud.common.redis")
public class RedisConfig {

    @Value("${spring.redis.host}")
    String serverUrl;

    @Value("${spring.redis.port}")
    int port;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        final SocketOptions socketOptions = SocketOptions.builder().connectTimeout(Duration.ofSeconds(5)).build();
        final ClientOptions clientOptions = ClientOptions.builder().socketOptions(socketOptions).build();
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(5)).clientOptions(clientOptions).build();
        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(serverUrl, port);
        final LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(serverConfig, clientConfig);
        lettuceConnectionFactory.setValidateConnection(true);
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

}
