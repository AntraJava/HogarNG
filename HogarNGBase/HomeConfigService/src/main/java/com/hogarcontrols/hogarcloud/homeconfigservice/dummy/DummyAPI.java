package com.hogarcontrols.hogarcloud.homeconfigservice.dummy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class DummyAPI {
    @GetMapping("/home/test")
    public Map<String, String> getDummy() {
        log.info("Get call in dummy api");
        Map<String, String> data = new HashMap<>();
        data.put("name", "dummy api");
        return data;
    }
}
