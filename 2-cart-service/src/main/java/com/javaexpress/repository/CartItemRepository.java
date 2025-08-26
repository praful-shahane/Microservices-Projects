package com.javaexpress.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaexpress.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	
	Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
	
//	List<CartItem>  findByUserId(Long userId);
	
	void  deleteByUserId(Long userId);
	
	void deleteByUserIdAndProductId(Long userId, Long productId);
	
	@Query(value = "select * from ecomcartms.cart_items where user_id= :userId",nativeQuery = true)
	List<CartItem>  findByUserId(@Param("userId") Long userId);

}
