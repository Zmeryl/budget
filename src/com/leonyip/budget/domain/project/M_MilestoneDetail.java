package com.leonyip.budget.domain.project;

import java.util.Date;

public class M_MilestoneDetail {
	private long detailId;
	
	private M_Milestone milestone;
	
	private Long resId;
	
	private String resName;
	
	private Integer resNum;
	
	private Date beginDate;
	
	private Date endDate;
	
	private Integer useRate;
	
	private double price;
	
	private String resType;
	
	private Integer priceRate;

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public M_Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(M_Milestone milestone) {
		this.milestone = milestone;
	}

	public Long getResId() {
		return resId;
	}

	public void setResId(Long resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Integer getResNum() {
		return resNum;
	}

	public void setResNum(Integer resNum) {
		this.resNum = resNum;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getUseRate() {
		return useRate;
	}

	public void setUseRate(Integer useRate) {
		this.useRate = useRate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public Integer getPriceRate() {
		return priceRate;
	}

	public void setPriceRate(Integer priceRate) {
		this.priceRate = priceRate;
	}
}