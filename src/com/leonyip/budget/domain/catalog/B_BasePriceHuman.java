package com.leonyip.budget.domain.catalog;

import com.leonyip.budget.domain.department.B_DeptRole;
import com.leonyip.budget.domain.user.B_User;

public class B_BasePriceHuman {
	private long priceId;
	
	private B_BaseCatalog catalog;
	
	private String cataName;
	
	private B_DeptRole deptRole;
	
	private String roleName;
	
	private String priceType;
	
	private double price;
	
	private Integer publicRate;
	
	private String info;
	
	private B_User user;
	
	private String userName;

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
	
	public B_DeptRole getDeptRole() {
		return deptRole;
	}

	public void setDeptRole(B_DeptRole deptRole) {
		this.deptRole = deptRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public B_User getUser() {
		return user;
	}

	public void setUser(B_User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
