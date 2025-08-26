package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.ShippingInfo;

public interface ShippingRepository extends JpaRepository<ShippingInfo, Long> {
	
	
	ShippingInfo  findByOrderId(Long orderId);

}
