package com.surya.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig 
{
	
	
	
  @Bean //authentication	 
  UserDetailsService userDetailsService(PasswordEncoder encoder)
  {
	  UserDetails admin=User.withUsername("surya")
			    .password(encoder.encode("surya"))
			    .roles("ADMIN")
			    .build();
	  UserDetails user=User.withUsername("user")
			    .password(encoder.encode("user"))
			    .roles("USER")
			    .build();
	 return new InMemoryUserDetailsManager(admin,user);
   }
    
	  
    @Bean
     SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/products/save").permitAll()
                                .requestMatchers("/products/**")
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
     PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
}
