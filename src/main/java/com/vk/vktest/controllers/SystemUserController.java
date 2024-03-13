package com.vk.vktest.controllers;

import com.vk.vktest.configuration.AuthenticationFacade;
import com.vk.vktest.models.SystemUser;
import com.vk.vktest.services.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/sysuser")
public class SystemUserController {

    @Autowired
    private AuthenticationFacade authenticationFacade;
    @Autowired
    private SystemUserService userService;
    @PostMapping(path = "register")
    public SystemUser register(@RequestBody SystemUser user) {
        return userService.register(user);
    }
    @PostMapping(path = "login")
    public SystemUser login(@RequestBody SystemUser user) {
        return userService.login(user);
    }
}
