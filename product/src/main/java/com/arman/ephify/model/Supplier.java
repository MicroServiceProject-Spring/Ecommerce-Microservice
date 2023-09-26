package com.arman.ephify.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactAddress; 
    private String phoneNumber; 
    private String emailAddress; 
    private String website; 
    private String paymentTerms; 
    private String leadTime; 
    private int minOrderQuantity; 
    private String deliveryOptions; 
    private double supplierRating; 
    private String supplierReviews; 
    private String productRange; 
    private String certifications; 
    private String supplierNotes; 


    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    // Getters and setters
}

