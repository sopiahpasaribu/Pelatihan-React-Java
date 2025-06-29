package com.coffeshop.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Coffee {

    @Id
    private String code; // PRIMARY KEY
    private String merk;
    private String type;
    private double price;
}
