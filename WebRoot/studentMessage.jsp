<%@page import="com.graduationsystem.db.student.StudentDAO"%>
<%@page import="com.graduationsystem.db.subject.Subject"%>
<%@page import="com.graduationsystem.db.subject.SubjectDAO"%>
<%@page import="com.graduationsystem.db.student.Student"%>
<%@page contentType="text/html; charset=UTF-8"%>
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

#nav {
	height: 30px;
	width: 799px;
	background-color: #0187c4;
	border: 1px solid #d2d6d8;
}

#nav ul {
	list-style: none;
	margin-top: 2px;
}

#nav ul li {
	margin-left: 50px;
	float: left;
	width: 90px;
	line-height: 25px;
	text-align: center;
	padding: 0 10px;
}

#nav a {
	display: block;
	color: white;
	text-decoration: none;
	padding: 0 10px;
}

#nav a:hover {
	background-color: darkseagreen;
}

#nav  ul li ul {
	top: 120px;
	display: none;
	background-color: indianred;
}

#nav  ul li ul li {
	float: none;
	background-color: cornflowerblue;
	margin-left: -40px;
	padding: 0 0;
}

#nav ul li:hover ul {
	display: block;
}

#nav ul li ul li a:hover {
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
</style>
</head>

<body>
	<%
		SubjectDAO subjectDAO = new SubjectDAO();
		StudentDAO studentDAO = new StudentDAO();
		Student student = (Student) session.getAttribute("student");
		Subject subject = subjectDAO.getById(student.getSubject_id());
	%>
	<div id="Bodycontainer">
		<div id="Curpage">
			<a class="Curpagea">当前位置：</a> <a href="index_.jsp"
				style="text-decoration:none; color:#999;" target="mainFrame"> 首
				页 </a>
			</li> <a class="Curpagea">>信息管理</a>
		</div>
		<div id="showMessageBox">
			<div class="personMessageLogo">个人信息</div>
			<form action="<%=request.getContextPath()%>/user/modify"
				method="post">
				<input type="hidden" name="student_id"
					value="<%=student.getStudent_id()%>">
				<table class="showMessageTab">
					<tr>
						<td align="right">学号：</td>
						<td align="left"><input type="text"
							value="<%=student.getStudent_num()%>" name="student_num">
						</td>
					</tr>
					<tr>
						<td align="right">姓名：</td>
						<td align="left"><input type="text"
							value="<%=student.getStudent_name()%>" name="student_name">
						</td>
					</tr>
					<tr>
						<td align="right">性别：</td>
						<td align="left"><input type="text"
							value="<%=student.getStudent_gender()%>" name="student_gender">
						</td>
					</tr>
					<tr>
						<td align="right">年级：</td>
						<td align="left"><input type="text"
							value="<%=student.getStudent_grade()%>" name="student_grade">
						</td>
					</tr>
					<tr>
						<td align="right">专业：</td>
						<td align="left"><input type="text"
							value="<%=student.getStudent_major()%>" name="student_major">
						</td>
					</tr>
					<tr>
						<td align="right">电话：</td>
						<td align="left"><input type="text"
							value="<%=student.getStudent_telphone()%>"
							name="student_telphone"></td>
					</tr>
					<tr>
						<td align="right">密码：</td>
						<td align="left"><input type="password"
							value="<%=student.getStudent_password()%>"
							name="student_password"></td>
					</tr>
					<tr>
						<td align="right">选题情况：</td>
						<td align="left">
							<%
								if (subject != null) {
									out.println("<a href='" + request.getContextPath() + "/subject/seeSubjectDetail?subject_id=" + subject.getSubject_id() + "'>" + subject.getSubject_title() + "</a>");
								} else {
									out.println("无");
								}
							%>
						</td>
					</tr>
				</table>
				<table style="margin: 10px 500px">
					<tr>
						<td><input type="submit" value="修改"
							style="background-color:#0066ff"></td>
					</tr>
				</table>
			</form>
			<!--<p style="margin: 10px 500px"><input type="button" value="修改"> <input type="button" value="保存"></p>-->

		</div>

	</div>
</body>
</html>
