package com.yifeng.web.controller.export;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.business.controller.BaseController;
import com.yifeng.identify.api.OutboundApi;
import com.yifeng.identify.dto.OutboundDto;
import com.yifeng.identify.util.ExportUtil;
import com.yifeng.identify.util.UpdateFileUtil;

@Controller
@RequestMapping(value = "/export")
public class ExportOutboundController extends BaseController{
	@Autowired
	private OutboundApi outboundApi;
	 /**
     * 根据id查询品种
	 * @throws Throwable 
     */
    @RequestMapping(value = "/exportOutBound.jhtml")
    @ResponseBody
    public void exportOutBound(HttpServletRequest request,HttpServletResponse response) throws Throwable {
         String id=request.getParameter("id");        
         if(StringUtils.isBlank(id)||id==null||id.equals("null")){
//        	 throw new Exception("大哥,你这条出库单都没生成,导出啥excel啊");   
         }else{
        	 List<LinkedHashMap<String, Object>> a=new ArrayList<LinkedHashMap<String,Object>>();
          	 List<LinkedHashMap<String, Object>> list=outboundApi.getObrNpage(id);
          	 OutboundDto obd=outboundApi.selectByPrimaryKey(id);          	          	 
          	 ExportUtil.exportOutbound(obd, list,response);
         }
        
    }
    /**
     * 根据id查询品种
	 * @throws Throwable 
     */
    @RequestMapping(value = "/updateFile.jhtml")
    public void updateFile(HttpServletRequest request,HttpServletResponse response) throws Throwable {
        UpdateFileUtil.scfj(request); 
    }

}
