package com.example.ApiBlockChainFinancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.ApiBlockChainFinancer")
public class ApiBlockChainFinancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiBlockChainFinancerApplication.class, args);
	}

}
