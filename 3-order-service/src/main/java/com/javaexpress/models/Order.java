package com.javaexpress.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	
	private BigDecimal totalPrice;
	
	private String status;
	private LocalDateTime placedAt =  LocalDateTime.now();
	
	//one order has multiple order_items;
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderItem> items;
	

}
