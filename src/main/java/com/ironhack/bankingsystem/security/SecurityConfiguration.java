package com.ironhack.bankingsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().ignoringAntMatchers("/admin/*")

                .ignoringAntMatchers("/posts")
                .ignoringAntMatchers("/posts/*");
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin/account-balance/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/account-holder").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/third-party").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/checking").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/saving").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin/credit-card").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/admin/account-balance/*").hasRole("ADMIN")

                .antMatchers(HttpMethod.POST, "/posts").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .antMatchers(HttpMethod.PUT, "/authors/*").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .antMatchers(HttpMethod.PUT, "/posts/*").hasAnyRole("ADMIN", "CONTRIBUTOR")
                .antMatchers(HttpMethod.DELETE, "/authors/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/posts/*").hasRole("ADMIN")
                .anyRequest().permitAll();
    }
}
