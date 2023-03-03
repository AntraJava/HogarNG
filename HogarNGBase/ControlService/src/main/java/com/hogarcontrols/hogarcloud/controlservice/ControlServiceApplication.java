package com.hogarcontrols.hogarcloud.controlservice;

import com.hogarcontrols.hogarcloud.common.autoConfig.EnableHogarMqtt;
import com.hogarcontrols.hogarcloud.common.autoConfig.EnableHogarRedis;
import com.hogarcontrols.hogarcloud.common.redis.HogarCacheUtil;
import com.hogarcontrols.hogarcloud.common.mqtt.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableHogarRedis
@EnableHogarMqtt
public class ControlServiceApplication {
    @Value("${hogarNG.baseURL}")
    String dummyField;

    @Autowired
    MqttGateway gateway;

    @Autowired
    HogarCacheUtil cacheUtil;

    public static void main(String[] args) {
        SpringApplication.run(ControlServiceApplication.class);
    }
}
