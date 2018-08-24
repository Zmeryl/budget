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
function kindsSearch(){
	document.all.frm.searchType.value = "2";
	document.all.frm.searchValue.value = document.all.frm.baseDeptId.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "userAction.jhtml";
	if(document.all.frm.baseDeptId.value == "-1"){
		document.all.frm.searchType.value = "1";
	}
	document.all.frm.submit();
}

function keywordSearch(){
	document.all.frm.searchType.value = "3";
	document.all.frm.searchValue.value = document.all.frm.keywords.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "userAction.jhtml";
	document.all.frm.submit();
}


function getSelectSingle(){
	var radioObj="";
	var radioValue="";
	var managerName = "";
	radioObj = document.getElementsByName("userId");
	for(var m = 0; m < radioObj.length; m++){
		if(radioObj[m].checked){
        	radioValue = radioObj[m].value;
        	managerName = radioObj[m].value2;
      	}
    }
    if(radioValue == ""){
        alert("请选择项目负责人!");
        return false;
    }
    
    parent.window.opener.frm.managerId.value = radioValue;
    parent.window.opener.frm.managerName.value = managerName;

	window.close();
}
</script>
<title>人员管理</title>
</head>
<body>
<div class="black_t">
	<div class="black_t_l"></div>
	<div class="black_t_f">人员列表</div>
</div>
<div class="tabcont">
		<form action="" name="frm" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td width="74%">
			<select class="select_01" name="baseDeptId" onchange="kindsSearch()">
				<option value="-1">--分类部门查看--</option>
				<c:forEach  items="${deptList}" var="baseDept" varStatus="BaseDeptStatus">
					<option value="${baseDept.deptId}" <c:if test="${searchValue == baseDept.deptId}"><c:out value="selected"></c:out> </c:if>>${baseDept.deptName}</option>
				</c:forEach>
			</select>
			<input type="text" class="input_01" value="" name="keywords" />
			<input type="button" class="button_02" value="" onclick="keywordSearch()" />
		</td>
		<td>
<!--			<a href="<%=strBase%>/user/userAction!showAddUserJSP.jhtml"><img src="<%=strBase%>/images/caozuo_01.gif" />添加</a>&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--			<a onclick="doEdit()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_02.gif" />修改</a>&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--			<a onclick="doDelete()" style="cursor:hand"><img src="<%=strBase%>/images/caozuo_03.gif" />删除</a>-->
			<input type="hidden" name="searchType" value="">
			<input type="hidden" name="searchValue" value="">
			<input type="hidden" name="deleteValue" value="">
		</td>
		</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titlelb">
			<td width="1%" class="r-b">&nbsp;</td>
			<td width="10%" height="18" class="r-b">登录名</td>
			<td width="10%" height="18" class="r-b">姓名</td>
			<td width="10%" class="r-b">部门角色</td>
			<td width="10%" class="r-b">系统角色</td>
			<td width="10%" class="r-b">用户状态</td>
			<td width="20%" class="r-b">上一次登录时间</td>
		</tr>
		<input type="hidden" name="userId" >
		<c:forEach items="${page.result}" var="user" varStatus="userStatus" >
			<tr class="white">
				<td><input type="radio" value="${user.userId}" name="userId" value2="<c:forEach items="${user.userDetailList}" var="userDetail">${userDetail.realName}</c:forEach>" ></td>
				<td><a href="#">${user.loginName}&nbsp;</a></td>
				<td><a href="#">
				<c:forEach items="${user.userDetailList}" var="userDetail">${userDetail.realName}</c:forEach>
				&nbsp;</a></td>
				<td>${user.deptRoleName}&nbsp;</td>
				<td>${user.sysRoleName}&nbsp;</td>
				<td><c:if test="${user.status == '1'}">正常</c:if><c:if test="${user.status == '0'}">停用</c:if>&nbsp;</td>
				<td>${user.lastLoginTime}&nbsp;</td>
			</tr>
		</c:forEach>
		
		<tr class="gray">
		<td colspan="100" align="center">
		<input type="button" onclick="getSelectSingle()" class="button_03" value=" 确定 " />
		<input type="button" onclick="javascript:window.close();" class="button_03" value=" 关闭窗口 " />
		</td>
		</tr>
		
		</table>
		</form>
		<table>
    	<tr class="titletop">
    		<td>
        	<ambow:pagination actionName="userAction" 
        	                  total="${page.totalPageCount}" 
        	                  num="${page.currentPageNo}" 
        	                  otherParams=""  />
    		</td>
    	</tr>
		</table>
</div>
</body>
</html>
