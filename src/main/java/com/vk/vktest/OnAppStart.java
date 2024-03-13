package com.vk.vktest;

import com.vk.vktest.enums.RolesEnum;
import com.vk.vktest.models.Role;
import com.vk.vktest.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
@Log4j2
public class OnAppStart {
    @Autowired
    private RoleRepository roleRepository;
    @EventListener(ApplicationReadyEvent.class)
    @Order(1)
    public void setRolesIfNot() {
        for (RolesEnum role : RolesEnum.values()) {
            if (!roleRepository.findByTitle(role).isPresent()) {
                log.info("Role " + role + " not found, inserting...");
                roleRepository.save(new Role(role));
            }
        }
    }
}
