package br.com.server.gallery

import br.com.server.gallery.model.Gallery
import br.com.server.gallery.model.GalleryResponse
import org.springframework.http.ResponseEntity

fun galleryOk(gallery: Gallery): ResponseEntity<GalleryResponse> {
    return ResponseEntity.ok(GalleryResponse(200, gallery))
}