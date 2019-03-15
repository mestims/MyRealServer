package br.com.server.image

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController
class ImageController {

    @Autowired
    lateinit var imageRepository: ImageRepository

    @RequestMapping(method = [GET], value = ["/images"])
    fun getImages() = ok(imageRepository.getImages())
}