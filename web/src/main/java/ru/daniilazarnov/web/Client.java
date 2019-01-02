package ru.daniilazarnov.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Client {

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

}
