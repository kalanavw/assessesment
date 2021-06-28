package com.example.inventory.service.impl;

import com.example.inventory.dto.UnitPriceDto;
import com.example.inventory.entity.Product;
import com.example.inventory.exception.ResourceNotFoundException;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2021 All Rights Reserved
 * Created by Kalana Weerarathne on 25/06/2021 3:17 PM.
 */
@Service
public class ProductServiceImpl implements ProductService {
    //@formatter:off
    private @Autowired ProductRepository productRepository;
    //@formatter:on
    @Override
    public Page<Product> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("productName").ascending());
        return this.productRepository.findAll(pageable);
    }

    @Override
    public Double calculatePrice(long productId, int units) {
        Product product = this.findById(productId);
        return findTotalPrice(product, units);
    }

    @Override
    public List<UnitPriceDto> findUnitPrices(long productId) {
        Product product = this.findById(productId);
        List<UnitPriceDto> unitPriceDtos = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            UnitPriceDto dto = new UnitPriceDto();
            dto.setUnitCount(i);
            dto.setUnitPrice(this.findTotalPrice(product, i));
            unitPriceDtos.add(dto);
        }
        return unitPriceDtos;
    }

    @Override
    public Double findTotalPrice(Product product, int units) {
        double price = 0;
        if (units < product.getUnitsPerCarton()) {
            price = price + (product.getUnitPrice() * units) + (units * product.getUnitPrice() * 30 / 100);
        } else if (units % product.getUnitsPerCarton() == 0) {
            int cartons = units / product.getUnitsPerCarton();
            if (cartons >= 3) {
                price = price + product.getCartonPrice() * cartons - (cartons * product.getCartonPrice() * 10 / 100);
            } else {
                price = price + product.getCartonPrice() * cartons;
            }
        } else {
            int singleUnits = units % product.getUnitsPerCarton();
            price = price + findTotalPrice(product, singleUnits) + findTotalPrice(product, units - singleUnits);
        }
        return price;
    }

    private Product findById(long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found for the given id"));
    }
}
