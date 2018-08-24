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
	if(document.all.frm.cataId.value == "0" ){
		alert("请选择要添加人员基价的所属类别");
		return false;
	}
	document.all.frm.target = "_self";
	document.all.frm.action = "otherPriceAction!updatePriceOther.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "编辑其他资源基价";
</script>
<title>修改基价</title>
</head>

<body>

<div class="tabcont">
	<form action="" method="post" name="frm">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td>
			<select class="select_01" name="cataId">
				<option value="0">--请选择基价类别--</option>
				<c:forEach items="${cataList}" var="cata" varStatus="cataStatus" >
				<option value="${cata.cataId}" <c:if test="${cata.cataId == priceOther.catalog.cataId }">selected</c:if> >${cata.cataName}</option>
				</c:forEach>
			</select>
		</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">资源名称：</td>
		<td><input type="text" class="input_01" name="resName" value="${priceOther.resName }" />
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">资源单位：</td>
		<td><input type="text" class="input_02" name="resUnit" value="${priceOther.resUnit }" />
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">费用类型：</td>
		<td>
		<select name="priceType" class="select_01">
			<option value="1" <c:if test="${priceOther.priceType == '1' }">selected</c:if>>固定值</option>
			<option value="2" <c:if test="${priceOther.priceType == '2' }">selected</c:if>>百分比</option>
		</select>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">费用：</td>
		<td><input type="text" class="input_02" name="price" value="${priceOther.price }" /><input type="hidden" value="${priceOther.priceId }" name="priceId" >
		元
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">公摊数：</td>
		<td>
		<select class="select_02" name="publicRate">
			<option value="1" <c:if test="${priceHuman.publicRate == 1}">selected</c:if> >1</option>
			<option value="2" <c:if test="${priceHuman.publicRate == 2}">selected</c:if> >2</option>
			<option value="3" <c:if test="${priceHuman.publicRate == 3}">selected</c:if> >3</option>
			<option value="4" <c:if test="${priceHuman.publicRate == 4}">selected</c:if> >4</option>
			<option value="5" <c:if test="${priceHuman.publicRate == 5}">selected</c:if> >5</option>
			<option value="6" <c:if test="${priceHuman.publicRate == 6}">selected</c:if> >6</option>
			<option value="7" <c:if test="${priceHuman.publicRate == 7}">selected</c:if> >7</option>
			<option value="8" <c:if test="${priceHuman.publicRate == 8}">selected</c:if> >8</option>
			<option value="9" <c:if test="${priceHuman.publicRate == 9}">selected</c:if> >9</option>
			<option value="10" <c:if test="${priceHuman.publicRate == 10}">selected</c:if> >10</option>
		</select>&nbsp;（注：默认为1，即不公摊。）
		</td>
		</tr>
		
		<tr class="white">
		<td>&nbsp;</td>
		<td class="align-r align-t">备注信息：</td>
		<td><textarea class="textarea_01" name="info" >${priceOther.info }</textarea></td>
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
