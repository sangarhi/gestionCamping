package com.proyecto.camping.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("[SecurityConfig-configure]");
						
		
		  auth.inMemoryAuthentication()
		  .withUser("user1")
		 .password(passwordEncoder().encode("user1"))
		  .roles("PUBLIC") 
		  .and()
		  .withUser("admin1")
		  .password(passwordEncoder().encode("admin1"))
		  .roles("ADMIN");

	}
	
	
	  @Override public void configure(WebSecurity web) throws Exception {
	  web.ignoring().antMatchers("/images/**");
	  web.ignoring().antMatchers("/css/**"); }
	 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("[SecurityConfig-configure]");
		
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/camping/**").permitAll()  
		.antMatchers("/h2-console/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
        .formLogin()
        .loginPage("/auth")
        .defaultSuccessUrl("/camping",true)
        .permitAll()
        .and()
		.logout()
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.and()
		.headers().frameOptions().disable();
	}
	
	
	
	  @Bean public BCryptPasswordEncoder passwordEncoder() {
	  
	  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
	  
	  return bCryptPasswordEncoder;
	  
	  }
}
