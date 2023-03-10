package com.hogarcontrols.hogarcloud.admin.test;

import com.hogarcontrols.hogarcloud.common.feign.HomeConfigFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    HomeConfigFeignClient homeConfigFeignClient;

    @Autowired
    RestTemplate rt;

    @GetMapping("/test")
    public String test() {
        log.info("Feign - "+homeConfigFeignClient.getHomeDummy());
        log.info("RT    - "+rt.getForEntity("http://homeconfigservice/home/test", String.class).getBody());
        return "ok";
    }
}
