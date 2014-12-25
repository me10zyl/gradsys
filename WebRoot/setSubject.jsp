<%@page contentType="text/html; charset=utf-8"%>
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
	<div id="Bodycontainer">
		<div id="Curpage">
			<a class="Curpagea">当前位置：</a> <a href="<%=request.getContextPath()%>/index_.jsp"
				style="text-decoration:none; color:#999;"> 首 页 </a>
			</li> <a class="Curpagea">>出题</a>
		</div>
		<div id="showMessageBox">
			<div class="personMessageLogo">出题</div>
			<form action="<%=request.getContextPath()%>/Subject/Set"
				method="post">
				<table class="showMessageTab">
					<tr>
						<td align="right">题目名称：</td>
						<td align="left"><input type="text" title="请输入你出题目"
							value="请输入你出题目" onfocus="onFoc(this)" name="subject_title">
						</td>
					</tr>
					<tr>
						<td align="right">出题老师：</td>
						<td align="left">张小华</td>
					</tr>
					<tr>
						<td align="right">题目详情：</td>
						<td align="left"><textarea rows="10" cols="20"
								title="请输入题目详情" onfocus="onFoc2(this)" name="subject_detail">请输入题目详情</textarea>
						</td>
					</tr>
				</table>
				<table style="margin: 10px 500px">
					<tr>
						<td><input type="submit" value="出题"
							style="background-color:#0066ff"></td>
					</tr>
				</table>
			</form>
		</div>

	</div>
	<script type="text/javascript">
		var count = 0;
		var count_b = 0;
		function onFoc(a) {
			if (count == 0)
				a.value = '';
			count = 1;
		}
		function onFoc2(a) {
			if (count_b == 0)
				a.value = '';
			count_b = 1;
		}
	</script>
</body>
</html>
