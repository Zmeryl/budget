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
parent.window.document.getElementById('msgDiv').innerHTML = "项目成本报告";
</script>
<title>成本报告</title>
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

		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="projectlb">
		
		<tr class="gray">
		<td width="5%">&nbsp;</td>
		<td width="10%" class="align-r">项目名称：</td>
		<td width="10%" class="align-l">${prj.prjName }</td>
		<td width="10%" class="align-r">负责人：</td>
		<td width="10%" class="align-l">${prj.managerName }</td>
		<td width="10%" class="align-r">所属部门：</td>
		<td width="10%" class="align-l">${prj.deptName }</td>
		</tr>
		
		<tr class="white">
		<td width="5%">&nbsp;</td>
		<td width="10%" class="align-r">预计开始时间：</td>
		<td width="10%" class="align-l"><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${prj.beginDatePlan }" /></td>
		<td width="10%" class="align-r">预计结束时间：</td>
		<td width="10%" class="align-l"><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${prj.endDatePlan }" /></td>
		<td width="10%" class="align-r">项目计划费用：</td>
		<td width="10%" class="align-l">${prj.sumPricePlan }</td>
		</tr>
		
		<tr class="gray">
		<td width="5%">&nbsp;</td>
		<td width="10%" class="align-r">实际开始时间：</td>
		<td width="10%" class="align-l"><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${prj.beginDateFact }" /></td>
		<td width="10%" class="align-r">实际结束时间：</td>
		<td width="10%" class="align-l"><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${prj.endDateFact }" /></td>
		<td width="10%" class="align-r">项目实际费用：</td>
		<td width="10%" class="align-l">${prj.sumPriceFact }</td>
		</tr>
		
		<tr class="white">
		<td width="5%">&nbsp;</td>
		<td class="align-r" valign="top">项目说明：</td>
		<td class="align-l" colspan="5">s${prj.prjDesc }&nbsp;</td>
		</tr>

		</table>
		
		 <br><br>
		 
		
		
		<c:forEach items="${prj.milestoneList}" var="milestone">
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		
		<tr class="gray">
		<td width="5%">&nbsp;</td>
		<td width="10%" class="align-r">里程碑名称：</td>
		<td width="10%" class="align-l">${milestone.milestoneName }&nbsp;</td>
		<td width="10%" class="align-r">&nbsp;</td>
		<td width="10%" class="align-l">&nbsp;</td>
		<td width="10%" class="align-r">&nbsp;</td>
		<td width="10%" class="align-l">&nbsp;</td>
		</tr>
		
		<tr class="gray">
		<td width="5%">&nbsp;</td>
		<td width="10%" class="align-r">预计开始时间：</td>
		<td width="10%" class="align-l"><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.endDatePlan }" /></td>
		<td width="10%" class="align-r">预计结束时间：</td>
		<td width="10%" class="align-l"><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.endDatePlan }" /></td>
		<td width="10%" class="align-r">预计成本：</td>
		<td width="10%" class="align-l">${milestone.pricePlan }&nbsp;</td>
		</tr>
		
		<tr class="gray">
		<td width="5%">&nbsp;</td>
		<td width="10%" class="align-r">实际开始时间：</td>
		<td width="10%" class="align-l"><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.endDateFact }" /></td>
		<td width="10%" class="align-r">实际结束时间：</td>
		<td width="10%" class="align-l"><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.endDateFact }" /></td>
		<td width="10%" class="align-r">实际成本：</td>
		<td width="10%" class="align-l">${milestone.priceFact }&nbsp;</td>
		</tr>
		
		<tr class="gray">
		<td width="5%">&nbsp;</td>
		<td width="10%" class="align-r">里程碑说明：</td>
		<td width="10%" class="align-l">${milestone.milestoneDesc }&nbsp;</td>
		<td width="10%" class="align-r">变更说明：</td>
		<td width="10%" class="align-l">${milestone.modifyDesc }&nbsp;</td>
		<td width="10%" class="align-r">&nbsp;</td>
		<td width="10%" class="align-l">&nbsp;</td>
		</tr>

		</table>
		<br><br>
		</c:forEach>
		  
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="gray">
		<td colspan="100" align="center"><input type="button" onclick="javascript:history.go(-1);" class="button_03" value=" 返回 " /></td>
		</tr>
		</table>
		<br />
	</form>
</div>

</body>
</html>
