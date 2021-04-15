package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableCaching
@EnableSwagger2
@SpringBootApplication(scanBasePackages={
"com.example.demo.service",
		"com.example.model",
		"com.example.demo.controller",
		"com.example.demo.repository",
		"com.example.demo.Utils",
//		"com.example.demo.filter",
		"com.example.demo"
})
public class SpringMysqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringMysqlApplication.class, args);
	}
}
