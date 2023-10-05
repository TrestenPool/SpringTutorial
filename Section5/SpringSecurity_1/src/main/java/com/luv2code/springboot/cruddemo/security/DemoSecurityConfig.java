package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    /**** IN MEMORY STORAGE ****/
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        // john
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        // mary
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        // susan
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

    /***** JDBC CONNECTION *****/
    @Bean
    public UserDetailsManager userDetailsManager(DataSource datasource){
        JdbcUserDetailsManager jdbcUserDetailsManager =  new JdbcUserDetailsManager(datasource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // configure the http requests that come in
        http.authorizeHttpRequests(configure ->
                configure
                        // employee roles
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")

                        // manager roles
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")

                        // admin roles
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        // use HTTP basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable csrc
        // in general, not required for stateless REST APIs.
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

}
