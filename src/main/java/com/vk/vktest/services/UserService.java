package com.vk.vktest.services;

import com.vk.vktest.models.User;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserService {

    private final WebClient webClient;

    @CachePut("users")
    public User getUser(String userId){
        return webClient.get()
                .uri(String.join("", "/users/", userId))
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }
    @CachePut("users")
    public User saveUser(User user) {
        return webClient.post()
                .uri(String.join("", "/users/"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(user), User.class)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }
    @CachePut(value = "users", key = "#userId")
    public User updateUser(User user, String userId) {
        return webClient.put()
                .uri(String.join("", "/users/", userId))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(user), User.class)
                .retrieve()
                .bodyToMono(User.class)
                .block();
    }
    @CacheEvict("users")
    public void deleteUser(String id) {
        webClient.delete()
                .uri(String.join("", "/users/", id));
    }
}
