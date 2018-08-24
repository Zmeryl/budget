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
function doEdit(){
	document.all.frm.target = "_self";
	document.all.frm.action = "catalogAction!updateCatalog.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "修改基价分类";
</script>
<title>类别修改</title>
</head>

<body>

<div class="tabcont">
	<form action="" method="post" name="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td>
			<select class="select_01" name="superId">
				<option value="0">请选择上一级分类</option>
				<c:forEach  items="${cataList}" var="cata" varStatus="cataStatus">
						<option value="${cata.cataId}" <c:if test="${catalog.superId == cata.cataId}"><c:out value="selected"></c:out> </c:if>>${cata.cataName}</option>
				</c:forEach>
			</select>
		</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">分类类型：</td>
		<td>
		<input type="radio" name="cataType" value = "1"  <c:if test="${catalog.cataType == '1'}">checked</c:if> />人员
		<input type="radio" name="cataType" value = "2" <c:if test="${catalog.cataType == '2'}">checked</c:if> />资源
		<input type="radio" name="cataType" value = "3" <c:if test="${catalog.cataType == '3'}">checked</c:if> />其他
		</td>
		</tr>
		
		<tr class="gray">
			<input type="hidden" name="cataId" value="${catalog.cataId}"/>
			<td width="10%">&nbsp;</td>
			<td width="12%" class="align-r">类别名称：</td>
			<td><input type="text" class="input_01" name="cataName" value="${catalog.cataName}" /></td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" onclick="doEdit()" class="button_03" value=" 确定 " />
		<input type="button" onclick="javascript:history.go(-1);" class="button_03" value=" 返回 " />
		</td>
		</tr>
		</table>
		<br />
	</form>
</div>

</body>
</html>
