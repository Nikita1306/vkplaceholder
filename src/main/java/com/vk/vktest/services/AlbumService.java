package com.vk.vktest.services;

import com.vk.vktest.models.Album;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AlbumService {

    private final WebClient webClient;

    @CachePut("albums")
    public Album getAlbum(String albumId){
        return webClient.get()
                .uri(String.join("", "/albums/", albumId))
                .retrieve()
                .bodyToMono(Album.class)
                .block();
    }
    @Cacheable("albums")
    public Album saveAlbum(Album album) {
        return webClient.post()
                .uri(String.join("", "/albums/"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(album), Album.class)
                .retrieve()
                .bodyToMono(Album.class)
                .block();
    }
    @CachePut(value = "albums", key = "#albumId")
    public Album updateAlbum(Album album, String albumId) {
        return webClient.put()
                .uri(String.join("", "/albums/", albumId))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(album), Album.class)
                .retrieve()
                .bodyToMono(Album.class)
                .block();
    }
    @CacheEvict("albums")
    public void deleteAlbum(String id) {
        webClient.delete()
                .uri(String.join("", "/albums/", id));
    }
}
