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
	document.all.frm.action = "showEditRoleFun2.jhtml";
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

function doAdd(){
	var valueStr =  getSelectedMulti();
	if(valueStr == "" || valueStr.length == 0){
		alert("请选择要修改的记录");
		return false;
	}
	
	document.all.frm.addFunValue.value = valueStr;
	document.all.frm.target = "_self";
	document.all.frm.action = "doRoleFunction.jhtml";
	//window.setTimeout(window.close(),100000);
	document.all.frm.submit();
}

</script>

<title>权限配置</title>
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
			<input type="hidden" name="searchType" value="">
			<input type="hidden" name="searchValue" value="">
			<input type="hidden" name="deleteValue" value="">
		</td>
		</tr>
		</table>
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titlelb">
			<td width="1%" class="r-b">&nbsp;</td>
			<td width="22%" height="18" class="r-b">功能名称</td>
			<td width="25%" class="r-b">功能代码</td>
			<td width="25%" class="r-b">功能编码</td>
		</tr>
		<input type="hidden" name="_IDCheck" id="_IDCheck">
		<input type="hidden" name="addFunValue" >
		<input type="hidden" name="sysRoleId" value="${role.sysRoleId}" />
		<c:forEach items="${page.result}" var="func" varStatus="funcStatus" >
			<tr class="white">
				<td><input type="checkbox" value="${func.funId}" id="_IDCheck" name="_IDCheck" ></td>
				<td><a href="#">${func.funName}&nbsp;</a></td>
				<td>${func.funCode}&nbsp;</td>
				<td>${func.funDesc}&nbsp;</td>
			</tr>
		</c:forEach>
		</table>
		</form>
		<table>
    	<tr class="titletop">
    		<td>
        	<ambow:pagination actionName="functionAction" 
        	                  total="${page.totalPageCount}" 
        	                  num="${page.currentPageNo}" 
        	                  otherParams="" />
    		</td>
    	</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb" align="center">
		<tr class="gray">
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>
		<input type="button" onclick="doAdd()" class="button_03" value=" 确定 " />
		<input type="button" onclick="javascript:window.close();" class="button_03" value=" 关闭 " />
		</td>
		</tr>
		</table>
		
</div>
</body>
<script type="text/javascript">

var chkLength = document.all.frm._IDCheck.length;
<c:forEach items="${roleFunList}" var="roleFunItem" varStatus="roleFunItemStatus" >

for( i=0; i<chkLength; i++ ){
	if( document.all.frm._IDCheck[i].value == '${roleFunItem.sysFunction.funId}'){
		document.all.frm._IDCheck[i].checked = "checked";
	}
}
</c:forEach>

</script>
</html>
