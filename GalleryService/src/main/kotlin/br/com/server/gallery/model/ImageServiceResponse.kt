package br.com.server.gallery.model

data class ImageServiceResponse(
    val status: Int,
    val images: List<Image>
)