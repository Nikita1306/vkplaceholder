package com.vk.vktest.controllers;

import com.vk.vktest.models.User;
import com.vk.vktest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping(path = "{id}")
    public User getUser (@PathVariable String id) {
        return userService.getUser(id);
    }

    @PutMapping(path = "{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        return userService.updateUser(user, id);
    }
    @DeleteMapping(path = "{id}")
    public ResponseEntity patchUser (@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
