package br.com.server.firebase

import com.google.firebase.database.FirebaseDatabase
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @RequestMapping(method = [POST], path = ["/test"])
    fun test(@RequestBody user: User): ResponseEntity<String> {
        val ref = FirebaseDatabase.getInstance().getReference("/test/admin/")
        ref.setValueAsync(user)
        return ok("só vamos")
    }

}

data class User(
    val name: String?,
    val age: Int?,
    val email: String?
)