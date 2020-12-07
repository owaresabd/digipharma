package com.pms.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.CommonInfo;
import com.pms.model.MaterialInfo;
import com.pms.model.ProductInfo;
import com.pms.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<ProductInfo> getAll(String status) {
		return productRepository.findAll(status);
	}

	public void saveProductInfos(ProductInfo info) {
		productRepository.saveProductInfos(info);
	}
	public CommonInfo getProductInfoById(UUID id) {
		CommonInfo info = productRepository.getProductInfoById(id);
		return info;
	}
	public List<MaterialInfo> getAllProduct(String type,String status) {
		return productRepository.findAllProduct(type,status);
	}

}
