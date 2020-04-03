package io.aikosoft.customer_service;

import io.aikosoft.customer_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/add").access("hasRole('ADMIN')")
            .antMatchers("/update/**").access("hasRole('ADMIN')")
            .antMatchers("/add-customer").access("hasRole('ADMIN')")
            .antMatchers("/remove-customer").access("hasRole('ADMIN')")
            .antMatchers("/update-customer").access("hasRole('ADMIN')")
            .antMatchers("/**").permitAll()

            .and()

            .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth
            .inMemoryAuthentication()
            .withUser("admin")
            .password(encoder.encode("password"))
            .roles("ADMIN");
    }
}
