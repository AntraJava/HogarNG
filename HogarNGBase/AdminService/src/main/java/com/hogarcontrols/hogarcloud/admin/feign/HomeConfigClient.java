package com.hogarcontrols.hogarcloud.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient("HomeConfigService")
public interface HomeConfigClient {
    @GetMapping("/home/test")
    Map<String, String> getHomeDummy();
}
//https://catfact.ninja/fact
