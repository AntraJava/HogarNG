package com.hogarcontrols.hogarcloud.clientservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${hogarNG.websocket.allowedOrigins}")
    String[] allowedOrigins;

    @Value("${hogarNG.websocket.endpoint}")
    String[] endpoints;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic");
        config.enableStompBrokerRelay("/topic").setVirtualHost("hogarWebsocketHost").setClientLogin("guest").setClientPasscode("guest");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(endpoints).setAllowedOriginPatterns(allowedOrigins).withSockJS();
        registry.addEndpoint(endpoints).setAllowedOriginPatterns(allowedOrigins);
    }

    @EventListener(AbstractSubProtocolEvent.class)
    public void handleEvent(AbstractSubProtocolEvent event) {
        System.out.println(event);
    }
}
