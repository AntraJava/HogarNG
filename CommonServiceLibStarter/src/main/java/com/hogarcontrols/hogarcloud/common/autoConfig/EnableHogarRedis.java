package com.hogarcontrols.hogarcloud.common.autoConfig;

import com.hogarcontrols.hogarcloud.common.config.redis.RedisConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RedisConfig.class)
public @interface EnableHogarRedis {
}
