package com.leonyip.budget.web.action.department;

import java.util.List;

import com.leonyip.budget.domain.department.B_Department;
import com.leonyip.budget.service.department.B_DepartmentService;
import com.leonyip.budget.web.action.function.S_CheckFunctionAction;
import com.leonyip.core.dao.support.Page;
import com.leonyip.core.web.action.BaseAction;

public class B_DepartmentAction extends BaseAction {

	private static final long serialVersionUID = 8375683627883912066L;
	
	private static final String SYS_RES_NAME = "department";
	
	private long deptId;
	
	private long superId;
	
	private String deptName;
	
	private String deptInfo;
	
	private B_DepartmentService departmentService;
	
	private int pageNo;
	
	private String searchType;
	
	private String searchValue;
	
	private String deleteValue;
	
	
	
	
	
	public B_DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(B_DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}
		
	public long getSuperId() {
		return superId;
	}

	public void setSuperId(long superId) {
		this.superId = superId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptInfo() {
		return deptInfo;
	}

	public void setDeptInfo(String deptInfo) {
		this.deptInfo = deptInfo;
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
			page = departmentService.getAllDepartmentPage(pageNo);	
		}else if(searchType.equals("2")){
			
			page = departmentService.getDepartmentPageByKind(pageNo, Long.parseLong(searchValue));
		}else{
			page = departmentService.getDepartmentPageByKeywords(pageNo, searchValue);
			searchValue="";
		}
		
		List<B_Department> deptList = departmentService.getAllDepartment();
		this.setRequestAttribute("deptList", deptList);
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		return SUCCESS;
	}
	
	public String viewDepartment(){
		String result = "viewDepartment";
		B_Department dept =(B_Department) departmentService.get(deptId);
		this.setRequestAttribute("dept", dept);
		return result;
	}
	
	public String getDeptList(){
		String result = "getDeptList"; 
		List<B_Department> deptList = departmentService.getAllDepartment();
		this.setRequestAttribute("deptList", deptList);
		
		return result;
	}
	
	public String addDepartment() throws Exception{
		if(deptName == null || deptName.trim().length() == 0){
			throw new NullPointerException("deptName is null...");
		}
		if(!checkDeptName(deptName)){
			this.setRequestAttribute("_error_message", "部门名称已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addDepartment";
		B_Department dept = new B_Department();
		dept.setDeptName(deptName);
		dept.setSuperId(superId);
		dept.setDeptInfo(deptInfo);
		departmentService.save(dept);
		return result;
	}
	
	public String deleteDepartment(){
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		String result = "deleteDepartment";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			
			departmentService.removeById(deleteId);
		}
		return result;
	}
	
	public String updateDepartment(){
		
		if(!checkDeptName(deptName)){
			this.setRequestAttribute("_error_message", "部门名称已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "updateDepartment";
		B_Department dept = new B_Department();
		dept.setDeptId(deptId);
		dept.setDeptName(deptName);
		dept.setSuperId(superId);
		dept.setDeptInfo(deptInfo);
		departmentService.update(dept);
		return result;
	}
	
	public String showAddDepartmentJSP(){
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showAddDepartmentJSP";
		List<B_Department> deptList = departmentService.getAllDepartment();
		this.setRequestAttribute("deptList", deptList);
		
		return result;
	}
	
	public String showEditDepartmentJSP(){
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showEditDepartmentJSP";
		B_Department dept = departmentService.get(deptId);
		this.setRequestAttribute("dept", dept);
		List<B_Department> deptList = departmentService.getAllDepartment();
		this.setRequestAttribute("deptList", deptList);
		
		return result;
	}
	
	private boolean checkDeptName(String name){
		B_Department dept = departmentService.getDeptByName(name);
		if(dept == null){
			return true;
		}else{
			return false;
		}
	}
}