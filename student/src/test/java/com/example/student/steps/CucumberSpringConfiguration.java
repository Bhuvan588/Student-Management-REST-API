package com.example.student.steps;

import com.example.student.StudentApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = StudentApplication.class)
public class CucumberSpringConfiguration {
}
