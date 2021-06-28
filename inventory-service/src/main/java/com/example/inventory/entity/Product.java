package com.example.inventory.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Kalana_105476, 6/24/2021 12:16 PM
 */
@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "UNITS_PER_CARTON")
    private int unitsPerCarton;

    @Column(name = "CARTON_PRICE")
    private double cartonPrice;

    @Column(name = "UNIT_PRICE")
    private double unitPrice;

    @Column(name = "IMAGE_URL")
    private String imageUrl;
}
