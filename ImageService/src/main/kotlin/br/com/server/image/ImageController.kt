package br.com.server.image

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ImageController {

    @Autowired
    lateinit var images: List<Image>

    @RequestMapping("/images")
    fun getImages() = ok(images)
}