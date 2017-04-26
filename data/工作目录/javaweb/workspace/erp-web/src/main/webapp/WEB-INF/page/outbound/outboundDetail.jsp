<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>出库单详情</title>
</head>
<body>
<div>
<form id="outboundForm" name="form" method="post" class="easyui-form">
   <div style="display: none;">
          OutboundId<input id="id" name="id" type="text" value="<%=request.getParameter("outboundId")%>">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">品名</span>
   <input id="outboundName" name="" class="easyui-textbox" style="width: 300px; height: 25px;">
   </div>
   <div>
   <span style="margin-left: 5px; display:inline-block; width:60px;">数量</span>
   <input id="count" name="" class="easyui-textbox" style="width: 300px; height: 25px;">
   </div>
<!--    <div> -->
<!--    <span style="margin-left: 5px; display:inline-block; width:60px;">单位</span> -->
<!--    <input id="" name="" class="easyui-textbox" style="width: 300px; height: 25px;"> -->
<!--    </div> -->
<!--    <div> -->
<!--    <span style="margin-left: 5px; display:inline-block; width:60px;">规格</span> -->
<!--    <input id="" name="" class="easyui-textbox" style="width: 300px; height: 25px;"> -->
<!--    </div> -->
<!--    <div> -->
<!--    <span style="margin-left: 5px; display:inline-block; width:60px;">单价</span> -->
<!--    <input id="" name="" class="easyui-textbox" style="width: 300px; height: 25px;"> -->
<!--    </div> -->
   <div>
   <a id="creSelect" class="easyui-linkbutton" style="width: 50px">保存</a>
   <a id="btnReset" class="easyui-linkbutton" style="width: 50px">重置 </a>
   </div>
</form>

</div>





<script type="text/javascript">

$(function() {
	$.ajax({
		type : 'get',
		url : '/outbound/getOutbound.jhtml',
		data : {
			"id" : $("#id").val()
		},
		dataType : 'json',
		success : function(data,varieties) {
			$("#outboundName").textbox('setValue', data.outboundName);
			$("#count").textbox('setValue', data.count);
		}
	});
});


/* 重置 */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#outboundForm").form("reset");
	}

});

/* 保存 */
$("#creSelect").click(function(){
	var id = $("#id").val();
	if(id!=""){
		$.ajax({
			type : 'post',
			url : '/outbound/updateOutbound.jhtml',
			data : {
				"id" : $("#id").val(),
				"outboundName" : $("#outboundName").val(),
				"count" : $("#count").val(),
			},
			success : function(data) {
				$.myMessager.info("您成功修改了出库单的信息~");
			}
		});
	}
	
	if(id=="null"){
		$.ajax({
			type : 'post',
			url : '/outbound/createOutbound.jhtml',
			data : {
				"outboundName" : $("#outboundName").val(),
				"count" : $("#count").val(),
			},
			success : function(data) {
				$.myMessager.info("您成功的创建了出库单~");
			}
		});
	}
});



</script>
</body>
</html>