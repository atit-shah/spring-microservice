package com.learn.cloud.product.dataaccess;

import java.util.List;

import com.learn.cloud.product.model.Product;

public interface ProductDAO {

	Product findById(String id);

	List<Product> findByIdIn(List<String> idList);

	Product save(Product prodct);

	boolean delete(String id);

}
