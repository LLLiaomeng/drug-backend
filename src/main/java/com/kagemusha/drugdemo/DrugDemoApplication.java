package com.kagemusha.drugdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.kagemusha.drugdemo.mapper")
@SpringBootApplication
public class DrugDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugDemoApplication.class, args);
    }

}
