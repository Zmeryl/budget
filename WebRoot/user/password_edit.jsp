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
<script type="text/javascript" src="<%=strBase%>/js/check.js"></script>
<script type="text/javascript">
function doEdit(){
	var oldPwd = document.all.frm.oldPwd.value;
	var newPwd1 = document.all.frm.newPwd.value;
	var newPwd2 = document.all.frm.newPwd2.value;

	if(chklength(newPwd1) == 0 || chklength(newPwd1) > 20){
		alert("新密码长度必须在0-20之间");
		return false;
	}
	
	if(!chkpasswd(newPwd1)){
		alert("新密码格式有误");
		return false;
	}

	
	if(oldPwd != '${_S__SESSION_USER_OBJ.loginPwd}'){
		alert("旧密码输入错误");
		return false;
	}

	if(newPwd1 != newPwd2){
		alert("新密码输入有误");
		return false;
	}
	
	document.all.frm.target = "_parent";
	document.all.frm.action = "<%=request.getContextPath()%>/user/editUserPwdAction.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "修改个人密码";
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
		<td width="12%" class="align-r">旧密码：</td>
		<td><input type="password" class="input_01" name="oldPwd"  /></td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">新密码：</td>
		<td><input type="password" class="input_01" name="newPwd"  /></td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">重复新密码：</td>
		<td><input type="password" class="input_01" name="newPwd2"  /></td>
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
		</form>
		
		<br />
</div>

</body>
</html>
