<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="ambow" prefix="ambow"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String strBase = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=strBase%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=strBase%>/css/content.css" rel="stylesheet" type="text/css" />
<title>人员管理</title>
<script type="text/javascript">
function initPwd(){
	document.all.frm.loginPwd.value = document.all.frm.loginName.value;
}

function doAdd(){
	if(document.all.frm.deptRoleId.value == "-1" || document.all.frm.sysRoleId.value == "-1"){
		alert(" 请先选择用户的部门角色及系统角色 ");
		return false;
	}
	document.all.frm.target = "_self";
	document.all.frm.action = "userAction!addUser.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "添加用户";
</script>
</head>

<body>
<div class="tabcont">
	<form action="" method="post" name="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td>
			<select class="select_01" name="deptRoleId">
				<option value="-1" >--请选择部门角色--</option>
				<c:forEach items="${deptRoleList}" var="deptRole" >
				<option value="${deptRole.deptRoleId}" >${deptRole.deptRoleName}</option>
				</c:forEach>
			</select>
			<select class="select_01" name="sysRoleId">
				<option value="-1" >--请选择系统角色--</option>
				<c:forEach items="${roleList}" var="role" >
				<option value="${role.sysRoleId}" >${role.sysRoleName}</option>
				</c:forEach>
			</select>
		</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">登录名：</td>
		<td><input type="text" class="input_01" name="loginName" onkeyup="initPwd()" /></td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td class="align-r">密码：</td>
		<td>
		<input type="password" name="loginPwd" value="" />(默认与登录名相同)
		</td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td class="align-r">用户状态：</td>
		<td>
		<input type="radio" name="status" value = "1" checked="checked"/>正常
		<input type="radio" name="status" value = "0" />停用
		</td>
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
		</form>
		
		<br />
</div>

</body>
</html>
