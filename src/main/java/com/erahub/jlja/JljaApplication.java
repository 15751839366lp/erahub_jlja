package com.erahub.jlja;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.erahub.jlja.mapper")
@SpringBootApplication
public class JljaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JljaApplication.class, args);
    }

}
