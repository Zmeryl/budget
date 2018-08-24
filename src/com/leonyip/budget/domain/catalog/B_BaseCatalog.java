package com.leonyip.budget.domain.catalog;

import java.util.List;

public class B_BaseCatalog {
	private long cataId;
	
	private long superId;
	
	private String cataName;
	
	private String cataType;
	
	private List<B_BasePriceHuman> humanPriceList;
	
	private List<B_BasePriceRes> resPriceList;
	
	private List<B_BasePriceOther> otherPriceList;

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

	public List<B_BasePriceHuman> getHumanPriceList() {
		return humanPriceList;
	}

	public void setHumanPriceList(List<B_BasePriceHuman> humanPriceList) {
		this.humanPriceList = humanPriceList;
	}

	public List<B_BasePriceRes> getResPriceList() {
		return resPriceList;
	}

	public void setResPriceList(List<B_BasePriceRes> resPriceList) {
		this.resPriceList = resPriceList;
	}

	public List<B_BasePriceOther> getOtherPriceList() {
		return otherPriceList;
	}

	public void setOtherPriceList(List<B_BasePriceOther> otherPriceList) {
		this.otherPriceList = otherPriceList;
	}
}
