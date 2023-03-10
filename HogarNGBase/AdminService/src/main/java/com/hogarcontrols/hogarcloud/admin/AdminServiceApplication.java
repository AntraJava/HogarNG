package com.hogarcontrols.hogarcloud.admin;

import com.hogarcontrols.hogarcloud.common.autoConfig.EnableHogarRedis;
import com.hogarcontrols.hogarcloud.common.constants.HogarConstants;
import com.hogarcontrols.hogarcloud.common.autoConfig.EnableHogarMqtt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableHogarMqtt
@EnableScheduling
@EnableHogarRedis
public class AdminServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(AdminServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

    @Bean
    @ServiceActivator(inputChannel = HogarConstants.MQTT_COMMON_INPUT_CHANNEL)
    public MessageHandler handler2() {
        return message -> {
            System.out.println(message.getPayload());
            System.out.println(message.getHeaders());
        };
    }
}
