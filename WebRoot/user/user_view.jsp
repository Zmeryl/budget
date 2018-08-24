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
<title>人员管理</title>
<script type="text/javascript">
function doEdit(){
	if(document.all.frm.deptRoleId.value == "-1" || document.all.frm.sysRoleId.value == "-1"){
		alert(" 请先选择用户的部门角色及系统角色 ");
		return false;
	}
	document.all.frm.target = "_self";
	document.all.frm.action = "userAction!updateUser.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "查看用户信息";
</script>
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
		<td width="12%" class="align-r">登录名：</td>
		<td>${user.loginName}</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">部门角色：</td>
		<td>${user.deptRoleName}</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">系统角色：</td>
		<td>${user.sysRoleName}</td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td class="align-r">用户状态：</td>
		<td>
		<c:if test="${user.status == '1'}">正常</c:if><c:if test="${user.status == '0'}">停用</c:if>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="20%" class="align-r">真实姓名：</td>
		<td width="50%">${userDetail.realName}</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="20%" class="align-r">性别：</td>
		<td width="50%">
		<c:if test="${userDetail.sex == '1'}">男</c:if> 
		<c:if test="${userDetail.sex == '0'}">女</c:if>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="20%" class="align-r">入职时间：
		<td width="50%">
		<fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${userDetail.joinTime }" />
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%" >&nbsp;</td>
		<td width="20%" class="align-r">基价：</td>
		<td width="50%">
		<c:if test="${userDetail.userPrice == 0 }">${_rolePrice}" 元</c:if>
		<c:if test="${userDetail.userPrice != 0 }">${userDetail.userPrice}"元</c:if>
		</td>
		</tr>
		
		<tr class="white">
		<td width="10%" >&nbsp;</td>
		<td width="20%" class="align-r align-t">备注信息：</td>
		<td width="50%">${userDetail.userDesc }</td>
		</tr>
				
		<tr class="gray">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" onclick="javascript:history.go(-1);" class="button_03" value=" 返回 " />
		</td>
		</tr>
		</table>
		</form>
		
		<br />
</div>

</body>
</html>
