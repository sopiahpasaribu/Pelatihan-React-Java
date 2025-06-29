package com.coffeshop.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data


public class Barista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id; // PRIMARY KEY
    private String name;
    private int age;
    private String gender;
    private String email;
}