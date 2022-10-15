package com.example.homework1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Ivan")
                .password("{noop}123456")
                .authorities("random")
                .and()
                .withUser("Alex")
                .password("{noop}")
                .authorities("random", "randomList")
                .and()
                .withUser("Admin")
                .password("{noop}Admin")
                .authorities("random", "randomList", "admin");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/hello").permitAll()
                .and()
                .authorizeRequests().antMatchers("/random").hasAuthority("random")
                .and()
                .authorizeRequests().antMatchers("/randomList").hasAuthority("randomList")
                .and()
                .authorizeRequests().antMatchers("/setBound").hasAuthority("admin");
    }
}
