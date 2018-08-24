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
<script type="text/javascript">
function getSelectedMulti(){
    var checkNumber = 0;
    var checkedCode = "";
    var chkLength =  document.all.frm._IDCheck.length;
    for( i=0; i<chkLength; i++ ){
      if( document.all.frm._IDCheck[i].checked ){
        checkNumber++;
        if(checkNumber==1){
          checkedCode =  document.all.frm._IDCheck[i].value;
        }else{
          checkedCode = checkedCode + "," + document.all.frm._IDCheck[i].value;
        }
      }
    }
    return checkedCode;
}

function doEdit(){
	var valueStr =  getSelectedMulti();
	if(valueStr == "" || valueStr.length == 0){
		alert("请选择要修改的记录");
		return false;
	}
	
	if(valueStr.indexOf(",") > 0){
		alert("请选择一条记录进行修改");
		return false;
	}
	document.all.frm.milestoneId.value = valueStr;
	document.all.frm.target = "_self";
	document.all.frm.action = "milestoneAction!showEditMilestoneJSP.jhtml";
	document.all.frm.submit();
}

function doDelete(){
	var valueStr =  getSelectedMulti();
	if(valueStr == "" || valueStr.length == 0){
		alert("请选择要删除的记录");
		return false;
	}
	if(confirm("确定删除？")==true){
		document.all.frm.deleteValue.value = valueStr;
		document.all.frm.target = "_self";
		document.all.frm.action = "milestoneAction!deleteMilestone.jhtml";
		document.all.frm.submit();
	}
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "里程碑列表";
</script>
<title>里程碑</title>
</head>
<body>
<div class="tabcont">
		<form action="" name="frm" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td width="74%">&nbsp;</td>
		<td>
			<a href="<%=strBase%>/project/milestoneAction!showAddMilestoneJSP.jhtml?prjId=${prj.prjId}"><img src="<%=strBase%>/images/caozuo_01.gif" />添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="doEdit()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_02.gif" />修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="doDelete()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_03.gif" />删除</a>
			<input type="hidden" name="deleteValue" value="">
			<input type="hidden" name="prjId" value="${prj.prjId}" >
		</td>
		</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titlelb">
			<td width="1%" class="r-b">&nbsp;</td>
			<td width="10%" height="18" class="r-b">里程碑名称</td>
			<td width="10%" height="18" class="r-b">计划开始时间</td>
			<td width="10%" height="18" class="r-b">计划结束时间</td>
			<td width="10%" height="18" class="r-b">实际开始时间</td>
			<td width="10%" height="18" class="r-b">实际结束时间</td>
			<td width="10%" height="18" class="r-b">预计成本</td>
			<td width="10%" height="18" class="r-b">实际成本</td>
		</tr>
		<input type="hidden" name="_IDCheck" id="_IDCheck">
		<input type="hidden" name="milestoneId" value="0">
		<c:forEach items="${page.result}" var="milestone" varStatus="milestoneStatus" >
			<tr class="white">
				<td><input type="checkbox" value="${milestone.milestoneId}" id="_IDCheck" name="_IDCheck"></td>
				<td><a href="detailAction.jhtml?milestoneId=${milestone.milestoneId}">${milestone.milestoneName}&nbsp;</a></td>
				<td><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.beginDatePlan}" />&nbsp;</td>
				<td><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.endDatePlan}" />&nbsp;</td>
				<td><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.beginDateFact}" />&nbsp;</td>
				<td><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${milestone.endDateFact}" />&nbsp;</td>
				<td>
					<c:if test="${milestone.pricePlan > 0}">
						${milestone.pricePlan}元
					</c:if>
					<c:if test="${milestone.pricePlan == 0 && milestone.priceRatePlan > 0}">
						${milestone.priceRatePlan}%
					</c:if>
					<c:if test="${milestone.pricePlan == 0 && milestone.priceRatePlan == 0}">
						${milestone.pricePlan}元
					</c:if>
					&nbsp;
				</td>
				<td>
					<c:if test="${milestone.priceFact > 0}">
						${milestone.priceFact}元
					</c:if>
					<c:if test="${milestone.priceFact == 0 && milestone.priceRateFact > 0}">
						${milestone.priceRatePlan}%
					</c:if>
					<c:if test="${milestone.priceFact == 0 && milestone.priceRateFact == 0}">
						${milestone.priceFact}元
					</c:if>
					&nbsp;
				</td>
			</tr>
		</c:forEach>
		
		</table>
		</form>
		<table>
    	<tr class="titletop">
    		<td>
        	<ambow:pagination actionName="milestoneAction" 
        	                  total="${page.totalPageCount}" 
        	                  num="${page.currentPageNo}" 
        	                  otherParams=""  />
    		</td>
    	</tr>
		</table>
		
		<table>
		<tr class="titletop">
		<td>
		<input type="button" onclick="location.href = 'projectAction.jhtml'" class="button_03" value=" 返回 " />
		</td>
		</tr>
		</table>
</div>
</body>
</html>
