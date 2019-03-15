package br.com.server.gallery

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.web.client.RestTemplate
import java.net.URI

@Configuration
class Config {

    @Bean
    @LoadBalanced
    fun providesRestTemplate(): RestTemplate = RestTemplate()

    @Bean("images-request")
    fun providesRequestEntity(requestURI: URI, mediaType: Array<MediaType>): RequestEntity<Void> {
        return RequestEntity
            .get(requestURI)
            .accept(*mediaType)
            .build()
    }

    @Bean("image-service")
    fun providesImageService(): URI = URI("http://image-service/images/")

    @Bean
    fun providesMediaType(): Array<MediaType> = arrayOf(MediaType.APPLICATION_JSON)
}