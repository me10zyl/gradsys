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
	width:100%;
	height:100px;
	background-color: #0187c4;
	margin: 0 auto;
	position: relative;
}

#logo {
	margin-left: 90px;
	height: 90px;
	display: inline;
}

#nav {
	height: 30px;
	width: 500px;
	background-color: #0187c4;
	position: absolute;
	right: 0;
	bottom: 0;
}

#nav ul {
	list-style: none;
	margin-top: 2px;
}

#nav ul li {
	margin-left: 5px;
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
    text-decoration: underline;
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
</style>
</head>

<body>
	<div id="header">
		<div id="logo">
			<img src="<%=request.getContextPath()%>/images/logintest.png">
		</div>
		<div id="nav">
			<ul>
				<li><a href="<%=request.getContextPath()%>/index"><s:text
							name="header.homepage"></s:text></a></li>
				<li><a href="<%=request.getContextPath()%>/user/seeSelf"
					><s:text name="header.profile"></s:text></a></li>
				<li><a href="<%=request.getContextPath()%>/subject/seeSubject"
					><s:text name="header.subject.selection"></s:text></a></li>
				<li><a href="<%=request.getContextPath()%>/i18n"
					onclick="javascript:void(0)"><s:text name="header.change.language"></s:text></a></li>
			</ul>
		</div>
		<script type="text/javascript">
		<s:if test="#request.refresh == true">
    		window.onload = refreshParent();
    	</s:if>
    	function refreshParent()
    	{
    		//setTimeout(function(){
    		parent.location.href="<%=request.getContextPath()%>";
			//	}, 10);

		}
		</script>
	</div>
</body>
</html>
