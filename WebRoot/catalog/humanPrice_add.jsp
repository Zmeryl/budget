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
function doAdd(){
	if(document.all.frm.cataId.value == "0" || document.all.frm.deptRoleId.value == "0"){
		alert("请选择要添加人员基价的所属类别和角色");
		return false;
	}
	
	document.all.frm.target = "_self";
	document.all.frm.action = "humanPriceAction!addPriceHuman.jhtml";
	document.all.frm.submit();
}

function initDeptRolePrice(){
	var id = document.all.frm.deptRoleId.value;
	document.all.frm.price.value = document.getElementById("deptRolePrice@"+id).value_price;
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "添加人员基价";
</script>

<title>添加人员基价</title>
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
				<option value="${cata.cataId}">${cata.cataName}</option>
				</c:forEach>
			</select>
			<select class="select_01" name="deptRoleId" onchange="initDeptRolePrice()">
				<option value="0" >--请选择角色-</option>
				<c:forEach items="${deptRoleList}" var="deptRole" varStatus="deptRoleStatus" >
				<option id="deptRolePrice@${deptRole.deptRoleId}" value_price="${deptRole.deptRolePrice }" value="${deptRole.deptRoleId}">${deptRole.deptRoleName}</option>
				</c:forEach>
			</select>
		</td>
		</tr>
		</table>

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">人员名称：</td>
		<td><input type="text" class="input_01" name="userName" value="点击选择" readonly="readonly" onclick="window.open('chooseUserAction.jhtml')"/>
		<input type="hidden" name="userId" >
		<input type="hidden" name="userPrice" >
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">费用类型：</td>
		<td>
		<select name="priceType" class="select_01">
			<option value="1">固定值</option>
			<option value="2">百分比</option>
		</select>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">费用：</td>
		<td><input type="text" class="input_02" name="price" />
		元
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">公摊数：</td>
		<td>
		<select class="select_02" name="publicRate">
			<option value="1">1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
			<option value="9" >9</option>
			<option value="10" >10</option>
		</select>&nbsp;（注：默认为1，即不公摊。）
		</td>
		</tr>
		
		<tr class="gray">
		<td>&nbsp;</td>
		<td class="align-r align-t">备注信息：</td>
		<td><textarea class="textarea_01" name="info" ></textarea></td>
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
