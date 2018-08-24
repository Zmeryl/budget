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
	if(document.all.frm.cataId.value == "0" || document.all.frm.deptRoleId.value == "0"){
		alert("请选择要添加人员基价的所属类别和角色");
		return false;
	}
	document.all.frm.target = "_self";
	document.all.frm.action = "humanPriceAction!updatePriceHuman.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "查看人员基价";
</script>
<title>修改基价</title>
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
		<td width="12%" class="align-r">基价类别：</td>
		<td>${priceHuman.cataName}</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">姓名：</td>
		<td>${priceHuman.userName}</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">角色：</td>
		<td>${priceHuman.roleName}</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">费用类型：</td>
		<td>
			<c:if test="${priceHuman.priceType == '1' }">固定值</c:if>
			<c:if test="${priceHuman.priceType == '2' }">百分比</c:if>
		</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">费用：</td>
		<td>${priceHuman.price }元</td>
		</tr>
		
		<tr class="gray">
		<td width="10%">&nbsp;</td>
		<td width="12%" class="align-r">公摊数：</td>
		<td>${priceHuman.publicRate }</td>
		</tr>
		
		<tr class="white">
		<td>&nbsp;</td>
		<td class="align-r align-t">备注信息：</td>
		<td>${priceHuman.info }</td>
		</tr>

		
		<tr class="gray">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" onclick="javascript:history.go(-1);" class="button_03" value=" 返回 " />
		</td>
		</tr>
		</table>
		<br />
	</form>
</div>

</body>
</html>
