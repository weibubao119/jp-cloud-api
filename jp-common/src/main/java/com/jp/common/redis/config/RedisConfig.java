package com.jp.common.redis.config;

import com.jp.common.redis.SelectableRedisTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * Redis配置相关
 * author huangtuL
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)// 自动获取application.yml中的配置
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {
    private RedisProperties properties;

    public RedisConfig(RedisProperties properties){
        this.properties = properties;
    }

    @Bean
    @Primary
    public JedisConnectionFactory jedisConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(properties.getHost());
        config.setPort(properties.getPort());
        config.setPassword(RedisPassword.of(properties.getPassword()));
        config.setDatabase(properties.getDatabase());
        return new JedisConnectionFactory(config, getJedisClientConfiguration());
    }

    private JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
        if (properties.isSsl()) {
            builder.useSsl();
        }
        if (properties.getTimeout() != null) {
            Duration timeout = properties.getTimeout();
            builder.readTimeout(timeout).connectTimeout(timeout);
        }
        RedisProperties.Pool pool = properties.getJedis().getPool();
        if (pool != null) {
            builder.usePooling().poolConfig(jedisPoolConfig(pool));
        }
        return builder.build();
    }

    private JedisPoolConfig jedisPoolConfig(RedisProperties.Pool pool) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(pool.getMaxActive());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        if (pool.getMaxWait() != null) {
            config.setMaxWaitMillis(pool.getMaxWait().toMillis());
        }
        return config;
    }

    @Bean(name = "SelectableRedisTemplate")
    @Primary
    public SelectableRedisTemplate redisTemplate() {
        SelectableRedisTemplate redisTemplate = new SelectableRedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        System.out.println("uwuwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
        return redisTemplate;
    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        //为string类型的key设置序列化
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        //为string类型的value设置序列化
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        //为hash类型的key设置序列化
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        //为hash类型的value设置序列化
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
//        return redisTemplate;
//    }


}
