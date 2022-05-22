package com.github.a1exandersuvorov.transporeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TransporeonApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransporeonApplication.class, args);
	}
}
