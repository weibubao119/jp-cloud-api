package com.jp.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wsj
 * @projectName hr-recruit
 * @date 2022/6/249:36
 */
@Component
public class RedisDbChangeUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    public void lettuceSetDataBase(int num){
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory)redisTemplate.getConnectionFactory();
        if (connectionFactory != null && num != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(num);
            this.redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection();
        }
    }

        public void jRedisSetDataBase(int num){
            jedisConnectionFactory.setDatabase(num);
    }
}
