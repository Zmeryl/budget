package com.leonyip.budget.web.action.catalog;

import java.util.List;

import com.leonyip.budget.domain.catalog.B_BaseCatalog;
import com.leonyip.budget.domain.catalog.B_BasePriceOther;
import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.budget.web.action.function.S_CheckFunctionAction;
import com.leonyip.core.dao.support.Page;

public class B_BasePriceOtherAction extends B_BasePriceAction {
	
	private static final long serialVersionUID = 8505700085129175786L;
	
	private static final String SYS_RES_NAME = "otherprice";
	
	private long priceId;
	
	private long cataId;
	
	private String resName;
	
	private String resUnit;
	
	private String priceType;
	
	private double price;
	
	private int publicRate;
	
	private String info;
	
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
			page = priceOtherService.getAllPriceOtherPage(pageNo);	
		}else if(searchType.equals("2")){
			page = priceOtherService.getPriceOtherPageByKind(pageNo, Long.parseLong(searchValue));
		}else{
			page = priceOtherService.getPriceOtherPageByKeywords(pageNo, searchValue);
		}
		
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_OTHER);
		
		this.setRequestAttribute("cataList", cataList);
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		this.setRequestAttribute("searchType", searchType);
		return SUCCESS;
	}
	
	public String showAddPriceOtherJSP(){
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showAddPriceOtherJSP";
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_OTHER);
		this.setRequestAttribute("cataList", cataList);
		
		return result;
	}
	
	public String addPriceOther() throws Exception{
		if(!checkName(resName)){
			this.setRequestAttribute("_error_message", "资源名称已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addPriceOther";
		
		B_BaseCatalog catalog = catalogService.get(cataId);
		
		B_BasePriceOther priceModel = new B_BasePriceOther();
		priceModel.setCatalog(catalog);
		priceModel.setCataName(catalog.getCataName());
		priceModel.setResName(resName);
		priceModel.setResUnit(resUnit);
		priceModel.setPriceType(priceType);
		priceModel.setPrice(price);
		priceModel.setPublicRate(publicRate);
		priceModel.setInfo(info);
		priceOtherService.save(priceModel);
		
		return result;
	}
	
	public String showEditPriceOtherJSP(){
		
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showEditPriceOtherJSP";
		B_BasePriceOther priceOther = priceOtherService.get(priceId);
		this.setRequestAttribute("priceOther", priceOther);
		
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_OTHER);
		this.setRequestAttribute("cataList", cataList);
		
		return result;
	}
	
	public String updatePriceOther(){
//		if(!checkName(resName)){
//			this.setRequestAttribute("_error_message", "资源名称已存在，请检查后重新操作！");
//			return "error_function";
//		}
		
		String result = "updatePriceOther";
		B_BaseCatalog catalog = catalogService.get(cataId);
		
		B_BasePriceOther priceModel = new B_BasePriceOther();
		priceModel.setPriceId(priceId);
		priceModel.setCatalog(catalog);
		priceModel.setCataName(catalog.getCataName());
		priceModel.setResName(resName);
		priceModel.setResUnit(resUnit);
		priceModel.setPriceType(priceType);
		priceModel.setPrice(price);
		priceModel.setPublicRate(publicRate);
		priceModel.setInfo(info);
		priceOtherService.save(priceModel);
		
		return result;
	}
	
	public String deletePriceOther(){
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "deletePriceOther";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			priceOtherService.removeById(deleteId);
		}
		return result;
	}
	
	public String viewPriceOther(){
		String result = "viewPriceOther";
		B_BasePriceOther priceOther = priceOtherService.get(priceId);
		this.setRequestAttribute("priceOther", priceOther);
		
		return result;
	}
	
	private boolean checkName(String name){
		if(name == null){
			return true;
		}
		B_BasePriceOther priceOther = priceOtherService.getPriceByName(name);
		if(priceOther == null){
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

	public long getCataId() {
		return cataId;
	}

	public void setCataId(long cataId) {
		this.cataId = cataId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResUnit() {
		return resUnit;
	}

	public void setResUnit(String resUnit) {
		this.resUnit = resUnit;
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
}