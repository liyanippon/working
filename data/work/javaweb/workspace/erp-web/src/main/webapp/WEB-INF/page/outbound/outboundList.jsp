<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>出库单列表</title>
</head>
<body>
<div style="display: none;">
<input id="hiddenid" name="hiddenid" class="easyui-textbox" style="width: 400px; height: 25px;"/>
</div>
<div id="dd"></div>
		<div id="tb" style="padding: 0px; width: 100%">
		<a id="btnSelect" class="easyui-linkbutton" style="width: 100px" data-options="plain:true,iconCls:'icon-search'">查询</a>
		<a id="btnReset" style="width: 100px"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">重置</a>
		</div>
		<div id="btnPanel" class="easyui-panel" style="width: 100%; vertical-align: middle; padding-left: 6px; padding-bottom: 3px; padding-top: 13px;">
			<form id="form1">
			<div style="float:left; width: 30%; height:35px;">
			<label for="name" style="margin-left: 5px; display:inline-block; width:60px;">出库编号:</label> 
			<input id="txtLsh" class="easyui-textbox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
			<div style="float:left; width: 30%; height:35px;">
			<label for="name" style="margin-left: 5px; display:inline-block; width:60px;">创建人:</label> 
			<input id="txtType" class="easyui-combobox" style="line-height: 25px; height: 25px; border: 1px solid #ccc">
			</div>
			<a id="btnOut" style="width: 150px;float: right;"  class="easyui-linkbutton" onclick="abc();">出库</a>
			</form>
		</div>

<!-- 提交审批 -->
<div id="shenpi" style="display: none;">
<form id="a">
<a id="tongguo" style="width: 50px"  class="easyui-linkbutton" onclick="tongguo();">通过</a>
<a id="bohui" style="width: 50px"  class="easyui-linkbutton" onclick="bohui();">驳回</a>
</form>
</div>

<!-- 审批 -->

<div id="shenpi1" style="display: none;">
<form id="b">
<div>
<span>审批人:</span>
<input id="spr" name="shName" class="easyui-combobox" style="width: 300px; height: 25px;"/>
</div>
<a id="querenabc" style="width: 50px"  class="easyui-linkbutton" onclick="submit();">确认</a>
<a id="quxiaoabc" style="width: 50px"  class="easyui-linkbutton" onclick="quxiao();">取消</a>
</form>
</div>




<table id="outboundList" class="easyui-datagrid" style="width:100%;"
		data-options="url:'/outbound/loadOutboundPageData.jhtml',fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true,queryForm:'form1'">
		<thead>
			<tr>
				<th data-options="field:'lsh',width:70,align:'center'">编码</th>
				<th data-options="field:'createBy',width:70,align:'center'">创建人</th>
				<th data-options="field:'createTime',width:70,align:'center',formatter:time">创建时间</th>
				<th data-options="field:'outboundPeople',width:70,align:'center'">审批人</th>
				<th data-options="field:'stuChinese',width:70,align:'center'">状态</th>
				<th data-options="field:'operate',width:100,align:'center',formatter:formatOper">操作</th>
			</tr>
		</thead>
</table>



<script type="text/javascript">
/*选择审核人下拉框*/
$("#spr").combotree({    
	url: '/org/getOrgTreeList.jhtml',     
	required: true
});
$("#txtType").combotree({    
	url: '/org/getOrgTreeList.jhtml',     
	required: true
});
//提交页面
function submit(){
	var id = $("#hiddenid").val();
	var userTree = $('#spr').combotree('tree');	// 获取人员ID
	if(userTree.tree('getSelected')==null){
		return $.myMessager.show("请选择提交人");
	}else{
		var userId = userTree.tree('getSelected').id;
    	$.myAjax({
    		url : '/outbound/addOutboundPeople.jhtml',
    		data : {
    			"id" :id,
    			"outboundPeople":userId
    		},
    		success : function(data) {
    				$("#shenpi1").dialog("close");
    				$.myMessager.show("保存成功");
    				$('#outboundList').datagrid('reload'); 
    		}
    	});
	}		
} 

//驳回
function bohui(){
	var id = $("#hiddenid").val();
	$.myAjax({
		url : '/outbound/backOutboundPeople.jhtml',
		data : {
			"id" :id
		},
		success : function(data) {
				    $.myMessager.show("驳回成功");
				  //刷新页面
					$('#outboundList').datagrid('load');
					$("#shenpi").dialog("close");
		}
	});	
} 

