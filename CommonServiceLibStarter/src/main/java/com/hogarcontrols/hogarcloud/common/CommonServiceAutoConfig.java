package com.hogarcontrols.hogarcloud.common;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:common-properties.properties")
@ComponentScan({
		"com.hogarcontrols.hogarcloud.common.listener",
		"com.hogarcontrols.hogarcloud.common.config.general"
})
@EnableFeignClients("com.hogarcontrols.hogarcloud.common.feign")
public class CommonServiceAutoConfig {

	private static final Logger logger = LoggerFactory.getLogger(CommonServiceAutoConfig.class);

	@Value("${spring.application.name}")
	String appName;

	@PostConstruct
	private void init() {
		if(logger.isInfoEnabled()) logger.info("**** Finished loading HogarNG common service from {} ****", appName);
	}

}
