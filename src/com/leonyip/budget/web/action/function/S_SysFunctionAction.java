package com.leonyip.budget.web.action.function;

import java.util.List;

import com.leonyip.budget.domain.function.S_SysFunction;
import com.leonyip.budget.domain.function.S_SysRoleFun;
import com.leonyip.budget.service.function.S_SysFunctionService;
import com.leonyip.core.dao.support.Page;
import com.leonyip.core.web.action.BaseAction;

public class S_SysFunctionAction extends BaseAction {

	private static final long serialVersionUID = -1349725628835489929L;
	
	private static final String SYS_RES_NAME = "sysfunction";

	private long funId;
	
	private String funName;
	
	private String funCode;
	
	private String funDesc;
	
	private List<S_SysRoleFun> sysRoleFunList;
	
	private S_SysFunctionService functionService;
	
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
			page = functionService.getAllFunctionPage(pageNo);	
		}else{
			page = functionService.getFunctionPageByKeywords(pageNo, searchValue);
		}
		
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		return SUCCESS;
	}
	
	public String viewFunction(){
		String result = "viewFunction";
		S_SysFunction func = functionService.get(funId);
		this.setRequestAttribute("func", func);
		return result;
	}
	
	public String addFunction() throws Exception{
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		if(funName == null || funName.trim().length() == 0){
			throw new NullPointerException("funName is null...");
		}
		
		if(!checkName(funName)){
			this.setRequestAttribute("_error_message", "功能名称已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addFunction";
		S_SysFunction func = new S_SysFunction();
		func.setFunName(funName);
		func.setFunCode(funCode);
		func.setFunDesc(funDesc);
		functionService.save(func);
		return result;
	}
	
	public String deleteFunction(){
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "deleteFunction";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			functionService.removeById(deleteId);
		}
		return result;
	}
	
	public String updateFunction(){
		if(!checkName(funName)){
			this.setRequestAttribute("_error_message", "功能名称已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "updateFunction";
		S_SysFunction func = new S_SysFunction();
		func.setFunId(funId);
		func.setFunName(funName);
		func.setFunCode(funCode);
		func.setFunDesc(funDesc);
		functionService.save(func);
		return result;
	}
	
	public String showEditFunctionJSP(){
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showEditFunctionJSP";
		S_SysFunction func = functionService.get(funId);
		
		this.setRequestAttribute("func", func);
		return result;
	}
	
	private boolean checkName(String name){
		if(name == null){
			return true;
		}
		S_SysFunction func = functionService.getFunctionByName(name);
		if(func == null){
			return true;
		}else{
			return false;
		}
	}
	
	public long getFunId() {
		return funId;
	}

	public void setFunId(long funId) {
		this.funId = funId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getFunDesc() {
		return funDesc;
	}

	public void setFunDesc(String funDesc) {
		this.funDesc = funDesc;
	}

	public List<S_SysRoleFun> getSysRoleFunList() {
		return sysRoleFunList;
	}

	public void setSysRoleFunList(List<S_SysRoleFun> sysRoleFunList) {
		this.sysRoleFunList = sysRoleFunList;
	}

	public String getAuthStr() {
		return null;
	}

	public S_SysFunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(S_SysFunctionService functionService) {
		this.functionService = functionService;
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