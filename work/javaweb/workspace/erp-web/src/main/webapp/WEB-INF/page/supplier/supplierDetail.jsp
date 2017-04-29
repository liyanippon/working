<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>供应商详情</title>
</head>
<body>

<div id="supplierDetail" style="width: 100%; height: 100%; overflow: hidden;">
   <div>
   <a id="creSelect" class="easyui-linkbutton" style="width: 50px">保存</a>
   <a id="btnReset" class="easyui-linkbutton" style="width: 50px">重置 </a>
   </div>
   <form id="supplierForm" name="form" method="post" class="easyui-form">
      <div style="display: none;">
          SupplierId<input id="id" name="id" type="text" value="<%=request.getParameter("supplierId")%>">
      </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">供应商名称</span>
   <input id="supplierName" name="supplierName" class="easyui-textbox" style="width: 150px; height: 25px;">
   </div>
    <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">企业名称</span>
   <input id="companyName" name="companyName" class="easyui-textbox" style="width: 150px; height: 25px;">
   </div>
    <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">联系人</span>
   <input id="people" name="people" class="easyui-textbox" style="width: 150px; height: 25px;">
   </div>
    <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">联系电话</span>
   <input id="tel" name="tel" class="easyui-textbox" style="width: 150px; height: 25px;">
   </div>
    <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">联系地址</span>
   <input id="adress" name="adress" class="easyui-textbox" style="width: 150px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">创建人</span>
   <input id="createBy" name="createBy" class="easyui-textbox" style="width: 150px; height: 25px;">
   </div>
<!--     <div> -->
<!--    <span>创建时间</span> -->
<!--    <input id="createTime" name="createTime" class="easyui-textbox" style="width: 150px; height: 25px;"> -->
<!--    </div> -->
    <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">更新人</span>
   <input id="updateBy" name="updateBy" class="easyui-textbox" style="width: 150px; height: 25px;">
   </div>
<!--     <div> -->
<!--    <span>更新时间</span> -->
<!--    <input id="updateTime" name="updateTime" class="easyui-textbox" style="width: 150px; height: 25px;"> -->
<!--    </div> -->
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">备注</span>
   <input id="mome" name="mome" class="easyui-textbox" style="width: 150px; height: 25px;">
   </div>
   </form>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">相关资质</span>
   <input id="xgzz" type="file" class="easyui-textbox" style="width: 150px; height: 25px;" value="选择文件">
   <a id="saveXgzz" class="easyui-linkbutton" style="width: 50px" onclick="save();">添加</a>
   </div>
   <div id="XgzzDiv">
   <table id=xgzzList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true">
		<thead>
			<tr>
				<th data-options="field:'mome',width:70,align:'center'">文件</th>
				<th data-options="field:'operate',width:70,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
	</table>
   </div>
</div>

<script type="text/javascript">

/**
 * 添加文件
 */
 function load(){  
	     var fileObj = document.getElementById("xgzz").files[0]; // 获取文件对象  
	     var FileController = "entityServlet1"; // 接收上传文件的后台地址  
	     if(fileObj){  
	         alert(fileObj);  
	          // FormData 对象  
	              var form = new FormData();   
	              form.append("file", fileObj);// 文件对象     
	              // XMLHttpRequest 对象  
	              var xhr = new XMLHttpRequest();      
	              xhr.open("post", FileController, true);      
	              xhr.onload = function () {   
	                  alert(xhr.responseText);     
	              };   
	              xhr.send(form);  
	     }else{  
	         alert("未选择文件");  
	     }  
	 }  



/* 操作 */
function formatOper(val, row, index) {
	var id=row.id;
	var deleted = row.deleted;
	var r = '<a href="#" onclick="load(\''+id+'\')">下载 </a>  '
			+   '<a href="#", onclick="deletes(\''+id+'\')">删除</a>';
	return r;
		}

/* 保存 */
$("#creSelect").click(function(){
	var id = $("#id").val();
	if(id!=""){
		$.ajax({
			type : 'post',
			url : '/supplier/updateSupplier.jhtml',
			data : {
				"id" : $("#id").val(),
				"supplierName" : $("#supplierName").val(),
				"companyName" : $("#companyName").val(),
				"people" : $("#people").val(),
				"tel" : $("#tel").val(),
				"adress" : $("#adress").val(),
				"mome" : $("#mome").val(),
			},
			success : function(data) {
				$.myMessager.info("您成功修改了供应商的信息~");
				$('#workspace').panel('refresh', '/supplier/showSupplierList.jhtml');
			}
		});
	}
	if(id=="null"){
		$.ajax({
			type : 'post',
			url : '/supplier/createSupplier.jhtml',
			data : {
				"supplierName" : $("#supplierName").val(),
				"companyName" : $("#companyName").val(),
				"people" : $("#people").val(),
				"tel" : $("#tel").val(),
				"adress" : $("#adress").val(),
				"mome" : $("#mome").val(),
			},
			success : function(data) {
				$.myMessager.info("您成功的创建了供应商~");
				$('#workspace').panel('refresh', '/supplier/showSupplierList.jhtml');
			}
		});
	}
	
	
});

/* 重置 */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#supplierForm").form("reset");
	}

});

$(function() {
	var operated=<%=request.getParameter("operated")%>;
	//1是詳情
	if(operated=='2'||operated==2){
		document.getElementById("creSelect").style.display="none";
		document.getElementById("btnReset").style.display="none";
	}
	$.ajax({
		type : 'get',
		url : '/supplier/getSupplier.jhtml',
		data : {
			"id" : $("#id").val()
		},
		dataType : 'json',
		success : function(data) {
			$("#supplierName").textbox('setValue', data.supplierName);
			$("#companyName").textbox('setValue', data.companyName);
			$("#people").textbox('setValue', data.people);
			$("#tel").textbox('setValue', data.tel);
			$("#adress").textbox('setValue', data.adress);
			$("#createBy").textbox('setValue', data.createBy);
// 			$("#createTime").textbox('setValue', data.createTime);
			$("#updateBy").textbox('setValue', data.updateBy);
// 			$("#updateTime").textbox('setValue', data.updateTime);
			$("#mome").textbox('setValue', data.mome);
		}
	});
});




</script>




</body>
</html>