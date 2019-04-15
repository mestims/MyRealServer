package br.com.server.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import javax.annotation.PostConstruct

@Configuration
class Config {

//    @Bean
//    @DependsOn("firebaseInitialization")
//    fun providesUserReference(): DatabaseReference = FirebaseDatabase.getInstance().getReference("users/")

//    @Bean
//    fun providesDataRepository(): DataRepository = DataRepository()
}