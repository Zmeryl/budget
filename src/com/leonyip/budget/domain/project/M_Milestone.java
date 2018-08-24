package com.leonyip.budget.domain.project;

import java.util.Date;
import java.util.List;
public class M_Milestone {
	private long milestoneId;
	
	private M_Project prj;
	
	private String prjName;
	
	private String milestoneName;
	
	private Date beginDatePlan;
	
	private Date endDatePlan;
	
	private Date beginDateFact;
	
	private Date endDateFact;
	
	private double pricePlan;
	
	private double priceFact;
	
	private String milestoneDesc;
	
	private String modifyDesc;
	
	private Integer priceRatePlan;
	
	private Integer priceRateFact;
	
	private List<M_MilestoneDetail> detailList;
	
	private List<M_MilestoneDetailFact> detailFactList;

	public long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public M_Project getPrj() {
		return prj;
	}

	public void setPrj(M_Project prj) {
		this.prj = prj;
	}

	public String getPrjName() {
		return prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	public String getMilestoneName() {
		return milestoneName;
	}

	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}

	public Date getBeginDatePlan() {
		return beginDatePlan;
	}

	public void setBeginDatePlan(Date beginDatePlan) {
		this.beginDatePlan = beginDatePlan;
	}

	public Date getEndDatePlan() {
		return endDatePlan;
	}

	public void setEndDatePlan(Date endDatePlan) {
		this.endDatePlan = endDatePlan;
	}

	public Date getBeginDateFact() {
		return beginDateFact;
	}

	public void setBeginDateFact(Date beginDateFact) {
		this.beginDateFact = beginDateFact;
	}

	public Date getEndDateFact() {
		return endDateFact;
	}

	public void setEndDateFact(Date endDateFact) {
		this.endDateFact = endDateFact;
	}

	public double getPricePlan() {
		return pricePlan;
	}

	public void setPricePlan(double pricePlan) {
		this.pricePlan = pricePlan;
	}

	public double getPriceFact() {
		return priceFact;
	}

	public void setPriceFact(double priceFact) {
		this.priceFact = priceFact;
	}

	public String getMilestoneDesc() {
		return milestoneDesc;
	}

	public void setMilestoneDesc(String milestoneDesc) {
		this.milestoneDesc = milestoneDesc;
	}

	public String getModifyDesc() {
		return modifyDesc;
	}

	public void setModifyDesc(String modifyDesc) {
		this.modifyDesc = modifyDesc;
	}

	public List<M_MilestoneDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<M_MilestoneDetail> detailList) {
		this.detailList = detailList;
	}

	public List<M_MilestoneDetailFact> getDetailFactList() {
		return detailFactList;
	}

	public void setDetailFactList(List<M_MilestoneDetailFact> detailFactList) {
		this.detailFactList = detailFactList;
	}

	public Integer getPriceRatePlan() {
		return priceRatePlan;
	}

	public void setPriceRatePlan(Integer priceRatePlan) {
		this.priceRatePlan = priceRatePlan;
	}

	public Integer getPriceRateFact() {
		return priceRateFact;
	}

	public void setPriceRateFact(Integer priceRateFact) {
		this.priceRateFact = priceRateFact;
	}
	
}
