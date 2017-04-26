
$('#menuList').datagrid({
	url : '/system/loadMenuPageData.jhtml',
	fitColumns : true,
	singleSelect : true,
	rownumbers : true,
	pagination : true,
	striped : true,
	queryForm : 'selectMenu',
	columns : [ [ {
		field : 'menuName',
		title : '菜单名称',
		width : 100
	},{
		field : 'url',
		title : '连接名称',
		width : 100,

	}, {
		field : 'menuType',
		title : '菜单种别',
		width : 100,
		formatter : function(value) {
			return value.label;
		}
	},{
		field : 'operate',
		title : '操作',
		width : 100,
		formatter : formate
	} ] ]
});

/*操作*/
    function formate(val,row,index){
  	  var id=row.id;
  	  var deleted=row.deleted;
  		var str = '<span><a href="#" onclick="edit(\''+id+'\')" >修改</a></span>  '
  			+'<span><a href="#" onclick="del(\''+id+'\')">删除</a></span>  ';
  			return str;
  		}
    
    /* 根据id删除菜单  */
	function del(id) {
		$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
			if (r) {
				$.ajax({
					url : '/system/deleteMenuPageData.jhtml',
					data : {
						"id" : id,
					},
					success : function() {
						$('#menuList').datagrid('reload');
						$.myMessager.info("删除用户成功！");
					}
				});
			}
		});
	}
    
	/* 修改菜单信息 */
	function edit(id) {
		$.ajax({
			url:'/system/getMenuDetail.jhtml?id=' + id,
			success:function(data) {
				$("#url1").textbox("setValue", data.url);
				$("#menuName1").textbox("setValue", data.menuName);
				$("#menuType1").combobox("setValue", data.menuType.code);
			}
			
		});
		$("#updateMenu").dialog(
				{
					width : 400,
					height : 320,
					closed : false,
					buttons : [ {
						text : '保存',
						handler : function() {
							$.ajax({
								url : '/system/updateMenu.jhtml',
								data : {
									"id" : id,
									"menuName" : $("#menuName1").val(),
									"url" : $("#url1").val(),
									"menuType" : $("#menuType1").combobox("getValue"),
								},
								success : function() {
									$.myMessager.info("修改成功");
									$("#updateMenu").dialog('close');
									$('#menuList').datagrid('load');
								}
							});
						}
					}, {
						text : '退出',
						handler : function() {
							$("#updateMenu").dialog('close');
						}
					} ]
				});
	}
	
	/* 创建菜单 */
	$("#createUser").dialog({
		title : '新建菜单 ',
		width : 400,
		height : 320,
		closed : true,
		model : true,
	});
	function crtMenu() {
		$("#createMenu").dialog({
			width : 400,
			height : 320,
			closed : false,
			buttons : [ {
				text : '保存',
				handler : function() {
					var nextData = $("#creContinue").is(":checked");
					$.ajax({
						url : '/system/addMenu.jhtml',
						data : {
							"menuName" : $("#menuname2").val(),
							"url" : $("#url2").val(),
							"parentId" : $("#parentId2").combobox("getValue"),
							"sortorder" : $("#sortorder2").val(),
							"menuType" : $("#menuType2").combobox("getValue"),
						},
						success : function() {
							$.myMessager.info("保存成功");
				            if(nextData){
				            	$("#menuList").datagrid("load");
				            	$("#form2").form('clear');
				            }else{
				            	$("#createMenu").dialog("close");
					            $("#menuList").datagrid("load");
					            $("#form2").form('clear');
				            }
						}
					});
				}
			}, {
				text : '退出',
				handler : function() {
					$("#createMenu").dialog('close');
				}
			} ]
		})
	}
	
	/* 模糊查询用户姓名 */
	$('#btnSelect').linkbutton({
		onClick : function() {
			var name = $("#txtmenuName").textbox("getValue");
			var url = $("#txtUrl").textbox("getValue");
			var menuType = $("#txtmenuType").textbox("getValue");
			$('#menuList').datagrid('load', {
				"menuName" : name,
				"url" : url,
				"menuType" : menuType,
			});
		}
	});
	
	/* 重置按钮  */
	$('#btnReset').linkbutton({
		onClick : function() {
			var name = $("#txtmenuName").textbox("reset");
			var rename = $("#txtmenuName").textbox("getValue");
			var url = $("#txtUrl").textbox("reset");
			var reurl = $("#txtUrl").textbox("getValue");
			var menuType = $("#txtmenuType").textbox("reset");
			var remenuType = $("#txtmenuType").textbox("getValue");
			var gender = $("#comboSelect1").combobox("reset");
			var state = $("#comboSelect2").combobox("reset");
			$("#menuList").datagrid('load', {
				menuName : rename,
				url : reurl,
				menuType : remenuType,
			});
		}

	});
	
	/* 菜单种别下拉框 */
	$('#menuType1').combobox({    
	    url:'/system/getMenuCodeDate.jhtml',    
	    valueField:'code',    
	    textField:'label'   
	});
	$('#txtmenuType').combobox({    
	    url:'/system/getMenuCodeDate.jhtml',    
	    valueField:'code',    
	    textField:'label'   
	});
	$('#menuType2').combobox({    
	    url:'/system/getMenuCodeDate.jhtml',    
	    valueField:'code',    
	    textField:'label'   
	});
	$('#parentId2').combobox({    
	    url:'/system/getMenuP.jhtml',    
	    valueField:'id',    
	    textField:'menuName'   
	});
	