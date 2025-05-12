package com.example.student.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class AppSecurity {


    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }



    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config -> config

                //below added for REST API's
                .requestMatchers(HttpMethod.GET, "/class/students").hasAnyRole("STUDENT", "TEACHER")
                .requestMatchers(HttpMethod.GET, "/class/students/**").hasAnyRole("STUDENT", "TEACHER")
                .requestMatchers(HttpMethod.POST, "/class/students").hasRole("TEACHER")
                .requestMatchers(HttpMethod.PUT, "/class/students").hasRole("TEACHER")
                .requestMatchers(HttpMethod.DELETE, "/class/students/**").hasRole("TEACHER")

                //Below was added when thymeleaf forms and validation was added
                // Allow frontend listing page for everyone
                .requestMatchers("/students/student_list").permitAll()

                // Protect student form (only TEACHERS can access)
                .requestMatchers("/students/studentForm").hasRole("TEACHER")

                // Allow form processing (POST from teachers)
                .requestMatchers("/students/processForm").hasRole("TEACHER")



        );

        http.formLogin(form-> form
                .loginPage("/showLogin")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/students/student_list", true)
                .permitAll()
        );

        http.logout(logout->logout

                .logoutUrl("/logout")
                .logoutSuccessUrl("/showLogin?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        http.exceptionHandling(exception -> exception.accessDeniedPage("/access-denied"));


        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
