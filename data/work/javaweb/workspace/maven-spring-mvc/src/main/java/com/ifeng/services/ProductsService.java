package com.ifeng.services;

import java.util.List;

import com.ifeng.entitys.CtmProduct;

public interface ProductsService {

	public List<CtmProduct> getAllProduct();
	public List<CtmProduct> getProductName(String name);
}
