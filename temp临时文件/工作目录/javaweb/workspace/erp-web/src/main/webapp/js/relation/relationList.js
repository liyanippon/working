
$('#relationList').datagrid({
	url : '/relation/loadRelation.jhtml',
	fitColumns : true,
	singleSelect : true,
	rownumbers : true,
	pagination : true,
	striped : true,
	queryForm : 'queryRelation',
	columns : [ [  {
		field : 'oid',
		title : '操作',
		width : 30,
		formatter : check
	},{
		field : 'userName',
		title : '账号',
		width : 100,
	},{	
		field : 'roleList',
		title : '角色',
		width : 100,
		formatter:function(value){
			var s
			for(var i = 0; i<value.length;i++) {
			$.ajax({
					url:"/role/getRoleDetail.jhtml",
					data:{id:value[i].roleId},
					dataType:"json",
					async:false,
					success:function(d){
						if(s==undefined){							
							s=d.roleName
						}else{
							s=s+","+d.roleName
						}
					},
					error:function(){
						
					}
				})
				
			}
			return s
		
		}
	}] ]
});



/*单选按钮*/
 function check(value, rowData, rowIndex){
	 return "<input type='radio' name='selectRadio' id='selectRadio' "+ rowIndex +" />";
}

 /*保存关联*/
function save(){
	var a;
	var row=$('#relationList').datagrid('getChecked');
	for(var c=0;c<row.length;c++){
		a = row[c].userName;
	}
	
	var b;
	var nodes = $('#tree').tree('getChecked');
	for(var i=0;i<nodes.length;i++) {
		b = nodes[i].id;
		$.ajax({
			type : 'post',
			url : '/relation/addRelation.jhtml',
			data : {
				"userName":a,
				"roleId" : b,
			},
			success : function(data) {
				$.myMessager.info("关联成功");
			}
		});
	}
	
	
	
}

/*清空*/
function reset(){
	var nodes = $('#tree').tree('getChecked');
	for(var i=0; i<nodes.length; i++) {
		$('#tree').tree('uncheck', nodes[i].target);
	}
}
 
 
 