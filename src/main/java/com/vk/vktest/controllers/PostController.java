package com.vk.vktest.controllers;


import com.vk.vktest.models.Post;
import com.vk.vktest.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(path = "save")
    public Post savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }

    @GetMapping(path = "{id}")
    public Post getPost (@PathVariable String id) {
        return postService.getPost(id);
    }

    @PutMapping(path = "{id}")
    public Post updatePost(@PathVariable String id, @RequestBody Post post) {
        return postService.updatePost(post, id);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity patchPost (@PathVariable String id) {
        postService.deletePost(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
