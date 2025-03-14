package com.civilink.tender_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TenderManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenderManagementApplication.class, args);
	}

}
