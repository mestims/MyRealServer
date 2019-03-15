package br.com.server.gallery

import br.com.server.gallery.model.Gallery
import br.com.server.gallery.model.ImageServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpMethod
import org.springframework.http.RequestEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

@Repository
class GalleryRepository {

    @Autowired
    lateinit var restClient: RestTemplate

    @Autowired
    @Qualifier("images-request")
    lateinit var requestEntity: RequestEntity<Void>


    fun getGallery(id: Long = 1): Gallery {
        val response = restClient.exchange(requestEntity, ImageServiceResponse::class.java)

        return if (response.statusCode.is2xxSuccessful) {
            Gallery(id, response.body?.images)
        } else {
            Gallery(id, null)
        }

    }


}