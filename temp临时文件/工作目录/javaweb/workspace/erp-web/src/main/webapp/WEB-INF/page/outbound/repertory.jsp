<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>库存列表</title>
</head>
<body>
 <div>     
 		  <jsp:include page="../publicjsp/updatefile.jsp" flush="true"/>
 </div>
<div>
   <div id="chukudan" style="width: 100%;height:40px; vertical-align: middle; padding-left: 6px; padding-bottom: 3px; padding-top: 13px;">
   <div style="display: none;"> 
   <form id="aaa">
		<span style="margin-left: 5px; display:inline-block; width:60px;">id</span>
  		<input id="id" readonly="readonly" name="id" class="easyui-textbox" style="width: 300px; height: 25px;" value="<%=request.getParameter("outboundId")%>">
   </form>
   </div>
   <div style="float:left; width: 30%; height:35px;">
   <span style="margin-left: 5px; display:inline-block; width:60px;">编号:</span>
   <input id="lsh" readonly="readonly" name="lsh" class="easyui-textbox" style="width: 300px; height: 25px;">
   </div>
   <div style="float:left; width: 30%; height:35px;">
   <span style="margin-left: 5px; display:inline-block; width:60px;">创建人:</span>
   <input id="outboundPeople" readonly="readonly" name="outboundPeople" class="easyui-textbox" style="width: 300px; height: 25px;">
   </div>
   <a id="addSelect" class="easyui-linkbutton" style="width: 150px">生成出库单</a>
   <a id="btnOut" style="width: 150px;float: right;"  class="easyui-linkbutton" onclick="abc();">返回出库单</a>
  <!--  导出出库单,需求要就加 -->
   <a id="exportOutBound" style="width: 150px;float: right;"  class="easyui-linkbutton">导出出库单</a>
   </div>
   <form id="from1" style="display: none;">
   <div style="width: 100%; height:50px; vertical-align: middle; padding-left: 6px; padding-bottom: 3px; padding-top: 13px;">
   <div style="float:left; width: 30%; height:35px;">
   <span style="margin-left: 5px; display:inline-block; width:60px;">货品名称:</span>
   <input id="repertoryProducts" name="repertoryProducts" class="easyui-combobox" style="width: 300px; height: 25px;">
   </div>
   <div style="float:left; width: 30%; height:35px;">
   <span style="margin-left: 4px; display:inline-block; width:60px;">数量:</span>
   <input id="productsnumber" name="productsnumber" class="easyui-validatebox" style="width: 300px; height: 25px;">
   </div>
   <div style="float:left; width: 30%;  height: 35px; ">
      <a id="creSelect" class="easyui-linkbutton" style="width: 150px">添加</a>
   </div>
   </div>
   </form>
<div id="xiangqing" style="width: 100%; vertical-align: middle; padding-left: 6px; padding-bottom: 3px; padding-top: 13px;display: none;">
  <span style="margin-left:2px; display:inline-block; width:80px;" >货品名称:</span>
  <label id="goodsname"></label>
 <span style="margin-left:25px; display:inline-block; width:80px;" >货品规格:</span>
 <label id="specifications_name"></label>
 
 <span style="margin-left:25px; display:inline-block; width:80px;" >货品种类:</span>
 <label id="category_name"></label>
 <span style="margin-left:25px; display:inline-block; width:80px;" >货品类别:</span>
 <label id="varieties_name"></label>
 <span style="margin-left:25px; display:inline-block; width:100px;" >剩余货品数量:</span>
 <label id="goodscount"></label>
 </div>
</div>
<table id="repertoryList" class="easyui-datagrid" style="width:100%;"
		data-options="fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true">
		<thead>
			<tr>
				<th data-options="field:'goodsname',width:70,align:'center'">货品名称</th>
				<th data-options="field:'specifications',width:70,align:'center'">货品规格</th>
				<th data-options="field:'category',width:70,align:'center'">货品种类</th>
				<th data-options="field:'varieties',width:70,align:'center'">货品类别</th>
				<th data-options="field:'output_quantity',width:70,align:'center'">出库数量</th>
				<th data-options="field:'operate',width:100,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
</table>

<script type="text/javascript">



