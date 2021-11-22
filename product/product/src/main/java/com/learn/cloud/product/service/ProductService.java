package com.learn.cloud.product.service;

import java.util.List;

import com.learn.cloud.product.dto.request.ProudctBuyRequest;
import com.learn.cloud.product.dto.response.ProudctBuyResponse;
import com.learn.cloud.product.model.Product;
import com.learn.cloud.product.util.ApplicationResponse;

public interface ProductService {

	ApplicationResponse<Product> findById(String id);

	ApplicationResponse<List<Product>> findByIdIn(List<String> idList);

	ApplicationResponse<Product> save(Product product);

	ApplicationResponse<Boolean> delete(String id);

	ApplicationResponse<ProudctBuyResponse> buyProduct(ProudctBuyRequest request);

}
