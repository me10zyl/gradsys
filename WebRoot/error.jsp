<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
body {
	padding: 0;
	height: 800px;
	width: 790px;
	left: 275px;
	margin: 0 auto;
	height: 800px;
}

.err {
	color: red;
}
</style>
</head>
<body>
<%@include file="header.jsp" %>
	<div>
		<h1>囧,服务器出错了...</h1>
		你遭遇了<span class="err"> <s:property value="exception.message" /></span>错误...
	</div>
	<div><h2>请稍后再试</h2></div>
	<s:debug></s:debug>
<%@include file="footer.jsp"%>
</body>
</html>