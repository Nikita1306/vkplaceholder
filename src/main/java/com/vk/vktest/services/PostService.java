package com.vk.vktest.services;

import com.vk.vktest.models.Post;
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
public class PostService {

    private final WebClient webClient;

    @CachePut("posts")
    public Post getPost(String postId){
        return webClient.get()
                .uri(String.join("", "/posts/", postId))
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }
    @Cacheable("posts")
    public Post savePost(Post post) {
        return webClient.post()
                .uri(String.join("", "/posts/"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(post), Post.class)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }
    @CachePut(value ="posts", key ="#postId")
    public Post updatePost(Post post, String postId) {
        return webClient.put()
                .uri(String.join("", "/posts/", postId))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(post), Post.class)
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }
    @CacheEvict("posts")
    public void deletePost(String id) {
        webClient.delete()
                .uri(String.join("", "/posts/", id));
    }
}
