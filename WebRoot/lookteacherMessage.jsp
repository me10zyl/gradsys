<%@page import="com.graduationsystem.db.subject.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.graduationsystem.db.teacher.Teacher"%>
<%@page import="com.graduationsystem.db.teacher.TeacherDAO"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
	border: 2px solid royalblue;
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
</style>
</head>

<body>
<%@include file="header.jsp" %>
	<%
		Teacher teacher = (Teacher) request.getAttribute("teacher");
		TeacherDAO teacherDAO = new TeacherDAO();
		ArrayList<Subject> arr_subject = teacherDAO.getSubjectsByTeacherId(teacher.getTeacher_id());
	%>
	<div id="Bodycontainer">
		<div id="Curpage">
			<a class="Curpagea"><s:text name='profile.current.position'></s:text></a> <a href="<%=request.getContextPath()%>/index"
				style="text-decoration:none; color:#999;"> <s:text name='profile.profile'></s:text> </a>
			</li> <a class="Curpagea">><s:text name='profile.teacher.profile'></s:text></a>
		</div>
		<div id="showMessageBox">
			<div class="personMessageLogo"><s:text name='profile.profile'></s:text></div>
			<table class="showMessageTab">
				<tr>
					<td align="right"><s:text name='profile.job.number'></s:text></td>
					<td align="left"><%=teacher.getTeacher_num()%></td>
				</tr>
				<tr>
					<td align="right"><s:text name='profile.name'></s:text></td>
					<td align="left"><%=teacher.getTeacher_name()%></td>
				</tr>
				<tr>
					<td align="right"><s:text name='profile.gender'></s:text></td>
					<td align="left"><%=teacher.getTeacher_gender()%></td>
				</tr>
				<tr>
					<td align="right"><s:text name='profile.tel'></s:text></td>
					<td align="left"><%=teacher.getTeacher_telephone()%></td>
				</tr>
				<tr>
					<td align="right"><s:text name='profile.teacher.subject.responsibility'></s:text></td>
					<td align="left">
						<%
							for (Subject subject : arr_subject) {
								out.print("<a href='" + request.getContextPath() + "/subject/seeSubjectDetail?subject_id=" + subject.getSubject_id() + "'>" + subject.getSubject_title() + "</a><br>");
							}
						%>
					</td>
				</tr>
			</table>

		</div>

	</div>
<%@include file="footer.jsp"%>
</body>
</html>
