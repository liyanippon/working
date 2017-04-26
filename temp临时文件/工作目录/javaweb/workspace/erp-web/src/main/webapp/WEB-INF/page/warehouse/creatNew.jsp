<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增入库</title>
</head>
<body>
	<!-- 新增入库 -->
	<div id="create" class="easyui-form"
		style="width: 500px; height: 450px; padding: 10px 20px;" closed="true">
		<div id = "btn">
		<a id="btnSave" class="easyui-linkbutton" style="width: 50px"onclick="save();">保存</a>  
		<a id="btnReset" class="easyui-linkbutton" style="width: 50px";>重置</a> 
		</div>
		<form id="formcre" method="post" class="easyui-form" style ="padding: 20px 0px;">
			<div style="display: none;" >
				<label>ID </label> 
				<input name="id" id="id" type="text" />
			</div>
			<div style="height: 40px;display:none">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">入库单编码：</label> 
				<input class="easyui-validatebox" id="warehousenumHid"  type="text" name="warehousenum" style="width:300px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">货物名称：</label> 
				<input  id="goodsname_d"  type="text" name="goodsname" style="width:300px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">货物类别：</label> 
				<input class="easyui-validatebox" id="varietiesId" type="text" name="varietiesId" style="width:300px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">品名：</label> 
				<input class="easyui-combobox" id="categoryId" type="text" name="categoryId" style="width:300px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">数量：</label> 
				<input class="easyui-validatebox" id="goodscount" type="text" name="goodscount" style="width:100px"/>
				<label for="name"style="margin-left: 5px; display:inline-block; width:80px;">单位：</label> 
				<input class="easyui-validatebox" id="unitId" type="text" name="unitId" style="width:100px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">规格：</label> 
				<input class="easyui-validatebox" id="specificationsId" type="text" name="specificationsId" style="width:300px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">存放仓库：</label> 
				<input class="easyui-validatebox" id="storehouseId" type="text" name="storehouseId" style="width:300px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">存放库位：</label> 
				<input class="easyui-validatebox" id="storepositionId" type="text" name="storepositionId" style="width:300px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">存放货盘：</label> 
				<input class="easyui-validatebox" id="goodsdishId" type="text" name="goodsdishId" style="width:300px"/>
			</div>
			<div style="height: 40px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">存放货位：</label> 
				<input class="easyui-validatebox" id="goodsplaceId" type="text" name="goodsplaceId" style="width:300px"/>
			</div>
			<div style="height: 300px;">
				<label for="name"style="margin-left: 5px; display:inline-block; width:100px;">备注：</label> 
				<input class="easyui-validatebox" id="meno" type="text" name="meno" style="width:300px;hight:250px"/>
			</div>
		</form>
	    </div>
	    
	<!-- 供应商信息列表 -->
	<div id="gysList" style="width: 100%;hight:300px">
	<h2>供应商信息</h2>
	     <div id = "tjgys">
	     <a id="btnSave" class="easyui-linkbutton" style="width: 50px"onclick="selectSupplier();">添加</a> 
	     </div>
	    <table id="yxzgysList" ></table>
	</div>
	
	<!-- 打开选择供应商 -->
	<div id="selectSupplier" class="easyui-dialog" 
	style="width: 600px; height: 600px; padding: 10px 20px;"
	closed="true"></div>

    <!--打开审批-->
    <div id="opinionSP" class="easyui-dialog" closed="true"></div>
	<script type="text/javascript">
	var forwarehouseId =$("#id").val();
	if(forwarehouseId==""){
		forwarehouseId = '<%=request.getParameter("warehouseId")%>';
	}
	
	//加载页面时判断是否显示供应商信息
	<%-- var id = $("#id").val(<%=request.getParameter("id")%>); --%>
	var option ='<%=request.getParameter("option")%>';//1编辑 2 只读
	var warehousenumId = '<%=request.getParameter("warehousenumId")%>';
	 $("#warehousenumHid").val(warehousenumId);
	
	//如果是编辑 1
	if(option=="1"){
		if(""=="1"){//如果是编辑状态 并且id位空 供应商列表隐藏
			$("#gysList").hide();
			getWarehouseByreturnid(forwarehouseId);
		}else{
			$("#gysList").show();
			getWarehouseByreturnid(forwarehouseId);	
		}
	}else if(option=="2"){
		getWarehouseByreturnid(forwarehouseId);
		$("#btn").hide();
		$("#tjgys").hide();
 		$("#formcre input").attr("disabled","disabled")
		$("#gysList").show();
		
	}else{
		$("#gysList").hide();
	}
	$('#yxzgysList').datagrid({
		url:'/warehouse/getGoodsSupplierMapplingList.jhtml?warehouseId='+forwarehouseId,
		width: '100%',
		rownumbers: true,
		fitColumns:true,
		rownumbers:true,
		pagination:true,
		striped:true,
		queryForm:'formcre',
		columns:[[
			{ field: 'id', title: '供应商ID',hidden:true},
			{ field: 'warehouseId', title: '入库单主键' ,width:70,hidden:true},
			{ field: 'supplierId', title: '供应商主键' ,width:70,hidden:true},
			{ field: 'supplier', title: '供应商名称' ,width:70},
			{ field: 'operate', title: '操作' ,width:100,formatter:formatGYS},
			
		]],
		singleSelect: false,
		selectOnCheck: true,
		checkOnSelect: true,          
		});
	/*操作*/
	function formatGYS(val, row, index) {
		var id = row.id;
		var supplierId = row.supplierId;
		var deleted = row.deleted;
		var str = "";
		if(option=="2"){
			str = '<span><a href="#" onclick="detailGYS(\'' + supplierId
			+ '\')" >详细</a></span>  ';
		}else{
			str = '<span><a href="#" onclick="detailGYS(\'' + supplierId
			+ '\')" >详细</a></span>  '
			+ '<span><a href="#" onclick="del(\'' + id
			+ '\')">删除</a></span>  ';
		}
		
		return str;
	}
	
	 /*选择类别下拉框*/
	 $("#varietiesId").combobox({    
	    url:'/varieties/getVarietiesList.jhtml',    
	    valueField:'id',    
	    textField:'varietiesName',
	    editable:false,
	    required: true,  
	}); 
	 /*货物品名下拉框*/
	 $("#categoryId").combobox({
		url:'/category/getAllCategory.jhtml',    
		valueField:'id',    
		textField:'categoryName',
		editable:false,
		required: true,  
	 }); 
	 /*单位下拉框*/
	 $("#unitId").combobox({
		url:'/unit/getAllUnit.jhtml',    
		valueField:'id',    
		textField:'unitName',
		editable:false,
		required: true,  
	 });
	 /*规格下拉框*/
	 $("#specificationsId").combobox({
		url:'/specifications/getAllSpecifications.jhtml',    
		valueField:'id',    
		textField:'specificationsName',
		editable:false,
		required: true,  
	 });
	 /*存放仓库下拉框*/
	 $("#storehouseId").combobox({
		 url:'/store/getAllStoreHouse.jhtml', 
		    valueField:'id', 
		    textField:'storehouseName',
		    editable:false,
         onSelect: function(rec){ 
	            var url = '/store/getAllStorePositionDto.jhtml?storeid='+rec.id;    
	            $('#storepositionId').combobox({    
				    url:url, 
				    valueField:'id', 
				    textField:'stortPosition',
				    editable:false
				}); 
	        }
	 }); 
	 /*存放库位下拉框*/
	 $("#storepositionId").combobox({
		url:'/store/getAllStorePositionDto.jhtml?',    
		valueField:'id',    
		textField:'stortPosition',
		editable:false,
		onSelect: function(rec){ 
	            var url = '/store/getAllGoodsDishDto.jhtml?positionid='+rec.id;    
	            $('#goodsdishId').combobox({    
				    url:url, 
				    valueField:'id', 
				    textField:'goodsDishName',
				    editable:false
				}); 
	        }
	 });
	 /*存放货盘下拉框*/
	 $("#goodsdishId").combobox({
		url:'/store/getAllGoodsDishDto.jhtml?',    
		valueField:'id',    
		textField:'goodsDishName',
		editable:false,
			onSelect: function(rec){ 
	            var url = '/store/getAllGoodsPlace.jhtml?dishid='+rec.id;    
	            $('#goodsplaceId').combobox({    
				    url:url, 
				    valueField:'id', 
				    textField:'goodsPlaceName',
				    editable:false
				}); 
	        }
	 });
	 /*存放货位下拉框*/
	 $("#goodsplaceId").combobox({
		url:'/store/getAllGoodsPlace.jhtml?',    
		valueField:'id',    
		textField:'goodsPlaceName',
		editable:false,
	 });
	
	 
	 
