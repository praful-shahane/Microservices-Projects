package com.javaexpress.feignclient;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ORDER-SERVICE",path = "/api/orders")
public interface OrderFeignClient {

}
