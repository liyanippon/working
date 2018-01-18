package com.ifeng.utils;

import org.apache.poi.ss.formula.functions.T;

import com.sun.tools.javac.util.List;

public class Page {
	private int pageSize =10; //每页显示条数
    private int totalCount; //总条数
    private int start; //开始条数
    private int pageNo;//当前页
    private int totalPages; //总页数
    private List<T> pageList;//数据
    public Page(int totalCount){
        this.totalCount = totalCount;
    }
      
    /**
     * 获取下一条
     */
    public int getCurrentPageNo(){
        return start / pageSize + 1;
    }
    /**       
     *是否有下一条
     * @return
     */
    public boolean getHasNextPage(){
        return getCurrentPageNo() < totalPages;
    }
    /**
     *当前页是否大于1
     * @return
     */
    public boolean getHasPavPage(){
        return getCurrentPageNo() > 1;
    }
    /**
     * 获取中页数
     * @return
     */
    public int getTotalPages() {
        totalPages = totalCount / pageSize;
          
        if(totalCount % pageSize != 0){
            totalPages++;
        }
          
        return totalPages;
    }
    /**
     * 设置当前页的开始条数
     * @param pageNo页数��
     * @return
     */
    public int getStart(int pageNo){
          
        if(pageNo < 1){
            pageNo = 1;
        }
        else if(getTotalPages()>0&&pageNo > getTotalPages()){
            pageNo = getTotalPages();
        }
          
        start = (pageNo-1) * pageSize;
        return start;
    }
      
    //get and set
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public List<T> getPageList() {
        return pageList;
    }
    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
 
    public int getPageNo() {
        return pageNo;
    }
 
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}
