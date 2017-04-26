<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新建项目</title>
</head>
<body>

	<div>
		<form id="projectForm" name="form" method="post" class="easyui-form">
			<div style="display: none;">
				Id<input id="id" name="id" type="text"
					value="<%=request.getParameter("projectId")%>">
			</div>
			<div>
				<span style="margin-left: 8px; display: inline-block; width: 60px;">项目名称</span>
				<input id="projectName" name="projectName" class="easyui-textbox"
					style="width: 300px; height: 25px;">
			</div>
			<div>
				<span style="margin-left: 8px; display: inline-block; width: 60px;">项目经理</span>
				<input id="projectManager" name="projectManager"
					class="easyui-combotree" style="width: 300px; height: 25px;">
			</div>

			<div>
				<span style="margin-left: 8px; display: inline-block; width: 60px;">开始时间</span>
				<input id="startTime" name="startTime" class="easyui-datebox"
					style="width: 300px; height: 25px;">
			</div>
			<div>
				<span style="margin-left: 8px; display: inline-block; width: 60px;">结束时间</span>
				<input id="endTime" name="endTime" class="easyui-datebox"
					style="width: 300px; height: 25px;">
			</div>
			<div>
				<span style="margin-left: 8px; display: inline-block; width: 60px;">项目描述</span>
				<input id="mome" name="mome" class="easyui-textbox"
					style="width: 300px; height: 25px;">
			</div>
			<div>
				<a id="creSelect" class="easyui-linkbutton" style="width: 50px">保存</a>
				<a id="btnReset" class="easyui-linkbutton" style="width: 50px">重置
				</a>
			</div>
		</form>
	<div style="display: none;" id="keng">
			<span style="margin-left: 8px; display: inline-block; width: 60px;">项目人员</span>
			<input  class="easyui-combotree" id="projectPeople"
				style="width: 300px; height: 25px;">
			<div style="display: none;">
				<input id="projectId" name="projectId" class="easyui-textbox"
					style="width: 300px; height: 25px;">
			</div>
			<a id="addSelect" class="easyui-linkbutton" style="width: 50px">添加</a>
		</div>
	</div>
<table id="projectPeopleList" class="easyui-datagrid" style="width:100%;"
		data-options="fitColumns:true,singleSelect:true,
		rownumbers:true,pagination:true,striped:true">
		<thead>
			<tr>
				<th data-options="field:'project_user',width:70,align:'center'">项目成员</th>
				<th data-options="field:'join_time',width:70,align:'center', formatter:timeformat">成员加入时间</th>
				<th data-options="field:'leave_time',width:70,align:'center', formatter:timeformat">成员离开时间</th>
				<th data-options="field:'operate',width:70,align:'center', formatter:oper">操作</th>			
			</tr>
		</thead>
