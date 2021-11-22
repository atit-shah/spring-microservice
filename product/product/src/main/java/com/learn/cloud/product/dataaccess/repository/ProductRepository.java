package com.learn.cloud.product.dataaccess.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.learn.cloud.product.base.ProductBaseRepository;
import com.learn.cloud.product.model.Product;

@Repository
public interface ProductRepository extends ProductBaseRepository<Product, String> {

	List<Product> findByIdIn(List<String> idList);

}
