package com.ifeng.servicesimpl;

import java.util.List;

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
	public List<CtmProduct> getAllProduct() {
		// TODO Auto-generated method stub
		
		List<CtmProduct> ctmProductList=productMapper.selectByExample(null);
		return ctmProductList;
	}

	
}
