package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
