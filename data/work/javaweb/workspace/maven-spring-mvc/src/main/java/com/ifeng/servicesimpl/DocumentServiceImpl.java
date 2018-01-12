package com.ifeng.servicesimpl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ifeng.entitys.DmsDocument;
import com.ifeng.entitys.DmsDocumentExample;
import com.ifeng.mappers.DmsDocumentMapper;
import com.ifeng.services.DocumentService;
import com.ifeng.utils.Page;

@Service
public class DocumentServiceImpl implements DocumentService{
	@Autowired
	DmsDocumentMapper documentMapper;
	@Override
	public boolean addDocument(DmsDocument user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<DmsDocument> showDocumentAll(Page page) {
		// TODO Auto-generated method stub
		
		DmsDocumentExample example=new DmsDocumentExample();
		example.setLimit(page.getPageSize());//设置分页
		example.setStart(page.getStart(page.getPageNo()));
		DmsDocumentExample.Criteria criteria = example.createCriteria();
		return (ArrayList<DmsDocument>) documentMapper.selectByExample(example);
	}

	@Override
	public DmsDocument showDocument(String documentNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteDocument(String documentName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long showCount(String keyWords) {
		// TODO Auto-generated method stub
		DmsDocumentExample example=new DmsDocumentExample();
		DmsDocumentExample.Criteria criteria = example.createCriteria();
		criteria.andAuthorNameLike(keyWords);
		criteria.andDocumentNameLike(keyWords);
		return documentMapper.countByExample(example);
	}

}
