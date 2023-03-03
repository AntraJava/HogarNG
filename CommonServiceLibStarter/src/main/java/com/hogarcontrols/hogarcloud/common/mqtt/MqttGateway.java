package com.hogarcontrols.hogarcloud.common.mqtt;

import com.hogarcontrols.hogarcloud.common.constants.HogarConstants;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = HogarConstants.MQTT_COMMON_OUTPUT_CHANNEL)
public interface MqttGateway {
    void sendToMqtt(String data);

    void sendToMqtt(String payload, @Header(MqttHeaders.TOPIC) String topic);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}
