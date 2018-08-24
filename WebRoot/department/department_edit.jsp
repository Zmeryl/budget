<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/ambow.tld" prefix="ambow"%>
<%
	String strBase = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=strBase%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=strBase%>/css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function doEdit(){
	document.all.frm.target = "_self";
	document.all.frm.action = "departmentAction!updateDepartment.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "编辑部门信息";
</script>
<title>人员管理</title>
</head>

<body>

<div class="tabcont">
	<form action="" method="post" name="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td>
			<select class="select_01" name="superId">
				<option value="0">请选择上一级部门</option>
				<c:forEach  items="${deptList}" var="baseDept" varStatus="BaseDeptStatus">
						<option value="${baseDept.deptId}" <c:if test="${dept.superId == baseDept.deptId}"><c:out value="selected"></c:out> </c:if>>${baseDept.deptName}</option>
				</c:forEach>
			</select>
		</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
			<input type="hidden" name="deptId" value="${dept.deptId}"/>
			<td width="10%">&nbsp;</td>
			<td width="12%" class="align-r">部门名称：</td>
			<td><input type="text" class="input_01" name="deptName" value="${dept.deptName}" /></td>
		</tr>
		
		<tr class="white">
			<td>&nbsp;</td>
			<td class="align-r align-t">部门描述：</td>
			<td><textarea class="textarea_01" name="deptInfo" >${dept.deptInfo}</textarea></td>
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
