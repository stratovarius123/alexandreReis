package br.com.alexandre.concrete.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

 @Autowired
 private CustomAuthenticationProvider customAuthenticationProvider;

 private static final String [] PUBLIC_MATCHERS = {"/favicon.ico/**","/user/**","/h2-console","/h2-console/**"};
 private static final String [] PUBLIC_MATCHERS_POST = {"/login"};
 
 @Override
 protected void configure(HttpSecurity httpSecurity) throws Exception {
	 
	 httpSecurity.csrf().disable().authorizeRequests()
	                .antMatchers(PUBLIC_MATCHERS).permitAll()
	                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
	                .anyRequest().authenticated()
	                .and()
	                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                        UsernamePasswordAuthenticationFilter.class)
	                .addFilterBefore(new JWTAuthenticationFilter(),
	                        UsernamePasswordAuthenticationFilter.class);
	 httpSecurity.headers().frameOptions().disable();
 }

 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.inMemoryAuthentication();
  auth.authenticationProvider((AuthenticationProvider) customAuthenticationProvider);
 }
	    
 @Bean
 public BCryptPasswordEncoder bCryptPasswordEncoder() {
	 return new BCryptPasswordEncoder();
 }

}
