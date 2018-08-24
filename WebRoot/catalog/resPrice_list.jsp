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
	document.all.frm.searchValue.value = document.all.frm.BaseCataId.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "resPriceAction.jhtml";
	if(document.all.frm.BaseCataId.value == "-1"){
		document.all.frm.searchType.value = "1";
	}
	document.all.frm.submit();
}

function keywordSearch(){
	document.all.frm.searchType.value = "3";
	document.all.frm.searchValue.value = document.all.frm.keywords.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "resPriceAction.jhtml";
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
	document.all.frm.priceId.value = valueStr;
	document.all.frm.target = "_self";
	document.all.frm.action = "resPriceAction!showEditPriceResJSP.jhtml";
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
		document.all.frm.action = "resPriceAction!deletePriceRes.jhtml";
		document.all.frm.submit();
	}
}
</script>
<script type="text/javascript">
parent.window.document.getElementById('msgDiv').innerHTML = "资源基价列表";
</script>
<title>人员基价</title>
</head>
<body>
<div class="tabcont">
		<form action="" name="frm" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td width="74%">
			<select class="select_01" name="BaseCataId" onchange="kindsSearch()">
				<option value="-1">--按资源类别查看--</option>
				<c:forEach  items="${cataList}" var="cata" varStatus="cataStatus">
					<option value="${cata.cataId}" <c:if test="${searchType == 2 && searchValue == cata.cataId}"><c:out value="selected"></c:out> </c:if>>${cata.cataName}</option>
				</c:forEach>
			</select>
			<input type="text" class="input_01" value="" name="keywords" />
			<input type="button" class="button_02" value="" onclick="keywordSearch()" />
		</td>
		<td>
			<a href="<%=strBase%>/catalog/resPriceAction!showAddPriceResJSP.jhtml"><img src="<%=strBase%>/images/caozuo_01.gif" />添加</a>&nbsp;&nbsp;&nbsp;&nbsp;
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
			<td width="10%" height="18" class="r-b">类别名称</td>
			<td width="10%" height="18" class="r-b">资源名称</td>
			<td width="10%" height="18" class="r-b">资源单位</td>
			<td width="10%" height="18" class="r-b">资源品牌</td>
			<td width="10%" height="18" class="r-b">供应商</td>
			<td width="10%" height="18" class="r-b">费用类型</td>
			<td width="5%" height="18" class="r-b">价格</td>
			<td width="5%" height="18" class="r-b">公摊数</td>
			<td width="10%" height="18" class="r-b">备注信息</td>
		</tr>
		<input type="hidden" name="_IDCheck" id="_IDCheck">
		<input type="hidden" name="priceId" value="0">
		<c:forEach items="${page.result}" var="priceRes" varStatus="priceResStatus" >
			<tr class="white">
				<td><input type="checkbox" value="${priceRes.priceId}" id="_IDCheck" name="_IDCheck"></td>
				<td><a href="resPriceAction!viewPriceRes.jhtml?priceId=${priceRes.priceId}">${priceRes.cataName}&nbsp;</a></td>
				<td>${priceRes.resName}&nbsp;</td>
				<td>${priceRes.resUnit}&nbsp;</td>
				<td>${priceRes.resBrand}&nbsp;</td>
				<td>${priceRes.provider}&nbsp;</td>
				<td>
				<c:if test="${priceRes.priceType == '1'}">固定值</c:if>
				<c:if test="${priceRes.priceType == '2'}">百分比</c:if>
				&nbsp;</td>
				<td>${priceRes.price}&nbsp;</td>
				<td>${priceRes.publicRate}&nbsp;</td>
				<td>${priceRes.info}&nbsp;</td>
			</tr>
		</c:forEach>
		</table>
		</form>
		<table>
    	<tr class="titletop">
    		<td>
        	<ambow:pagination actionName="resPriceAction" 
        	                  total="${page.totalPageCount}" 
        	                  num="${page.currentPageNo}" 
        	                  otherParams=""  />
    		</td>
    	</tr>
		</table>
</div>
</body>
</html>
