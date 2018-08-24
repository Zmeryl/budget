package com.leonyip.budget.domain.catalog;

public class B_BasePriceRes {
	private long priceId;
	
	private B_BaseCatalog catalog;
	
	private String cataName;
	
	private String resName;
	
	private String resUnit;
	
	private String resBrand;
	
	private String provider;
	
	private String priceType;
	
	private double price;
	
	private Integer publicRate;
	
	private String info;

	public long getPriceId() {
		return priceId;
	}

	public void setPriceId(long priceId) {
		this.priceId = priceId;
	}
	
	
	public B_BaseCatalog getCatalog() {
		return catalog;
	}

	public void setCatalog(B_BaseCatalog catalog) {
		this.catalog = catalog;
	}

	public String getCataName() {
		return cataName;
	}

	public void setCataName(String cataName) {
		this.cataName = cataName;
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

	public Integer getPublicRate() {
		return publicRate;
	}

	public void setPublicRate(Integer publicRate) {
		this.publicRate = publicRate;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
