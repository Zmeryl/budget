package com.leonyip.budget.web.action.catalog;

import com.leonyip.budget.service.catalog.B_BaseCatalogService;
import com.leonyip.budget.service.catalog.B_BasePriceHumanService;
import com.leonyip.budget.service.catalog.B_BasePriceOtherService;
import com.leonyip.budget.service.catalog.B_BasePriceResService;
import com.leonyip.core.web.action.BaseAction;

public abstract class B_BasePriceAction extends BaseAction {

	private static final long serialVersionUID = 3291287444617231371L;

	
	public abstract String execute();
	
	protected B_BaseCatalogService catalogService;
	
	protected B_BasePriceHumanService priceHumanService;
	
	protected B_BasePriceResService priceResService;
	
	protected B_BasePriceOtherService priceOtherService;
	
	protected int pageNo;
	
	protected String searchType;
	
	protected String searchValue;
	
	protected String deleteValue;
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public B_BaseCatalogService getCatalogService() {
		return catalogService;
	}

	public void setCatalogService(B_BaseCatalogService catalogService) {
		this.catalogService = catalogService;
	}

	public B_BasePriceHumanService getPriceHumanService() {
		return priceHumanService;
	}

	public void setPriceHumanService(B_BasePriceHumanService priceHumanService) {
		this.priceHumanService = priceHumanService;
	}

	public B_BasePriceResService getPriceResService() {
		return priceResService;
	}

	public void setPriceResService(B_BasePriceResService priceResService) {
		this.priceResService = priceResService;
	}

	public B_BasePriceOtherService getPriceOtherService() {
		return priceOtherService;
	}

	public void setPriceOtherService(B_BasePriceOtherService priceOtherService) {
		this.priceOtherService = priceOtherService;
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