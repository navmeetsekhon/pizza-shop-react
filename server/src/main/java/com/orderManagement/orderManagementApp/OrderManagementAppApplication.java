package com.orderManagement.orderManagementApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.orderManagement.orderManagementApp.controllers.AuthController;

@SpringBootApplication
public class OrderManagementAppApplication {
	public static void main(String[] args) {
//		AuthController.userCredentials.put("navmeetsingh","password");
		SpringApplication.run(OrderManagementAppApplication.class, args);
	}
}
