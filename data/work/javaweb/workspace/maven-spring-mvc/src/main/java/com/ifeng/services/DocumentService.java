package com.ifeng.services;

import java.util.ArrayList;

import com.ifeng.entitys.DmsDocument;
import com.ifeng.utils.Page;

public interface DocumentService {
	public long showCount(String keyWords);//获取总条数
	public boolean addDocument(DmsDocument user);//添加文档
	public ArrayList<DmsDocument> showDocumentAll(Page page);//显示文档
	public DmsDocument showDocument(String documentNo);//根据文档编号显示文档
	public boolean deleteDocument(String documentName);//删除文档
}
