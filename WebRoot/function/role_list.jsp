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
function keywordSearch(){
	document.all.frm.searchType.value = "3";
	document.all.frm.searchValue.value = document.all.frm.keywords.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "roleAction.jhtml";
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
	document.all.frm.sysRoleId.value = valueStr;
	document.all.frm.target = "_self";
	document.all.frm.action = "roleAction!showEditRoleJSP.jhtml";
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
		document.all.frm.action = "roleAction!deleteRole.jhtml";
		document.all.frm.submit();
	}
}

function editRule(sysRoleId){
	//window.open("showEditRoleFun.jhtml?sysRoleId="+sysRoleId);
	window.open("roleAction!showEditRoleJSP.jhtml?sysRoleId="+sysRoleId);
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "系统角色列表";
</script>
<title>功能管理</title>
</head>
<body>
<div class="tabcont">
		<form action="" name="frm" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td width="74%">
			<input type="text" class="input_01" value="" name="keywords" />
			<input type="button" class="button_02" value="" onclick="keywordSearch()" />
		</td>
		<td>
			<a href="<%=strBase%>/function/roleAction!showAddRoleJSP.jhtml"><img src="<%=strBase%>/images/caozuo_01.gif" />添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
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
			<td width="22%" height="18" class="r-b">角色名称</td>
			<td width="40%" class="r-b">角色描述</td>
			<td width="20%" class="r-b">配置</td>
		</tr>
		<input type="hidden" name="_IDCheck" id="_IDCheck">
		<input type="hidden" name="sysRoleId" value="0">
		<c:forEach items="${page.result}" var="role" varStatus="roleStatus" >
			<tr class="white">
				<td><input type="checkbox" value="${role.sysRoleId}" id="_IDCheck" name="_IDCheck"></td>
				<td><a href="roleAction!viewRole.jhtml?sysRoleId=${role.sysRoleId}">${role.sysRoleName}&nbsp;</a></td>
				<td>${role.sysRoleDesc}&nbsp;</td>
				<td><a style="cursor:hand" onclick="editRule('${role.sysRoleId}')" >【分配权限】</a></td>
			</tr>
		</c:forEach>
		</table>
		</form>
		<table>
    	<tr class="titletop">
    		<td>
        	<ambow:pagination actionName="roleAction" 
        	                  total="${page.totalPageCount}" 
        	                  num="${page.currentPageNo}" 
        	                  otherParams=""  />
    		</td>
    	</tr>
		</table>
</div>
</body>
</html>
