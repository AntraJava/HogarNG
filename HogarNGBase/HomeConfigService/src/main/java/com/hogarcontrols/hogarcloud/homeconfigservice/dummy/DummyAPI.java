package com.hogarcontrols.hogarcloud.homeconfigservice.dummy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@Slf4j
public class DummyAPI {
    @GetMapping("/home/test")
    public Map<String, String> getDummy(@RequestHeader(value="Authorization", required = false) String authorization) {
        log.info("Get call in dummy api : " + authorization);
        Map<String, String> data = new HashMap<>();
//        if(new Random().nextInt(100)/10 > 5 ){
//            throw new RuntimeException("Randomly error");
//        }
        data.put("name", "dummy api");
        return data;
    }
}
