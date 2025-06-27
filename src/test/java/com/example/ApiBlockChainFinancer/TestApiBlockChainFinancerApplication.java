package com.example.ApiBlockChainFinancer;

import org.springframework.boot.SpringApplication;

public class TestApiBlockChainFinancerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ApiBlockChainFinancerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
