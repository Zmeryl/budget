package com.leonyip.budget.web.action.project;

import java.util.List;

import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.budget.domain.project.M_MilestoneDetail;
import com.leonyip.budget.service.catalog.B_BasePriceHumanService;
import com.leonyip.budget.service.catalog.B_BasePriceOtherService;
import com.leonyip.budget.service.catalog.B_BasePriceResService;
import com.leonyip.budget.service.project.M_MilestoneDetailFactService;
import com.leonyip.budget.service.project.M_MilestoneDetailService;
import com.leonyip.budget.service.project.M_MilestoneService;
import com.leonyip.budget.service.project.M_ProjectService;
import com.leonyip.budget.util.ArithUtil;
import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.core.web.action.BaseAction;

public abstract class M_BaseProjectAction extends BaseAction{
	
	private static final long serialVersionUID = -898708046245030508L;

	public abstract String execute();


	protected M_ProjectService projectService;
	
	protected M_MilestoneService milestoneService;
	
	protected M_MilestoneDetailService milestoneDetailService;
	
	protected M_MilestoneDetailFactService milestoneDetailFactService;
	
	protected int pageNo;
	
	protected String searchType;
	
	protected String searchValue;
	
	protected String deleteValue;
	
	protected B_BasePriceHumanService priceHumanService;
	
	protected B_BasePriceResService priceResService;
	
	protected B_BasePriceOtherService priceOtherService;

	public M_ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(M_ProjectService projectService) {
		this.projectService = projectService;
	}

	public M_MilestoneService getMilestoneService() {
		return milestoneService;
	}

	public void setMilestoneService(M_MilestoneService milestoneService) {
		this.milestoneService = milestoneService;
	}

	public M_MilestoneDetailService getMilestoneDetailService() {
		return milestoneDetailService;
	}

	public void setMilestoneDetailService(
			M_MilestoneDetailService milestoneDetailService) {
		this.milestoneDetailService = milestoneDetailService;
	}

	public M_MilestoneDetailFactService getMilestoneDetailFactService() {
		return milestoneDetailFactService;
	}

	public void setMilestoneDetailFactService(
			M_MilestoneDetailFactService milestoneDetailFactService) {
		this.milestoneDetailFactService = milestoneDetailFactService;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getDeleteValue() {
		return deleteValue;
	}

	public void setDeleteValue(String deleteValue) {
		this.deleteValue = deleteValue;
	}
	
	public B_BasePriceHumanService getPriceHumanService() {
		return priceHumanService;
	}

	public void setPriceHumanService(B_BasePriceHumanService priceHumanService) {
		this.priceHumanService = priceHumanService;
	}

	public B_BasePriceResService getPriceResService() {
		return priceResService;
	}

	public void setPriceResService(B_BasePriceResService priceResService) {
		this.priceResService = priceResService;
	}

	public B_BasePriceOtherService getPriceOtherService() {
		return priceOtherService;
	}

	public void setPriceOtherService(B_BasePriceOtherService priceOtherService) {
		this.priceOtherService = priceOtherService;
	}
	
	/**
	 * 计算金额
	 * @param price 单价
	 * @param num	数量	
	 * @param rate	试用程度
	 * @param publicRate	公摊数
	 * @return
	 */
	protected double milestoneDetailCalculator(boolean isHuman, double price, int num, int rate, int publicRate, String priceType , int dayAmount) {
		
		//如果是按项目百分比计算费用则返回0，在计算项目总费用时再进行计算
		if(priceType.equals(BudgetDict.PRICE_TYPE_RATE)){
			return 0;
		}
		//如果数量1 投入100% 公摊均为1 则直接返回单价
		if(isHuman){
			if(num == 1 && rate == 100 && publicRate == 1 && dayAmount == BudgetDict.HUMAN_WORK_DAYS_PRE_MONTH){
				return price;
			}
		}else{
			if(num == 1 && rate == 100 && publicRate == 1){
				return price;
			}
		}
		
		double result = price;
		if(isHuman){
			result = ArithUtil.div(result, BudgetDict.HUMAN_WORK_DAYS_PRE_MONTH, 2);
			result = ArithUtil.mul(result, dayAmount);
		}
		if(rate != 100){
			result = ArithUtil.div(result, 100, 2);
			result = ArithUtil.mul(result, rate);
		}
		if(num > 1){
			result = ArithUtil.mul(result, num);
		}
		if(publicRate > 1){
			result = ArithUtil.div(result, publicRate, 2);
		}
		
		System.out.println("--------------detail price----------------------");
		System.out.println("days:"+dayAmount);
		System.out.println(result);
		
		return result;
	}
	
	/**
	 * 检查要添加或更新的资源是否已经存在里程碑当中了
	 * @param stone
	 * @param resId
	 * @param resType
	 * @param detailId 如果是新添加detail对象是则传入-1
	 * @return true 不存在 false 已经存在
	 */
	protected boolean checkMilestoneDetail (M_Milestone stone, long resId, String resType, long detailId){
		List<M_MilestoneDetail> detailList = stone.getDetailList();
		
		for(M_MilestoneDetail detail : detailList){
			if(detail.getResType().equals(resType) && detail.getResId() == resId){
				//如果是更新已存在的模型 则通过检查
				if(detailId == detail.getDetailId()){
					continue;
				}
				return false;
			}
		}
		
		return true;
	}
}
