package com.hogarcontrols.hogarcloud.common.config.mqtt;

import com.hogarcontrols.hogarcloud.common.constants.HogarConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@ComponentScan
@IntegrationComponentScan("com.hogarcontrols.hogarcloud.common.mqtt")
public class MqttConfigOut {

    @Value("${hogarNG.mqtt.publisher.name:${spring.application.name}_Publisher_Client}_#{@ephemeralServiceId.getEphemeralId()}")
    String mqttClientPubName;

    @Bean
    @ServiceActivator(inputChannel = HogarConstants.MQTT_COMMON_OUTPUT_CHANNEL)
    public MessageHandler mqttOutbound(MqttPahoClientFactory clientFactory) {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(mqttClientPubName, clientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("default-topic");
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
}

