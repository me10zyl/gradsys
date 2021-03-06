<%@page import="com.graduationsystem.db.student.Student"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
  height: 50px;
  width: 790px;
  line-height: 50px;
  font-size: 16px;
  font-family: "幼圆";
  margin: 0 auto;
  text-align: center;
  background: #cccccc;
}

#userMessage {
	width: 285px;
	height: 280px;
	margin: 20px 10px;
	border: 2px solid royalblue;
	float: left;
}

.loginTab {
	margin:0px auto;
	width:200px;
	font-size:16px;
	font-family:"幼圆";
	font-weight:bold;
	color:#0066FF;
	line-height:40px;
}

#information {
	width: 457px;
	height: 400px;
	border: 2px solid royalblue;
	float: left;
	margin: 20px 5px;
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
#userMessage .title
{
	position: relative;
	height: 29px;
}
#userMessage .title img
{
	 width:285px;
}
#userMessage .title span
{
	position:absolute;
	left:40px;
	top:8px;
	color: #ffffff;
	font-size: 14px;
	font-family:"幼圆";
	font-weight: bold;
}
#information .title img {
	width: 457px;
}
#information .title
{
	position: relative;
}
#information .title span
{
	position:absolute;
	left:40px;
	top:8px;
	color: #ffffff;
	font-size: 14px;
	font-family:"幼圆";
	font-weight: bold;
	color: #0187c4;
}
</style>
</head>

<body>
<%@include file="header.jsp" %>
	<div id="Bodycontainer">
		<%
			String loginState = (String) session.getAttribute("loginState");
			Object user = session.getAttribute("student");
			Student student = user instanceof Student ? (Student) user : null;
		%>
		<div id="Curpage">
			<p>
				<%
					if (student != null)
						out.print(student.getStudent_name());
				%><s:text name="login.welcome"></s:text>
			</p>
		</div>
		<div id="userMessage">
			<div class="title">
				<img src="<%=request.getContextPath()%>/images/login2.png">
				<span>
				<s:text name="login.profile"></s:text>
				</span>
			</div>
			<%
				if (loginState.equals("success")) {
			%>
			<!-- success -->
			<table class="loginTab">
				<tr>
					<td align="right" width="60"><s:text name="login.student.number"></s:text></td>
					<td align="left"><%=student.getStudent_num()%></td>
				</tr>
				<tr>
					<td align="right" width="60"><s:text name="login.name"></s:text></td>
					<td align="left"><%=student.getStudent_name()%></td>
				</tr>
				<tr>
					<td align="right" width="60"><s:text name="login.major"></s:text></td>
					<td align="left"><%=student.getStudent_major()%></td>
				</tr>
				<tr align="center">
					<td align="right" width="60"><input type="submit" value="<s:text name='login.logout'></s:text>"
						title="<s:text name='login.click.to.logout'></s:text>" style="background-color: royalblue;color:white"
						onClick="javascript:void(location.href='<%=request.getContextPath()%>/user/logout')">
					</td>
				</tr>
			</table>
			<p style="margin-left:150px;">
				<a href="<%=request.getContextPath()%>/subject/seeSubject"
					style="color: #FA7355;font-size: 12px"><s:text name="login.check.subject.selection"></s:text>></a>
			</p>
			<%
				} else if (loginState.equals("notexist")) {
			%>
			<!-- not exist -->
			<table class="loginTab">
				<tr align="center">
					<td align="right" width="60" colspan="2"><font color='red'
						size="+1" face="幼圆"><s:text name='login.username.not.exist'></s:text></font></td>
				</tr>
				<tr align="center">
					<td align="right" width="60"><b><a href="<%=request.getContextPath()%>/index"
							><s:text name='login.click'></s:text></a> </b><s:text name='login.login.again'></s:text></td>
				</tr>
			</table>
			<%
				} else if (loginState.equals("wrong")) {
			%>
			<!-- wrong -->
			<table class="loginTab">
				<tr>
					<td align="right" width="60"><font color='red' size="+1"
						face="幼圆"><s:text name='login.password.wrong'></s:text></font></td>
				</tr>
				<tr align="center">
					<td align="right" width="60"><b><a href="<%=request.getContextPath()%>/index"
							><s:text name='login.click'></s:text></a> </b><s:text name='login.login.again'></s:text></td>
				</tr>
			</table>
			<%
				}
			%>

		</div>
		<div id="information">
			<div class="title">
				<img src="<%=request.getContextPath()%>/images/inform2.png">
				<span>
				<s:text name="login.notice"></s:text>
				</span>
			</div>
			<ul>
			</ul>
		</div>
	</div>
	<script type="text/javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.11.1.min.js"></script>
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
				url : "<%=request.getContextPath()%>/notice/seeAll",
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
						$("#information ul").append("<li><a href='<%=request.getContextPath()%>/notice/see?notice_id="+json[i].notice_id+"'>"+ json[i].notice_title+"</a></li>");
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
<%@include file="footer.jsp"%>
</body>
</html>
