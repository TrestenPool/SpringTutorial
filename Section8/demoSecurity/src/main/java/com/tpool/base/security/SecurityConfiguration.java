package com.tpool.base.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // in memory configuration
//    @Bean
//    public UserDetailsService users() {
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("EMPLOYEE", "MANAGER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

    // security filter chain configuration
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // enable csrf with the defaults
                .csrf(Customizer.withDefaults())

                .authorizeHttpRequests(authorize ->
                            authorize
                                // restrict urls based on roles
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )

                // uses http basic auth
                .httpBasic(Customizer.withDefaults())

                // Tells it to use the default login form page
//                .formLogin(Customizer.withDefaults());

                // uses custom login form
                .formLogin(
                        form ->
                                // login page
                                form.loginPage("/showMyLoginPage")
                                // where the login page is posted to, we don't have to worry about this,
                                // ... spring will take care and create this automagically for us in the background
                                .loginProcessingUrl("/authenticateTheUser")
                                // allow everybody to see the login page
                                .permitAll()
                )

                .exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied")
                )

                // allow every user to logout
                .logout(
                        logout -> logout.permitAll()
                );


        // return the http build for the spring security filter chain
        return http.build();
    }

    // connect to the mysql db for authentication
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        // gets the datasource created by spring by default
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // finds the user by the username
        jdbcUserDetailsManager
                .setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id=?");

        // finds the roles for the user by the username
        jdbcUserDetailsManager
                .setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id=?");

        return jdbcUserDetailsManager;
    }
}
