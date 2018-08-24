<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<script type="text/javascript" src="<%=strBase%>/js/calendar.js"></script>
<title>人员管理</title>
<script type="text/javascript">
function doEdit(){
	document.all.frm.target = "_self";
	document.all.frm.action = "userDetailAction!updateUserDetail.jhtml";
	document.all.frm.submit();
}

</script>
</head>

<body>

<div class="black_t">
	<div class="black_t_l"></div>
	<div class="black_t_f">编辑用户基本信息</div>
</div>

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
		<td width="20%" class="align-r">真实姓名：</td>
		<td width="50%"><input type="text" class="input_01" name="realName" value="${userDetail.realName}"  />
		<input type="hidden" name="userId" value="${userDetail.userId }" /></td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="20%" class="align-r">性别：</td>
		<td width="50%">
		<input type="radio" name="sex" value = "1" <c:if test="${userDetail.sex == '1'}">checked</c:if> />男
		<input type="radio" name="sex" value = "0" <c:if test="${userDetail.sex == '0'}">checked</c:if>/>女
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="20%" class="align-r">入职时间：
		<td width="50%">
		<input type="text" class="input_01" name="joinTime" width="100" readonly="readonly" value="<fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${userDetail.joinTime }" />" />
		<a onclick="calendar(joinTime)"><img src="<%=strBase%>/images/icon_calendar.gif" alt="选择日期" style="cursor:hand" /></a>   
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%" >&nbsp;</td>
		<td width="20%" class="align-r">职业类型：</td>
		<td width="50%">
			<select class="select_01" name="userType">
			<option value="0" >选择职位类型</option>
			<option value="1">全职</option>
			<option value="2">兼职</option>
			<option value="3">外包</option>
			</select>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%" >&nbsp;</td>
		<td width="20%" class="align-r">基价：</td>
		<td width="50%">
		<c:if test="${userDetail.userPrice == 0 }">
		<input type="text" class="input_02" name="userPrice" value="${_rolePrice}" />元</td>
		</c:if>
		<c:if test="${userDetail.userPrice != 0 }">
		<input type="text" class="input_02" name="userPrice" value="${userDetail.userPrice}" />元</td>
		</c:if>
		</tr>
		
		<tr class="white">
		<td width="10%" >&nbsp;</td>
		<td width="20%" class="align-r align-t">备注信息：</td>
		<td width="50%"><textarea class="textarea_01" name="userDesc" >${userDetail.userDesc }</textarea></td>
		</tr>
				
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="20%" class="align-r">人员当前工作配额：</td>
		<td width="50%">${userDetail.userRate }%</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="20%" class="align-r">人员当前工作状态：</td>
		<td width="50%">
		<c:if test="${userDetail.workStatus == '1' }">项目中</c:if>
		<c:if test="${userDetail.workStatus == '0' }">空闲</c:if>
		</td>
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
