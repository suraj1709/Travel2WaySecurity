package com.travel2way.security.config;

import com.travel2way.security.role.ApplicationUserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.travel2way.security.role.ApplicationUserRole.ADMIN;
import static com.travel2way.security.role.ApplicationUserRole.VISITOR;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public  class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {

    public final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               // .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
                //and().
                .csrf().disable().
                authorizeRequests()
                .antMatchers("/secure/**")
                .hasRole(VISITOR.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user= User.builder()
                .username("User")
                .password(passwordEncoder.encode("password123"))
                .authorities(ADMIN.getGrantedAuthorities())
               .build();
        UserDetails user1=User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails user2=User.builder()
                .username("suraj")
                .password(passwordEncoder.encode("codeforfun"))
                .authorities(VISITOR.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(user,user1,user2);
    }
}