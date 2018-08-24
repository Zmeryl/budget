package com.leonyip.budget.web.action.user;

import java.util.Date;
import java.util.List;

import com.leonyip.budget.domain.department.B_Department;
import com.leonyip.budget.domain.department.B_DeptRole;
import com.leonyip.budget.domain.function.S_SysRole;
import com.leonyip.budget.domain.user.B_User;
import com.leonyip.budget.domain.user.B_UserDetail;
import com.leonyip.budget.service.department.B_DepartmentService;
import com.leonyip.budget.service.department.B_DeptRoleService;
import com.leonyip.budget.service.function.S_SysRoleService;
import com.leonyip.budget.service.user.B_UserDetailService;
import com.leonyip.budget.service.user.B_UserService;
import com.leonyip.budget.web.action.function.S_CheckFunctionAction;
import com.leonyip.core.dao.support.Page;
import com.leonyip.core.util.MD5;
import com.leonyip.core.web.action.BaseAction;

public class B_UserAction extends BaseAction {

	private static final long serialVersionUID = -7116114115199469684L;
	
	private static final String SYS_RES_NAME = "user";
	
	private long userId;
	
	private long deptRoleId;
	
	private String deptRoleName;
	
	private long sysRoleId;
	
	private String sysRoleName;
	
	private String loginName;
	
	private String loginPwd;
	
	private String status;
	
	private Date lastLoginTime;
	
	private Date stopTime;
	
	private Date createDate;
	
	private Date updateDate;
	
	private int pageNo;
	
	private String searchType;
	
	private String searchValue;
	
	private String deleteValue;	
	
	private List<B_UserDetail> userDetailList;
	
	private B_UserService userService;
	
	private B_UserDetailService userDetailService;
	
	private S_SysRoleService roleService;
	
	private B_DeptRoleService deptRoleService;
	
	private B_DepartmentService departmentService;
	
	public String execute() throws Exception {
		
		String action = "view";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		if(pageNo < 1){
			pageNo = 1;
		}
		Page page ;
		if(searchType == null || searchType.equals("") || searchType.equals("1")){
			page = userService.getAllUserPage(pageNo);	
		}else if(searchType.equals("2")){
			page = userService.getUserByDeptPage(pageNo, Long.parseLong(searchValue));
		}else{
			page = userService.getUserByKeywordsPage(pageNo, searchValue);
		}
		
		List<B_Department> deptList = departmentService.getAll(); 
		this.setRequestAttribute("deptList", deptList);
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		this.setRequestAttribute("searchType", searchType);
		return SUCCESS;
	}
	
	public String showAddUserJSP(){
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showAddUserJSP";
		List<S_SysRole> roleList = roleService.getAll();
		List<B_DeptRole> deptRoleList = deptRoleService.getAll();
		this.setRequestAttribute("roleList", roleList);
		this.setRequestAttribute("deptRoleList", deptRoleList);
		
		return result;
	}
	
	public String addUser(){
		if(!checkName(loginName)){
			this.setRequestAttribute("_error_message", "登录名已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addUser";
		B_User user = new B_User();
		B_DeptRole deptRole = deptRoleService.get(deptRoleId);
		user.setDeptRole(deptRole);
		user.setDeptRoleName(deptRole.getDeptRoleName());
		S_SysRole role = roleService.get(sysRoleId);
		user.setSysRole(role);
		user.setSysRoleName(role.getSysRoleName());
		user.setLoginName(loginName);
		loginPwd = MD5.crypt(loginPwd);
		user.setLoginPwd(loginPwd);
		user.setStatus(status);
		user.setLastLoginTime(new Date());
		user.setStopTime(new Date());
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		userService.save(user);
//		this.setRequestAttribute("user", user);		
		////
		B_UserDetail userDetail = new B_UserDetail();
		userDetail.setUserId(user.getUserId());
		userDetail.setRealName(user.getLoginName());
		userDetailService.save(userDetail);
		return result;
		////
	}
	
	public String showEditUserJSP(){
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showEditUserJSP";
		B_User user = userService.get(userId);
		List<S_SysRole> roleList = roleService.getAll();
		List<B_DeptRole> deptRoleList = deptRoleService.getAll();
		this.setRequestAttribute("roleList", roleList);
		this.setRequestAttribute("deptRoleList", deptRoleList);
		this.setRequestAttribute("user", user);
		return result;
	}
	
	public String updateUser(){
//		if(!checkName(loginName)){
//			this.setRequestAttribute("_error_message", "登录名已存在，请检查后重新操作！");
//			return "error_function";
//		}
		
		String result = "updateUser";
		B_User oldUser = userService.get(userId);
		userService.clear();
		B_User user = new B_User();
		user.setUserId(userId);
		B_DeptRole deptRole = deptRoleService.get(deptRoleId);
		user.setDeptRole(deptRole);
		user.setDeptRoleName(deptRole.getDeptRoleName());
		S_SysRole role = roleService.get(sysRoleId);
		user.setSysRole(role);
		user.setSysRoleName(role.getSysRoleName());
		loginPwd = MD5.crypt(loginPwd);
		user.setLoginPwd(loginPwd);
		user.setStatus(status);
		user.setUpdateDate(new Date());
		
		user.setLoginName(oldUser.getLoginName());
		user.setLastLoginTime(oldUser.getLastLoginTime());
		user.setStopTime(oldUser.getStopTime());
		user.setCreateDate(oldUser.getCreateDate());
		
		userService.save(user);
		
		return result;
	}
	
	public String deleteUser(){
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "deleteUser";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			if(userDetailService.get(deleteId) != null){
				userDetailService.removeById(deleteId);	
			}
			userService.removeById(deleteId);
		}
		return result;
	}
	
	public String viewUser(){
		String result = "viewUser";
		B_User user = userService.get(userId);
		this.setRequestAttribute("user", user);
		B_UserDetail userDetail = userDetailService.get(userId);
		this.setRequestAttribute("userDetail", userDetail);
		return result;
	}
	
	private boolean checkName(String name){
		if(name == null){
			return true;
		}
		B_User user = userService.getUserByName(name);
		if(user == null){
			return true;
		}else{
			return false;
		}
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getDeptRoleId() {
		return deptRoleId;
	}

	public void setDeptRoleId(long deptRoleId) {
		this.deptRoleId = deptRoleId;
	}

	public String getDeptRoleName() {
		return deptRoleName;
	}

	public void setDeptRoleName(String deptRoleName) {
		this.deptRoleName = deptRoleName;
	}

	public long getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getSysRoleName() {
		return sysRoleName;
	}

	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<B_UserDetail> getUserDetailList() {
		return userDetailList;
	}

	public void setUserDetailList(List<B_UserDetail> userDetailList) {
		this.userDetailList = userDetailList;
	}


	public B_UserService getUserService() {
		return userService;
	}

	public void setUserService(B_UserService userService) {
		this.userService = userService;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getDeleteValue() {
		return deleteValue;
	}

	public void setDeleteValue(String deleteValue) {
		this.deleteValue = deleteValue;
	}

	public S_SysRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(S_SysRoleService roleService) {
		this.roleService = roleService;
	}

	public B_DeptRoleService getDeptRoleService() {
		return deptRoleService;
	}

	public void setDeptRoleService(B_DeptRoleService deptRoleService) {
		this.deptRoleService = deptRoleService;
	}

	public B_UserDetailService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(B_UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	public B_DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(B_DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	
}