$(function() {		
	var operated=<%=request.getParameter("operated")%>;
	//1是詳情
	if(operated=='1'||operated==1){
		document.getElementById("addSelect").style.display="none";
	}
	//2是修改
	if(operated=='2'||operated==2){
		document.getElementById("addSelect").style.display="none";
		$("#from1").slideToggle();
	}
	var outboundbh=$("#id").val();
	if(outboundbh!=""&& outboundbh!=null&&outboundbh!="null"){
		$.ajax({
			url : '/outbound/selectOutbound.jhtml',
		    data : {
				"id":outboundbh
			},
			success : function(data) {
				$("#id").textbox('setValue',data.id);
				$("#lsh").textbox('setValue',data.lsh);
				$("#outboundPeople").textbox('setValue',data.createBy);
				$('#repertoryList').datagrid({
					url:'/outbound/showOutboundRepertoryList.jhtml?outboundbh=' + outboundbh
				});
			}
		});
	}
	
});
/* 操作 */
function formatOper(val, row, index) {
	var id=row.id;
	var operated=<%=request.getParameter("operated")%>;
	var deleted = row.deleted;
	var r;	
	if(operated!=1&&operated!="1"){
		 r= '<a href="#" onclick="out(\''+id+'\')">删除</a>';
	}else{		
	}
    return r;
}
	
/*返回出库单*/	
function abc(){
	$('#workspace').panel('refresh',
			'/outbound/showOutboundList.jhtml');
}

//数量 
$("#productsnumber").validatebox({    
    required: true,  
    validType:  ['length[1,13]','nunmber','minNum'],
});
//初始值校验
$("#initvalue").validatebox({    
    required: true,  
    validType:  ['integ'],
});
$.extend($.fn.validatebox.defaults.rules, {
	 nunmber:{
        validator:function(value,param){
          return /^\d{1,}$|^\d{1,}$/.test(value);
        },
        message: '请输入整数'
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
	
	
	
	
	
$('#creSelect').click(function(){	
	var repertoryProducts = $("#repertoryProducts").combobox("getValue");
	var number=$("#productsnumber").val();
	if(number==""){
		$.myMessager.info("请填写数量");
		return
	}
	if(isNaN(number)){
		$.myMessager.info("请填写数字");
		return
	}
	if(repertoryProducts==""){
		$.myMessager.info("请填写货品名称");
		return
	}
	if(number=="0"){
		$.myMessager.info("数量不能填0");
		return
	}
	if(number!="" && number!="0" && repertoryProducts!=""){
		$.ajax({
			url : '/outbound/addOutboundRepertory.jhtml',
			data : {
				"number":number,
				"outboundid":$("#id").val(),
				"repertoryProducts":repertoryProducts,
			},
			success : function() {
				var outboundbh=$("#id").val();
				$('#repertoryList').datagrid({
					url:'/outbound/showOutboundRepertoryList.jhtml?outboundbh=' + outboundbh
				});
				$.myMessager.info("添加成功！");
			}
		});
	}	
	
});

$('#addSelect').click(function(){
	$.ajax({
			url : '/outbound/saveOutbound.jhtml',
		    data : {
				"id":$("#id").val()
			},
			success : function(data) {
				$('#repertoryList').datagrid('reload');
				$("#from1").slideToggle();
				$.myMessager.info("保存成功！");
				$("#id").textbox('setValue',data.id);
				$("#lsh").textbox('setValue',data.lsh);
				$("#outboundPeople").textbox('setValue',data.createBy);
			}
		});
});
/*仓库物品下拉列表*/
$('#repertoryProducts').combobox({
	url:"/category/getrepertoryProducts.jhtml",
	valueField:'id',    
	textField:'goodsname',
	editable:false,
	onSelect:function(record){
		var goodsId=$('#repertoryProducts').combobox('getValue');		
		var node = document.getElementById("xiangqing").style.display;
		if(node!=""){
			$("#xiangqing").slideToggle();
		}
		$.ajax({
			url : '/outbound/selectGoodsDetails.jhtml',
			data : {
				"goodsId" : goodsId
			},
			success : function(data) {
				document.getElementById("goodsname").innerHTML=data.goodsname;
				document.getElementById("specifications_name").innerText=data.specifications_name;
				document.getElementById("category_name").innerText=data.category_name;
				document.getElementById("varieties_name").innerText=data.varieties_name;
				document.getElementById("goodscount").innerText=data.goodscount;
			}
		});
	}
});

/* 根据id删除 */
function out(id) {
	$.myMessager.confirm("提示！", "确实要删除吗 ？", function(r) {
		if (r) {
			$.ajax({
				url : '/outbound/deleteOut.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#repertoryList').datagrid('reload',{
						"outboundbh" :$("#id").val()
					});
					$.myMessager.info("删除成功！");
				}
			});
		}
	});
}
$('#exportOutBound').click(function(){
	var goodsId=$("#id").val();
	if(goodsId==null||goodsId==""||goodsId=="null"){
		$.myMessager.info("请先生成出库单");
		return
	}
	var targetForm = document.forms[ "aaa" ];
	targetForm.action ='/export/exportOutBound.jhtml'; 
	targetForm.submit();  
});
</script>


</body>
</html>