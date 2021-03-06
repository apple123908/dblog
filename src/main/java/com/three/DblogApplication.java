package com.three;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
@ServletComponentScan//spring能够扫描到我们自己编写的servlet和filter
public class DblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(DblogApplication.class, args);
	}


}
