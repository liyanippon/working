<!DOCTYPE html PUBLIC 
    "-//W3C//DTD XHTML 1.1 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page contentType="text/html; charset=UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>系统异常
</title>
<script type="text/javascript">

</script>
<style type="text/css">
<!--
body {
	background: #fcfdff
}
-->
</style>
</head>
<body>

	<h2>An unexpected error has occurred</h2>
	<p>
		Please report this error to your system administrator or appropriate technical support personnel. <br> Thank you for your cooperation.
	</p>
	<hr />
	<h3>Error Message</h3>
	<s:actionerror />
	<p>
		<FONT color="red"><s:property value="%{getText(exception.message)}" />
		</FONT>
	</p>
	<a href="javascript:void(0);" onclick="$('#detail-exception').show();" style="font-size:12px;">Detail Exception</a>
	<div id="detail-exception" style="display:none;">
		<s:property value="%{exception}" />
		<br />
		<s:property value="%{exception.stackTrace}" />
	</div>
	<br />
	<div id="button-box" align="center">
			<input class="formbtn" type="button" onclick="javascript:history.go(-1);" value="返回" />
	</div>
</body>
</html>

