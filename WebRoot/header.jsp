<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <style>
        body{ margin: 0;padding: 0;}
        #header{height: 120px; width: 800px; background-color: #0187c4; margin: 0 auto;}
        #logo{ margin-left:90px; width: 320px; height: 90px;}
        #nav{height: 30px;width: 799px;background-color: #0187c4;border: 1px solid #d2d6d8;}
        #nav ul{list-style: none;margin-top: 2px;}
        #nav ul li{margin-left:50px;float: left; width: 90px;line-height: 25px;text-align: center;padding: 0 10px;}
        #nav a{display: block; color:white;text-decoration: none;padding: 0 10px;}
        #nav a:hover{background-color: darkseagreen;}
        #nav  ul li ul{top: 120px;display: none;background-color: indianred;}
        #nav  ul li ul li{float: none;background-color: cornflowerblue;margin-left:-40px;padding: 0 0;}
        #nav ul li:hover ul{display: block;}
        #nav ul li ul li a:hover{background-color: #0066ff;}
    </style>
</head>

<body>
<div id="header">
    <div id="logo">
        <img src="<%=request.getContextPath()%>/images/logo1.png">
    </div>
    <div id="nav">
     <ul>
         <li><a href="index_.jsp" target="mainFrame"><s:text name="header.homepage"></s:text></a></li>
         <li><a href="<%=request.getContextPath()%>/user/seeSelf" target="mainFrame"><s:text name="header.profile"></s:text></a></li>
         <li><a href="<%=request.getContextPath()%>/subject/seeSubject" target="mainFrame"><s:text name="header.subjectSelection"></s:text></a></li>
         <li><a href="<%=request.getContextPath()%>/i18n"><s:text name="header.changlanguage"></s:text></a></li>
     </ul>
    </div>
</div>
</body>
</html>
