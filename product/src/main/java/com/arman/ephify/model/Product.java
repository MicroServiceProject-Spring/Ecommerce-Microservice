package com.arman.ephify.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.util.List;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String sku;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private List<ProductVariation> variations;

    @OneToMany(mappedBy = "product")
    private List<ProductAttribute> attributes;

    @OneToMany(mappedBy = "product")
    private List<ProductMedia> mediaList;

}
