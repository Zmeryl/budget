package com.leonyip.budget.domain.project;

import java.util.Date;
import java.util.List;

import com.leonyip.budget.domain.department.B_Department;
import com.leonyip.budget.domain.user.B_User;

public class M_Project {
	private long prjId;
	
	private String prjName;
	
	private B_Department dept;
	
	private String deptName;
	
	private B_User manager;
	
	private String managerName;
	
	private Date beginDatePlan;
	
	private Date endDatePlan;
	
	private double sumPricePlan;
	
	private Date beginDateFact;
	
	private Date endDateFact;
	
	private double sumPriceFact;
	
	private String prjDesc;
	
	private List<M_Milestone> milestoneList;

	public long getPrjId() {
		return prjId;
	}

	public void setPrjId(long prjId) {
		this.prjId = prjId;
	}

	public String getPrjName() {
		return prjName;
	}

	public void setPrjName(String prjName) {
		this.prjName = prjName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
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

	public double getSumPricePlan() {
		return sumPricePlan;
	}

	public void setSumPricePlan(double sumPricePlan) {
		this.sumPricePlan = sumPricePlan;
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

	public double getSumPriceFact() {
		return sumPriceFact;
	}

	public void setSumPriceFact(double sumPriceFact) {
		this.sumPriceFact = sumPriceFact;
	}

	public String getPrjDesc() {
		return prjDesc;
	}

	public void setPrjDesc(String prjDesc) {
		this.prjDesc = prjDesc;
	}

	public List<M_Milestone> getMilestoneList() {
		return milestoneList;
	}

	public void setMilestoneList(List<M_Milestone> milestoneList) {
		this.milestoneList = milestoneList;
	}

	public B_Department getDept() {
		return dept;
	}

	public void setDept(B_Department dept) {
		this.dept = dept;
	}

	public B_User getManager() {
		return manager;
	}

	public void setManager(B_User manager) {
		this.manager = manager;
	}
	
}