//保存入库信息
function save(id) {
	var warehouse ="";
	if(""!=id&&undefined!=id){
		  warehouse =id;	 
		 }else{
		  warehouse=$("#id").val();
		}
	var isValid = $("#formcre").form('validate');
	if(isValid){
		$.myAjax({
			url : '/warehouse/creatNewWarehouse.jhtml',
			data : {
				"id" : warehouse,//id号
				"warehousenum" : $("#warehousenum").val(),//入库单号
				"goodsname" : $("#goodsname_d").val(),//货物名称
				"varietiesId" : $("#varietiesId").combobox("getValue"),//类别
				"categoryId" : $("#categoryId").combobox("getValue"),//品种
				"goodscount" : $("#goodscount").val(),
				"unitId" : $("#unitId").combobox("getValue"),
				"specificationsId" : $("#specificationsId").combobox("getValue"),
				"storehouseId" : $("#storehouseId").combobox("getValue"),
				"storepositionId" : $("#storepositionId").combobox("getValue"),
				"goodsdishId" : $("#goodsdishId").combobox("getValue"),
				"goodsplaceId" : $("#goodsplaceId").combobox("getValue"),
				"meno" : $("#meno").val(),//备注
			},
			
			success : function(data) {
				if (data.succ) {
					    $.myMessager.show("保存成功");
					    //返回数据
					    getWarehouseByreturnid(data.returnid);
						//供应商列表显示	
						$("#gysList").show();
						//刷新页面
						$('#rkpertoryList').datagrid('load', {    
							"rkwarehousenum" : data.warehousenum,
						}); 
						
						
						
			    }else {
			       $.myMessager.show("保存失败！");
			    }
			}
		});
	}
}
/* 返回数据库入库信息*/
function getWarehouseByreturnid(id) {
	$.myAjax({
		url:'/warehouse/getWarehouseDtoById.jhtml?id=' + id,		
		success:function(data) {
			$("#id").val(data.id);//入库单编码
			$("#warehousenum").textbox("setValue", data.warehousenum);//入库单编码
			$("#goodsname_d").val( data.goodsname);//货物名称
			$("#varietiesId").combobox("setValue", data.varietiesId);//类别
			$("#categoryId").combobox("setValue", data.categoryId);//品种
			$("#goodscount").val(data.goodscount);//货物数量
			$("#unitId").combobox("setValue", data.unitId);//单位
			$("#specificationsId").combobox("setValue", data.specificationsId);////规格 
			$("#storehouseId").combobox("setValue", data.storehouseId);//仓库
			$("#storepositionId").combobox("setValue", data.storepositionId);//库位 
			$("#goodsdishId").combobox("setValue", data.goodsdishId);//货盘 
			$("#goodsplaceId").combobox("setValue", data.goodsplaceId);//货位
			$("#meno").val(data.meno);//备注 
		}
	});
}
					
