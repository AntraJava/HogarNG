package com.hogarcontrols.hogarcloud.admin;

import com.hogarcontrols.hogarcloud.admin.feign.HomeConfigClient;
import com.hogarcontrols.hogarcloud.admin.feign.TestClient;
import com.hogarcontrols.hogarcloud.common.constants.HogarConstants;
import com.hogarcontrols.hogarcloud.common.autoConfig.EnableHogarMqtt;
import com.hogarcontrols.hogarcloud.common.feign.HomeConfigFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableHogarMqtt
@EnableScheduling
public class AdminServiceApplication {

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

    @Autowired
    HomeConfigFeignClient homeConfigFeignClient;

    @Scheduled(fixedDelay = 1000)
    public void run() {
        System.out.println(homeConfigFeignClient.getHomeDummy());
    }
}
