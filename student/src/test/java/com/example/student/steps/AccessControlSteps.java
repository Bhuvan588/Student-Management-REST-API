package com.example.student.steps;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration
public class AccessControlSteps {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private String role;
    private ResultActions resultActions;

    @Given("I have role {string}")
    public void i_have_role(String role) {
        this.role = role;
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @When("I send a {word} request to {string}")
    public void i_send_request_to(String method, String endpoint) throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        // Here we take users from our JDBC database
        String username = switch (role.toLowerCase()) {
            case "student" -> "Mary";
            case "teacher" -> "Robert";
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };

        String jsonContent = """
        {
          "firstName": "Bob",
          "lastName": "Smith",
          "science": 75,
          "maths": 80,
          "art": 65,
          "classesHeld": 50,
          "classesAttended": 45
        }
    """;

        RequestBuilder request;
        switch (method.toUpperCase()) {
            case "GET" -> request = get(endpoint)
                    .with(SecurityMockMvcRequestPostProcessors.user(username));
            case "POST" -> request = post(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContent)
                    .with(SecurityMockMvcRequestPostProcessors.user(username));
            case "PUT" -> request = put(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonContent)
                    .with(SecurityMockMvcRequestPostProcessors.user(username));
            case "DELETE" -> request = delete(endpoint)
                    .with(SecurityMockMvcRequestPostProcessors.user(username));
            default -> throw new IllegalArgumentException("Unsupported method: " + method);
        }

        resultActions = mockMvc.perform(request);
    }


    @Then("I should receive status code {int}")
    public void i_should_receive_status_code(int expectedStatus) throws Exception {
        int actualStatus = resultActions.andReturn().getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }
}
