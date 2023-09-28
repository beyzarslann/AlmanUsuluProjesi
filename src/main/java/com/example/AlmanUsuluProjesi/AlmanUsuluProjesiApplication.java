package com.example.AlmanUsuluProjesi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:4200/")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AlmanUsuluProjesiApplication  {


    public static void main(String[] args) {
        SpringApplication.run(AlmanUsuluProjesiApplication.class, args);


    }
}
