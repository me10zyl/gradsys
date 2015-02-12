<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>毕业设计管理系统</title>
</head>

<frameset rows="120,*" cols="*" frameborder="NO" border="0"
	framespacing="0">
	<frame src="header.jsp" name="topFrame" scrolling="NO" noresize>
	<frame src="index_.jsp" name="mainFrame">
</frameset>

<noframes>
	<body>
	</body>
</noframes>

</html>
