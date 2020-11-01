package com.ski11up.sales.contact.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * <p>
 * File details.
 * </p>
 *
 * <p>
 * Usage of this file.
 * </>
 *
 * @author Jagdeep Jain
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
          .anyRequest()
          .authenticated()
        .and()
        .formLogin()
          .loginPage("/login")
          .loginProcessingUrl("/authenticateTheUser")
          .defaultSuccessUrl("/contact/list", true)
          .permitAll()
        .and().logout()
          .clearAuthentication(true)
          .permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource);
  }
}
