package com.javaexpress.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.ProductRequestDTO;
import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.models.Product;
import com.javaexpress.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository  productRepository;

	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
		log.info("ProductServiceImpl :: addProduct method");
		 Product product = new Product();
		 /*
		  * as our variables name of product entity & ProductRequestDTO is same.
		  * we use BeanUtils class.
		  */
		BeanUtils.copyProperties(productRequestDTO, product);
		Product dbProduct = productRepository.save(product);
		return mapToDto(dbProduct);
	}

	 public  ProductResponseDTO mapToDto(Product product) {
		 ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		  BeanUtils.copyProperties(product, productResponseDTO);
		  return productResponseDTO; 
	 }
	
	@Override
	public boolean isProductExists(Long productId) {
		log.info("ProductServiceImpl :: isProductExists method");
		return productRepository.existsById(productId);
	}

	@Override
	public ProductResponseDTO getProductById(Long productId) {
		log.info("ProductServiceImpl :: getProductById method");
		return productRepository.findById(productId)
				.map(p -> mapToDto(p))
				.orElseThrow(() -> new RuntimeException("Product not found in Database"));
	}

}
