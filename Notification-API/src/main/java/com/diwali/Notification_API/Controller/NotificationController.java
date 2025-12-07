package com.diwali.Notification_API.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@EnableDiscoveryClient
public class NotificationController {

    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/notification")
    public String notification(){
        return "Jayani ke text ki notification ";
    }

    @GetMapping("/notification/person/{id}")
    public String getPerson(@PathVariable String id) {
        String s1 = "Hello from notification API";
        String s2 = restTemplate.getForObject("http://localhost:9091/person/" + id, String.class);
        return s1 + " " + s2;
    }


}
