
package com.springmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder ();
    }

    //Authorization Part
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users/*").authenticated ()
                .anyRequest ().permitAll ()
                .and()
                .formLogin().loginPage("/views/customerLogin.jsp")
                .loginProcessingUrl("/customer/login")
                .defaultSuccessUrl("/customerDashboard", true)
                .failureUrl("/customerLogin.jsp?message=loginFailed")
                .and()
                .csrf().disable()
                .cors().disable();
    }


}

