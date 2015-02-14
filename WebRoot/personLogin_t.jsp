<%@page import="com.graduationsystem.db.teacher.Teacher"%>
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

#userMessage {
	width: 285px;
	height: 200px;
	margin: 10px 10px;
	border: 2px solid royalblue;
	float: left;
}

.loginTab {
	text-align: center;
	padding: 4px;
	border-color: #006699;
	width: 200px;
	font-size: 12px;
}

#information {
	width: 433px;
	height: 400px;
	border: 2px solid royalblue;
	float: left;
	margin: 10px 5px;
}

#information a {
	text-decoration: none;
	color: greenyellow
}

#information a:hover {
	text-decoration: inherit;
	color: red
}
</style>
<base href="<%=request.getContextPath()%>">
</head>

<body>
	<div id="Bodycontainer">
		<%
			String loginState = (String) session.getAttribute("loginState");
			String userType = (String) session.getAttribute("userType");
			Object user = session.getAttribute("teacher");
			Teacher teacher = user instanceof Teacher ? (Teacher) user : null;
		%>
		<div id="Curpage">
			<p><%
					if (teacher != null)
						out.print(teacher.getTeacher_name());
				%>老师您好，欢迎您！</p>
		</div>
		<div id="userMessage">
			<img src="<%=request.getContextPath()%>/images/login1.jpg">
			<%
				if (loginState.equals("success")) {
			%>
			<!-- success -->
			<table class="loginTab">
				<tr>
					<td align="right" width="60">工号：</td>
					<td align="left"><%=teacher.getTeacher_num()%></td>
				</tr>
				<tr>
					<td align="right" width="60">姓名：</td>
					<td align="left"><%=teacher.getTeacher_name()%></td>
				</tr>
				<tr align="center">
					<td align="right" width="60"><input type="submit" value="注 销"
						title="点击注销" style="background-color: royalblue" onClick="javascript:void(location.href='<%=request.getContextPath()%>/user/logout')">
					</td>
					<td align="right" width="60"><input type="submit" value="出 题"
						title="点击出题" style="background-color: royalblue" onClick="javascript:void(location.href='<%=request.getContextPath()%>/setSubject.jsp')" style="font-color:red;">
					</td>
				</tr>
			</table>
			<p style="margin-left:150px;">
				<a href="<%=request.getContextPath()%>/subject/seeSubject"
					style="color: #0099FF;font-size: 12px">负责题目情况>></a>
			</p>
			<%
				} else if (loginState.equals("notexist")) {
			%>
			<!-- not exist -->
			<table class="loginTab">
				<tr align="center">
					<td align="right" width="60" colspan="2"><font color='red'
						size="+1" face="幼圆">用户名不存在！</font>
					</td>
				</tr>
				<tr align="center">
					<td align="right" width="60"><b><a href="<%=request.getContextPath()%>/index_.jsp"
							target="mainFrame">点击</a> </b>重新登陆...</td>
				</tr>
			</table>
			<%
				} else if (loginState.equals("wrong")) {
			%>
			<!-- wrong -->
			<table class="loginTab">
				<tr>
					<td align="right" width="60"><font color='red' size="+1"
						face="幼圆">密码错误!</font>
					</td>
				</tr>
				<tr align="center">
					<td align="right" width="60"><b><a href="<%=request.getContextPath()%>/index_.jsp"
							target="mainFrame">点击</a> </b>重新登陆...</td>
				</tr>
			</table>
			<%
				}
			%>

		</div>
		<div id="information">
			<img src="<%=request.getContextPath()%>/images/inform.png">
			<ul>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
				<li><a >2014毕业设计选题开始了</a>
				</li>
			</ul>

		</div>
	</div>
</body>
</html>
