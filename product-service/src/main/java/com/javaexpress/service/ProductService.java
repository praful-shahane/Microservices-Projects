package com.javaexpress.service;

import com.javaexpress.dto.ProductRequestDTO;
import com.javaexpress.dto.ProductResponseDTO;

public interface ProductService {
	
	ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
	
	boolean isProductExists(Long productId);
	
	ProductResponseDTO getProductById(Long productId);
}
