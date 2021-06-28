package com.example.inventory.service.impl;

import com.example.inventory.dto.UnitPriceDto;
import com.example.inventory.entity.Product;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Copyright (c) 2021 All Rights Reserved
 * Created by Kalana Weerarathne on 25/06/2021 3:16 PM.
 */
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void findAll() {
        Page<Product> all = this.productService.findAll(0, 20);
        Assertions.assertNotNull(all);
    }

    @Test()
    void calculatePrice() {
        //        invalid product
        Double price;
        try {
            price = this.productService.calculatePrice(11, 1);
        } catch (Exception e) {
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
                throw new ResourceNotFoundException("Product not found");
            });
        }
        //        valid product
        price = this.productService.calculatePrice(1, 1);
        Assertions.assertNotNull(price);
    }

    @Test
    void findUnitPrices() {
        List<UnitPriceDto> unitPrices = this.productService.findUnitPrices(1);
        Assertions.assertNotNull(unitPrices);
    }

    @Test
    void findTotalPrice() {
        Product p1 = new Product();
        p1.setProductName("Penguin-ears");
        p1.setUnitsPerCarton(20);
        p1.setCartonPrice(175);
        p1.setImageUrl("http://localhost:4200/assets/images/penguin.jpg");
        p1.setUnitPrice(p1.getCartonPrice() / p1.getUnitsPerCarton());

        int units = 10;
        //        units < product.getUnitsPerCarton()
        Double totalPrice = this.productService.findTotalPrice(p1, units);
        Assertions.assertNotNull(totalPrice);

        //        units == product.getUnitsPerCarton()
        units = 20;
        totalPrice = this.productService.findTotalPrice(p1, units);
        Assertions.assertNotNull(totalPrice);

        //        units == product.getUnitsPerCarton()
        units = 20;
        totalPrice = this.productService.findTotalPrice(p1, units);
        Assertions.assertNotNull(totalPrice);

        //     carton size == 3
        units = 60;
        totalPrice = this.productService.findTotalPrice(p1, units);
        Assertions.assertNotNull(totalPrice);

        //     carton size > 3
        units = 62;
        totalPrice = this.productService.findTotalPrice(p1, units);
        Assertions.assertNotNull(totalPrice);
    }
}
