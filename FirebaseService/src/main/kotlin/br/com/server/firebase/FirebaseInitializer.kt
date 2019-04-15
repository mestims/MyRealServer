package br.com.server.firebase

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import com.google.firebase.FirebaseApp
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import java.io.FileInputStream



@Component
class FirebaseInitializer {

    @PostConstruct
    fun initializeFirebase() {
        val serviceAccount = FileInputStream("serviceAccountKey.json")

        val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://farm-1d11c.firebaseio.com")
            .build()

        FirebaseApp.initializeApp(options)

    }
}