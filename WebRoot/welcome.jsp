<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String strBase = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=strBase%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=strBase%>/css/content.css" rel="stylesheet" type="text/css" />
<title>欢迎页</title>
</head>

<body>
<div class="tabcont">
			<div class="right_div_top"></div>
			<div class="welcome">
				<img src="<%=strBase%>/images/welcome.jpg" />
			</div>
</div>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "欢迎页";
</script>
</body>
</html>
