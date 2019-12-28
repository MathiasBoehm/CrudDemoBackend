package de.struktuhr.crudbackend.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .mvcMatchers("/posts/**").hasAuthority("SCOPE_posts")
                .mvcMatchers("/authors/**").hasAuthority("SCOPE_posts")
                .anyRequest().denyAll()
                .and()
                .oauth2ResourceServer()
                .jwt();
    }
}