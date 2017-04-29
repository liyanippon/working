<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>选择查看供应商详情</title>
<link rel="stylesheet" type="text/css" media="screen"
    href="./css/selsupplierDetail.css" />
</head>
<body>
   <form id="sssupplierForm" name="form" method="post" class="easyui-form" style="width:400px;height:400px;padding: 30px,30px;">
      <div style="display: none;">
          SupplierId<input id="id" name="id" type="text" >
      </div>
	   <div style="height:35px;">
	   		<span style="margin-left: 5px; display:inline-block; width:80px;">供应商名称</span>
	        <span id="supplierName" name="supplierName" class="easyui-textbox" type="text" style="width: 300px; height: 25px;"  BackColor="transparent" BorderStyle="None">
	   </div>
	    <div style="height:35px;">
		   <span style="margin-left: 5px; display:inline-block; width:80px;">企业名称</span>
		   <span id="companyName" name="companyName" class="easyui-textbox" style="width: 300px; height: 25px;">
	   </div>
	    <div style="height:35px;">
		   <span style="margin-left: 5px; display:inline-block; width:80px;">联系人</span>
		   <span id="people" name="people" class="easyui-textbox" style="width: 300px; height: 25px;">
	   </div>
	   <div style="height:35px;">
		   <span style="margin-left: 5px; display:inline-block; width:80px;">联系电话</span>
		   <span id="tel" name="tel" class="easyui-textbox" style="width: 300px; height: 25px;">
	   </div>
	   <div style="height:35px;">
		   <span style="margin-left: 5px; display:inline-block; width:80px;">联系地址</span>
		   <span id="adress" name="adress" class="easyui-textbox" style="width: 300px; height: 20px;">
	   </div>
	   <div style="height:100;">
		   <span style="margin-left: 5px; display:inline-block; width:80px;">备注</span>
		   <span id="mome" name="mome" class="easyui-textbox" style="width: 300px; height: 100px;">
	   </div>
   </form>
<script type="text/javascript">
//加载表单数据
$.myAjax({
	url:'/supplier/getSupplier.jhtml?id=<%=request.getParameter("supplierId")%>',
		success:function(data) {
			$("#supplierName").textbox("setValue",data.supplierName);
			$("#companyName").textbox("setValue",data.companyName);
			$("#people").textbox("setValue",data.people);
			$("#tel").textbox("setValue",data.tel);
			$("#adress").textbox("setValue",data.adress);
			$("#mome").textbox("setValue",data.mome);
		}
	});
	
</script>
</body>
</html>