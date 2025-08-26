package com.javaexpress.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaexpress.dto.ProductResponseDTO;



@FeignClient(name = "PRODUCT-SERVICE",path = "/api/products") 
public interface ProductFeignClient{
	
	@GetMapping("/exists/{productId}")
	public boolean isProductExists(@PathVariable("productId") Long productId);
	
	@GetMapping("/{productId}")
	public ProductResponseDTO getProduct(@PathVariable("productId") Long productId);

}
