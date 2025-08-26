package com.javaexpress.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "shipping_info")
public class ShippingInfo {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderId; //from order-service
	private String shippingMethod; //STANDARD, NEXT-DAY
	private LocalDateTime shippedAt;
	
	private LocalDateTime deliveryDate;
	private String status; //SHIPPED, IN_TRANSIT, DELIVERED
	private String carrier; //FedEx , DHL,UPS,Shadow
	
	
}
