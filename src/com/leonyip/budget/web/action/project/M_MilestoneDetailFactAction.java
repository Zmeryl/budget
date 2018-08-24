package com.leonyip.budget.web.action.project;

import java.util.Date;

import com.leonyip.budget.domain.catalog.B_BasePriceHuman;
import com.leonyip.budget.domain.catalog.B_BasePriceOther;
import com.leonyip.budget.domain.catalog.B_BasePriceRes;
import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.budget.domain.project.M_MilestoneDetail;
import com.leonyip.budget.domain.project.M_MilestoneDetailFact;
import com.leonyip.budget.util.DateUtil;
import com.leonyip.budget.util.dict.BudgetDict;

public class M_MilestoneDetailFactAction extends M_BaseProjectAction {

	private static final long serialVersionUID = -4317189842108262720L;
	
	private long detailId;
	
	private long milestoneId;
	
	private long resId;
	
	private String resName;
	
	private int resNum;
	
	private Date beginDate;
	
	private Date endDate;
	
	private int useRate;
	
	private String alterDesc;
	
	private String resType;
	
	private double detail_price = 0;
	
	private int priceRate;
	
	public String execute() {
		return SUCCESS;
	}
	
	public String addDetailFact() throws Exception{
		String result = "addDetailFact";
		
		int dayAmount = DateUtil.getDayAmount(beginDate, endDate, false);
		if(resType.equals(BudgetDict.CATALOG_TYPE_HUMAN)){
			B_BasePriceHuman res = priceHumanService.get(resId);
			detail_price = milestoneDetailCalculator(true, res.getPrice(), resNum, useRate, res.getPublicRate(), res.getPriceType(), dayAmount);
			if(detail_price == 0){
				priceRate = new Double(res.getPrice()).intValue();
			}
		}else if(resType.equals(BudgetDict.CATALOG_TYPE_RES)){
			B_BasePriceRes res = priceResService.get(resId);
			detail_price = milestoneDetailCalculator(false, res.getPrice(), resNum, useRate, res.getPublicRate(), res.getPriceType(), dayAmount);
			if(detail_price == 0){
				priceRate = new Double(res.getPrice()).intValue();
			}
		}else{
			B_BasePriceOther res = priceOtherService.get(resId);
			detail_price = milestoneDetailCalculator(false, res.getPrice(), resNum, useRate, res.getPublicRate(), res.getPriceType(), dayAmount);
			if(detail_price == 0){
				priceRate = new Double(res.getPrice()).intValue();
			}
		}
		M_Milestone milestone = milestoneService.get(milestoneId);
		
		M_MilestoneDetailFact detail = new M_MilestoneDetailFact();
		if(this.getHttpServletRequest().getAttribute("_detailFactId") == null){
			throw new NullPointerException("detailId is null");
		}
		detail.setDetailId(Long.parseLong(this.getHttpServletRequest().getAttribute("_detailFactId").toString()));
		detail.setMilestone(milestone);
		detail.setResId(resId);
		detail.setResName(resName);
		detail.setResNum(resNum);
		detail.setBeginDate(beginDate);
		detail.setEndDate(endDate);
		detail.setUseRate(useRate);
		detail.setPrice(detail_price);
		detail.setAlterDesc("");
		detail.setResType(resType);
		detail.setPriceRate(priceRate);
		milestoneDetailFactService.save(detail);
		
		return result;
	}
	
	public String showEditDetailFactJSP(){
		String result = "showEditDetailFactJSP";
		M_MilestoneDetailFact detail = milestoneDetailFactService.get(detailId);
		this.setRequestAttribute("detail", detail);
		
		if(detail.getResType() != null){
			if(detail.getResType().equals(BudgetDict.CATALOG_TYPE_HUMAN)){
				B_BasePriceHuman price = priceHumanService.get(detail.getResId());
				this.setRequestAttribute("_price", price);
			}else if(detail.getResType().equals(BudgetDict.CATALOG_TYPE_RES)){
				B_BasePriceRes price = priceResService.get(detail.getResId());
				this.setRequestAttribute("_price", price);
			}else{
				B_BasePriceOther price = priceOtherService.get(detail.getResId());
				this.setRequestAttribute("_price", price);
			}	
		}
		
		return result;
	}
	
