package com.diwali.Product_Feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductFeignApplication.class, args);
	}

}
