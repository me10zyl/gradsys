<%@page import="com.graduationsystem.db.teacher.Teacher"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title></title>
<style>
body {
	margin: 0;
	padding: 0;
	text-align: center;
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
	margin: 0 auto;
}

#Curpage {
	height: 20px;
	width: 790px;
	font-size: 12px;
	margin-left: 20px;
}

#login {
	width: 285px;
	height: 200px;
	margin: 10px 10px;
	border: 1px solid royalblue;
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
	border: 1px solid royalblue;
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
</head>

<body>
	<%
		String loginState = (String) session.getAttribute("loginState");
		if (loginState != null) {
			if (loginState.equals("success")) {
				String userType = (String) session.getAttribute("userType");
				if (userType.equals("student")) {
					if (session.getAttribute("student") != null)
						response.sendRedirect(request.getContextPath() + "/personLogin.jsp");
				} else if (userType.equals("teacher")) {
					if (session.getAttribute("teacher") != null)
						response.sendRedirect(request.getContextPath() + "/personLogin_t.jsp");
				}
			}
		}
	%>
	<div id="Bodycontainer">
		<div id="Curpage">
			<p>您好，欢迎来到成都东软学院毕业设计管理系统！</p>
		</div>
		<div id="login">
			<form action="<%=path%>/User/Login" method="post">
				<img src="<%=request.getContextPath()%>/images/login1.jpg">
				<table class="loginTab">
					<tr>
						<td align="right" width="60" id="num">学号：</td>
						<td><input type="text" name="username" size="11" />
						</td>
					</tr>
					<tr>
						<td align="right">密码：</td>
						<td><input type="password" name="password" size="11">
						</td>
					</tr>
					<tr>
						<td align="center"><input type="radio" name="userType"
							value="teacher" onclick="changeNum(0)"/>教师</td>
						<td><input type="radio" name="userType" value="student"
							checked="checked" onclick="changeNum(1)"/>学生</td>
					</tr>
					<tr>
						<td align="right"><input type="submit" value="登 录"
							style="background-color: royalblue; color: white;width: 50px;">
						</td>
						<td align="center"><input type="button" value="忘记密码"
							style="background-color: royalblue; color: white;width: 70px">
						</td>
					</tr>

				</table>
			</form>
		</div>
		<div id="information">
			<img src="<%=request.getContextPath()%>/images/inform.png">
			<ul>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
				<li><a>2014毕业设计选题开始了</a></li>
			</ul>

		</div>
	</div>
<script type="text/javascript">
function changeNum(n)
{
	var num = document.getElementById('num');
	if(n == 0)
	{
		num.innerHTML = "工号：";
	}else{
		num.innerHTML = "学号：";
	}
}
</script>
</body>
</html>
