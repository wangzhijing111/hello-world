package com.cyh.sfxt;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.cyh.sfxt.mapper")
public class SfxtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SfxtApplication.class, args);
    }

}
