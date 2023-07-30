package de.rieckpil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

  private final String postServiceBaseUrl;

  public WebClientConfiguration(@Value("${post-service-base-url}") String postServiceBaseUrl) {
    this.postServiceBaseUrl = postServiceBaseUrl;
  }

  @Bean
  public WebClient postWebClient(WebClient.Builder webClientBuilder) {
    return webClientBuilder.baseUrl(postServiceBaseUrl).build();
  }
}
