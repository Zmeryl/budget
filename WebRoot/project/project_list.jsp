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
function kindsSearch(){
	document.all.frm.searchType.value = "2";
	document.all.frm.searchValue.value = document.all.frm.BaseCataId.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "projectAction.jhtml";
	if(document.all.frm.BaseCataId.value == "-1"){
		document.all.frm.searchType.value = "1";
	}
	document.all.frm.submit();
}

function keywordSearch(){
	document.all.frm.searchType.value = "3";
	document.all.frm.searchValue.value = document.all.frm.keywords.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "projectAction.jhtml";
	document.all.frm.submit();
}

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
	document.all.frm.prjId.value = valueStr;
	document.all.frm.target = "_self";
	document.all.frm.action = "projectAction!showEditProjectJSP.jhtml";
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
		document.all.frm.action = "projectAction!deleteProject.jhtml";
		document.all.frm.submit();
	}
}

function milestone_view(prjId){
	document.all.frm.prjId.value = prjId;
	document.all.frm.target = "_self";
	document.all.frm.action = "milestoneAction.jhtml";
	document.all.frm.submit();
}

function doReport(){
	var valueStr =  getSelectedMulti();
	if(valueStr == "" || valueStr.length == 0){
		alert("请选择要修改的记录");
		return false;
	}
	
	if(valueStr.indexOf(",") > 0){
		alert("请选择一条记录进行修改");
		return false;
	}
	document.all.frm.prjId.value = valueStr;
	document.all.frm.target = "_self";
	document.all.frm.action = "projectAction!prjReport.jhtml";
	document.all.frm.submit();
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "项目列表";
</script>
<title>项目管理</title>
</head>
<body>
<div class="tabcont">
		<form action="" name="frm" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td width="64%">
			<select class="select_01" name="BaseCataId" onchange="kindsSearch()">
				<option value="-1">--按项目所属部门查看--</option>
				<c:forEach  items="${deptList}" var="dept" varStatus="deptStatus">
					<option value="${dept.deptId}" <c:if test="${searchType==0 &&searchValue == dept.deptId}"><c:out value="selected"></c:out> </c:if>>${dept.deptName}</option>
				</c:forEach>
			</select>
			<input type="text" class="input_01" value="" name="keywords" />
			<input type="button" class="button_02" value="" onclick="keywordSearch()" />
		</td>
		<td>
			<a href="<%=strBase%>/project/projectAction!showAddProjectJSP.jhtml"><img src="<%=strBase%>/images/caozuo_01.gif" />添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="doEdit()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_02.gif" />修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="doDelete()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_03.gif" />删除</a>
			<a onclick="doReport()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_04.gif" />成本报告</a>
			<input type="hidden" name="searchType" value="">
			<input type="hidden" name="searchValue" value="">
			<input type="hidden" name="deleteValue" value="">
		</td>
		</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titlelb">
			<td width="1%" class="r-b">&nbsp;</td>
			<td width="10%" height="18" class="r-b">项目名称</td>
			<td width="10%" height="18" class="r-b">所属部门</td>
			<td width="8%" height="18" class="r-b">负责人</td>
			<td width="12%" height="18" class="r-b">计划开始时间</td>
			<td width="12%" height="18" class="r-b">计划结束时间</td>
			<td width="12%" height="18" class="r-b">预计成本</td>
			<td width="12%" height="18" class="r-b">实际开始时间</td>
			<td width="12%" height="18" class="r-b">实际结束时间</td>
			<td width="12%" height="18" class="r-b">实际成本</td>
		</tr>
		<input type="hidden" name="_IDCheck" id="_IDCheck">
		<input type="hidden" name="prjId" value="0">
		<c:forEach items="${page.result}" var="project" varStatus="projectStatus" >
			<tr class="white">
				<td><input type="checkbox" value="${project.prjId}" id="_IDCheck" name="_IDCheck"></td>
				<td><a href="#" onclick="milestone_view('${project.prjId}')">${project.prjName}&nbsp;</a></td>
				<td>${project.deptName}&nbsp;</td>
				<td>${project.managerName}&nbsp;</td>
				<td><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${project.beginDatePlan}" />&nbsp;</td>
				<td><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${project.endDatePlan}" />&nbsp;</td>
				<td>${project.sumPricePlan}元&nbsp;</td>
				<td><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${project.beginDateFact}" />&nbsp;</td>
				<td><fmt:formatDate	type="date" pattern ="yyyy-MM-dd" value="${project.endDateFact}" />&nbsp;</td>
				<td>${project.sumPriceFact}元&nbsp;</td>
			</tr>
		</c:forEach>
		</table>
		</form>
		<table>
    	<tr class="titletop">
    		<td>
        	<ambow:pagination actionName="projectAction" 
        	                  total="${page.totalPageCount}" 
        	                  num="${page.currentPageNo}" 
        	                  otherParams=""  />
    		</td>
    	</tr>
		</table>
</div>
</body>
</html>
