package com.alevel.module.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static com.alevel.module.auth.configs.UserRoles.ROLE_ADMIN;
import static com.alevel.module.auth.configs.UserRoles.ROLE_USER;

// TODO rename class to ..Configuration
@Configuration
public class AuthConfigurer extends WebSecurityConfigurerAdapter {

    private PlayerDetailsService playerDetailsService;
    private DataSource dataSource;

    @Autowired
    public AuthConfigurer(PlayerDetailsService playerDetailsService, DataSource dataSource) {
        this.playerDetailsService = playerDetailsService;
        this.dataSource = dataSource;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(playerDetailsService);
        auth
            .jdbcAuthentication()
            .dataSource(dataSource)
            .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .anonymous()
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/chess/player/register").permitAll()
                .antMatchers(HttpMethod.POST, "/chess/player/login").permitAll()
                .anyRequest().authenticated() // .hasAnyRole(ROLE_USER.getShortTitle(), ROLE_ADMIN.getShortTitle()) // .authenticated()
                // roles can be configured in controllers with @PreAuthorize annotation!but at leas one mapping is required (?) TODO investigate
                .and()
            // or filters: JWTAuthenticationFilter
            .httpBasic()
                .and()
            .csrf().disable()
            .formLogin().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
