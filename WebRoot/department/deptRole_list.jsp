<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="ambow" prefix="ambow"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String strBase = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=strBase%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=strBase%>/css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function kindsSearch(){
	document.all.frm.searchType.value = "2";
	document.all.frm.searchValue.value = document.all.frm.baseDeptId.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "deptRoleAction.jhtml";
	if(document.all.frm.baseDeptId.value == "-1"){
		document.all.frm.searchType.value = "1";
	}
	document.all.frm.submit();
}

function keywordSearch(){
	document.all.frm.searchType.value = "3";
	document.all.frm.searchValue.value = document.all.frm.keywords.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "deptRoleAction.jhtml";
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
	document.all.frm.deptRoleId.value = valueStr;
	document.all.frm.target = "_self";
	document.all.frm.action = "deptRoleAction!getDeptRole.jhtml";
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
		document.all.frm.action = "deptRoleAction!deleteDeptRole.jhtml";
		document.all.frm.submit();
	}
}
</script>

<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "部门角色列表";
</script>

<title>角色管理</title>
</head>
<body>
<div class="tabcont">
		<form action="" name="frm" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td width="74%">
			<select class="select_01" name="baseDeptId" onchange="kindsSearch()">
				<option value="-1">--按部门查看--</option>
				<c:forEach  items="${deptList}" var="baseDept" varStatus="BaseDeptStatus">
					<c:if test="${baseDept.superId == 0}">
						<option value="${baseDept.deptId}" <c:if test="${searchValue == baseDept.deptId}"><c:out value="selected"></c:out> </c:if>>${baseDept.deptName}</option>
					</c:if>
				</c:forEach>
			</select>
			<input type="text" class="input_01" value="" name="keywords" />
			<input type="button" class="button_02" value="" onclick="keywordSearch()" />
		</td>
		<td>
			<a href="<%=strBase%>/department/showAddDeptRoleJSP.jhtml"><img src="<%=strBase%>/images/caozuo_01.gif" />添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="doEdit()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_02.gif" />修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a onclick="doDelete()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_03.gif" />删除</a>
			<input type="hidden" name="searchType" value="">
			<input type="hidden" name="searchValue" value="">
			<input type="hidden" name="deleteValue" value="">
		</td>
		</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titlelb">
			<td width="1%" class="r-b">&nbsp;</td>
			<td width="22%" height="18" class="r-b">部门角色名称</td>
			<td width="20%" height="18" class="r-b">部门角色价格</td>
			<td width="30%" class="r-b">部门角色描述</td>
		</tr>
		<input type="hidden" name="_IDCheck" id="_IDCheck">
		<input type="hidden" name="deptRoleId" >
		<c:forEach items="${page.result}" var="deptRole" varStatus="deptRoleStatus" >
			<tr class="white">
				<td><input type="checkbox" value="${deptRole.deptRoleId}" id="_IDCheck" name="_IDCheck"></td>
				<td><a href="deptRoleAction!viewDeptRole.jhtml?deptRoleId=${deptRole.deptRoleId}">${deptRole.deptRoleName}&nbsp;</a></td>
				<td>${deptRole.deptRolePrice}&nbsp;</td>
				<td>${deptRole.deptRoleInfo}&nbsp;</td>
			</tr>
		</c:forEach>
		</table>
		</form>
		<table>
    	<tr class="titletop">
    		<td>
        	<ambow:pagination actionName="deptRoleAction" 
        	                  total="${page.totalPageCount}" 
        	                  num="${page.currentPageNo}" 
        	                  otherParams=""  />
    		</td>
    	</tr>
		</table>
</div>
</body>
</html>