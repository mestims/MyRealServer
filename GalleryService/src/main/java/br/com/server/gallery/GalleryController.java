package br.com.server.gallery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RestController
public class GalleryController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/gallery")
    public ResponseEntity<Gallery> getGallery() {
        ResponseEntity<List<Image>> responseImages = restTemplate.exchange(
                "http://image-service/images/",
                GET,
                null,
                new ParameterizedTypeReference<List<Image>>() {
                }
        );

        Gallery gallery = new Gallery(
                1,
                responseImages.getBody()
        );

        return ResponseEntity.ok(gallery);
    }
}
