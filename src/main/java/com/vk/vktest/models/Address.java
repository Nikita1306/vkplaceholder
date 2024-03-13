package com.vk.vktest.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

}
