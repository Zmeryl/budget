package com.leonyip.budget.domain.project;

import java.util.Date;

public class M_MilestoneDetailFact {
	private long detailId;
	
	private M_Milestone milestone;
	
	private long resId;
	
	private String resName;
	
	private Integer resNum;
	
	private Date beginDate;
	
	private Date endDate;
	
	private Integer useRate;
	
	private double price;
	
	private String alterDesc;
	
	private String resType;
	
	private Integer priceRate;

	public long getDetailId() {
		return detailId;
	}

	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}

	public M_Milestone getMilestone() {
		return milestone;
	}

	public void setMilestone(M_Milestone milestone) {
		this.milestone = milestone;
	}

	public long getResId() {
		return resId;
	}

	public void setResId(long resId) {
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

	public String getAlterDesc() {
		return alterDesc;
	}

	public void setAlterDesc(String alterDesc) {
		this.alterDesc = alterDesc;
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
