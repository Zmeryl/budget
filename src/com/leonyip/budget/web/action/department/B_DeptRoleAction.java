package com.leonyip.budget.web.action.department;

import java.util.List;

import com.leonyip.budget.domain.department.B_Department;
import com.leonyip.budget.domain.department.B_DeptRole;
import com.leonyip.budget.service.department.B_DeptRoleService;
import com.leonyip.budget.web.action.function.S_CheckFunctionAction;
import com.leonyip.core.dao.support.Page;
import com.leonyip.core.web.action.BaseAction;

public class B_DeptRoleAction extends BaseAction {

	private static final long serialVersionUID = 8124636017328696964L;
	
	private static final String SYS_RES_NAME = "deptrole";
	
	private long deptRoleId;
	
	private long deptId;
	
	private String deptRoleName;
	
	private double deptRolePrice;
	
	private String deptRoleInfo;
	
	private B_DeptRoleService deptRoleService;
	
	private int pageNo;
	
	private String searchType;
	
	private String searchValue;
	
	private String deleteValue;
	

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
			page = deptRoleService.getAllDeptRolePage(pageNo);	
		}else if(searchType.equals("2")){
			page = deptRoleService.getDeptRolePageByKind(pageNo, Long.parseLong(searchValue));
		}else{
			page = deptRoleService.getDeptRolePageByKeywords(pageNo, searchValue);
		}
		
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		return SUCCESS;
	}
	
	public String viewDeptRole(){
		String result = "viewDeptRole";
		B_DeptRole deptRole = deptRoleService.get(deptRoleId);
		this.setRequestAttribute("deptRole", deptRole);
		return result;
	}
	
	public String getDeptRoleList(){
		String result = "getDeptRoleList"; 
		List<B_DeptRole> deptRoleList = deptRoleService.getAll();
		this.setRequestAttribute("deptRoleList", deptRoleList);
		return result;
	}
	
	public String getDeptRole(){
		String result = "getDeptRole";
		B_DeptRole deptRole = deptRoleService.get(deptRoleId);
		this.setRequestAttribute("deptRole", deptRole);
		return result;
	}
	
	public String addDepartmentRole(){
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		if(deptRoleName == null || deptRoleName.trim().length() == 0){
			throw new NullPointerException("deptRoleName is null...");
		}
		
		if(!checkDeptName(deptRoleName)){
			this.setRequestAttribute("_error_message", "部门角色已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addDepartmentRole";
		B_DeptRole deptRole = new B_DeptRole();
		B_Department department = new B_Department();
		department.setDeptId(deptId);
		deptRole.setDept(department);
		deptRole.setDeptRoleName(deptRoleName);
		deptRole.setDeptRolePrice(deptRolePrice);
		deptRole.setDeptRoleInfo(deptRoleInfo);
		deptRoleService.addDepartmentRole(deptRole);
		return result;
	}
	
	public String deleteDeptRole(){
		
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "deleteDeptRole";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			deptRoleService.removeById(deleteId);
		}
		return result;
	}
	
	public String updateDeptRole(){
		
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		if(!checkDeptName(deptRoleName)){
			this.setRequestAttribute("_error_message", "部门角色已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "updateDeptRole";
		B_DeptRole deptRole = new B_DeptRole();
		deptRole.setDeptRoleId(deptRoleId);
		B_Department department = new B_Department();
		department.setDeptId(deptId);
		deptRole.setDept(department);
		deptRole.setDeptRoleName(deptRoleName);
		deptRole.setDeptRolePrice(deptRolePrice);
		deptRole.setDeptRoleInfo(deptRoleInfo);
		deptRoleService.updateDepartmentRole(deptRole);
		return result;
	}
	
	private boolean checkDeptName(String name){
		B_DeptRole deptRole = deptRoleService.getDeptRoleByName(name);
		if(deptRole == null){
			return true;
		}else{
			return false;
		}
	}
	
	public long getDeptRoleId() {
		return deptRoleId;
	}

	public void setDeptRoleId(long deptRoleId) {
		this.deptRoleId = deptRoleId;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptRoleName() {
		return deptRoleName;
	}

	public void setDeptRoleName(String deptRoleName) {
		this.deptRoleName = deptRoleName;
	}

	public double getDeptRolePrice() {
		return deptRolePrice;
	}

	public void setDeptRolePrice(double deptRolePrice) {
		this.deptRolePrice = deptRolePrice;
	}

	public String getDeptRoleInfo() {
		return deptRoleInfo;
	}

	public void setDeptRoleInfo(String deptRoleInfo) {
		this.deptRoleInfo = deptRoleInfo;
	}
	
	public B_DeptRoleService getDeptRoleService() {
		return deptRoleService;
	}

	public void setDeptRoleService(B_DeptRoleService deptRoleService) {
		this.deptRoleService = deptRoleService;
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
}