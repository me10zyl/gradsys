<%@page import="com.graduationsystem.db.notice.Notice"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@page import="com.graduationsystem.db.notice.NoticeDAO"%>
<%@page import="com.graduationsystem.db.student.StudentDAO"%>
<%@page import="com.graduationsystem.db.subject.Subject"%>
<%@page import="com.graduationsystem.db.subject.SubjectDAO"%>
<%@page import="com.graduationsystem.db.student.Student"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<style>
body {
	margin: 0;
	padding: 0;
}

#header {
	height: 120px;
	width: 800px;
	background-color: #0187c4;
	margin: 0 auto;
}

#logo {
	margin-left: 90px;
	width: 320px;
	height: 90px;
}

#nav-nouse {
	height: 30px;
	width: 799px;
	background-color: #0187c4;
	border: 1px solid #d2d6d8;
}

#nav-nouse ul {
	list-style: none;
	margin-top: 2px;
}

#nav-nouse ul li {
	margin-left: 50px;
	float: left;
	width: 90px;
	line-height: 25px;
	text-align: center;
	padding: 0 10px;
}

#nav-nouse a {
	display: block;
	color: white;
	text-decoration: none;
	padding: 0 10px;
}

#nav-nouse a:hover {
	background-color: darkseagreen;
}

#nav-nouse  ul li ul {
	top: 120px;
	display: none;
	background-color: indianred;
}

#nav-nouse  ul li ul li {
	float: none;
	background-color: cornflowerblue;
	margin-left: -40px;
	padding: 0 0;
}

#nav-nouse ul li:hover ul {
	display: block;
}

#nav-nouse ul li ul li a:hover {
	background-color: #0066ff;
}

#Bodycontainer {
	height: 800px;
	width: 790px;
	left: 275px;
	margin: 0 auto;
}

#Curpage {
	height: 20px;
	width: 790px;
	font-size: 12px;
	margin-left: 20px;
}

.Curpagea {
	text-decoration: none;
	color: #999;
	font-size: 13px;
}

#showMessageBox {
	width: 700px;
	height: 500px;
	border: 1px solid royalblue;
	float: left;
	margin: 10px 30px;
}

.personMessageLogo {
	width: 100%;
	height: 30px;
	background-color: royalblue;
	border-bottom: 2px dotted yellow;
	text-align: center;
	font-size: 20px;
	color: white;
}

.showMessageTab {
	width: 600px;
	text-align: center;
	border-collapse: collapse;
	margin-left: 50px;
	margin-top: 20px;
	color: gray;
	font-size: 12px;
	font-weight: bold
}

.showMessageTab tr,td,th {
	padding: 10px;
}

.showMessageTab a {
	text-decoration: none;
	color: darkolivegreen;
}

.showMessageTab a:hover {
	color: darkorange;
}

p{
 
}

.title{
 font-family: 幼圆;
}
.teacher{
	font-color:gray;
	text-align: right;
	font-size: small;
}
</style>
</head>

<body>
<%@include file="header.jsp" %>
	<div id="Bodycontainer">
		<div id="Curpage">
			<a class="Curpagea"><s:text name='profile.current.position'></s:text></a> <a href="<%=request.getContextPath()%>/index"
				style="text-decoration:none; color:#999;" ><s:text name='profile.homepage'></s:text> </a>
			</li> <a class="Curpagea">><s:text name='notice.title'></s:text></a>
		</div>
	<form action="<%=request.getContextPath() %>/notice/delete" method="post">
		<input type="hidden" value="${notice.noticce_id}" name="notice_id">
		<div id="showMessageBox">
			<div class="personMessageLogo"><s:text name='notice.title'></s:text></div>
			<h2><div class="title">${notice.notice_title}</div></h2>
			<p>${notice.notice_detail}
			<div class="teacher"><s:text name='notice.teacher'></s:text>${notice_teacher}
			</div>
			<s:if test="#session.teacher != null && #request.notice_teacher_id == #session.teacher.teacher_id">
				<div>
					<input type="submit" value='<s:text name="notice.delete"></s:text>'>
				</div>
			</s:if>
		</div>
	</form>
	</div>
<%@include file="footer.jsp"%>
</body>
</html>
