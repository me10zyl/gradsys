<%@page import="com.graduationsystem.db.subject.SubjectDAO"%>
<%@page import="com.graduationsystem.db.teacher.Teacher"%>
<%@page import="com.graduationsystem.db.subject.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.graduationsystem.db.teacher.TeacherDAO"%>
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

.pers {
	width: 700px;
	height: 50px;
	border: 1px solid forestgreen;
}

#choiceTopicBox {
	width: 700px;
	height: 500px;
	border: 1px solid royalblue;
	float: left;
	margin: 10px 30px;
}

#choiceTopicBox p {
	
}

.choiceLogo {
	width: 100%;
	height: 30px;
	background-color: royalblue;
	text-align: center;
	font-size: 20px;
	color: white;
}

.choiceTab {
	width: 600px;
	border: 1px solid black;
	text-align: center;
	border-collapse: collapse;
	margin-left: 50px;
	margin-top: 10px
}

.choiceTab tr,td,th {
	padding: 5px;
	border: 1px solid black;
}

.choiceTab a {
	text-decoration: none;
	color: darkolivegreen;
}

.choiceTab a:hover {
	color: darkorange;
}
</style>
</head>

<body>
	<div id="Bodycontainer">
		<div id="Curpage">
			<a class="Curpagea">当前位置：</a> <a
				href="<%=request.getContextPath()%>/index_.jsp"
				style="text-decoration:none; color:#999;" target="mainFrame"> 首
				页 </a>
			</li> <a class="Curpagea">>选题</a>
		</div>
		<div id="choiceTopicBox">
			<div class="choiceLogo">选 题</div>
			<div class="pers">
				<%
					SubjectDAO subjectDAO = new SubjectDAO();
					int page_ = (Integer) request.getAttribute("page");
					int pagecount = (Integer) request.getAttribute("pagecount");
					ArrayList<Subject> arr_subject = (ArrayList<Subject>) request.getAttribute("arr_subject");
					Student student = (Student) session.getAttribute("student");
				%>
				<table align="center">
					<tr>
						<th>学号：<%=student.getStudent_num()%></th>
						<th>姓名:<%=student.getStudent_name()%></th>
						<th>专业：<%=student.getStudent_major()%></th>
						<th>选课：<%=student.getSubject_id() > 0 ? subjectDAO.getById(student.getSubject_id()).getSubject_title() : "无"%></th>
					</tr>
				</table>
			</div>
			<form action="<%=request.getContextPath()%>/Subject/StudentChoose">
				<table class="choiceTab">
					<tr bgcolor="#7fffd4" align="center">
						<th>序号</th>
						<th>题 目</th>
						<th>负责老师</th>
						<th>选择</th>
					</tr>
					<%
						for (Subject subject : arr_subject) {
					%>
					<tr>
						<td align="center"><%=subject.getSubject_id()%></td>
						<td align="center"><a
							href="<%=request.getContextPath()%>/Subject/SeeSubjectDetail?subject_id=<%=subject.getSubject_id()%>"
							title="点击查看详情"><%=subject.getSubject_title()%></a></td>
						<%
							ArrayList<Teacher> arr_teacher = subjectDAO.getDutyTeachersBySubjectId(subject.getSubject_id());
						%>
						<td align="center">
							<%
								for (int i = 0; i < arr_teacher.size() - 1; i++) {
										Teacher teacher = arr_teacher.get(i);
										out.print("<a href='" + request.getContextPath() + "/User/SeeOtherTeacher?teacher_id=" + teacher.getTeacher_id() + "' title='点击查看详情'>");
										out.print(teacher.getTeacher_name() + ",");
										out.print("</a>");
									}
									if (arr_teacher.size() - 1 >= 0) {
										out.print("<a href='" + request.getContextPath() + "/User/SeeOtherTeacher?teacher_id=" + arr_teacher.get(arr_teacher.size() - 1).getTeacher_id() + "' title='点击查看详情'>");
										out.print(arr_teacher.get(arr_teacher.size() - 1).getTeacher_name());
										out.print("</a>");
									}
							%> </a></td>
						<td align="center"><input type="radio" name="subject_id"
							value="<%=subject.getSubject_id()%>"<%=(student.getSubject_id() == subject.getSubject_id()) ? "checked='checked'" : ""%>" >
						</td>
					</tr>
					<%
						}
					%>
				</table>
				<p style="margin-left: 280px;font-size: 12px;color: blue">
					<%
						if (page_ != 1) {
					%>
					<a
						href="<%=request.getContextPath()%>/Subject/SeeSubject?page=<%=page_ - 1%>">上一页
					</a>
					<%
						}
					%>第
					<%
						for (int i = 1; i <= pagecount; i++) {
							if (i == page_) {
								out.println("<b>" + i + "</b>");
							} else {
								out.println("<a href='" + request.getContextPath() + "/Subject/SeeSubject?page=" + i + "'>" + i + "</a> ");
							}
						}
					%>页
					<%
						if (page_ != pagecount) {
					%>
					<a
						href="<%=request.getContextPath()%>/Subject/SeeSubject?page=<%=page_ + 1%>">下一页</a>
					<%
						}
					%>
				</p>

				<p style="margin-left:450px;color: red;font-size: 12px">温馨提示：每个人只能选择一个题目！</p>
				<p style="margin-left:600px">
					<input type="hidden" value="<%=page_%>" name="page">
					<input type="submit" value="提 交">
			</form>
			</p>
		</div>

	</div>
</body>
</html>
