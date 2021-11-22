package com.learn.cloud.product.base;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductBaseRepository<T, ID> extends MongoRepository<T, ID> {

}