/* 操作 */
function formatOper(val, row, index) {
	var id=row.id;
	var stu=row.stu;//当前数据状态
	var r;
	if(stu=="016001"){//新登记
		r = '<a href="#" onclick="detail(\''+id+'\')">详情 </a>    '
        +   '<a href="#", onclick="updates(\''+id+'\')">修改</a>   '
        +   '<a href="#", onclick="outShenpi(\''+id+'\')">提交审批</a>   '
		+   '<a href="#", onclick="deletes(\''+id+'\')">删除</a>';
	}else if(stu=="016002"){//待审批
		r = '<a href="#" onclick="detail(\''+id+'\')">详情 </a>    '
        +   '<a href="#", onclick="outboundShenpi(\''+id+'\')">审批</a>   ';
	}else if(stu=="016003"){//已通过
		r = '<a href="#" onclick="detail(\''+id+'\')">详情 </a>    ';
	}else if(stu=="016004"){//已驳回
		r = '<a href="#" onclick="detail(\''+id+'\')">详情 </a>    '
        +   '<a href="#", onclick="updates(\''+id+'\')">修改</a>   '
        +   '<a href="#", onclick="outShenpi(\''+id+'\')">提交审批</a>   '
		+   '<a href="#", onclick="deletes(\''+id+'\')">删除</a>';
	}
	return r; 
		}
		
/* 时间 */
function time(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
}
	
/* 模糊查询 */
$('#btnSelect').linkbutton({
	onClick : function() {
		var BHname = $("#txtLsh").textbox("getValue");
		var CJRname = $("#txtType").combobox("getValue");
		$('#outboundList').datagrid('load', {
			"lsh" : BHname,
			"createBy" : CJRname,
		});
	}
});

/* 重置 */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#form1").form("reset");
		$("#outboundList").datagrid('load', {});
	}

});

	/*出库*/	
	function abc(){
		$('#workspace').panel('refresh',
				'/outbound/showRepertoryList.jhtml');
	}	
	
	/* 详情 */
	function detail(id) {
				$('#workspace').panel('refresh',
				'/outbound/showoutboundDetail.jhtml?outboundId=' + id+'&&operated=1');
	}
	
	/* 修改 */
	function updates(id) {
				$('#workspace').panel('refresh',
				'/outbound/showoutboundDetail.jhtml?outboundId=' + id+"&&operated=2");
	}
	
	/* 根据id删除 */
	function deletes(id) {
		$.myMessager.confirm("提示！", "确实要删除出库单吗 ？", function(r) {
			if (r) {
				$.ajax({
					url : '/outbound/deleteOutbound.jhtml',
					data : {
						"id" : id,
					},
					success : function() {
						$('#outboundList').datagrid('reload');
						$.myMessager.info("删除成功！");
					}
				});
			}
		});
	}
	
	/* 根据id提交出库单 */
	function outShenpi(id) {
		document.getElementById("hiddenid").value =id;
		$('#shenpi1').window({
            width: 600,
            height: 400,
            modal: true,
            title: "提交",
        });
		
	}
	
	/* 根据id审核出库单 */
	function outboundShenpi(id) {
		document.getElementById("hiddenid").value =id;
		$('#shenpi').window({
            width: 600,
            height: 400,
            modal: true,
            title: "审批",
        });
		
	}
	
	/* 审批时间 */
	$('#outboundTime').datebox({    
    required:true   
    });  

	/* 提交 */
// 	$('#queren').click(function(){
// 			$.ajax({
// 				url : '/outbound/addOutboundPeople.jhtml',
// 				data : {
// 					"id":$("#hiddenid").val(),
// 				},
// 				success : function() {
// 					$("#shenpi1").dialog("close");
// 					$('#outboundList').datagrid('reload');
// 					$.myMessager.info("提交成功！");
// 				}
// 			});
		
// 	});
 	/* 通过 */
	$('#tongguo').click(function(){
		var id= $("#hiddenid").val();
			$.ajax({
				url : '/outbound/gogoOutboundBills.jhtml',
				data : {
					"id":id,
				},
				success : function(data) {
					if(data.result!=""){
						$.myMessager.info(data.result);
					}else{
						$("#shenpi").dialog("close");
						$('#outboundList').datagrid('reload');
						$.myMessager.info("通过审批！");
					}					
				}
			});
		
	}); 
	/* 驳回 */
	$('#bohui').click(function(){
		$.ajax({
				url : '/outbound/backOutboundPeople.jhtml',
				data : {
					"id":id,
				},
				success : function() {
					$("#shenpi").dialog("close");
					$('#outboundList').datagrid('reload');
					$.myMessager.info("驳回成功！");
				}
			});
		
	});
	
	
	/* 关闭 */
	$('#quxiaoabc').click(function(){
		$("#shenpi1").dialog("close");  
	});
	
	
	
</script>

</body>
</html>