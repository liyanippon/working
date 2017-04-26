<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新建合同</title>
</head>
<body>

<div >
   <form id="contractForm" name="form" method="post" class="easyui-form">
   <div style="display: none;">
          ContractId<input id="id" name="id" type="text" value="<%=request.getParameter("contractId")%>">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">甲方</span>
   <input id="firstPeople" name="firstPeople" class="easyui-textbox" style="width: 300px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">乙方</span>
   <input id="secondPeople" name="secondPeople" class="easyui-textbox" style="width: 300px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">审批相关人</span>
   <input id="relevantPeople" name="relevantPeople" class="easyui-textbox" style="width: 300px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">品种</span>
   <input id="varieties" name="varieties" class="easyui-combobox" style="width: 135px; height: 25px;">
   <span>类别</span>
   <input id="category" name="category" class="easyui-combobox" style="width: 135px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">数量</span>
   <input id="number" name="number" class="easyui-textbox" style="width: 135px; height: 25px;">
   <span>价格</span>
   <input id="price" name="price" class="easyui-textbox" style="width: 135px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">质量标准</span>
   <input id="quality" name="quality" class="easyui-textbox" style="width: 300px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">交付方式</span>
   <input id="delivery" name="delivery" class="easyui-combobox" style="width: 300px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">付款方式</span>
   <input id="payment" name="payment" class="easyui-combobox" style="width: 300px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">结算方式</span>
   <input id="settlement" name="settlement" class="easyui-combobox" style="width: 300px; height: 25px;">
   </div>
   <div>
   <a id="creSelect" class="easyui-linkbutton" style="width: 50px">保存</a>
   <a id="btnReset" class="easyui-linkbutton" style="width: 50px">重置 </a>
   </div>
   </form>

</div>


<script type="text/javascript">

$(function() {
	var operated=<%=request.getParameter("operated")%>;
	//1是詳情
	if(operated=='2'||operated==2){
		document.getElementById("creSelect").style.display="none";
		document.getElementById("btnReset").style.display="none";
	}
	$.ajax({
		type : 'get',
		url : '/contract/getContract.jhtml',
		data : {
			"id" : $("#id").val()
		},
		dataType : 'json',
		success : function(data,varieties) {
			$("#firstPeople").textbox('setValue', data.firstPeople);
			$("#secondPeople").textbox('setValue', data.secondPeople);
			$("#relevantPeople").textbox('setValue', data.relevantPeople);
			$("#varieties").combobox('setValue',data.varieties);
			$("#category").combobox('setValue', data.category);
			$("#number").textbox('setValue', data.number);
			$("#price").textbox('setValue', data.price);
			$("#quality").textbox('setValue', data.quality);
			$("#delivery").combobox('setValue', data.delivery);
			$("#settlement").combobox('setValue', data.settlement);
			$("#payment").combobox('setValue', data.payment);
		}
	});
});


/* 保存 */
$("#creSelect").click(function(){
	var id = $("#id").val();
	if(id!=""){
		$.ajax({
			type : 'post',
			url : '/contract/updateContract.jhtml',
			data : {
				"id" : $("#id").val(),
				"firstPeople" : $("#firstPeople").val(),
				"secondPeople" : $("#secondPeople").val(),
				"relevantPeople" : $("#relevantPeople").val(),
				"varieties" : $("#varieties").combobox("getValue"),
				"category" : $("#category").combobox("getValue"),
				"number" : $("#number").val(),
				"price" : $("#price").val(),
				"quality" : $("#quality").val(),
				"delivery" : $("#delivery").combobox("getValue"),
				"settlement" : $("#settlement").combobox("getValue"),
				"payment" : $("#payment").combobox("getValue"),
			},
			success : function(data) {
				$.myMessager.info("您成功修改了合同的信息~");
				$('#workspace').panel('refresh', '/contract/showContractList.jhtml');
			}
		});
	}
	
	if(id=="null"){
		$.ajax({
			type : 'post',
			url : '/contract/createContract.jhtml',
			data : {
				"firstPeople" : $("#firstPeople").val(),
				"secondPeople" : $("#secondPeople").val(),
				"relevantPeople" : $("#relevantPeople").val(),
				"varieties" : $("#varieties").combobox("getValue"),
				"category" : $("#category").combobox("getValue"),
				"number" : $("#number").val(),
				"price" : $("#price").val(),
				"quality" : $("#quality").val(),
				"delivery" : $("#delivery").combobox("getValue"),
				"settlement" : $("#settlement").combobox("getValue"),
				"payment" : $("#payment").combobox("getValue"),
			},
			success : function(data) {
				$.myMessager.info("您成功的创建了合同~");
				$('#workspace').panel('refresh', '/contract/showContractList.jhtml');
			}
		});
	}
});

/* 重置 */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#contractForm").form("reset");
	}

});


/*品种下拉框*/
$('#varieties').combobox({
	url:"/varieties/getVarietiesList.jhtml",
	valueField:'id',    
	textField:'varietiesName'
});
/*类别下拉框*/
$('#category').combobox({
	url:"/contract/getCategory.jhtml",
	valueField:'id',    
	textField:'categoryName'
});
/*交付方式下拉框*/
$('#delivery').combobox({
	url:"/contract/getDelivery.jhtml",
	valueField:'code',    
	textField:'label'
});
/*付款方式下拉框*/
$('#payment').combobox({
	url:"/contract/getPayment.jhtml",
	valueField:'code',    
	textField:'label'
});
/*结算下拉框*/
$('#settlement').combobox({
	url:"/contract/getSettlement.jhtml",
	valueField:'code',    
	textField:'label'
});

</script>

</body>
</html>