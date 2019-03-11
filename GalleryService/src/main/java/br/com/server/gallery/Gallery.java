package br.com.server.gallery;

import java.util.List;

public class Gallery {

    private long id;
    private List<Image> images;

    public Gallery() {
    }

    public Gallery(long id, List<Image> images) {
        this.id = id;
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
