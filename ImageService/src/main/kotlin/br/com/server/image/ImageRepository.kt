package br.com.server.image

import org.springframework.stereotype.Repository

@Repository
class ImageRepository {


    fun getImages() = providesImages()

    private fun providesImages(): List<Image> = listOf(
        Image(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
        Image(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
        Image(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112")
    )
}