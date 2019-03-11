package br.com.server.gallery;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GalleryRestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate provideRestTemplate() {
        return new RestTemplate();
    }
}
