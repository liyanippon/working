package com.yifeng.web.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织结构树：第一级公司，第二级部门，第三级人员
 * 
 * @author Administrator
 *
 */
public class TreeNode{

	    /** 
	     * 显示节点的id 
	     */  
	    private String id;  
	    /** 
	     * 显示节点的名称 
	     */  
	    private String text;  
	    /** 
	     * 显示节点的图标 
	     */  
        private String icon;  
	   /** 
	    * 显示节点的父节点 
	   */  
	    private String parentId;  
	    /** 
	     * 显示数据库中的唯一标示Id
	     */
	    private String bsId;  
	    /** 
	     * 显示节点的子节点集合 
	     */
	    
	    private List<TreeNode> children;  
	      
	    public List<TreeNode> getChildren() {
			return children;
		}

		public void setChildren(List<TreeNode> children) {
			this.children = children;
		}

		/** 
	     * 空的构造函数 
	     */  
	    public TreeNode(){  
	          
	   }  
	     
	   /** 
	    * 有参数的构造参数 
	    * @param id 显示的节点ID 
	    * @param text 显示的节点名称 
	    * @param icon 显示的节点图标 
	    * @param parentId 显示的节点的父节点 
	    * @param children 显示节点的子节点 
	    */  
	  public TreeNode(String id, String text,String icon,String parentId,List<TreeNode> children){  
	        super();  
	        this.setId(id);  
	        this.setText(text);  
	        this.setIcon(icon);  
	        this.setParentId(parentId);  
	        this.children=children;  
	    }  
	 
	   /** 
	    *省略get/set方法 
	    */  
	      
	   /** 
	    * 添加子节点的方法 
	    * @param node 树节点实体 
	    */  
	    public void addChild(TreeNode node){  
	        if(this.children==null){  
	           children=new ArrayList<TreeNode>();  
	           children.add(node);  
	       }else{  
	           children.add(node);  
	       }  
	   }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getBsId() {
		return bsId;
	}

	public void setBsId(String bsId) {
		this.bsId = bsId;
	}  
     
	}  
