package com.vk.vktest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User  {
    private Long id;
    private String name;
    @Column(unique = true)
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;





}
