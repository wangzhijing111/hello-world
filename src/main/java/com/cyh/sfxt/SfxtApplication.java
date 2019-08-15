package com.cyh.sfxt;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan(basePackages = "com.cyh.sfxt.mapper")
public class SfxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfxtApplication.class, args);
    }

}
