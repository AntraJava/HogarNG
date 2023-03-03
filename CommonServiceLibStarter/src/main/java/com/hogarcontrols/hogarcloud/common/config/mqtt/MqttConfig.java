package com.hogarcontrols.hogarcloud.common.config.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.event.MqttIntegrationEvent;
import org.springframework.integration.mqtt.event.MqttSubscribedEvent;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

@Configuration
@ComponentScan("com.hogarcontrols.hogarcloud.common.config.mqtt")
public class MqttConfig {
    private static Logger logger = LoggerFactory.getLogger(MqttConfig.class);

    @Value("${hogarNG.mqtt.broker.urls}")
    String[] mqttBroker;

    @Value("${hogarNG.mqtt.subscriber.name:${spring.application.name}_Subscriber_Client}_#{@ephemeralServiceId.getEphemeralId()}")
    String mqttClientSubName;

    @Value("${hogarNG.mqtt.subscriber.topics:default-topic}")
    String[] mqttClientSubTopics;

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(mqttBroker);
//        options.setUserName("username");
//        options.setPassword("password".toCharArray());
        factory.setConnectionOptions(options);
        logger.info("*** Start Emqx connection with client {}", mqttClientSubName);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound(MqttPahoClientFactory clientFactory) {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(mqttClientSubName, clientFactory,mqttClientSubTopics);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @EventListener
    public void mqttEventHandler(MqttSubscribedEvent event) {
        logger.info("***{}***", event.getMessage());
    }
    @EventListener
    public void mqttEventHandler(MqttIntegrationEvent event) {
        logger.debug("....{}", event);
    }
}
