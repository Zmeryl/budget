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
<script type="text/javascript" src="<%=strBase%>/js/calendar.js"></script>
<script type="text/javascript" src="<%=strBase%>/js/check.js"></script>
<script type="text/javascript">
function doAdd(){
	if(checkfrm() == false){
		return false;
	}
	
	if(document.all.frm.deptId.value == "0" ){
		alert("请选择要添加项目的所属部门");
		return false;
	}
	
	document.all.frm.target = "_self";
	document.all.frm.action = "projectAction!addProject.jhtml";
	document.all.frm.submit();
}

function showManagerAddJSP(){
	window.open("showManagerAddJSP.jhtml");
}

function checkfrm(){
	if(document.all.frm.prjName.value == ""){
		alert("请填写项目名称");
		return false;
	}
	
	if(document.all.frm.managerName.value == "" || document.all.frm.managerName.value == "点击选取负责人"){
		alert("请选择项目负责人");
		return false;
	}
	
	if( chkdatestr(document.all.frm.beginDatePlan.value)==false ){
		alert("请检查开始日期格式");
		return false;
	}
	
	if( chkdatestr(document.all.frm.endDatePlan.value)==false ){
		alert("请检查结束日期格式");
		return false;
	}
	
	if( document.all.frm.prjDesc.value == "" ){
		alert("请填写里程碑说明");
		return false;
	}
	
	return true;
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "添加新项目";
</script>
<title>添加新项目</title>
</head>

<body>

<div class="tabcont">
	<form action="" method="post" name="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td>
			<select class="select_01" name="deptId">
				<option value="0">--请选择项目所属部门--</option>
				<c:forEach items="${deptList}" var="dept" varStatus="deptStatus" >
				<c:if test="${dept.superId == '0'}">
					<option value="${dept.deptId}">${dept.deptName}</option>
				</c:if>
				</c:forEach>
			</select>
		</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">项目名称：</td>
		<td><input type="text" class="input_01" name="prjName" />
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">项目负责人：</td>
		<td><input type="text" class="input_01" name="managerName" value="点击选取负责人" onclick="showManagerAddJSP()" />
		<input type="hidden" name="managerId" >
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">预计开始时间：</td>
		<td><input type="text" class="input_01" name="beginDatePlan" readonly="readonly" />
		<a onclick="calendar(beginDatePlan)"><img src="<%=strBase%>/images/icon_calendar.gif" alt="选择日期" style="cursor:hand" /></a>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">预计结束时间：</td>
		<td><input type="text" class="input_01" name="endDatePlan" readonly="readonly" />
		<a onclick="calendar(endDatePlan)"><img src="<%=strBase%>/images/icon_calendar.gif" alt="选择日期" style="cursor:hand" /></a>
		</td>
		</tr>
		
		<tr class="white">
		<td>&nbsp;</td>
		<td class="align-r align-t">项目说明：</td>
		<td><textarea class="textarea_01" name="prjDesc" ></textarea></td>
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
