package com.arman.ephify.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description; 
    private String image; 
    private String seoMetaTitle; 
    private String seoMetaDescription; 
    private String seoMetaKeywords; 
    private String urlSlug; 

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory; // for hierarchical structure
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    // Getters and setters
}
