package com.leonyip.budget.web.action.project;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.leonyip.budget.domain.catalog.B_BasePriceHuman;
import com.leonyip.budget.domain.catalog.B_BasePriceOther;
import com.leonyip.budget.domain.catalog.B_BasePriceRes;
import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.budget.domain.project.M_MilestoneDetail;
import com.leonyip.budget.domain.project.M_MilestoneDetailFact;
import com.leonyip.budget.util.DateUtil;
import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.core.dao.support.Page;

public class M_MilestoneDetailAction extends M_BaseProjectAction {

	private static final long serialVersionUID = 1884730298424765867L;
	
	private long detailId;
	
	private long milestoneId;
	
	private long resId;
	
	private String resName;
	
	private int resNum;
	
	private Date beginDate;
	
	private Date endDate;
	
	private int useRate;
	
	private String resType;
	
	private double detail_price = 0;
	
	private int priceRate;
	
	public String execute() {
		if(pageNo < 1){
			pageNo = 1;
		}
		Page page  = milestoneDetailService.getMilestoneDetailPage(pageNo,milestoneId);
		this.setRequestAttribute("page", page);
		M_Milestone milestone = milestoneService.get(milestoneId);
		this.setRequestAttribute("milestone", milestone);
		
		HashMap<String,M_MilestoneDetailFact> map = new HashMap<String, M_MilestoneDetailFact>();
		List<M_MilestoneDetailFact> detailFactList = milestoneDetailFactService.getDetailFactListByMilestoneId(milestoneId);
		for(M_MilestoneDetailFact fact : detailFactList){
			map.put(String.valueOf(fact.getDetailId()), fact);
		}
		this.setRequestAttribute("factMap", map);
		
		return SUCCESS;
	}
	
	public String showAddDetailJSP(){
		String result = "showAddDetailJSP";
		M_Milestone milestone = milestoneService.get(milestoneId);
		
		//验证日期
		if(DateUtil.isAfterTheDay(milestone.getBeginDatePlan(), new Date())){
			result = "error_date";
			this.setRequestAttribute("_error_message", "里程碑已经开始，无法添加新资源");
			return result;
		}
		
		this.setRequestAttribute("milestone", milestone);
		return result;
	}
	
	public String addDetail() throws Exception{
		String result = "addDetail";
		M_Milestone milestone = milestoneService.get(milestoneId);
		
		//验证日期
		if(DateUtil.isBeforeTheDay(beginDate, endDate)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "请检查输入的资源使用开始时间与结束时间");
			return result;
		}
		
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
		
		//计算成本金额
		int dayAmount = DateUtil.getDayAmount(beginDate, endDate, false);
		if(resType.equals(BudgetDict.CATALOG_TYPE_HUMAN)){
			//检查人员是否已经满负荷
			if(!checkUserUseRate(resId, useRate)){
				result = "error_date";
				this.setRequestAttribute("_error_message", "该员工配额已经超出100%");
				return result;
			}
			
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
		if(checkMilestoneDetail(milestone, resId, resType, -1)){
			M_MilestoneDetail detail = new M_MilestoneDetail();
			detail.setMilestone(milestone);
			detail.setResId(resId);
			detail.setResName(resName);
			detail.setResNum(resNum);
			detail.setBeginDate(beginDate);
			detail.setEndDate(endDate);
			detail.setUseRate(useRate);
			detail.setPrice(detail_price);
			detail.setResType(resType);
			detail.setPriceRate(priceRate);
			milestoneDetailService.save(detail);
			this.setRequestAttribute("_detailFactId", detail.getDetailId());
			this.setRequestAttribute("_detailModel", detail);
			return result;
		}else{
			result = "error_addRepeat";
			this.setRequestAttribute("_error_message", "该资源已经存在，请检查后重新操作！");
			return result;
		}
	}
	
	public String showEditDetailJSP(){
		String result = "showEditDetailJSP";
		M_MilestoneDetail detail = milestoneDetailService.get(detailId);
		this.setRequestAttribute("detail", detail);
		
		//验证日期
		if(DateUtil.isAfterTheDay(detail.getMilestone().getBeginDatePlan(), new Date())){
			result = "error_date";
			this.setRequestAttribute("_error_message", "里程碑已经开始，无法修改现有资源配置");
			return result;
		}
		
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
	
	public String updateDetail(){
		String result = "updateDetail";
		M_Milestone milestone = milestoneService.get(milestoneId);
		
		//验证日期
		if(DateUtil.isBeforeTheDay(beginDate, endDate)){
			result = "error_date";
			this.setRequestAttribute("_error_message", "请检查输入的资源使用开始时间与结束时间");
			return result;
		}
		
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
		
		int dayAmount = DateUtil.getDayAmount(beginDate, endDate, false);
		if(resType.equals(BudgetDict.CATALOG_TYPE_HUMAN)){
			//检查人员是否已经满负荷
			if(!checkUserUseRate(resId, useRate)){
				result = "error_date";
				this.setRequestAttribute("_error_message", "该员工配额已经超出100%");
				return result;
			}
			
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
			milestoneDetailService.clear();		//由于前面的checkMilestoneDetail()对milestone对象进行了更新，这里要重新读取
			M_Milestone stone = milestoneService.get(milestoneId);
			M_MilestoneDetail detail = new M_MilestoneDetail();
			detail.setDetailId(detailId);
			detail.setMilestone(stone);
			detail.setResId(resId);
			detail.setResName(resName);
			detail.setResNum(resNum);
			detail.setBeginDate(beginDate);
			detail.setEndDate(endDate);
			detail.setUseRate(useRate);
			detail.setPrice(detail_price);
			detail.setResType(resType);
			detail.setPriceRate(priceRate);
			milestoneDetailService.save(detail);
			this.setRequestAttribute("_detailModel", detail);
			return result;
		}else{
			result = "error_updateRepeat";
			this.setRequestAttribute("_error_message", "该资源已经存在，请检查后重新操作！");
			return result;
		}
	}
	
	public String deleteDetail(){
		String result = "deleteDetail";
		String[] deleteArray = deleteValue.split(",");
		for(int i = 0; i < deleteArray.length; i++){
			long deleteId = Long.parseLong(deleteArray[i]);
			milestoneDetailService.removeById(deleteId);
			milestoneDetailFactService.removeById(deleteId);
		}
		return result;
	}
	
	private boolean checkUserUseRate(long userId, int useRate){
		Calendar ca = Calendar.getInstance();
		List<M_MilestoneDetailFact> list = milestoneDetailFactService.getWordedUserList(userId, ca.getTime());
		int sum = 0;
		for(M_MilestoneDetailFact fact : list){
			sum = sum + fact.getUseRate();
		}
		
		if(sum + useRate > 100){
			return false;
		}else{
			return true;
		}
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

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}
	
}