//添加供应商
function selectSupplier() {
	var warehouseId = $("#id").val();
	var warehousenumHid = $("#warehousenumHid").val();
	$("#selectSupplier").dialog(
			'refresh',
			'/warehouse/showselSupplierList.jhtml?warehouseId='+warehouseId+'&warehousenumHideeneen='+warehousenumHid);
	$("#selectSupplier").dialog('open');
	}
	

//查看供应商详细
function detailGYS(supplierId) {
	$("#selectSupplier").dialog({
		title : "供应商详细信息",
		width : 600,
		height : 500,
		closed : false,
		href: '/warehouse/showSelsupplierDetail.jhtml?supplierId='+supplierId,
	});
}
/* 根据id删除供应商关系*/
function del(id) {
	$.myMessager.confirm("提示！", "确实要删除该供应商么！", function(r) {
		if (r) {
			$.myAjax({
				url : '/warehouse/delGoodsSupplierMapplingDtoById.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#yxzgysList').datagrid('reload');
					$.myMessager.info("删除供应商成功!");
				}
			});
		}
	});
}

//打开提交页面
function submitSH(){
	//提交之前先保存
	var id = $("#id").val();
	$("#submitSH").dialog({
		title : "提交审核",
		width :400,
		height : 200,
		closed : false,
		href: '/warehouse/showSubmitSh.jhtml',  
		
	})
}

/* 重置按钮  */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#formcre").form("reset");
		$("#formcre").form('load',{});
	}
});

//页面校验 
//货物名称
$("#goodsname").validatebox({    
    required: true,  
    validType: 'length[1,30]',
}); 
//数量 
$("#goodscount").validatebox({    
    required: true,  
    validType:  ['length[1,15]','nunmber','minNum'],
});
//初始值校验
$("#initvalue").validatebox({    
    required: true,  
    validType:  ['integ'],
});
$.extend($.fn.validatebox.defaults.rules, {
	 nunmber:{
         validator:function(value,param){
           return /^\d{1,}$|^\d{1,}.\d{2,2}$/.test(value);
         },
         message: '请输入整数或者保留两位小数'
       },
       minNum:{  
           validator:function(value,param){ 
        	   if(value >0){
        		   return true;  
                   
               }else{  
                   return false;  
               }  
           },  
           message:'请输入大于0的正整数'  
       }, 
       inniLength:{  
           validator:function(value,param){
        	   var length = $("#serialLength").val();
			     if(value== length){
			      return true;  
			      }else{  
			       return false ;  
			            }  
           },  
           message:'请输入'+length+'个字符的正整数'  
       },  

	}); 
	</script>
</body>
</html>