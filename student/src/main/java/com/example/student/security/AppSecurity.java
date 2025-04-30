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
    public InMemoryUserDetailsManager userDetails()
    {
        UserDetails mary = User.builder()
                .username("Mary")
                .password(("{noop}mj123")) //noop is the encryption algo, pass123 is password for bob
                .roles("STUDENT")
                .build();

        UserDetails peter = User.builder()
                .username("Peter")
                .password(("{noop}spidey456")) //noop is the encryption algo, pass123 is password for bob
                .roles("STUDENT")
                .build();

        UserDetails robert = User.builder()
                .username("Robert")
                .password(("{noop}rdj3000")) //noop is the encryption algo, pass123 is password for bob
                .roles("TEACHER")
                .build();

        UserDetails steve = User.builder()
                .username("Steve")
                .password(("{noop}cap1000")) //noop is the encryption algo, pass123 is password for bob
                .roles("TEACHER")
                .build();

        return new InMemoryUserDetailsManager(mary,peter,robert,steve);
    }


//    @Bean
//    public UserDetailsManager users(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // only for testing; use BCrypt in production
//    }



    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config -> config
                .requestMatchers(HttpMethod.GET, "/class/students").hasAnyRole("STUDENT", "TEACHER")
                .requestMatchers(HttpMethod.GET, "/class/students/**").hasAnyRole("STUDENT", "TEACHER")
                .requestMatchers(HttpMethod.POST, "/class/students").hasRole("TEACHER")
                .requestMatchers(HttpMethod.PUT, "/class/students").hasRole("TEACHER")
                .requestMatchers(HttpMethod.DELETE, "/class/students/**").hasRole("TEACHER")
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
