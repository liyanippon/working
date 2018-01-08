package com.ifeng.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifeng.entitys.CtmProduct;
import com.ifeng.mappers.CtmProductMapper;
import com.ifeng.services.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {
	@Autowired
	CtmProductMapper productMapper;
	
	@Override
	public void getAllProduct() {
		// TODO Auto-generated method stub
		CtmProduct ctmProduct=productMapper.selectByExample(null).get(0);
		System.out.println(ctmProduct.getMarketPrice()+",");
	}

	
}
