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
function doEdit(){
	if(document.all.frm.deptId.value == "-1"){
		alert("请选择要添加的角色所在的部门");
		return false;
	}
	
	document.all.frm.target = "_self";
	document.all.frm.action = "deptRoleAction!updateDeptRole.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "查看部门角色";
</script>
<title>修改角色</title>
</head>

<body>

<div class="tabcont">
		<form name="frm" method="post" action="" >
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td>&nbsp;</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">角色名称：</td>
		<td>${deptRole.deptRoleName }</td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td class="align-r">角色基价：</td>
		<td>${deptRole.deptRolePrice }元
		</td>
		</tr>
		
		<tr class="white">
		<td>&nbsp;</td>
		<td class="align-r align-t">角色描述：</td>
		<td>${deptRole.deptRoleInfo }</td>
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
