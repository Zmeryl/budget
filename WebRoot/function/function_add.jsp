﻿<%@ page language="java" contentType="text/html; charset=utf-8"
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
function doAdd(){
	document.all.frm.target = "_self";
	document.all.frm.action = "functionAction!addFunction.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "添加系统功能";
</script>
<title>添加功能</title>
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
		<td width="12%" class="align-r">功能名称：</td>
		<td><input type="text" class="input_01" name="funName" /></td>
		</tr>
		
		<tr class="white">
		<td>&nbsp;</td>
		<td class="align-r align-t">功能代码：</td>
		<td>
			<select name="funCode" class="select_01">
				<option value="add">add</option>
				<option value="delete">delete</option>
				<option value="edit">edit</option>
				<option value="view">view</option>
			</select>
		</td>
		</tr>
		
		<tr class="white">
		<td>&nbsp;</td>
		<td class="align-r align-t">资源名称：</td>
		<td><input type="text" class="input_01" name="funDesc" /></td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" onclick="doAdd()" class="button_03" value=" 确定 " />
		<input type="button" onclick="javascript:history.go(-1);" class="button_03" value=" 返回 " />
		</td>
		</tr>
		</table>
		<br />
	</form>
</div>

</body>
</html>
