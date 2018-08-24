<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="ambow" prefix="ambow"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String strBase = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=strBase%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=strBase%>/css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "系统信息";
</script>
<title>错误信息</title>
</head>

<body>

<div class="tabcont">
	<form action="" method="post" name="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td>&nbsp;</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="80%" class="align-c">用户登录已超时，请重新<a href = "<%=request.getContextPath()%>/login.jsp" target="_parent">登录</a>!</td>
		<td width="10%">&nbsp;</td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td align="center" >
		<input type="button" onclick="javascript:history.go(-1);" class="button_03" value=" 返回 " />
		</td>
		<td>&nbsp;</td>
		</tr>
		</table>
		<br />
	</form>
</div>
</body>
</html>
