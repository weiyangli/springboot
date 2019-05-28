package com.example.springBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@ComponentScan("com.example.springBoot") // 扫描由@component标记的类
//@MapperScan(basePackages = "com.example.springBoot.dao")  //扫描mapper使用
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, "--server.port=9999");
	}

}
