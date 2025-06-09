package de.rieckpil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfiguration {

  @Bean
  public SecurityFilterChain mainSecurityConfig(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
      .authorizeHttpRequests(
        requests ->
          requests
            .requestMatchers(HttpMethod.GET, "/api/comments")
            .permitAll()
            .requestMatchers(HttpMethod.POST, "/api/comments")
            .hasRole("ADMIN")
            .anyRequest()
            .authenticated())
      .httpBasic(Customizer.withDefaults())
      .csrf(AbstractHttpConfigurer::disable);

    return httpSecurity.build();
  }
}
