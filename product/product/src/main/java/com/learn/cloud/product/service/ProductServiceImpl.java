package com.learn.cloud.product.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learn.cloud.product.dataaccess.ProductDAO;
import com.learn.cloud.product.dto.request.ProudctBuyRequest;
import com.learn.cloud.product.dto.response.ProudctBuyResponse;
import com.learn.cloud.product.model.Product;
import com.learn.cloud.product.util.ApplicationResponse;
import com.learn.cloud.product.util.ApplicationResponseCode;
import com.learn.cloud.product.util.ApplicationResponseMessage;

@Service
class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;

	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Value("${eureka.instance.instance-id}")
	private String instanceId;

	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}

	@Override
	public ApplicationResponse<Product> findById(String id) {
		ApplicationResponse<Product> appResponse = new ApplicationResponse<>(ApplicationResponseCode.UNKNOWN_ERROR,
				ApplicationResponseMessage.UNKNOWN_ERROR, instanceId);
		Product product = productDAO.findById(id);
		if (product != null) {
			appResponse.setResponseObject(product);
			appResponse.setResponseCode(ApplicationResponseCode.SUCCESS);
			appResponse.setResponseMsg(ApplicationResponseMessage.SUCCESS);
		} else {
			appResponse.setResponseCode(ApplicationResponseCode.RESOURCE_NOT_FOUNT);
			appResponse.setResponseMsg(ApplicationResponseMessage.RESOURCE_NOT_FOUNT);
		}
		logger.info(String.format("findById: %s", appResponse));
		return appResponse;
	}

	@Override
	public ApplicationResponse<List<Product>> findByIdIn(List<String> idList) {
		ApplicationResponse<List<Product>> appResponse = new ApplicationResponse<>(
				ApplicationResponseCode.UNKNOWN_ERROR, ApplicationResponseMessage.UNKNOWN_ERROR, instanceId);
		List<Product> productList = productDAO.findByIdIn(idList);
		appResponse.setResponseObject(productList);
		appResponse.setResponseCode(ApplicationResponseCode.SUCCESS);
		appResponse.setResponseMsg(ApplicationResponseMessage.SUCCESS);
		logger.info(String.format("findByIdIn: %s", appResponse));
		return appResponse;
	}

	@Override
	public ApplicationResponse<Product> save(Product product) {
		ApplicationResponse<Product> appResponse = new ApplicationResponse<>(ApplicationResponseCode.UNKNOWN_ERROR,
				ApplicationResponseMessage.UNKNOWN_ERROR, instanceId);
		product = productDAO.save(product);
		if (product.getId() != null) {
			appResponse.setResponseObject(product);
			appResponse.setResponseCode(ApplicationResponseCode.SUCCESS);
			appResponse.setResponseMsg(ApplicationResponseMessage.SUCCESS);
		} else {
			appResponse.setResponseCode(ApplicationResponseCode.TRANSACTION_FAILURE);
			appResponse.setResponseMsg(ApplicationResponseMessage.TRANSACTION_FAILURE);
		}
		return appResponse;
	}

	@Override
	public ApplicationResponse<Boolean> delete(String id) {
		ApplicationResponse<Boolean> appResponse = new ApplicationResponse<>(ApplicationResponseCode.UNKNOWN_ERROR,
				ApplicationResponseMessage.UNKNOWN_ERROR, instanceId);
		boolean result = productDAO.delete(id);
		if (result) {
			appResponse.setResponseCode(ApplicationResponseCode.SUCCESS);
			appResponse.setResponseMsg(ApplicationResponseMessage.SUCCESS);
		} else {
			appResponse.setResponseCode(ApplicationResponseCode.TRANSACTION_FAILURE);
			appResponse.setResponseMsg(ApplicationResponseMessage.TRANSACTION_FAILURE);
		}
		return appResponse;
	}

	@Override
	public ApplicationResponse<ProudctBuyResponse> buyProduct(ProudctBuyRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
