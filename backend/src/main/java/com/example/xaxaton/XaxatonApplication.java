package com.example.xaxaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class XaxatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(XaxatonApplication.class, args);
	}

}
