package com.yifeng.identify.util;

import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yifeng.identify.dto.OutboundDto;

public class ExportUtil {

   /**
    * 生成出库excel
    * @param oub
    * @param list
 * @throws Exception 
    */
	public static void exportOutbound(OutboundDto oub,List<LinkedHashMap<String, Object>> list,HttpServletResponse response) throws Exception{
	   Workbook workbook;
	   String fileName="出库单"+oub.getLsh()+".xlsx";
	   if(fileName.endsWith("xlsx")){  
           workbook = new XSSFWorkbook();  
       }else if(fileName.endsWith("xls")){  
           workbook = new HSSFWorkbook();  
       }else{  
           throw new Exception("invalid file name, should be xls or xlsx");  
       }  
          
       Sheet sheet = workbook.createSheet("出库单"+oub.getLsh());  
          
       Iterator<LinkedHashMap<String, Object>> iterator = list.iterator();  
          
       outBoundCell(sheet,oub);
       //前期先写死,等待更新换代
       int rowIndex=5;
       Row row1 = sheet.createRow(rowIndex);
       String [] listname={"货品名称:","货品数量:","货品规格:","货品种类:","货品类别:"};
       //生成出库单详情表头
       outBoundBT(listname,row1);
       while(iterator.hasNext()){
    	   Map<String,Object> outBoundRepertory=iterator.next();
    	   Row row = sheet.createRow(++rowIndex);
    	   //循环当前行内容
    	   int i=0;
    	   for(Map.Entry<String, Object> entry:outBoundRepertory.entrySet()){
    		   Cell cell = row.createCell(i);
    		   cell.setCellValue(toNullString(entry.getValue()));
    		   i++;
    	   }
       } 
       response.setContentType("octets/stream"); 
       response.setHeader("Content-Disposition", "attachment;filename="+new String( fileName.getBytes("UTF-8"),"ISO8859-1"));  
       OutputStream out = response.getOutputStream();
       workbook.write(out); 
       out.flush();
       out.close();
       
   }
	/**
	 * 生成出库单详情
	 * @param row
	 */
   public static int outBoundCell(Sheet sheet,OutboundDto oub){	   
	   for(int i=0;i<3;i++){
		   if(i==0){
			   Row row = sheet.createRow(i);  
	           Cell cell0 = row.createCell(0);  
	           cell0.setCellValue("出库单详情");   
		   }else if(i==1){
			   Row row = sheet.createRow(i);  
	           Cell cell0 = row.createCell(0);  
	           cell0.setCellValue("出库单编号:");
	           Cell cell1 = row.createCell(1);  
	           cell1.setCellValue("创建人:");
	           Cell cell2 = row.createCell(2);  
	           cell2.setCellValue("创建时间:");  	                    
		   }else{
			   Row row = sheet.createRow(i);  
	           Cell cell0 = row.createCell(0);  
	           cell0.setCellValue(oub.getLsh());
	           Cell cell1 = row.createCell(1);  
	           cell1.setCellValue(oub.getCreateBy());
	           Cell cell2 = row.createCell(2);  
	           cell2.setCellValue(toChineseStyle(oub.getCreateTime()));
		   }  
	   }
	   return 4;
   }
   /**
    * 设置列头传入列头文字,和当前行数
    * @param aa
    * @param cellnub
    * @return
    */
   public static void outBoundBT(String [] aa,Row row1){  
	   for(int i=0;i<aa.length;i++){
		   row1.createCell(i).setCellValue(aa[i]);
	   }
   }
   /**
    * 
    * @param o
    */
  public static String toNullString(Object o){
	  if(o=="null"||o==null){
		  return "";
	  }else{
		  return o.toString();
	  }
  }
  /**
   * 将指定日期转化为XXXX年XX月XX日XX时XX分XX秒
   * 
   * @param date
   *            指定日期
   * @return XXXX年XX月XX日XX时XX分XX秒格式的时间字符串
   */
  public static String toChineseStyle(Date date) {
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(date);
      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH) + 1;
      int day = cal.get(Calendar.DAY_OF_MONTH);
      int hour = cal.get(Calendar.HOUR_OF_DAY);// 24小时制
      int minute = cal.get(Calendar.MINUTE);
      StringBuffer result = new StringBuffer();
      result.append(year).append("年").append(month).append("月").append(day)
              .append("日").append(hour).append("时").append(minute).append(
                      "分 ");
      return result.toString();
  }
}
