package com.example.inventory;

import com.example.inventory.entity.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class InventoryApplication implements CommandLineRunner {

    //@formatter:off
	private @Autowired ProductRepository productRepository;
	//@formatter:on

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product p1 = new Product();
        p1.setProductName("Penguin-ears");
        p1.setUnitsPerCarton(20);
        p1.setCartonPrice(175);
        p1.setImageUrl("http://localhost:4200/assets/images/penguin.jpg");
        p1.setUnitPrice(p1.getCartonPrice() / p1.getUnitsPerCarton());

        Product p2 = new Product();
        p2.setProductName("Horseshoe");
        p2.setUnitsPerCarton(5);
        p2.setCartonPrice(825);
        p2.setImageUrl("http://localhost:4200/assets/images/horseshoe.jpg");
        p2.setUnitPrice(p2.getCartonPrice() / p2.getUnitsPerCarton());

        this.productRepository.saveAll(Arrays.asList(p1, p2));
    }
}
