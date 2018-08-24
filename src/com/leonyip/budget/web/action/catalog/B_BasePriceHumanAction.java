package com.leonyip.budget.web.action.catalog;

import java.util.List;

import com.leonyip.budget.domain.catalog.B_BaseCatalog;
import com.leonyip.budget.domain.catalog.B_BasePriceHuman;
import com.leonyip.budget.domain.department.B_DeptRole;
import com.leonyip.budget.domain.user.B_User;
import com.leonyip.budget.service.department.B_DeptRoleService;
import com.leonyip.budget.service.user.B_UserService;
import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.budget.web.action.function.S_CheckFunctionAction;
import com.leonyip.core.dao.support.Page;

public class B_BasePriceHumanAction extends B_BasePriceAction {

	private static final long serialVersionUID = -9057119742215653768L;
	
	private static final String SYS_RES_NAME = "humanprice";
	
	private long priceId;
	
	private String priceType;
	
	private double price;
	
	private int publicRate;
	
	private String info;
	
	private B_DeptRoleService deptRoleService;
	
	private long cataId;
	
	private long deptRoleId;
	
	private long userId;
	
	private String userName;
	
	private B_UserService userService;
	
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
			page = priceHumanService.getAllPriceHumanPage(pageNo);	
		}else if(searchType.equals("2")){
			page = priceHumanService.getPriceHumanPageByKind(pageNo, Long.parseLong(searchValue));
		}else{
			page = priceHumanService.getPriceHumanPageByKeywords(pageNo, searchValue);
		}
		
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_HUMAN);
		
		this.setRequestAttribute("cataList", cataList);
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		this.setRequestAttribute("searchType", searchType);
		return SUCCESS;
	}
	
	public String showAddPriceHumanJSP(){
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showAddPriceHumanJSP";
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_HUMAN);
		this.setRequestAttribute("cataList", cataList);
		List<B_DeptRole> deptRoleList = deptRoleService.getAll();
		this.setRequestAttribute("deptRoleList", deptRoleList);
		
		return result;
	}
	
	public String addPriceHuman() throws Exception{
		
		if(!checkName(userName)){
			this.setRequestAttribute("_error_message", "人员名称已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addPriceHuman";
		
		B_BaseCatalog catalog = catalogService.get(cataId);
		B_DeptRole deptRole = deptRoleService.get(deptRoleId);
		B_User user = userService.get(userId);
		
		B_BasePriceHuman priceModel = new B_BasePriceHuman();
		priceModel.setCatalog(catalog);
		priceModel.setCataName(catalog.getCataName());
		priceModel.setDeptRole(deptRole);
		priceModel.setRoleName(deptRole.getDeptRoleName());
		priceModel.setPriceType(priceType);
		priceModel.setPrice(price);
		priceModel.setPublicRate(publicRate);
		priceModel.setInfo(info);
		priceModel.setUser(user);
		priceModel.setUserName(userName);
		
		priceHumanService.save(priceModel);
		
		return result;
	}
	
	public String showEditPriceHumanJSP(){
		
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showEditPriceHumanJSP";
		B_BasePriceHuman priceHuman = priceHumanService.get(priceId);
		this.setRequestAttribute("priceHuman", priceHuman);
		
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_HUMAN);
		this.setRequestAttribute("cataList", cataList);
		
		List<B_DeptRole> deptRoleList = deptRoleService.getAll();
		this.setRequestAttribute("deptRoleList", deptRoleList);
		
		return result;
	}
	
	public String updatePriceHuman(){
//		if(!checkName(userName)){
//			this.setRequestAttribute("_error_message", "人员名称已存在，请检查后重新操作！");
//			return "error_function";
//		}
		
		String result = "updatePriceHuman";
		B_BaseCatalog catalog = catalogService.get(cataId);
		B_DeptRole deptRole = deptRoleService.get(deptRoleId);
		B_User user = userService.get(userId);
		
		B_BasePriceHuman priceModel = new B_BasePriceHuman();
		priceModel.setPriceId(priceId);
		priceModel.setCatalog(catalog);
		priceModel.setCataName(catalog.getCataName());
		priceModel.setDeptRole(deptRole);
		priceModel.setRoleName(deptRole.getDeptRoleName());
		priceModel.setPriceType(priceType);
		priceModel.setPrice(price);
		priceModel.setPublicRate(publicRate);
		priceModel.setInfo(info);
		priceModel.setUser(user);
		priceModel.setUserName(userName);
		
		priceHumanService.save(priceModel);
		
		return result;
	}
	
	public String deletePriceHuman(){
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "deletePriceHuman";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			priceHumanService.removeById(deleteId);
		}
		return result;
	}
	
	public String viewPriceHuman(){
		String result = "viewPriceHuman";
		B_BasePriceHuman priceHuman = priceHumanService.get(priceId);
		this.setRequestAttribute("priceHuman", priceHuman);
		
		return result;
	}
	
	private boolean checkName(String name){
		if(name == null){
			return true;
		}
		B_BasePriceHuman priceHuman = priceHumanService.getPriceByName(name);
		if(priceHuman == null){
			return true;
		}else{
			return false;
		}
	}
	
	public long getPriceId() {
		return priceId;
	}

	public void setPriceId(long priceId) {
		this.priceId = priceId;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPublicRate() {
		return publicRate;
	}

	public void setPublicRate(int publicRate) {
		this.publicRate = publicRate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public B_DeptRoleService getDeptRoleService() {
		return deptRoleService;
	}

	public void setDeptRoleService(B_DeptRoleService deptRoleService) {
		this.deptRoleService = deptRoleService;
	}

	public long getCataId() {
		return cataId;
	}

	public void setCataId(long cataId) {
		this.cataId = cataId;
	}

	public long getDeptRoleId() {
		return deptRoleId;
	}

	public void setDeptRoleId(long deptRoleId) {
		this.deptRoleId = deptRoleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public B_UserService getUserService() {
		return userService;
	}

	public void setUserService(B_UserService userService) {
		this.userService = userService;
	}
}