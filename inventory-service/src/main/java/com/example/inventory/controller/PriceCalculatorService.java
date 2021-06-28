package com.example.inventory.controller;

import com.example.inventory.dto.UnitPriceDto;
import com.example.inventory.service.ProductService;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

/**
 * {@link PriceCalculatorService}
 * Copyright (c) 2021 All Rights Reserved
 * Created by Kalana Weerarathne on 26/06/2021 12:14 PM.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/prices")
@Validated
public class PriceCalculatorService {
    //@formatter:off
    private @Autowired ProductService productService;
    //@formatter:on
    @GetMapping
    public Double calculatePrice(
            @RequestParam(value = "product_id") long productId,
            @RequestParam(value = "units") int units
    ) {
        if (productId <= 0 || units <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.productService.calculatePrice(productId, units);
    }

    @GetMapping("/unit-prices")
    private List<UnitPriceDto> findUnitPrices(@RequestParam(value = "product_id") long productId) {
        if (productId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.productService.findUnitPrices(productId);
    }
}
