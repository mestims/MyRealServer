package br.com.server.gallery

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RestController

@RestController
class GalleryController {

    @Autowired
    lateinit var repository: GalleryRepository


    @RequestMapping(method = [GET], value = ["/gallery"])
    fun getGallery() = galleryOk(repository.getGallery())

}