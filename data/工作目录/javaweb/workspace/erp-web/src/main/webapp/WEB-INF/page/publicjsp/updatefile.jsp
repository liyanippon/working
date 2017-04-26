<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传附件</title>
</head>
<body>
    <!-- 引入此页面时页面上需要有一个 <input>标签 id为id value为信息主键 -->
	<form id="bbb" enctype="multipart/form-data" method="post" >
	         上传文件：<input type="file" name="file1"><br/>
	           <input id="updateFile" type="submit" value="提交">
	</form>
	<script type="text/javascript">
		$('#bbb').submit(function(){
			//利用ifame刷新大法
			var iframe = document.createElement("iframe");
	        iframe.width = 0;
	        iframe.height = 0;
	        iframe.border = 0;
	        iframe.name = "form-iframe";
	        iframe.id = "form-iframe";
	        iframe.setAttribute("style", "width:0;height:0;border:none");
	        //放到document
	        document.getElementById("bbb").appendChild(iframe);
	        document.getElementById("bbb").target = "form-iframe";
	        iframe.onload = function(){         
	            //删掉iframe
	            setTimeout(function(){
	                var _frame = document.getElementById("form-iframe");
	                _frame.parentNode.removeChild(_frame);
	            }, 100);
	        }
	        //先走submit,上传附件
			var goodsId=$("#id").val();//此为信息主键标示
			if(goodsId==null||goodsId==""||goodsId=="null"){
				$.myMessager.info("请先生成出库单");
				return
			}
			var targetForm = document.forms[ "bbb" ];
			targetForm.action ='/export/updateFile.jhtml?goodsId='+goodsId;
			targetForm.submit(); 
		});
	</script>
</body>
</html>