package com.ifeng.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifeng.entitys.CtmOrder;
import com.ifeng.mappers.CtmOrderMapper;
import com.ifeng.services.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	CtmOrderMapper ctmOrderMapper;
	
	@Override
	public List<CtmOrder> getAllOrder() {
		// TODO Auto-generated method stub
		
		return ctmOrderMapper.selectByExample(null);
	}

}
