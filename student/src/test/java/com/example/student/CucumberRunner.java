package com.example.student;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.example.student.steps", "com.example.student.steps.CucumberSpringConfiguration"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class CucumberRunner {

}
