package com.example.hellospringboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hellospringboot.model.Product;

public interface ProductRespository  extends JpaRepository<Product, Long>{
	List<Product> findByProductName(String productName);
}
