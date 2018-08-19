package com.mm.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan("com.mm.blog")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
