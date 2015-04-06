<%@page import="com.graduationsystem.db.teacher.TeacherDAO"%>
<%@page import="com.graduationsystem.db.subject.SubjectDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.graduationsystem.db.teacher.Teacher"%>
<%@page import="com.graduationsystem.db.subject.Subject"%>
<%@page contentType="text/html; charset=UTF-8"%>
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

.pers {
	width: 700px;
	height: 50px;
	border: 1px solid forestgreen;
}

#choiceTopicBox {
	width: 700px;
	height: 500px;
	border: 2px solid royalblue;
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
<%@include file="header.jsp" %>

	<div id="Bodycontainer">
		<div id="Curpage">
			<a class="Curpagea"><s:text name='subject.current.position'></s:text></a> <a
				href="<%=request.getContextPath()%>/index"
				style="text-decoration:none; color:#999;"><s:text name='subject.homepage'></s:text> </a>
			</li> <a class="Curpagea">><s:text name='subject.selection'></s:text></a>
		</div>
		<%
			Teacher teacher = (Teacher) session.getAttribute("teacher");
			SubjectDAO subjectDAO = new SubjectDAO();
			TeacherDAO teacherDAO = new TeacherDAO();
			ArrayList<Subject> arr_duty_subject = teacherDAO.getSubjectsByTeacherId(teacher.getTeacher_id());
			ArrayList<Subject> arr_subject = (ArrayList<Subject>) request.getAttribute("arr_subject");
			int page_ = (Integer) request.getAttribute("page");
			int pagecount = (Integer) request.getAttribute("pagecount");
		%>
		<div id="choiceTopicBox">
			<div class="choiceLogo"><s:text name='subject.subject.selection.space'></s:text></div>
			<div class="pers">
				<table align="center">
					<tr>
						<th><s:text name='subject.teacher.job.number'></s:text><%=teacher.getTeacher_num()%></th>
						<th><s:text name='subject.name'></s:text><%=teacher.getTeacher_name()%></th>
					</tr>
				</table>
			</div>
			<form action="<%=request.getContextPath()%>/subject/teacherChoose">
				<table class="choiceTab">
					<tr bgcolor="#7fffd4" align="center">
						<th><s:text name='subject.order'></s:text></th>
						<th><s:text name='subject.subject'></s:text></th>
						<th><s:text name='subject.responsibility.teacher'></s:text></th>
						<th><s:text name='subject.selection'></s:text></th>
					</tr>
					<%
						for (Subject subject : arr_subject) {
					%>
					<tr>
						<td align="center"><%=subject.getSubject_id()%></td>
						<td align="center"><a
							href="<%=request.getContextPath()%>/subject/seeSubjectDetail?subject_id=<%=subject.getSubject_id()%>"
							title="点击查看详情"><%=subject.getSubject_title()%></a>
						</td>
						<%
							ArrayList<Teacher> arr_teacher = subjectDAO.getDutyTeachersBySubjectId(subject.getSubject_id());
						%>
						<td align="center">
							<%
								//打印老师们
									for (int i = 0; i < arr_teacher.size() - 1; i++) {
										Teacher teacher2 = arr_teacher.get(i);
										if (teacher2.getTeacher_id() == teacher.getTeacher_id()) {
											out.print("<a href='" + request.getContextPath() + "/user/seeSelf' title='点击查看详情'>");
										} else {
											out.print("<a href='" + request.getContextPath() + "/user/seeOtherTeacher?teacher_id=" + teacher2.getTeacher_id() + "' title='点击查看详情'>");
										}
										out.print(teacher2.getTeacher_name() + ",");
										out.print("</a>");
									}
								//打印老师最后一个
									if (arr_teacher.size() - 1 >= 0) {
										if (arr_teacher.get(arr_teacher.size() - 1).getTeacher_id() == teacher.getTeacher_id()) {
											out.print("<a href='" + request.getContextPath() + "/user/seeSelf' title='点击查看详情'>");
										} else {
											out.print("<a href='" + request.getContextPath() + "/user/seeOtherTeacher?teacher_id=" + arr_teacher.get(arr_teacher.size() - 1).getTeacher_id() + "' title='点击查看详情'>");
										}
										out.print(arr_teacher.get(arr_teacher.size() - 1).getTeacher_name());
										out.print("</a>");
									}
							%>
						</td>
						<td align="center"><input type="checkbox" name="subject_id"
							value="<%=subject.getSubject_id()%>"<%=(arr_duty_subject.contains(subject)) ? "checked='checked'" : ""%> >
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
						href="<%=request.getContextPath()%>/subject/seeSubject?page=<%=page_ - 1%>"><s:text name='subject.previous.page'></s:text>
					</a>
					<%
						}
					%><s:text name='subject.the'></s:text>
					<%
						for (int i = 1; i <= pagecount; i++) {
							if (i == page_) {
								out.println("<b>" + i + "</b>");
							} else {
								out.println("<a href='" + request.getContextPath() + "/subject/seeSubject?page=" + i + "'>" + i + "</a> ");
							}
						}
					%><s:text name='subject.page'></s:text>
					<%
						if (page_ != pagecount) {
					%>
					<a
						href="<%=request.getContextPath()%>/subject/seeSubject?page=<%=page_ + 1%>"><s:text name='subject.next.page'></s:text></a>
					<%
						}
					%>
				</p>

				<!-- <p style="margin-left:450px;color: red;font-size: 12px">温馨提示：每个人只能选择两个题目！</p>  -->
				<p style="margin-left:600px">
					<input type="hidden" value="<%=page_%>" name="page"> <input
						type="submit" value="<s:text name='subject.submit'></s:text>">
				</p>
			</form>
		</div>

	</div>
<%@include file="footer.jsp"%>
</body>
</html>
