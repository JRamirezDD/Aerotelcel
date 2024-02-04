package com.reports_handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestReportsHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.from(ReportsHandlerApplication::main).with(TestReportsHandlerApplication.class).run(args);
	}

}