	public String updateDetailFact(){
		String result = "updateDetailFact";
		M_Milestone milestone = milestoneService.get(milestoneId);
		
		//验证日期
		if(DateUtil.isBeforeTheDay(beginDate, endDate)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "请检查输入的资源使用开始时间与结束时间");
			return result;
		}
		
		/** 核实资源时 不限定资源使用时间范围
		if(DateUtil.isBeforeTheDay(milestone.getBeginDatePlan(), beginDate)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "资源使用开始时间超出了里程碑的开始时间");
			return result;
		}
		
		if(DateUtil.isAfterTheDay(milestone.getEndDatePlan(), endDate)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "资源使用结束时间超出了里程碑的结束时间");
			return result;
		}
		**/
		
		//计算成本金额
		int dayAmount = DateUtil.getDayAmount(beginDate, endDate, false);
		if(resType.equals(BudgetDict.CATALOG_TYPE_HUMAN)){
			B_BasePriceHuman res = priceHumanService.get(resId);
			detail_price = milestoneDetailCalculator(true, res.getPrice(), resNum, useRate, res.getPublicRate(), res.getPriceType(), dayAmount);
			if(detail_price == 0){
				priceRate = new Double(res.getPrice()).intValue();
			}
		}else if(resType.equals(BudgetDict.CATALOG_TYPE_RES)){
			B_BasePriceRes res = priceResService.get(resId);
			detail_price = milestoneDetailCalculator(false, res.getPrice(), resNum, useRate, res.getPublicRate(), res.getPriceType(), dayAmount);
			if(detail_price == 0){
				priceRate = new Double(res.getPrice()).intValue();
			}
		}else{
			B_BasePriceOther res = priceOtherService.get(resId);
			detail_price = milestoneDetailCalculator(false, res.getPrice(), resNum, useRate, res.getPublicRate(), res.getPriceType(), dayAmount);
			if(detail_price == 0){
				priceRate = new Double(res.getPrice()).intValue();
			}
		}
		
		//检查是否重复添加
		if(super.checkMilestoneDetail(milestone, resId, resType, detailId)){
			M_MilestoneDetailFact detail = new M_MilestoneDetailFact();
			detail.setDetailId(detailId);
			detail.setMilestone(milestone);
			detail.setResId(resId);
			detail.setResName(resName);
			detail.setResNum(resNum);
			detail.setBeginDate(beginDate);
			detail.setEndDate(endDate);
			detail.setUseRate(useRate);
			detail.setPrice(detail_price);
			detail.setAlterDesc(alterDesc);
			detail.setResType(resType);
			detail.setPriceRate(priceRate);
			
			milestoneDetailFactService.save(detail);
//			milestoneDetailService.save(detail);
			return result;
		}else{
			result = "error_updateRepeat";
			this.setRequestAttribute("_error_message", "该资源已经存在，请检查后重新操作！");
			return result;
		}
		
	}
	
	public String updateDetailFactAuto(){
		String result = "updateDetailFactAuto";
		if(this.getHttpServletRequest().getAttribute("_detailModel") == null){
			throw new NullPointerException("_detailModel is null");
		}
		M_MilestoneDetail detail = (M_MilestoneDetail)this.getHttpServletRequest().getAttribute("_detailModel");
		M_MilestoneDetailFact fact = milestoneDetailFactService.get(detail.getDetailId());
		fact.setResId(detail.getResId());
		fact.setResName(detail.getResName());
		fact.setResNum(detail.getResNum());
		fact.setBeginDate(detail.getBeginDate());
		fact.setEndDate(detail.getEndDate());
		fact.setUseRate(detail.getUseRate());
		fact.setPrice(detail.getPrice());
		fact.setResType(detail.getResType());
		fact.setPriceRate(detail.getPriceRate());
		
		//这里不再重复检查对象是否重复
		milestoneDetailFactService.save(fact);
		return result;
		
	}
	
	public String deleteDetailFact(){
		String result = "deleteDetailFact";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			milestoneDetailFactService.removeById(deleteId);
		}
		return result;
	}
	

	public long getDetailId() {
		return detailId;
	}

	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}

	public long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
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

	public int getResNum() {
		return resNum;
	}

	public void setResNum(int resNum) {
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

	public int getUseRate() {
		return useRate;
	}

	public void setUseRate(int useRate) {
		this.useRate = useRate;
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
}