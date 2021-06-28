package com.example.inventory.controller;

import com.example.inventory.entity.Product;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * {@link ProductController}
 * Copyright (c) 2021 All Rights Reserved
 * Created by Kalana Weerarathne on 25/06/2021 2:58 PM.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    //@formatter:off
    private @Autowired ProductService productService;
    //@formatter:on
    @GetMapping
    public ResponseEntity<Page<Product>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return ResponseEntity.ok(this.productService.findAll(page, size));
    }
}
