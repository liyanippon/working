<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="<%=request.getContextPath() %>/common/style/admin.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/date_picker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/common/css/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/date.js" charset="utf-8"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/i18n/jquery-ui-timepicker-addon-i18n.js"></script>

<script type="text/javascript" src="<%=request.getContextPath() %>/common/js/jquery-ui-timepicker-zh-CN.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>测试页</title>
<style>

</style>
</head>
<body>
<center>
        <h2>测试页</h2>
        <img alt="" src="<%=request.getContextPath() %>/common/style/images/head.gif">
        <dl>
                <dt>性别</dt>
                <dd>
                	<input name="sex" type="radio" checked="checked" value="man" />男<input name="sex" type="radio" value="women" />女
				</dd>
              </dl>
    </center>
</body>
</html>