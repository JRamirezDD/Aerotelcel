package com.notifications_handler;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class NotificationsHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsHandlerApplication.class, args);
	}

}
