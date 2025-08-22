package com.javaexpress.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.ProductRequestDTO;
import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.service.ProductService;
import com.javaexpress.service.ProductServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService  productService;  
	
	
	@PostMapping
	public ProductResponseDTO addProduct(@Valid @RequestBody  ProductRequestDTO productRequestDTO) {
		log.info("ProductController :: addProduct method");
		return productService.addProduct(productRequestDTO);
	}
	
	@GetMapping("/{productId}")
	public ProductResponseDTO getProduct(@PathVariable Long productId) {
		log.info("ProductController :: getProduct method");
		ProductResponseDTO productResponseDTO = productService.getProductById(productId);
		return productResponseDTO;
	}
	@GetMapping("/exists/{productId}")
	public boolean isProductExists(@PathVariable Long productId) {
		log.info("ProductController :: isProductExists method");
		return productService.isProductExists(productId);
	}
	
	
	
}
