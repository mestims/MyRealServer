package br.com.server.image

data class ImageResponse(
    val status: Int,
    val images: List<Image>
)