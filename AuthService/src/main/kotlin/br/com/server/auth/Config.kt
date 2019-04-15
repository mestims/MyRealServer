package br.com.server.auth

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

    @Bean("users-request")
    fun providesRequestEntity(requestURI: URI, mediaType: Array<MediaType>): RequestEntity<Void> {
        return RequestEntity
            .get(requestURI)
            .accept(*mediaType)
            .build()
    }

    @Bean("firebase-service")
    fun providesImageService(): URI = URI("http://firebase-service/users/")

    @Bean
    fun providesMediaType(): Array<MediaType> = arrayOf(MediaType.APPLICATION_JSON)
}