package br.com.server.gallery.model

data class Gallery(
    val id: Long,
    val images: List<Image>?
)