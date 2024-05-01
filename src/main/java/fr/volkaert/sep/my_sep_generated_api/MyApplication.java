package fr.volkaert.sep.my_sep_generated_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) { SpringApplication.run(MyApplication.class, args); }

    @Bean
    public CommandLineRunner run() throws Exception {
        return (String[] args) -> {
            System.out.println("My Application started...");
        };
    }
}
