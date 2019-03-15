package br.com.server.image

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {

    @Bean
    fun providesImageRepository() = ImageRepository()
}