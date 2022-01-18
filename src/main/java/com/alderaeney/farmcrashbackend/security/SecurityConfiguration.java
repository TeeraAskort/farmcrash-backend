package com.alderaeney.farmcrashbackend.security;

import com.alderaeney.farmcrashbackend.player.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    PlayerService playerService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(playerService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/player/create/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/player/login").permitAll().antMatchers(HttpMethod.GET, "/crop/**")
                .permitAll().antMatchers(HttpMethod.GET, "/items/**").permitAll()
                .antMatchers(HttpMethod.GET, "/playerImage/**").permitAll()
                .antMatchers(HttpMethod.GET, "/worker/**").permitAll()
                .antMatchers(HttpMethod.GET, "/userImages/**").permitAll().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
