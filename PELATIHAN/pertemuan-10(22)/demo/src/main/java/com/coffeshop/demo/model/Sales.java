package com.coffeshop.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "code_coffee", referencedColumnName = "code")
    private Coffee coffee;  // Ubah dari codeCoffee menjadi coffee
    
    @ManyToOne
    @JoinColumn(name = "id_barista", referencedColumnName = "id")
    private Barista barista; // Ubah dari idBarista menjadi barista
}