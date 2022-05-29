package cz.cvut.fel.unirankings.common.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestClientConfig {

  @Value("${azure.function.baseurl}")
  private String azureFunctionBaseUrl;

  @Bean
  public RestTemplate getRestClient() {
    RestTemplate restClient = new RestTemplate();
    restClient.setUriTemplateHandler(new DefaultUriBuilderFactory(azureFunctionBaseUrl));
    return restClient;
  }
}
