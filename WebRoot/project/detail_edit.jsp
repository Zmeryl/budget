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
	document.all.frm.action = "detailAction!updateDetail.jhtml";
	document.all.frm.submit();
}

function showHumanAddJSP(){
	window.open("showHumanAddJSP.jhtml");
}

function showResAddJSP(){
	window.open("showResAddJSP.jhtml");
}

function showOtherAddJSP(){
	window.open("showOtherAddJSP.jhtml");
}

function checkfrm(){
	if(document.all.frm.resName.value == "点击左侧选择" ||document.all.frm.resName.value == ""){
		alert("请选择资源");
		return false;
	}
	if(chklength(document.all.frm.resNum.value) == 0){
		alert("请填写资源数量");
		return false;
	}
	if(chkinteger(document.all.frm.resNum.value) == false){
		alert("请检查资源数量格式");
		return false;
	}
	if( chkdatestr(document.all.frm.beginDate.value)==false ){
		alert("请检查开始日期格式");
		return false;
	}
	if( chkdatestr(document.all.frm.endDate.value)==false ){
		alert("请检查结束日期格式");
		return false;
	}

	return true;
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "编辑里程碑资源";
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
		<td width="12%" class="align-r">资源名称：</td>
		<td><input type="text" class="input_01" name="resName" value="${detail.resName}" readonly="readonly" />
		<input type="button" value="人员" onclick="showHumanAddJSP()">
		<input type="button" value="资源" onclick="showResAddJSP()">
		<input type="button" value="其他" onclick="showOtherAddJSP()">
		<input type="hidden" name="resId" value="${detail.resId }" />
		<input type="hidden" name="price" value="${_price.price }" />
		<input type="hidden" name="milestoneId" value="${detail.milestone.milestoneId}" >
		<input type="hidden" name="detailId" value="${detail.detailId }" />
		<input type="hidden" name="resType" value="${detail.resType }" />
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">资源数量：</td>
		<td><input type="text" class="input_02" name="resNum" value="${detail.resNum }" />
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">使用开始时间：</td>
		<td><input type="text" class="input_01" name="beginDate" readonly="readonly" value="<fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${detail.beginDate }" />" />
		<a onclick="calendar(beginDate)"><img src="<%=strBase%>/images/icon_calendar.gif" alt="选择日期" style="cursor:hand" /></a>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">使用结束时间：</td>
		<td><input type="text" class="input_01" name="endDate" readonly="readonly" value="<fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${detail.endDate }" />" />
		<a onclick="calendar(endDate)"><img src="<%=strBase%>/images/icon_calendar.gif" alt="选择日期" style="cursor:hand" /></a>
		&nbsp;注：人员工时指计算工作日成本
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">投入程度：</td>
		<td>
		<select name="useRate" class="select_01">
			<option value="100" <c:if test="${detail.useRate == 100}">selected</c:if> >100%</option>
			<option value="90" <c:if test="${detail.useRate == 90}">selected</c:if> >90%</option>
			<option value="80" <c:if test="${detail.useRate == 80}">selected</c:if> >80%</option>
			<option value="70" <c:if test="${detail.useRate == 70}">selected</c:if> >70%</option>
			<option value="60" <c:if test="${detail.useRate == 60}">selected</c:if> >60%</option>
			<option value="50" <c:if test="${detail.useRate == 50}">selected</c:if> >50%</option>
			<option value="40" <c:if test="${detail.useRate == 40}">selected</c:if> >40%</option>
			<option value="30" <c:if test="${detail.useRate == 30}">selected</c:if> >30%</option>
			<option value="20" <c:if test="${detail.useRate == 20}">selected</c:if> >20%</option>
			<option value="10" <c:if test="${detail.useRate == 10}">selected</c:if> >10%</option>
		</select>
		</td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" onclick="doEdit()" class="button_03" value=" 保存 " />
		<input type="button" onclick="javascript:history.go(-1);" class="button_03" value=" 返回 " />
		</td>
		</tr>
		</table>
		<br />
	</form>
</div>

</body>
</html>
