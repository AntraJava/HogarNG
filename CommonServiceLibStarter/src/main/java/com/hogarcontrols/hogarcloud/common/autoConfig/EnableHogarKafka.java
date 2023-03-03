package com.hogarcontrols.hogarcloud.common.autoConfig;

import com.hogarcontrols.hogarcloud.common.config.kafka.KafkaConfig;
import com.hogarcontrols.hogarcloud.common.config.redis.RedisConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(KafkaConfig.class)
public @interface EnableHogarKafka {
}
