<%@page import="com.graduationsystem.db.teacher.Teacher"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head lang="en">
<base href="<%= basePath %>">
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
	width: 457px;
	height: 400px;
	border: 1px solid royalblue;
	float: left;
	margin: 10px 5px;
	overflow: auto;
}

#information a {
	text-decoration: none;
	color: greenyellow
}

#information a:hover {
	text-decoration: inherit;
	color: red
}
ul
{
	text-align: left;
}
</style>
</head>

<body>
	<%
		String loginState = (String) session.getAttribute("loginState");
		if (loginState != null) {
			if (loginState.equals("success")) {
				String userType = (String) session.getAttribute("userType");
				if (userType.equals("student") || userType.equals("teacher")) {
					if (session.getAttribute("student") != null || session.getAttribute("teacher") != null)
					{
						response.sendRedirect(request.getContextPath()
								+ "/index");
					}
				} 
			}
		}
	%>
	<div id="Bodycontainer">
		<div id="Curpage">
			<p>
				<s:text name="index.welcome"></s:text>
			</p>
		</div>
		<div id="login">
			<form action="<%=path%>/user/login" method="post">
				<img src="<%=request.getContextPath()%>/images/login1.jpg">
				<table class="loginTab">
					<tr>
						<td align="right" width="60" id="num"><s:text name="index.student.number"></s:text></td>
						<td><input type="text" name="username" size="11" /></td>
					</tr>
					<tr>
						<td align="right"><s:text name="index.password"></s:text></td>
						<td><input type="password" name="password" size="11">
						</td>
					</tr>
					<tr>
						<td align="center"><input type="radio" name="userType"
							value="teacher" onclick="changeNum(0)" /> <s:text name="index.teacher"></s:text></td>
						<td><input type="radio" name="userType" value="student"
							checked="checked" onclick="changeNum(1)" /> <s:text
								name="index.student"></s:text></td>
					</tr>
					<tr>
						<td align="right"><input type="submit"
							value='<s:text name="index.login"></s:text>'
							style="background-color: royalblue; color: white; width: 50px;">
						</td>
						<td align="center"><input type="button"
							value='<s:text name="index.forget.password"></s:text>'
							style="background-color: royalblue; color: white; width: 70px"
							onclick="javascript:void(alert('请联系管理员!'))"></td>
					</tr>

				</table>
			</form>
		</div>
		<div id="information">
			<img src="<%=request.getContextPath()%>/images/inform.png">
			<ul>
			</ul>
		</div>

	</div>
	<script type="text/javascript" src="javascript/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
		function changeNum(n) {
			var num = document.getElementById('num');
			if (n == 0) {
				num.innerHTML = "<s:text name='index.job.number'></s:text>";
			} else {
				num.innerHTML = "<s:text name='index.student.number'></s:text>";
			}
		}
		$(
		function() {
			var old_data;
			var index = 0;
			$.ajax({
				type : "POST",
				//		dataType : "json",
				url : "notice/seeAll",
				//	timeout : 80000, //ajax请求超时时间80秒      
				//			data : {
				//			time : "100"
				//	},//40秒后无论结果服务器都返回数据      
				cache : false,
				success : function(data) {
					//从服务器得到数据，显示数据并继续查询
					if(data)
					{
					var json = JSON.parse(data);
					for(var i=0;i < json.length;i++)
					{
						$("#information ul").append("<li><a href='<%=path%>/notice/see?notice_id="+json[i].notice_id+"'>"+ json[i].notice_title+"</a></li>");
					}
					}else
					{
						alert('连接服务器失败！');
						$("#information ul").html("");
					}
					if(index == 0)
					{
						old_data = data;
					}
					if(old_data != data)
					{
						window.location.href="<%=request.getContextPath()%>/index";
						old_data = data;
					}
					index++;
				}
			})
			
		
		})
	</script>
</body>
</html>
