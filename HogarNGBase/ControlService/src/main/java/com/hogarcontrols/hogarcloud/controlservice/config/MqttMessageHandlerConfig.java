package com.hogarcontrols.hogarcloud.controlservice.config;

import com.hogarcontrols.hogarcloud.common.constants.HogarConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

import java.time.LocalDateTime;

@Configuration
public class MqttMessageHandlerConfig {

    @Bean
    @ServiceActivator(inputChannel = HogarConstants.MQTT_COMMON_INPUT_CHANNEL)
    public MessageHandler handler2() {
        return message -> System.out.println(message.getPayload() + " - " + LocalDateTime.now());
    }
}
