<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String strBase = request.getContextPath();
%>
<html>
<head>
<title>项目成本预算系统</title>
<link href="<%=strBase%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=strBase%>/css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=strBase%>/js/check.js"></script>
<script type="text/javascript">
function doLogin(){
	
	if(document.all.frm.loginName.value == "" 
		|| document.all.frm.loginName.length == 0 
		|| document.all.frm.loginName.length > 20){
		alert("登录名有误！");
		return false;
	}
	
	if(chkspace(document.all.frm.loginName.value) || chkspace(document.all.frm.loginPwd.value)){
		alert("输入内容包含空格！");
		return false;
	}
	/*
	if(chksafe(document.all.frm.loginName.value) || chksafe(document.all.frm.loginPwd.value)){
		alert("输入内容包含非法字符！");
		return false;
	}
	*/
	if(document.all.frm.loginPwd.value == ""
		|| document.all.frm.loginPwd.value == 0
		|| document.all.frm.loginPwd.value > 20){
		alert("密码输入有误！");
		return false;
	}

	document.all.frm.target = "_self";
	document.all.frm.action = "<%=strBase%>/user/loginController.jhtml";
	document.all.frm.submit();
}
</script>
</head>

<body class="login_bg">

	<div class="login_layout">
	
			<div class="login_left">
				<div class="login_left_t"></div>
				<div class="login_left_i"></div>
			</div>
			
			<div class="login_right">
				<form action="" name="frm" method="post">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="logintab">
						<tr>
						<td colspan="100">${_error_message}</td>
						</tr>
						
						<tr>
						<td class="align-r" width="14%">用户名：</td>
						<td><input type="text" name="loginName" class="input_01" /></td>
						</tr>
						
						<tr>
						<td class="align-r">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
						<td><input type="password" name="loginPwd" class="input_01" onkeypress="if(event.keyCode==13) doLogin()"/></td>
						</tr>
						
						<tr>
						<td>&nbsp;</td>
						<td><br />
						<input type="button" name="login" class="button_login" value="登录" onclick="doLogin()" /></td>
						</tr>
					</table>
				</form>
			</div>
	</div>
	<div class="login_bottom">
		<img src="<%=strBase%>/images/login_logo.gif" />
		提供运营及支持服务，版权所有©2010
	</div>
</body>
</html>