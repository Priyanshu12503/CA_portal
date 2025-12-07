package com.diwali.notification_feign.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@EnableDiscoveryClient
public class NotificationFeignController {


    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    public ProductAPI productAPI;


    @GetMapping("/notification")
    public String notification() {
        return "Jayani ke text ki notification ";
    }

    @GetMapping("/notification/person/{id}")
    public String getPerson(@PathVariable String id) {
        String s1 = "Hello from notification API";
        String s2 = productAPI.invokeProductAPi();

        return s1 + " " + s2;
    }
}
