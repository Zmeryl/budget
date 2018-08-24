package com.leonyip.budget.domain.user;

import java.util.Date;

public class B_UserDetail {
	private long userId;
	
	private String realName;
	
	private String sex;
	
	private Date joinTime;
	
	private String userType;
	
	private double userPrice;
	
	private String userDesc;
	
	private int userRate;
	
	private String workStatus;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public double getUserPrice() {
		return userPrice;
	}

	public void setUserPrice(double userPrice) {
		this.userPrice = userPrice;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public int getUserRate() {
		return userRate;
	}

	public void setUserRate(int userRate) {
		this.userRate = userRate;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	
	
}
