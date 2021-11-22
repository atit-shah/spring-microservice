package com.learn.cloud.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.cloud.product.dto.request.ProudctBuyRequest;
import com.learn.cloud.product.dto.response.ProudctBuyResponse;
import com.learn.cloud.product.model.Product;
import com.learn.cloud.product.service.ProductService;
import com.learn.cloud.product.util.ApplicationResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/")
	public ResponseEntity<ApplicationResponse<Product>> findById(@RequestParam String id) {
		return ResponseEntity.ok(productService.findById(id));
	}

	@GetMapping("/multi")
	public ResponseEntity<ApplicationResponse<List<Product>>> findByIdIn(@RequestParam List<String> idList) {
		return ResponseEntity.ok(productService.findByIdIn(idList));
	}

	@PostMapping("/")
	public ResponseEntity<ApplicationResponse<Product>> save(@RequestBody Product product) {
		return ResponseEntity.ok(productService.save(product));
	}

	@DeleteMapping("/")
	public ResponseEntity<ApplicationResponse<Boolean>> delete(@RequestParam String id) {
		return ResponseEntity.ok(productService.delete(id));
	}

	@PostMapping("/buy")
	public ResponseEntity<ApplicationResponse<ProudctBuyResponse>> buyProduct(ProudctBuyRequest request) {
		return ResponseEntity.ok(productService.buyProduct(request));
	}

}
