package com.jp.common.aspect;

import com.jp.common.annotation.RedisSelect;
import com.jp.common.redis.RedisSelectSupport;
import com.jp.common.redis.SelectableRedisTemplate;
import com.jp.common.utils.DbRedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Redis切面处理类
 *
 * author huangtuL
 */
@Aspect
@Component
public class RedisAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DbRedisUtils redisUtils;

    @Value("${spring.redis.database}")
    private int defaultDataBase;

//    @Around("execution(* com.example.util.RedisUtils.*(..))")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        Object result = null;
//        if(open){
//            try{
//                result = point.proceed();
//            }catch (Exception e){
//                logger.error("redis error", e);
//                throw new RRException("Redis服务异常");
//            }
//        }
//        return result;
//    }
//
    @Around("@annotation(com.jp.common.annotation.RedisSelect)")
    @ConditionalOnBean(SelectableRedisTemplate.class)
    public Object configRedis(ProceedingJoinPoint point) throws Throwable{
        int db = defaultDataBase;
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();

            RedisSelect config = method.getAnnotation(RedisSelect.class);
            if(config != null){
                db = config.value();
            }
            RedisSelectSupport.select(db);
            return point.proceed();
        } finally {
            RedisSelectSupport.select(defaultDataBase);
            logger.debug("redis reset {} to {}", db, defaultDataBase);
        }
    }
}
