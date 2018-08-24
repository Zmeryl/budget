<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String strBase = request.getContextPath();
%>
<html>
<head>
<title>项目成本预算系统</title>
<link href="<%=strBase%>/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=strBase%>/css/content.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function editPwd(){
	document.hidform.target="FM_Name";
	document.hidform.action="editUserPwdAction!showEditUserPasswordJSP.jhtml";
	document.hidform.submit();
}
</script>
</head>
<body class="main_bg">
<div id="header">
	<div class="logoimg"><img src="<%=strBase%>/images/logo_img.jpg" /></div>
	<div class="logout"><input type="button" class="button_01" onclick="location.href='<%=strBase%>/user/userLogout.jhtml'" value="退出系统" /></div>
	<div class="logout"><input type="button" class="button_01" onclick="editPwd()" value="修改密码" /></div>
</div>
<div id="main">
		<div class="left_div">
			<div class="top_bg"></div>
			<div class="menu">
				<ul>
					<li class="title"><img src="<%=strBase%>/images/title_01.gif" /></li>
					<li><a href="<%=strBase%>/department/departmentAction.jhtml" target="FM_Name">部门管理</a></li>
					<li><a href="<%=strBase%>/department/deptRoleAction.jhtml" target="FM_Name">部门角色</a></li>
					<li><a href="<%=strBase%>/function/functionAction.jhtml" target="FM_Name">功能管理</a></li>
					<li><a href="<%=strBase%>/function/roleAction.jhtml" target="FM_Name">系统角色</a></li>
					<li><a href="<%=strBase%>/user/userAction.jhtml" target="FM_Name">人员管理</a></li>
					<li class="title"><img src="<%=strBase%>/images/title_02.gif" /></li>
					<li><a href="<%=strBase%>/catalog/catalogAction.jhtml" target="FM_Name" >基价类别管理</a></li>
					<li><a href="<%=strBase%>/catalog/humanPriceAction.jhtml" target="FM_Name" >人员基价</a></li>
					<li><a href="<%=strBase%>/catalog/resPriceAction.jhtml" target="FM_Name" >资源基价</a></li>
					<li><a href="<%=strBase%>/catalog/otherPriceAction.jhtml" target="FM_Name" >其他基价</a></li>
					<li class="title"><img src="<%=strBase%>/images/title_03.gif" /></li>
					<li><a href="<%=strBase%>/project/projectAction.jhtml" target="FM_Name" >项目管理</a></li>
				</ul>
			</div>
		</div>
		<div class="right_div">
			<div class="right_div_top"></div>
			<div class="black_t">
				<div class="black_t_l"></div>
				<div id="msgDiv" class="black_t_f">项目成本报告</div>
			</div>
			<div class="right_cont"><iframe src="<%=strBase%>/welcome.jsp" name="FM_Name" id="FM_Id" class="mainiframe" frameborder="0" scrolling="auto" ></iframe></div>
		</div>
</div>
<div class="clearboth"></div>
<div id="footer">
	<div class="foot_left"></div>
	<div class="foot_bottom"><img src="<%=strBase%>/images/bottom_logo.gif" />提供运营及支持服务，版权所有©2010</div>
</div>
<form name="hidform"></form>
</body>
</html>