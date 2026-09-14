package com.example.bookexplorer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner createAdmin(UserRepository userRepository) {

        return args -> {

            if (userRepository.findByUsername("admin").isEmpty()) {

                User admin = new User();

                admin.setUsername("admin");
                admin.setEmail("admin@bookexplorer.com");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");

                userRepository.save(admin);

                System.out.println("ADMIN ACCOUNT CREATED ✅");
            }
        };
    }
}
