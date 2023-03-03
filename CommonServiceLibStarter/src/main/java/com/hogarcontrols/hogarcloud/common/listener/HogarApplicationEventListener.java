package com.hogarcontrols.hogarcloud.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HogarApplicationEventListener {
    private static final Logger logger = LoggerFactory.getLogger(HogarApplicationEventListener.class);

    @Value("${spring.application.name}")
    String appName;

    @EventListener(ApplicationReadyEvent.class)
    public void serviceStartListener() {
        if(logger.isInfoEnabled()) logger.info("**** {} is up ****", appName);
    }
}
