package com.learn.cloud.product.dataaccess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learn.cloud.product.dataaccess.repository.ProductRepository;
import com.learn.cloud.product.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	private ProductRepository productRepository;

	@Autowired
	public ProductDAOImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product findById(String id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public List<Product> findByIdIn(List<String> idList) {
		return productRepository.findByIdIn(idList);

	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public boolean delete(String id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