</table>
			




	<script type="text/javascript">
		/*先走这个查一遍*/
		$(function() {
			var operated = <%=request.getParameter("operated")%>;
		    var projectId = $("#id").val();	
		//1是詳情
			if (operated == '2' || operated == 2) {
				document.getElementById("creSelect").style.display = "none";
				document.getElementById("btnReset").style.display = "none";
				document.getElementById("addSelect").style.display = "none";
			}
			if (operated == '1' || operated == 1) {
				$("#keng").slideToggle();
			}
			$.ajax({
				type : 'get',
				url : '/project/getProject.jhtml',
				data : {
					"id" : $("#id").val()
				},
				dataType : 'json',
				success : function(data) {
					var date=new Date();
					$("#projectName").textbox('setValue', data.projectName);
					$("#projectManager").combotree('setValue',data.projectManager);
					var startTime=data.startTime;
					var endTime=data.endTime;
					$("#startTime").datebox('setValue',timeformat(startTime));
					$("#endTime").datebox().datebox('setValue',timeformat(endTime));
					$("#mome").textbox('setValue', data.mome);
					$('#projectPeopleList').datagrid({
						url:'/project/loadProjectPeoplePageData.jhtml?projectId=' + projectId
					});
				}
			});
		});
		
		$("#projectPeople").combotree({
			url: "/org/getOrgTreeList.ajax?pId=-1",
			lines: true,
			onBeforeExpand: function(node) {
				if(node.nodeType == "1") {
					$('#projectPeople').combotree("tree").tree('options').url = "/org/getOrgTreeList.ajax?compId=" + node.id + "&pId=" + node.id;
				} else if(node.nodeType == "2") {
					$('#projectPeople').combotree("tree").tree('options').url = "/org/getDepartTreeList.ajax?pId=" + node.id;
				}
			},
			onClick: function(node){
				if(node.nodeType=="1" || node.nodeType == "2") {
					$('#projectPeople').combotree("clear");
				}
			}
		
		});
		$("#projectManager").combotree({
			url: "/org/getOrgTreeList.ajax?pId=-1",
			lines: true,
			onBeforeExpand: function(node) {
				if(node.nodeType == "1") {
					$('#projectManager').combotree("tree").tree('options').url = "/org/getOrgTreeList.ajax?compId=" + node.id + "&pId=" + node.id;
				} else if(node.nodeType == "2") {
					$('#projectManager').combotree("tree").tree('options').url = "/org/getDepartTreeList.ajax?pId=" + node.id;
				}
			},
			onClick: function(node){
				if(node.nodeType=="1" || node.nodeType == "2") {
					$('#projectManager').combotree("clear");
				}
			}
		
		});

		/* 重置 */
		$('#btnReset').linkbutton({
			onClick : function() {
				$("#projectForm").form("reset");
			}

		});
		
		/* 操作 */
		function oper(val, row, index) {
					var id = row.id;
					var deleted = row.deleted;
					var str = '<span><a href="#" onclick="del(\'' + id + '\')">删除</a></span>  ';
					return str;
				}
		
		/* 删除 */
		function del(id) {
			$.myMessager.confirm("提示！", "确实要删除该人员吗 ？", function(r) {
				if (r) {
					$.ajax({
						url : '/project/deleteProjectPeople.jhtml',
						data : {
							"id" : id,
						},
						success : function() {
							$('#projectPeopleList').datagrid('reload');
							$.myMessager.info("删除成功！");
						}
					});
				}
			});
		}

		/* 保存 */
		$("#creSelect").click(
				function() {
					$("#keng").slideToggle();
					var id = $("#id").val();
					if (id != "null") {
						$.ajax({
							type : 'post',
							url : '/project/updateProject.jhtml',
							data : {
								"id" : $("#id").val(),
								"projectName" : $("#projectName").val(),
								"projectManager" : $("#projectManager")
										.combobox("getValue"),
								"projectPeople" : $("#projectPeople").combobox(
										"getValue"),
								"startTime" : $("#startTime").datebox(
										"getValue"),
								"endTime" : $("#endTime").datebox("getValue"),
								"mome" : $("#mome").val()
							},
							success : function(data) {
								$.myMessager.info("您成功修改了项目信息~");
								// 				$("#projectId").textbox('setValue', data.id);
								$('#workspace').panel('refresh',
										'/project/showProjectList.jhtml');
							}
						});
					} else {
						$.ajax({
							type : 'post',
							url : '/project/createProject.jhtml',
							data : {
								"projectName" : $("#projectName").val(),
								"projectManager" : $("#projectManager")
										.combobox("getValue"),
								"projectPeople" : $("#projectPeople").combobox(
										"getValue"),
								"startTime" : $("#startTime").datebox(
										"getValue"),
								"endTime" : $("#endTime").datebox("getValue"),
								"mome" : $("#mome").val()
							},
							success : function(data) {
								$.myMessager.info("您成功的创建了项目记录~");
								$("#projectId").textbox('setValue', data);
							}
						});
					}
				});

		/*下拉框*/
		$('#startTime').datebox({
			required : true
		});

		$('#endTime').datebox({
			required : true
		});

		/**添加 */
		$("#addSelect").click(function() {
			if($("#projectPeople").combotree("getValue")==""){
				$.myMessager.info('请选择人员.');
   
			}else{
				$.ajax({
					type : 'post',
					url : '/project/addProjectPeople.jhtml',
					data : {
						"projectId" : $("#projectId").textbox("getValue"),
						"projectPeople" : $("#projectPeople").combotree("getValue"),
					},
					success : function(data) {
						$.myMessager.info("添加成功 ");
						var projectId = $("#projectId").val();
						$('#projectPeopleList').datagrid({
							url:'/project/loadProjectPeoplePageData.jhtml?projectId=' + projectId
						});
					}
				});
			}
		});
		function timeformat(mmsecond){
			if(mmsecond){
				var time2 = new Date(mmsecond).Format("yyyy-MM-dd"); 
			    return time2;
			}
		}
		Date.prototype.Format = function(fmt)   
		{ //author: meizz   
		  var o = {   
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "h+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度
		    "S"  : this.getMilliseconds()           //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;   
		}  
	</script>
</body>
</html>