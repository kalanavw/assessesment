package com.example.inventory.service;

import com.example.inventory.dto.UnitPriceDto;
import com.example.inventory.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Copyright (c) 2021 All Rights Reserved
 * Created by Kalana Weerarathne on 25/06/2021 3:16 PM.
 */
public interface ProductService {
    Page<Product> findAll(int page, int size);

    Double calculatePrice(long productId, int units);

    List<UnitPriceDto> findUnitPrices(long productId);

    Double findTotalPrice(Product product, int units);
}
