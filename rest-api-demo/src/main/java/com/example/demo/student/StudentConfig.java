package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
public class StudentConfig {
    // Uncomment when running for first time in an empty db
//    @Bean
//    CommandLineRunner commandLineRunner(
//            StudentRepository studentRepository) {
//        return args -> {
//            Student mariam = new Student(
//                    "Mariam",
//                    "mariam@gmail.com",
//                    LocalDate.of(2000, 2, 13)
//            );
//            Student alex = new Student(
//                    "Alex",
//                    "alex@gmail.com",
//                    LocalDate.of(2004, 2, 13)
//            );
//            studentRepository.saveAll(Arrays.asList(
//                    mariam, alex
//            ));
//        };
//    }
}
