<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户和公司部门关联</title>
</head>
<body>
	<!--  -->
    <div id="tt" class="easyui-tabs" > 
    <div title="修改用户单位及部门" style="padding:20px;display:none;">   
           
       
      
		<form id="formcre" method="post" class="easyui-form" style="width: 300px; height: 350px; padding: 10px 20px;">
		     <div style="display: none">
				<label>ID </label> <input name="id" class="easyui-textbox"
					id="id" />
			</div>
			<div>
			<label for="name">姓名：</label> 
			<ul id="selectUserName" class="easyui-combotree" style="width:200px;"></ul> 
            </div>
			<div>
				<label for="name">公司：</label> 
				<input class="easyui-validatebox" id="orgName" type="text" name="orgName"  style="width:200px;"/>
			</div>
			<div>
				<label for="name">部门：</label> 
				<ul class="easyui-combotree" id="deptName" type="text" name="deptName"  style="width:200px;"></ul>
			</div>
		</form>
		
		<a id="crtCode" class="easyui-linkbutton" style="width: 60px"onclick="saveOrgUser();">保存</a> 
		<a id="btnReset" style="width: 50px" href="#" class="easyui-linkbutton">重置</a>
		</div>
	</div>
	<script type="text/javascript">
	 //公司下拉列表
	 $('#orgName').combobox({    
	    url:'/org/getAllComp.jhtml', 
	    required: false,
	    valueField:'id',    
	    textField:'orgName',
	}); 
	//到人员的组织结构树
	$('#selectUserName').combotree({    
	    url: '/org/getOrgTreeList.jhtml',    
	    required: false,
	    onSelect:function(node) {
	    	var bsId =  node.bsId;
	    	
	    	//赋值 
	    	$.ajax({
				url:'/org/getOrgUserDetial.jhtml?bsId=' + bsId,
				success:function(data) {
					$('#orgName').combobox({    
					    url:'/org/getAllComp.jhtml', 
					    valueField:'id',    
					    textField:'orgName',
					    onLoadSuccess:function(){
					    $("#orgName").combobox("select",data.orgId);
					    },//设置公司名称的默认值
					    onSelect: function(rec){   
					    	//设置部门树的默认值
				            var url = '/org/getDepartTreeList.jhtml?orgId='+rec.id;    
				            $('#deptName').combotree({    
							    url:url, 
							    onLoadSuccess:function(data1){
							    	$('#deptName').combotree('setValue', data.departId);
							    }
							}); 
				            
				        }
					});
					
					
				}
			});
	    	
        }
	}); 

	
	 

	//获取部门树
	$('#deptName').combotree({    
		url: '/org/getDepartTreeList.jhtml',    
		required: false

    }); 
    //点击保存按钮
	function saveOrgUser() {
    	var isValid = $("#formcre").form('validate');
		var userTree = $('#selectUserName').combotree('tree');	// 获取人员ID
		var userId = userTree.tree('getSelected').id;
		var bsId = userTree.tree('getSelected').bsId;	//获取唯一标示Id
		var orgId = $("#orgName").combobox("getValue");
		var deptTree = $('#deptName').combotree('tree');	// 获取部门ID
		var deptId = deptTree.tree('getSelected').id;	
    	
		
		if(isValid){
			$.ajax({
				url : '/org/updateOrgUser.jhtml',
				data : {
					"userId" : userId,
					"bsId":bsId,
					"orgId" :orgId,
					"deptId" : deptId,
				},
				success : function(data) {
					if ("true"==data) {
						$.myMessager.info("修改成功");
						
				    }else {
				       $.myMessager.info("修改失败！");
				    }
				}
			});
		}
	}
	/* 重置按钮  */
	$('#btnReset').linkbutton({
		onClick : function() {
			var selectUserName = $('#selectUserName').combotree("reset");
			var orgName = $('#orgName').combobox("reset");
			var deptName = $('#deptName').combotree("reset");
		}
	});

	</script>
</body>
</html>