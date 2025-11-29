package com.example.xaxaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication()
@EnableJpaRepositories("com.example.xaxaton.repository") // ← эта строка важна!
@EntityScan("com.example.xaxaton.domain.entity") // ← исправь на правильный пакет!
@ComponentScan({ "com.example.xaxaton.*" })
public class XaxatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(XaxatonApplication.class, args);
	}

}
