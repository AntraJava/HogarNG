package com.hogarcontrols.hogarcloud.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient("HomeConfigService")
public interface HomeConfigFeignClient {
    @GetMapping("/home/test")
    Map<String, String> getHomeDummy();
}
