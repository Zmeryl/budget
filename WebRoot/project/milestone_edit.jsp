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
<script type="text/javascript" src="<%=strBase%>/js/check.js"></script>
<script type="text/javascript">
function doEdit(){
	if(checkfrm() == false){
		return false;
	}
	document.all.frm.target = "_self";
	document.all.frm.action = "milestoneAction!updateMilestone.jhtml";
	document.all.frm.submit();
}

function checkfrm(){
	if(document.all.frm.milestoneName.value == ""){
		alert("请填写里程碑名称");
		return false;
	}
	if( chkdatestr(document.all.frm.beginDatePlan.value)==false ){
		alert("请检查开始日期格式");
		return false;
	}
	/*
	if( chkdatestr(document.all.frm.endDatePlan.value)==false ){
		alert("请检查结束日期格式");
		return false;
	}
	*/
	if( document.all.frm.modifyDesc.value == "" ){
		alert("请填写里程碑修改说明");
		return false;
	}
	
	return true;
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "编辑里程碑信息";
</script>
<title>修改里程碑属性</title>
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
		<td width="12%" class="align-r">里程碑名称：</td>
		<td><input type="text" class="input_01" name="milestoneName" value="${milestone.milestoneName}" />
		<input type="hidden" name="prjId" value="${milestone.prj.prjId}" >
		<input type="hidden" name="milestoneId" value="${milestone.milestoneId}" >
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">预计开始时间：</td>
		<td><input type="text" class="input_01" name="beginDatePlan" readonly="readonly" value="<fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.beginDatePlan }" />" />
		<a onclick="calendar(beginDatePlan)"><img src="<%=strBase%>/images/icon_calendar.gif" alt="选择日期" style="cursor:hand" /></a>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">预计结束时间：</td>
		<td><input type="text" class="input_01" name="endDatePlan" readonly="readonly" value="<fmt:formatDate type="date" pattern ="yyyy-MM-dd" value="${milestone.endDatePlan }" /> " />
		<a onclick="calendar(endDatePlan)"><img src="<%=strBase%>/images/icon_calendar.gif" alt="选择日期" style="cursor:hand" /></a>
		</td>
		</tr>
		
		<tr class="white">
		<td>&nbsp;</td>
		<td class="align-r align-t">里程碑说明：</td>
		<td><textarea class="textarea_01" name="milestoneDesc" readonly="readonly" >${milestone.milestoneDesc }</textarea></td>
		</tr>

		<tr class="white">
		<td>&nbsp;</td>
		<td class="align-r align-t">里程碑修改说明：</td>
		<td><textarea class="textarea_01" name="modifyDesc" >${milestone.modifyDesc }</textarea></td>
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
