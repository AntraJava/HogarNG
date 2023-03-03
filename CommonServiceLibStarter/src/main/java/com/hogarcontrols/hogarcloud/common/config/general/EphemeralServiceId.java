package com.hogarcontrols.hogarcloud.common.config.general;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class EphemeralServiceId {

    private static final Logger logger = LoggerFactory.getLogger(EphemeralServiceId.class);

    EphemeralServiceId() {}

    private int id;

    @PostConstruct
    private void init() {
        this.id = new SecureRandom().nextInt(1000,10000);
        logger.info("***EphemeralId {}***", this.id);
    }

    public int getEphemeralId() {
        return id;
    }
}
