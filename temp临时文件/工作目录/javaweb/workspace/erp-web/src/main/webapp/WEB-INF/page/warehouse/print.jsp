<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>入库单打印</title>
    <style type="text/css">
body{background:white;margin:0px;padding:0px;font-size:13px;text-align:left;}
.pb{font-size:13px;border-collapse:collapse;}
.pb th{font-weight:bold;text-align:center;border:1px solid #333333;padding:2px;}
.pb td{border:1px solid #333333;padding:2px;}
　　</style>
</head>
<body>
<script type="text/javascript" src="/js/print/print.js"></script>
入库单编号：
<input id="prwarehousenum" name="prwarehousenum" type = "text" style="width:100px"/>

    <script type="text/javascript">
        var prwarehousenum = '<%=request.getParameter("warehousenum")%>';
        document.write(window.dialogArguments);
        document.getElementById('prwarehousenum').value=prwarehousenum;
        window.print();
    </script>
</body>
</html>