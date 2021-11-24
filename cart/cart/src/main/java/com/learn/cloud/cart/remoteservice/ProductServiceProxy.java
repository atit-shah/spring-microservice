package com.learn.cloud.cart.remoteservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learn.cloud.cart.dto.ProductResponse;

@FeignClient(name = "product-app", path = "product")
public interface ProductServiceProxy {

	@GetMapping(value = "/multi?idList={idList}", consumes = "application/json")
	ProductResponse getProductsByIdIn(@RequestParam("idList") List<String> productIdList);

}
