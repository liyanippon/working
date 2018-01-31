package com.ifeng.servicesimpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
		
		int result = documentMapper.insertSelective(user);
		
		if(result==1){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public ArrayList<DmsDocument> showDocumentAll(Page page,String keyword) {
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
		DmsDocumentExample example=new DmsDocumentExample();
		DmsDocumentExample.Criteria criteria = example.createCriteria();
		criteria.andDocumentNameEqualTo(documentName);
		int result = documentMapper.deleteByExample(example);
		if(result==1){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public long showDocumentNameCount(String keyWords) {
		// TODO Auto-generated method stub
		long count=0;
		DmsDocumentExample example=new DmsDocumentExample();
		DmsDocumentExample.Criteria criteria = example.createCriteria();
		criteria.andDocumentNameLike(keyWords);
		count = documentMapper.countByExample(example);
		example=new DmsDocumentExample();
		criteria = example.createCriteria();
		criteria.andAuthorNameLike(keyWords);
		count+=documentMapper.countByExample(example);
		System.out.println("数量："+count);
		return count;
	}

	@Override
	public long showAuthorNameCount(String keyWords) {
		// TODO Auto-generated method stub
		DmsDocumentExample example=new DmsDocumentExample();
		DmsDocumentExample.Criteria criteria = example.createCriteria();
		criteria.andAuthorNameLike(keyWords);
		return documentMapper.countByExample(example);
	}

	@Override
	public ArrayList<DmsDocument> showDocumentNameDocument(Page page, String keyword) {
		// TODO Auto-generated method stub
		
		if(page==null){
			DmsDocumentExample example=new DmsDocumentExample();
			DmsDocumentExample.Criteria criteria = example.createCriteria();
			criteria.andDocumentNameLike(keyword);
			return (ArrayList<DmsDocument>) documentMapper.selectByExample(example);
		}
		ArrayList<DmsDocument> list;
		ArrayList<DmsDocument> list1;
		DmsDocumentExample example = new DmsDocumentExample();
		DmsDocumentExample.Criteria criteria = example.createCriteria();
		criteria.andDocumentNameLike(keyword);
		list=(ArrayList<DmsDocument>) documentMapper.selectByExample(example);
		example = new DmsDocumentExample();
		criteria = example.createCriteria();
		criteria.andAuthorNameLike(keyword);
		list1=(ArrayList<DmsDocument>) documentMapper.selectByExample(example);
		list.addAll(list1);
		return list;
	}

	@Override
	public long showCount(String keyWords) {
		// TODO Auto-generated method stub
		DmsDocumentExample example=new DmsDocumentExample();
		DmsDocumentExample.Criteria criteria = example.createCriteria();
		return documentMapper.countByExample(example);
	}

	@Override
	public boolean updateDocument(String documentSn,DmsDocument dmsDocument) {
		// TODO Auto-generated method stub
		DmsDocumentExample example=new DmsDocumentExample();
		DmsDocumentExample.Criteria criteria = example.createCriteria();
		criteria.andDocumentSnEqualTo(documentSn);
		int result=documentMapper.updateByExampleSelective(dmsDocument, example);
		if(result==1){
			return true;
		}
		else{
			return false;
		}
	}

}
