package com.leonyip.budget.web.action.project;

import java.util.Date;

import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.budget.domain.project.M_Project;
import com.leonyip.budget.util.DateUtil;
import com.leonyip.core.dao.support.Page;

public class M_MilestoneAction extends M_BaseProjectAction {
	
	private static final long serialVersionUID = -2128416513405813535L;
	
	private long milestoneId;
	
	private long prjId;
	
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
	
	public String execute() {
		if(pageNo < 1){
			pageNo = 1;
		}
		Page page  = milestoneService.getMilestonePageByPrj(pageNo,prjId);
		this.setRequestAttribute("page", page);
		M_Project prj = projectService.get(prjId);
		this.setRequestAttribute("prj", prj);
		return SUCCESS;
	}
	
	public String showAddMilestoneJSP(){
		String result = "showAddMilestoneJSP";
		M_Project prj = projectService.get(prjId);
		this.setRequestAttribute("prj", prj);
		return result;
	}
	
	public String addMilestone() throws Exception{
		if(!checkName(prjId, milestoneName)){
			this.setRequestAttribute("_error_message", "在当前项目中已存在同名里程碑，请检查后重新操作！");
			return "error_function";
		}
		
		String result = "addMilestone";
		M_Project prj = projectService.get(prjId);
		
		//验证日期
		if(DateUtil.isBeforeTheDay(beginDatePlan, endDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "请检查输入的里程碑开始时间与结束时间");
			return result;
		}
		
		if(DateUtil.isBeforeTheDay(prj.getBeginDatePlan(), beginDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "里程碑开始时间超出了项目的开始时间");
			return result;
		}
		
		if(DateUtil.isAfterTheDay(prj.getEndDatePlan(), endDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "里程碑结束时间超出了项目的结束时间");
			return result;
		}
		
		M_Milestone milestone = new M_Milestone();
		milestone.setPrj(prj);
		milestone.setPrjName(prj.getPrjName());
		milestone.setMilestoneName(milestoneName);
		milestone.setBeginDatePlan(beginDatePlan);
		milestone.setEndDatePlan(endDatePlan);
		milestone.setMilestoneDesc(milestoneDesc);
		milestone.setPricePlan(0);
		
		//初次添加项目时 默认与预期计划相同
		milestone.setBeginDateFact(beginDatePlan);
		milestone.setEndDateFact(endDatePlan);
		milestone.setPriceFact(0);
		milestone.setModifyDesc("");
		milestone.setPriceRatePlan(0);
		milestone.setPriceRateFact(0);
		
		milestoneService.save(milestone);
		
		return result;
	}
	
	public String showEditMilestoneJSP(){
		String result = "showEditMilestoneJSP";
		M_Milestone milestone = milestoneService.get(milestoneId);
		
		//时间检查
		if(DateUtil.isAfterTheDay(milestone.getBeginDatePlan(), new Date())){
			result = "error_date";
			this.setRequestAttribute("_error_message", "里程碑已经开始，无法修改项目属性");
			return result;
		}
		
		this.setRequestAttribute("milestone", milestone);
		return result;
	}
	
	public String updateMilestone(){
//		if(!checkName(prjId, milestoneName)){
//			this.setRequestAttribute("_error_message", "在当前项目中已存在同名里程碑，请检查后重新操作！");
//			return "error_function";
//		}
		
		String result = "updateMilestone";
		
		M_Project prj = projectService.get(prjId);
		
		//验证日期
		if(DateUtil.isBeforeTheDay(beginDatePlan, endDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "请检查输入的里程碑开始时间与结束时间");
			return result;
		}
		
		if(DateUtil.isBeforeTheDay(prj.getBeginDatePlan(), beginDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "里程碑开始时间超出了项目的开始时间");
			return result;
		}
		
		if(DateUtil.isAfterTheDay(prj.getEndDatePlan(), endDatePlan)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "里程碑结束时间超出了项目的结束时间");
			return result;
		}
		
		/***********************
		M_Milestone milestone = new M_Milestone();
		milestone.setMilestoneId(milestoneId);
		milestone.setPrj(prj);
		milestone.setPrjName(prjName);
		milestone.setMilestoneName(milestoneName);
		milestone.setBeginDatePlan(beginDatePlan);
		milestone.setEndDatePlan(endDatePlan);
		milestone.setBeginDateFact(beginDateFact);
		milestone.setEndDateFact(endDateFact);
		milestone.setPricePlan(pricePlan);
		milestone.setPriceFact(priceFact);
		milestone.setMilestoneDesc(milestoneDesc);
		milestone.setModifyDesc(modifyDesc);
		milestoneService.save(milestone);
		**************************/
		M_Milestone milestone = milestoneService.get(milestoneId);
		milestone.setMilestoneName(milestoneName);
		milestone.setBeginDatePlan(beginDatePlan);
		milestone.setEndDatePlan(endDatePlan);
		milestone.setMilestoneDesc(milestoneDesc);
		milestone.setModifyDesc(modifyDesc);
		
		milestoneService.save(milestone);
		
		return result;
	}
	
	public String deleteMilestone(){
		String result = "deleteMilestone";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			
			//里程碑状态检查
			M_Milestone milestone = milestoneService.get(deleteId);
			if(DateUtil.isAfterTheDay(milestone.getBeginDatePlan(), new Date())){
				result = "error_date";
				this.setRequestAttribute("_error_message", "里程碑已经进入开始状态，无法删除");
				return result;
			}
			
			milestoneService.removeById(deleteId);
		}
		return result;
	}
	
	private boolean checkName(long prjId, String name){
		if(name == null){
			return true;
		}
		M_Milestone milestone = milestoneService.getMilestoneByName(prjId, name);
		if(milestone == null){
			return true;
		}else{
			return false;
		}
	}

	public long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}

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
	
	
}
