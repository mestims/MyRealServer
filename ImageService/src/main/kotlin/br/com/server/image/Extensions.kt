package br.com.server.image

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok

fun imagesOK(images: List<Image>): ResponseEntity<ImageResponse> {
    return ok(ImageResponse(200, images))
}