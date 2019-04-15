package br.com.server.firebase

import br.com.server.firebase.model.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult

@RestController
class FirebaseController {

    @Autowired
    lateinit var repository: DataRepository

    @RequestMapping(method = [GET], path = ["/users"])
    fun testOb(): ResponseEntity<Response> {

        val result = DeferredResult<ResponseEntity<Response>>()

        val single = repository.getObUsers().toBlocking()

        return ResponseEntity.status(200).body(Response {
            data = single.value()
        })
    }

}