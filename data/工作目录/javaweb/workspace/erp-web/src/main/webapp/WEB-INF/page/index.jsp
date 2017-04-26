<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>大连翼锋软件有限公司</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="/css/menu.css">
<script type="text/javascript" src="/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.accordionTree.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.myMessager.js"></script>
<script type="text/javascript" src="/js/plugins/jquery.myAjax.js"></script>

</head>
<body class="easyui-layout" style="100%">
	<div data-options="region:'north'" style="height: 60px;">
		<div style="font-size:38px;width: 500px;">大连翼峰软件有限公司</div>
		<div style="display: inline;float: right;padding-right: 10px;margin-top:-15px;font-size:15px;width: 500px;">
			<div style="float:right;"><a href="/login/logout.jhtml">退出</a>&nbsp;</div>
			<div style="float:right;"><a href="http://192.168.1.103:8003/index.jhtml?sessionid=<%=session.getAttribute("ds_session_id") %>">系统管理</a>&nbsp;</div>
			<div style="float:right;" id="changePassword"><a href="#">修改密码</a>&nbsp;|&nbsp;</div> 
			<div style="float:right;">wy's page 欢迎你:<a href="http://192.168.1.27:8081/index.jhtml?sessionid=<%=session.getAttribute("ds_session_id")%>"><span id="nickName"></span></a>&nbsp;|&nbsp;</div> 
			<div style="float:right;"><fmt:formatDate value="<%=new java.util.Date() %>" type="both" dateStyle="long" pattern="yyyy年M月d日 E" />&nbsp;&nbsp;&nbsp;&nbsp;</div>
		</div>
	</div>
	<div data-options="region:'west',split:true" title="" style="width: 16%; height: 90%;">
		<div id="menu" style="height:100%;"></div>
	</div>
	<div id="workspace" data-options="region:'center',title:''">
	</div>
	

<script type="text/javascript">

		$(function() {
			$("#menu").accordionTree({
				url : "/system/getMenuByUser.jhtml?menuType=005002"
			});
			
			$.ajax({
				url:"/system/getLoginUser.jhtml",
				success:function(result){
					if(result.userDetail.nickName == undefined) {
						$("#nickName").html(result.userName);
					} else {
						$("#nickName").html(result.userDetail.nickName);
					}
				}
			});
			
			$("#changePassword").click(function(){
				$.myAjax({
					url : "/system/getMenuByUser.jhtml",
					success:function(result){
						alert("123");
					}
				});
			});
					
		});
		
		
</script>
</body>
</html>