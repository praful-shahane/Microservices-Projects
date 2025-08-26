package com.javaexpress.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CartItemResponseDto;
import com.javaexpress.dto.OrderItemResponseDto;
import com.javaexpress.dto.OrderResponseDto;
import com.javaexpress.dto.PlacedOrderRequestDto;
import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.dto.UserDto;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.feignclients.CartFeignClient;
import com.javaexpress.feignclients.ProductFeignClient;
import com.javaexpress.feignclients.UserFeignClient;
import com.javaexpress.models.Order;
import com.javaexpress.models.OrderItem;
import com.javaexpress.repository.OrderRepository;
import com.netflix.discovery.provider.Serializer;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private  OrderRepository orderRepository;
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	@Autowired
	private CartFeignClient cartFeignClient;
	
	@Autowired
	private ProductFeignClient productFeignClient;

	 
	

	@Override
	public OrderResponseDto placeOrder(PlacedOrderRequestDto placedOrderRequestDto) {
		
		//STEP 1=>validate user.
		UserDto  userDto = validateUser(placedOrderRequestDto.getUserId());
		if(userDto == null) {
			throw new ResourceNotFoundException("User Not found in DB");
		}
		
		//STEP 2=>get cart items.
		List<CartItemResponseDto> cartItems = fetchCartItems(placedOrderRequestDto.getUserId());
		if(cartItems == null || cartItems.isEmpty()) {
			throw new ResourceNotFoundException("CART IS EMPTY...");
		}
		
		//STEP 3=>calculate total price.
		BigDecimal totalPrice = calculateTotalPrice(cartItems);
		
		//STEP 4=>Build Cart Items
		List<OrderItem> orderItems = buildCartItems(cartItems);
		
		//Create Order Object.
		Order order = createOrder(placedOrderRequestDto, totalPrice, orderItems);
		
		Order dbOrder = orderRepository.save(order);
		
		//Clear the cart Items
		cartFeignClient.clearUserCart(placedOrderRequestDto.getUserId());
		
		
		return mapToOrderResponse(dbOrder,userDto);
	}


	private OrderResponseDto mapToOrderResponse(Order dbOrder, UserDto userDto) {
		
		 OrderResponseDto response = new OrderResponseDto();
		 /*
		  * nested object will be copied using BeanUtils.copyproperties();
		  * 
		  */
		 
		 BeanUtils.copyProperties(dbOrder, response,"items"); //Exclude items to map manually.
		 response.setUserDto(userDto);
		 
		 //as our OrderResponseDto's variable name & Order's variable name is diff we do manual mapping
		 response.setOrderId(dbOrder.getId());
		 
		 List<OrderItemResponseDto> orderItemresponseDto = dbOrder.getItems().stream().map(item ->{
			 OrderItemResponseDto itemDto = new OrderItemResponseDto();
			 BeanUtils.copyProperties(item,itemDto);
			 return itemDto;
		 }).toList();
		 
		 response.setItems(orderItemresponseDto);
		
		return response;
	}


	private Order createOrder(PlacedOrderRequestDto placedOrderRequestDto, BigDecimal totalPrice,
			List<OrderItem> orderItems) {
		Order order = new Order();
		order.setUserId(placedOrderRequestDto.getUserId());
		order.setTotalPrice(totalPrice);
		order.setStatus("PLACED");
		
		
		for (OrderItem orderItem : orderItems) {
			orderItem.setOrder(order);
		}
		
		order.setItems(orderItems);
		return order;
	}


	private List<OrderItem> buildCartItems(List<CartItemResponseDto> cartItems) {
		
		 List<OrderItem> orderItems= new ArrayList<OrderItem>();
		 
		 for (CartItemResponseDto item : cartItems) {
				ProductResponseDTO product = productFeignClient.getProduct(item.getProductId());
				
			 OrderItem orderItem = new OrderItem();
			 orderItem.setProductId(item.getProductId());
			 orderItem.setQuantity(item.getQuantity());
			 orderItem.setPrice(product.getPrice());
			
			 orderItems.add(orderItem);
		}
		 return orderItems;
		
		
	}


	private BigDecimal calculateTotalPrice(List<CartItemResponseDto> cartItems) {
	  BigDecimal total= BigDecimal.ZERO ;
		for (CartItemResponseDto item : cartItems) {
			//get product object
			ProductResponseDTO product = productFeignClient.getProduct(item.getProductId());
			//get quantity from item
			Integer quantity = item.getQuantity();
			
			BigDecimal individualPrice = product.getPrice().multiply(BigDecimal.valueOf(quantity));
			total = total.add(individualPrice);
			}
		return total;
		
	}


	private List<CartItemResponseDto> fetchCartItems(Long userId) {
		return cartFeignClient.getCartByUserId(userId);
		
	}


	private UserDto validateUser(Long userId) {
		return userFeignClient.fetchUser(userId.intValue());
	}


	@Override
	public void updateOrderStatus(Long orderId, String status) {
		
		
	}


	@Override
	public List<OrderResponseDto> getOrdersByUser(Long userId) {
	
		return null;
	}

}
