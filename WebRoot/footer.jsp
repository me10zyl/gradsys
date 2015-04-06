<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
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
	<div id="footer">
		<div class="footerWord">
			<p>
				<s:text name="index.footer.one"></s:text>
			</p>
			<p>
				<s:text name="index.footer.two"></s:text>
			</p>
		</div>
	</div>
</body>
</html>