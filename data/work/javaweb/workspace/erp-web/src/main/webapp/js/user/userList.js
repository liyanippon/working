//<script type="text/javascript">
var mainDivHeight = $("#mainDiv").height();
var queryDivHeight = $("#queryDiv").height();

$("#dataDiv").height(mainDivHeight - queryDivHeight);

$('#createtime1').datebox({
	required : true
});

$('#userList').datagrid({
	url : '/user/loadUserPageData.jhtml',
	fitColumns : true,
	singleSelect : true,
	rownumbers : true,
	pagination : true,
	striped : true,
	queryForm : 'queryUser',
	columns : [ [ {
		field : 'userDetail。userName',
		title : '账号',
		width : 100,
		formatter: function(value,row) {
			return row.userDetail.userName;
		}
	},{
		field : 'userDetail.nickName',
		title : '姓名',
		width : 100,
		formatter: function(value,row) {
			return row.userDetail.nickName;
		}

	},  {
		field : 'userDetail.sex',
		title : '性别',
		width : 100,
		formatter: function(value,row) {
			return row.userDetail.sex.label;
		}
	}, {
		field : 'mail',
		title : '邮箱',
		width : 100,
		formatter: function(value,row) {
			return row.userDetail.mail;
		}
	}, {
		field : 'tel',
		title : '电话',
		width : 100,
		formatter: function(value,row) {
			return row.userDetail.tel;
		}
	}, {
		field : 'createTime',
		title : '创建时间',
		width : 100,
		formatter : function(val, row, index) {
			var dateformate = new Date(row.userDetail.createTime);
			return dateformate.toLocaleString();
		}
	}, {
		field : 'locked',
		title : '状态',
		width : 100,
		formatter : function(val, row, index) {
			return row.userDetail.locked.label;
		}
	}, {
		field : 'operate',
		title : '操作',
		width : 100,
		formatter : formate
	} ] ]
});

/* 创建时间日期格式转化 */
function datetime(val, row, index) {
	var dateformate = new Date(val);
	return dateformate.toLocaleString();
};

/* 操作 */
function formate(val, row, index) {
	var id = row.userDetail.userName;
	var locked = row.userDetail.locked.code;
	if (locked == "008001") {
		var str = '<span><a onclick="details(\'' + id + '\')" href="#" >详情</a></span>  '
			+ '<span><a onclick="updaterow(\'' + id + '\')" href="#">修改</a></span>  ' 
			+ '<span><a onclick="drop(\''+ id + '\')" href="#">删除</a></span> '
			+ '<span><a onclick="unlock(\'' + id + '\')" href="#">解锁 </a></span>';
	} else {
		var str = '<span><a onclick="details(\'' + id + '\')" href="#" >详情</a></span>  '
			+ '<span><a onclick="updaterow(\'' + id + '\')" href="#">修改</a></span>  ' 
			+ '<span><a onclick="drop(\''+ id + '\')" href="#">删除</a></span> '
			+ '<span><a onclick="lock(\'' + id + '\')" href="#">锁定 </a></span>';
	}
	return str;
}

/* 根据id删除用户 */
function drop(id) {
	$.myMessager.confirm("提示！", "确实要删除该用户么！", function(r) {
		if (r) {
			$.ajax({
				url : '/user/deleteUserPageData.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#userList').datagrid('reload');
					$.myMessager.info("删除用户成功！");
				}
			});
		}
	});
}

/* 修改用户信息 */
function updaterow(id) {
			$('#workspace').panel('refresh',
			'/user/addUserJsp.jhtml?userId=' + id);
}

/* 用户详情 */
function details(id) {
	$("#detail").dialog({
		href : '/user/showUserDetail.jhtml?id=' + id,
		title : '用户详细信息',
		width : 400,
		height : 320,
		closed : false,
		model : true,
		collapsible : true,
		shadow : true,
		buttons : [ {
			text : '退出',
			handler : function() {
				$("#detail").dialog('close');
			}

		} ]
	});
}

/* 模糊查询用户姓名 */
$('#btnSelect').linkbutton({
	onClick : function() {
		var name = $("#txtStaffName").textbox("getValue");
		var nickName = $("#txtStaffnickName").textbox("getValue");
		var sex = $("#txtStaffsex").textbox("getValue");
		var mail = $("#txtStaffmail").textbox("getValue");
		var tel = $("#txtStafftel").textbox("getValue");
		$('#userList').datagrid('load', {
			userName : name,
			nickName : nickName,
			sex : sex,
			mail : mail,
			tel : tel
		});
	}
});

/* 重置 */
$('#btnReset').linkbutton({
	onClick : function() {
		$("#queryUser").form("reset");
		$("#userList").datagrid('load', {});
	}

});
/*锁定*/
function lock(id) {
	$.myMessager.confirm("提示！", "确实要锁定该用户么！", function(r) {
		if (r) {
			$.ajax({
				url : '/user/lockUser.jhtml',
				data : {
					"id" : id,
				},
				success : function() {
					$('#userList').datagrid('reload');
					$.myMessager.info("锁定用户成功");
				}
			});
		}
	});
}
function unlock(id) {
	$.ajax({
		url : '/user/unLock.jhtml',
		data : {
			"id" : id,
		},
		success : function() {
			$('#userList').datagrid('reload');
			$.myMessager.info("解锁成功");
		}
	});
}

/*性别下拉框*/
$('#txtStaffsex').combobox({
	url:"/user/getSexCodePageData.jhtml",
	valueField:'code',    
	textField:'label'
});
