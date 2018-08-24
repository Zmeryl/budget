package com.leonyip.budget.web.action.catalog;

import java.util.List;

import com.leonyip.budget.domain.catalog.B_BaseCatalog;
import com.leonyip.budget.domain.catalog.B_BasePriceRes;
import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.budget.web.action.function.S_CheckFunctionAction;
import com.leonyip.core.dao.support.Page;


public class B_BasePriceResAction extends B_BasePriceAction {

	private static final long serialVersionUID = 7726962910845876102L;
	
	private static final String SYS_RES_NAME = "resprice";
	
	private long priceId;
	
	private long cataId;
	
	private String resName;
	
	private String resUnit;
	
	private String resBrand;
	
	private String provider;
	
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
			page = priceResService.getAllPriceResPage(pageNo);	
		}else if(searchType.equals("2")){
			page = priceResService.getPriceResPageByKind(pageNo, Long.parseLong(searchValue));
		}else{
			page = priceResService.getPriceResPageByKeywords(pageNo, searchValue);
		}
		
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_RES);
		
		this.setRequestAttribute("cataList", cataList);
		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		this.setRequestAttribute("searchType", searchType);
		return SUCCESS;
	}
	
	public String showAddPriceResJSP(){
		String action = "add";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showAddPriceResJSP";
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_RES);
		this.setRequestAttribute("cataList", cataList);
		
		return result;
	}
	
	public String addPriceRes() throws Exception{
		if(!checkName(resName)){
			this.setRequestAttribute("_error_message", "资源名称已存在，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addPriceRes";
		
		B_BaseCatalog catalog = catalogService.get(cataId);
		
		B_BasePriceRes priceModel = new B_BasePriceRes();
		priceModel.setCatalog(catalog);
		priceModel.setCataName(catalog.getCataName());
		priceModel.setResName(resName);
		priceModel.setResUnit(resUnit);
		priceModel.setResBrand(resBrand);
		priceModel.setProvider(provider);
		priceModel.setPriceType(priceType);
		priceModel.setPrice(price);
		priceModel.setPublicRate(publicRate);
		priceModel.setInfo(info);
		priceResService.save(priceModel);
		
		return result;
	}
	
	public String showEditPriceResJSP(){
		
		String action = "edit";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "showEditPriceResJSP";
		B_BasePriceRes priceRes = priceResService.get(priceId);
		this.setRequestAttribute("priceRes", priceRes);
		
		List<B_BaseCatalog> cataList = catalogService.getAllByCataType(BudgetDict.CATALOG_TYPE_RES);
		this.setRequestAttribute("cataList", cataList);
		
		return result;
	}
	
	public String updatePriceRes(){
//		if(!checkName(resName)){
//			this.setRequestAttribute("_error_message", "资源名称已存在，请检查后重新操作！");
//			return "error_function";
//		}
		
		String result = "updatePriceRes";
		B_BaseCatalog catalog = catalogService.get(cataId);
		
		B_BasePriceRes priceModel = new B_BasePriceRes();
		priceModel.setPriceId(priceId);
		priceModel.setCatalog(catalog);
		priceModel.setCataName(catalog.getCataName());
		priceModel.setResName(resName);
		priceModel.setResUnit(resUnit);
		priceModel.setResBrand(resBrand);
		priceModel.setProvider(provider);
		priceModel.setPriceType(priceType);
		priceModel.setPrice(price);
		priceModel.setPublicRate(publicRate);
		priceModel.setInfo(info);
		priceResService.save(priceModel);
		
		return result;
	}
	
	public String deletePriceRes(){
		String action = "delete";
		if( !new S_CheckFunctionAction().checkUserFunction(SYS_RES_NAME, action)){
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}
		
		String result = "deletePriceRes";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			priceResService.removeById(deleteId);
		}
		return result;
	}
	
	public String viewPriceRes(){
		String result = "viewPriceRes";
		B_BasePriceRes priceRes = priceResService.get(priceId);
		this.setRequestAttribute("priceRes", priceRes);
		
		return result;
	}
	
	private boolean checkName(String name){
		if(name == null){
			return true;
		}
		B_BasePriceRes priceRes = priceResService.getPriceByName(name);
		if(priceRes == null){
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

	public String getResBrand() {
		return resBrand;
	}

	public void setResBrand(String resBrand) {
		this.resBrand = resBrand;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
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