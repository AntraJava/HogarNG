package com.hogarcontrols.hogarcloud.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class HogarCacheUtil {
    private static Logger logger = LoggerFactory.getLogger(HogarCacheUtil.class);
    private RedisTemplate redisTemplate;
    @Autowired
    private HogarCacheUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Object putUserCache() {
        this.redisTemplate.opsForValue().set("SuperMan","yeah");
        return null;
    }
}
