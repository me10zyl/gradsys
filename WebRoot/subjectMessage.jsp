<%@page import="com.graduationsystem.db.teacher.TeacherDAO"%>
<%@page import="com.graduationsystem.db.teacher.Teacher"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.graduationsystem.db.subject.SubjectDAO"%>
<%@page import="com.graduationsystem.db.subject.Subject"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
	<%
		SubjectDAO subjectDAO = new SubjectDAO();
		Subject subject = (Subject) request.getAttribute("subject");
		ArrayList<Teacher> arr_teacher = subjectDAO.getDutyTeachersBySubjectId(subject.getSubject_id());
		String userType = (String) session.getAttribute("userType");
		Teacher teacher_me = (Teacher)session.getAttribute("teacher");
		int teacher_id_set = (Integer)request.getAttribute("teacher_id_set");
		int teacher_id = 0;
		if(teacher_me != null)
			teacher_id = teacher_me.getTeacher_id();
	%>
	<div id="Bodycontainer">
		<div id="Curpage">
			<a class="Curpagea"><s:text name='profile.current.position'></s:text></a> <a
				href="<%=request.getContextPath()%>/index"
				style="text-decoration:none; color:#999;"> <s:text name='profile.homepage'></s:text> </a>
			</li> <a class="Curpagea">><s:text name='subject.detail.subject.information'></s:text></a>
		</div>
		<form action="<%=request.getContextPath()%>/subject/modify"
			method="post">
			<div id="showMessageBox">
				<div class="personMessageLogo"><s:text name='subject.detail.subject.information'></s:text></div>
				<table class="showMessageTab">
					<tr>
						<td align="right"><s:text name='subject.set.subject.name'></</s:text></td>
						<td align="left" id="title"><input type='text' id='title_input'
							value="<%=subject.getSubject_title()%>" disabled="disabled"
							name='subject_title'></td>
						<td align="left">
							<%
								if (userType.equals("teacher") && teacher_id_set == teacher_id) {
							%> <input type="button"
							value="<s:text name='profile.modify'></s:text>" title="点击<s:text name='profile.modify'></s:text>" style="background-color: #FF7415"
							style="font-color:red;" onclick="modify(0)">
							<%
								}
							%>
						</td>
					</tr>
					<tr>
						<td align="right"><s:text name='subject.set.teacher'></</s:text></td>
						<td align="left" id="description">
							<%
								out.println("<a href='"+request.getContextPath()+"/user/seeOtherTeacher?teacher_id=" + subject.getTeacher_id() + "'>" + request.getAttribute("teacher_set") + "</a>");
							%>
						</td>
					</tr>
					<tr>
						<td align="right"><s:text name='subject.duty.teacher'></</s:text></td>
						<td align="left" id="description">
							<%
								for (Teacher teacher : arr_teacher) {
													out.println("<a href='"+request.getContextPath()+"/user/seeOtherTeacher?teacher_id=" + teacher.getTeacher_id() + "'>" + teacher.getTeacher_name() + "</a>");
													}
							%>
						</td>
					</tr>
					<tr>
						<td align="right"><s:text name='subject.set.subject.detail'></</s:text></td>
						<td align="left"><textarea rows="10" cols="20"
								name="subject_description" style="padding:0;margin:0"><%=subject.getSubject_description()%></textarea>
						</td>
					</tr>
					<tr>
						<td><input type="hidden" name="subject_id"
							value="<%=subject.getSubject_id()%>">
						</td>
						<%
							if (userType.equals("teacher") && teacher_id_set == teacher_id) {
						%>
						<td align="center"><input type="submit" value="<s:text name='subject.detail.save'></s:text>"
							title="点击修改" style="background-color: royalblue"
							style="font-color:red;" onclick="removeDiabled()">
						</td>
						<td align="left"><input type="button" value="<s:text name='subject.detail.delete'></s:text>"
							title="点击删除" style="background-color: black;color:white;"
							 onclick="delete_()">
						</td>
						<%
							}
						%>
					
					<tr>
				</table>
		</form>
	</div>
	</div>
	<script type="text/javascript">
		function delete_()
		{
			var form = document.forms[0];
			form.action= "<%=request.getContextPath()%>/subject/delete";
			form.submit();
		}
		function modify(a) {
			if (a == 0) {
				var title = document.getElementById('title');
				title.innerHTML = "<input type='text' value='"+title.firstChild.value+"' name='subject_title'>";
			} else if (a == 2) {
				var description = document.getElementById('description');
				var teachers = "";
				for ( var i = 0; i < description.children.length; i++) {
					if (i == description.children.length - 1) {
						teachers += description.children[i].innerHTML + ",";
					} else {
						teachers += description.children[i].innerHTML;
					}
				}
				description.innerHTML = "<input type='text' value='"+teachers+"' name='subject_description'>"
			}
		}
		
		function removeDiabled()
		{
		 var input = document.getElementById('title_input');
		 input.removeAttributeNode(input.getAttributeNode("disabled"));
		 input.setAttribute("readonly","readonly");
		}
	</script>
</body>
</html>
