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
<base href="<%= basePath %>">
<head lang="en">
<meta charset="UTF-8">
<title></title>
<style>
    body {
        padding: 0;
        margin: 0;
         -webkit-animation: bodyback 10s infinite;
        -webkit-animation-delay: 6s;
        -o-animation: bodyback 10s infinite;
        -o-animation-delay: 6s;
        -moz-animation: bodyback 10s infinite;
        -moz-animation-delay: 6s;
    }

    @-webkit-keyframes bodyback {
        25% {
            background: url("images/b1.jpg") no-repeat;
        }
        50% {
            background: url("images/b2.jpg") no-repeat;
        }
        75% {
            background: url("images/b3.jpg") no-repeat;
        }
        100% {
            background: url("images/b4.jpg") no-repeat;
        }
    }
   .bg{ 
		position:fixed; 
		top:0; 
		left:0; 
		bottom:0; 
		right:0; 
		z-index:-1; 
	} 
	.bg > img { 
		height:100%; 
		width:100%; 
		border:0; 
	} 
    #header{
        position: relative;
        height: 100px;
        width: 100%;
    }
    #headerMask {
        height: 100px;
        background: rgb(225, 225, 225);
        filter: alpha(opacity=30);
        opacity: 0.3;
    }

    .logo {
        top: 10px;
        left: 10px;
        position: absolute;
        z-index: 100;
    }

    .chToeng {
        /*border: 1px solid blueviolet;*/
        position: absolute;
        bottom: 0px;
        right: 0px;
        /*float: right;*/
        /*margin: 40px 50px;*/
    }

    .chToeng a {
        text-decoration: none;
        font-size: 18px;
        color: #ffffff;
        font-family: "幼圆";
        font-weight: bold;
    }

    #InformAndLogin {
        width: 800px;
        height: 270px;
        position: relative;
        margin: 0 auto;
        top:218.5px;/*(content-height)/2 (707-270)/2*/
    }

    #InformAndLogin .informationMask {
        width: 430px;
        height: 260px;
        background: rgb(225, 225, 225);
        filter: alpha(opacity=90);
        position: absolute;
        opacity: 0.9;
        float: left;
    }

    #InformAndLogin .information {
        width: 430px;
        height: 260px;
        background: url("images/inform2.png") no-repeat;
        z-index: 100;
        position: absolute;
    }

    .notice {
        overflow: auto;
        height: 229px;
    }

    #InformAndLogin .information p {
        color: #0066FF;
        font-size: 14px;
        font-family: "幼圆";
        font-weight: bold;
        margin: 8px 35px;
    }

    #InformAndLogin .information ul {
        margin: 5px 20px;
    }

    #InformAndLogin .information ul li {
        line-height: 25px;
    }

    #InformAndLogin .information a {
        text-decoration: none;
        color: #0066FF;
        font-size: 14px;
        font-family: "幼圆";
        font-weight: bold;
    }

    #InformAndLogin .information a:hover {
        text-decoration: inherit;
        color: red;
    }

    #InformAndLogin .loginMask {
        width: 349px;
        height: 260px;
        background: rgb(225, 225, 225);
        filter: alpha(opacity=80);
        opacity: 0.8;
        float: right;
    }

    #InformAndLogin .login {
        width: 349px;
        height: 260px;
        background: url("images/login2.png") no-repeat;
        position: absolute;
        top: 0;
        left: 451px;
    }

    #InformAndLogin .login p {
        color: #ffffff;
        font-size: 14px;
        font-family: "幼圆";
        font-weight: bold;
        margin: 13px 45px;
    }

    #InformAndLogin .login .loginTab {
        margin: 20px 80px;
        width: 200px;
        font-size: 18px;
        font-family: "幼圆";
        font-weight: bold;
        color: #0066FF;
        line-height: 40px
    }

    #InformAndLogin .login .loginTab .tdSty {
        font-size: 14px;
    }

    #InformAndLogin .login .loginTab .inputSty2 {
        background-color: #0066FF;
        font-size: 16px;
        font-family: "幼圆";
        font-weight: bold;
        color: #ffffff;
        border-radius: 10px;
        -moz-border-radius: 10px;
        -webkit-border-radius: 10px;
        -o-border-radius: 10px;
    }

    .footerWord {
        margin: 0 auto 0 auto;
        width: 700px;
    }

    .footerWord p {
        font-size: 13px;
        font-family: "Arial";
        text-align: center;
        color: #ffffff;
        opacity: 1;
    }

    #content {
        position: relative;
        height: 707px;
    }

    #footer {
        -webkit-box-pack: center; /*水平居中*/
        -webkit-box-align: center; /*垂直居中*/
        width: 100%;
        height: 60px;
        background: #000000;
        filter: alpha(opacity=70);
        position: fixed;
        opacity: 0.7;
        bottom: 0;
        left: 0;
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
<div class="bg">
	<img src="images/b1.jpg">
</div>
<div id="header">
    <div id="headerMask"></div>
    <div class="logo">
        <img src="images/logintest.png">
    </div>
    <div class="chToeng">
        <a href="<%=request.getContextPath()%>/i18n"><s:text name="header.change.language"></s:text></a>
    </div>
</div>
<div id="content">
    <div id="InformAndLogin">
        <div class="informationMask"></div>
        <div class="information">
            <p><s:text name="index.notice"></s:text></p>
            <div class="notice">
                <ul>
                </ul>
            </div>
        </div>

        <div class="loginMask"></div>
         <form action="<%=request.getContextPath() %>/user/login" method="post">
        <div class="login">
            <p><s:text name="index.login.title"></s:text></p>
            <table class="loginTab">
                <tr>
                    <td id="num"><s:text name="index.student.number"></s:text></td>
                    <td><input type="text" name="username" size="12"/></td>
                </tr>
                <tr>
                    <td><s:text name="index.password"></s:text></td>
                    <td><input type="password" name="password" size="12"></td>
                </tr>
                <tr>
                    <td align="center" class="tdSty"><input type="radio" name="userType" onclick="changeNum(0)" value="teacher"/><s:text name="index.teacher"></s:text></td>
                    <td class="tdSty"><input type="radio" name="userType" checked="checked" onclick="changeNum(1)" value="student"/><s:text name="index.student"></s:text></td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" value="<s:text name="index.login"></s:text>" class="inputSty2"></td>
                    <td align="center"><input id="forgetPassword" type="button" value="<s:text name="index.forget.password"></s:text>" class="inputSty2" onclick="javascript:void(alert('<s:text name="index.forget.password.alert"></s:text>'))"></td>
                </tr>
            </table>
        </div>
       </form>
    </div>
</div>
<div id="footer">
    <div id="footerMask"></div>
    <div class="footerWord">
        <p><s:text name="index.footer.one"></s:text></p>
        <p><s:text name="index.footer.two"></s:text></p>
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
						$(".information ul").append("<li><a href='<%=path%>/notice/see?notice_id="+json[i].notice_id+"'>"+ json[i].notice_title+"</a></li>");
					}
					}else
					{
						alert('连接服务器失败！');
						$(".information ul").html("");
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
			var i = 1;
			setInterval(function(){
				if(i++ == 5)
				{
					i = 1;
				}
				$('.bg img').attr("src","images/b"+i+".jpg")
			},10000);
		})
	</script>
</body>
</html>