package com.example.inventory.repository;

import com.example.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kalana_105476, 6/24/2021 8:40 PM
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
}
