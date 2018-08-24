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
	document.all.frm.action = "showResAddJSP.jhtml";
	if(document.all.frm.BaseCataId.value == "-1"){
		document.all.frm.searchType.value = "1";
	}
	document.all.frm.submit();
}

function keywordSearch(){
	document.all.frm.searchType.value = "3";
	document.all.frm.searchValue.value = document.all.frm.keywords.value;
	document.all.frm.target = "_self";
	document.all.frm.action = "showResAddJSP.jhtml";
	document.all.frm.submit();
}

function getSelectSingle(){
	var radioObj="";
	var radioValue="";
	var resName = "";
	var resPrice = "";
	
	radioObj = document.getElementsByName("priceId");
	for(var m = 0; m < radioObj.length; m++){
		if(radioObj[m].checked){
        	radioValue = radioObj[m].value;
        	resName = radioObj[m].value2;
        	resPrice = radioObj[m].value3;
      	}
    }
    if(radioValue == ""){
        alert("请选择项目负责人!");
        return false;
    }
    
    parent.window.opener.frm.resId.value = radioValue;
    parent.window.opener.frm.resName.value = resName;
    parent.window.opener.frm.price.value = resPrice;

    parent.window.opener.frm.resType.value = document.all.frm.resType.value;
	window.close();
}

</script>
<title>人员基价</title>
</head>
<body>
<div class="black_t">
	<div class="black_t_l"></div>
	<div class="black_t_f">人员基价列表</div>
</div>
<div class="tabcont">
		<form action="" name="frm" method="post">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tablb">
		<tr class="titletop">
		<td width="1%">&nbsp;</td>
		<td width="74%">
			<select class="select_01" name="BaseCataId" onchange="kindsSearch()">
				<option value="-1">--按资源类别查看--</option>
				<c:forEach  items="${cataList}" var="cata" varStatus="cataStatus">
					<option value="${cata.cataId}" <c:if test="${searchValue == cata.cataId}"><c:out value="selected"></c:out> </c:if>>${cata.cataName}</option>
				</c:forEach>
			</select>
			<input type="text" class="input_01" value="" name="keywords" />
			<input type="button" class="button_02" value="" onclick="keywordSearch()" />
		</td>
		<td>
			<input type="hidden" name="searchType" value="">
			<input type="hidden" name="searchValue" value="">
			<input type="hidden" name="deleteValue" value="">
			<input type="hidden" name="resType" value="2">
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
		<input type="hidden" name="priceId" >
		<c:forEach items="${page.result}" var="priceRes" varStatus="priceResStatus" >
			<tr class="white">
				<td>
					<input type="radio" value="${priceRes.priceId}" name="priceId" value2="${priceRes.resName}" value3="${priceRes.price}" >
				</td>
				<td>${priceRes.cataName}&nbsp;</td>
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
        	<ambow:pagination actionName="showResAddJSP" 
        	                  total="${page.totalPageCount}" 
        	                  num="${page.currentPageNo}" 
        	                  otherParams=""  />
    		</td>
    	</tr>
		</table>
</div>
</body>
</html>
