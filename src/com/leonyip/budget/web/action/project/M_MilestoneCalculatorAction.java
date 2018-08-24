package com.leonyip.budget.web.action.project;

import java.util.LinkedList;
import java.util.List;

import com.leonyip.budget.domain.catalog.B_BasePriceHuman;
import com.leonyip.budget.domain.catalog.B_BasePriceOther;
import com.leonyip.budget.domain.catalog.B_BasePriceRes;
import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.budget.domain.project.M_MilestoneDetail;
import com.leonyip.budget.domain.project.M_MilestoneDetailFact;
import com.leonyip.budget.domain.project.M_Project;
import com.leonyip.budget.util.ArithUtil;
import com.leonyip.budget.util.dict.BudgetDict;

public class M_MilestoneCalculatorAction extends M_BaseProjectAction {

	private static final long serialVersionUID = -3142182360982036736L;
	
	private long milestoneId;
	
	public String execute() {
		//更新里程碑预算
		milestoneService.clear();
		M_Milestone milestone = milestoneService.get(milestoneId);
		List<M_MilestoneDetail> detailList = milestone.getDetailList();
		List<M_MilestoneDetailFact> detailFactList = milestone.getDetailFactList();
		
		double milestonePrice = 0;
		double milestoneFactPrice = 0;

		
		//list 存放公摊比例
		LinkedList<Double> ratePlanList = new LinkedList<Double>();
		LinkedList<Double> rateFactList = new LinkedList<Double>();
		
		//计划金额
		for(M_MilestoneDetail detail : detailList){
			if(detail.getPrice() == 0){
				if(detail.getResType() != null){
					if(detail.getResType().equals(BudgetDict.CATALOG_TYPE_HUMAN)){
						B_BasePriceHuman price = priceHumanService.get(detail.getResId());
						ratePlanList.add(price.getPrice());
					}else if(detail.getResType().equals(BudgetDict.CATALOG_TYPE_RES)){
						B_BasePriceRes price = priceResService.get(detail.getResId());
						ratePlanList.add(price.getPrice());
					}else{
						B_BasePriceOther price = priceOtherService.get(detail.getResId());
						ratePlanList.add(price.getPrice());
					}
				}
			}else{
				milestonePrice = ArithUtil.add(milestonePrice, detail.getPrice());
			}
		}
		
		//实际金额
		for(M_MilestoneDetailFact detailFact : detailFactList){
			if(detailFact.getPrice() == 0){
				if(detailFact.getResType() != null){
					if(detailFact.getResType().equals(BudgetDict.CATALOG_TYPE_HUMAN)){
						B_BasePriceHuman price = priceHumanService.get(detailFact.getResId());
						rateFactList.add(price.getPrice());
					}else if(detailFact.getResType().equals(BudgetDict.CATALOG_TYPE_RES)){
						B_BasePriceRes price = priceResService.get(detailFact.getResId());
						rateFactList.add(price.getPrice());
					}else{
						B_BasePriceOther price = priceOtherService.get(detailFact.getResId());
						rateFactList.add(price.getPrice());
					}
				}
			}else{
				milestoneFactPrice = ArithUtil.add(milestoneFactPrice, detailFact.getPrice());
			}
		}
		
		milestone.setPricePlan(milestonePrice);
		milestone.setPriceFact(milestoneFactPrice);
		
		int milestoneRateSumPlan = 0, milestoneRateSumFact = 0;
		for(Double d_rate : ratePlanList){
			milestoneRateSumPlan += d_rate.intValue();
		}
		for(Double d_rate : rateFactList){
			milestoneRateSumFact += d_rate.intValue();
		}
		milestone.setPriceRatePlan(milestoneRateSumPlan);
		milestone.setPriceRateFact(milestoneRateSumFact);
		
		milestoneService.save(milestone);
		
		//更新项目预算
		M_Project project = projectService.get(milestone.getPrj().getPrjId());
		List<M_Milestone> milestoneList = project.getMilestoneList();
		
		double prj_price_plan = 0;
		double prj_price_fact = 0;
		
		for(M_Milestone stone : milestoneList){
			prj_price_plan = ArithUtil.add(prj_price_plan, stone.getPricePlan());
			prj_price_fact = ArithUtil.add(prj_price_fact, stone.getPriceFact());
		}
		
		//计算占项目百分比项目的金额 累计到总金额中
		for(Double planRate : ratePlanList){
			double ratePrice = ArithUtil.div(prj_price_plan, 100, 2);
			ratePrice = ArithUtil.mul(ratePrice, planRate.doubleValue());
			prj_price_plan = ArithUtil.add(prj_price_plan, ratePrice);
		}
		
		for(Double factRate : rateFactList){
			double ratePrice = ArithUtil.div(prj_price_fact, 100, 2);
			ratePrice = ArithUtil.mul(ratePrice, factRate.doubleValue());
			prj_price_fact = ArithUtil.add(prj_price_fact, ratePrice);
		}
		
		project.setSumPricePlan(prj_price_plan);
		project.setSumPriceFact(prj_price_fact);
		projectService.save(project);
		
		return SUCCESS;
	}

	public long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}
	
}
