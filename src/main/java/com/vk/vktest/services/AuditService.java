package com.vk.vktest.services;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@Log4j2
public class AuditService {


    @Autowired
    SystemUserService userService;
    public void saveAction(String username,Collection<SimpleGrantedAuthority> authorities, HttpServletRequest request) {
        String uri = request.getServletPath();
        if (uri.startsWith("/posts")) {
            log.info("Время запроса: {}, Пользователь: {}, Путь запроса: {}, Имеется ли доступ: {}, Параметры запроса: {}"
                    , LocalDateTime.now(), username, request.getRequestURI()
                    ,check(authorities, "posts"), request.getParameterMap());
        }
        else if (uri.startsWith("/albums")) {
            log.info("Время запроса: {}, Пользователь: {}, Путь запроса: {}, Имеется ли доступ: {}, Параметры запроса: {}"
                    , LocalDateTime.now(), username, request.getRequestURI()
                    ,check(authorities, "albums"), request.getParameterMap());
        }
        else if (uri.startsWith("/users")) {
            log.info("Время запроса: {}, Пользователь: {}, Путь запроса: {}, Имеется ли доступ: {}, Параметры запроса: {}"
                    , LocalDateTime.now(), username, request.getRequestURI()
                    ,check(authorities, "users"), request.getParameterMap());
        }
        else if (uri.startsWith("/sysuser")) {
            log.info("Время запроса: {}, Пользователь: {}, Путь запроса: {}, Параметры запроса: {}"
                    , LocalDateTime.now(), username, request.getRequestURI()
                    , request.getParameterMap());
        }
    }

    private boolean check(Collection<SimpleGrantedAuthority> authorities, String uri) {
        return switch (uri) {
            case "posts" ->
                    authorities.stream().anyMatch(role -> role.getAuthority().equals("ROLE_POSTS") || role.getAuthority().equals("ROLE_ADMIN"));
            case "albums" ->
                    authorities.stream().anyMatch(role -> role.getAuthority().equals("ROLE_ALBUMS") || role.getAuthority().equals("ROLE_ADMIN"));
            case "users" ->
                    authorities.stream().anyMatch(role -> role.getAuthority().equals("ROLE_USERS") || role.getAuthority().equals("ROLE_ADMIN"));
            default -> false;
        };
    }
}
