package com.hogarcontrols.hogarcloud.admin.actuator;

import com.hogarcontrols.hogarcloud.common.mqtt.MqttGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
@RestControllerEndpoint(id="deephealth")
public class DeepHealthChecker {

    private static final Logger log = LoggerFactory.getLogger(DeepHealthChecker.class);

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    MqttGateway gateway;

    @GetMapping
    public ResponseEntity<Object> getDeepHealthCheck() {
        CheckResult result = new CheckResult();
        checkRedis(result);
        checkMqtt(result);
        return ResponseEntity.ok(result);
    }

    private void checkMqtt(CheckResult result) {
        try {
            StopWatch sw = new StopWatch();
            sw.start();
            this.gateway.sendToMqtt("testABCD");
            sw.stop();
            result.addResult("Mqtt Send", "Operation used:" + sw.getTotalTimeMillis() + "ms");
        }catch (Exception e) {
            result.addResult("Mqtt Error", e.getMessage());
        }
    }

    private void checkRedis(CheckResult result) {
        try {
            StopWatch sw = new StopWatch();
            sw.start();
            redisTemplate.opsForValue().set("testKey", new TestData("ABC"), Duration.ofSeconds(2));
            TestData data = (TestData) redisTemplate.opsForValue().get("testKey");
            sw.stop();
            if (data != null && "ABC".equals(data.getName())) {
                result.addResult("Redis Put/Get", "Operation used:" + sw.getTotalTimeMillis() + "ms");
            } else {
                result.addResult("Redis Put/Get", "NO");
            }
        } catch (Exception e) {
            log.error("Redis Error", e);
            result.addResult("Redis Error", e.getMessage());
        }

    }
}
class CheckResult {
    private Map<String, String> resultMap;

    public CheckResult() {
        this.resultMap = new HashMap<>();
    }

    public void addResult(String key, String value) {
        resultMap.put(key, value);
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

}

class TestData{
    private String name;

    public TestData() {
    }

    public TestData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
