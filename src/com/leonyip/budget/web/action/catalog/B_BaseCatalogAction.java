package com.leonyip.budget.web.action.catalog;

import java.util.List;

import com.leonyip.budget.domain.catalog.B_BaseCatalog;
import com.leonyip.budget.web.action.function.S_CheckFunctionAction;
import com.leonyip.core.dao.support.Page;

public class B_BaseCatalogAction extends B_BasePriceAction {

	private static final long serialVersionUID = -6896607300534191219L;
	
	private static final String SYS_RES_NAME = "catalog";

	private long cataId;
	
	private long superId;
	
	private String cataName;
	
	private String cataType;
	
	public String execute() {
		
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
			page = catalogService.getAllCatalogPage(pageNo);	
		}else if(searchType.equals("2")){
			page = catalogService.getCatalogPageByKind(pageNo, Long.parseLong(searchValue));
		}else{
			page = catalogService.getCatalogPageByKeywords(pageNo, searchValue);
		}
		
		List<B_BaseCatalog> cataList = catalogService.getAll();
		
		this.setRequestAttribute("cataList", cataList);
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		this.setRequestAttribute("searchType", searchType);
		return SUCCESS;
	}
	
	public String showAddCatalogJSP(){
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showAddCatalogJSP";
		List<B_BaseCatalog> cataList = catalogService.getAll();
		this.setRequestAttribute("cataList", cataList);
		return result;
	}
	
	public String addCatalog() throws Exception{
		if(cataName == null || cataName.trim().length() == 0){
			throw new NullPointerException("deptName is null...");
		}
		
		if(!checkName(cataName)){
			this.setRequestAttribute("_error_message", "类别名称已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addCatalog";
		B_BaseCatalog cata = new B_BaseCatalog();
		cata.setSuperId(superId);
		cata.setCataType(cataType);
		cata.setCataName(cataName);
		catalogService.save(cata);
		return result;
	}
	
	public String showEditCatalogJSP(){
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showEditCatalogJSP";
		B_BaseCatalog cata = catalogService.get(cataId);
		this.setRequestAttribute("catalog", cata);
		List<B_BaseCatalog> cataList = catalogService.getAll();
		this.setRequestAttribute("cataList", cataList);
		
		return result;
	}
	
	public String updateCatalog(){
//		if(!checkName(cataName)){
//			this.setRequestAttribute("_error_message", "类别名称已存在，请检查后重新操作！");
//			return "error_function";
//		}
		
		String result = "updateCatalog";
		B_BaseCatalog cata = new B_BaseCatalog();
		cata.setCataId(cataId);
		cata.setSuperId(superId);
		cata.setCataName(cataName);
		cata.setCataType(cataType);
		catalogService.save(cata);
		return result;
	}
	
	public String deleteCatalog(){
		
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "deleteCatalog";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			catalogService.removeById(deleteId);
		}
		return result;
	}
	
	public String viewCatalog(){
		String result = "viewCatalog";
		B_BaseCatalog cata = catalogService.get(cataId);
		this.setRequestAttribute("catalog", cata);
		
		return result;
	}
	
	private boolean checkName(String name){
		if(name == null){
			return true;
		}
		B_BaseCatalog cata = catalogService.getCatalogByName(name);
		if(cata == null){
			return true;
		}else{
			return false;
		}
	}
	
	public long getCataId() {
		return cataId;
	}

	public void setCataId(long cataId) {
		this.cataId = cataId;
	}

	public long getSuperId() {
		return superId;
	}

	public void setSuperId(long superId) {
		this.superId = superId;
	}

	public String getCataName() {
		return cataName;
	}

	public void setCataName(String cataName) {
		this.cataName = cataName;
	}

	public String getCataType() {
		return cataType;
	}

	public void setCataType(String cataType) {
		this.cataType = cataType;
	}	
}