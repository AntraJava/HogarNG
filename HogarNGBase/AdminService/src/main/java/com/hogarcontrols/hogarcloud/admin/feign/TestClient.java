package com.hogarcontrols.hogarcloud.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(value = "catFactAPI", url = "https://catfact.ninja")
public interface TestClient {
    @GetMapping("/fact")
    Map<String, String> getCatFact();
}
//https://catfact.ninja/fact
