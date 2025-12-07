package com.diwali.notification_feign.Controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "PRODUCT-FEIGN")
public interface ProductAPI {

    @GetMapping("/place")
    public String invokeProductAPi();
}
