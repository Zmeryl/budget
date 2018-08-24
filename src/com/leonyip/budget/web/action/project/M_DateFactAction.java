package com.leonyip.budget.web.action.project;

import java.util.Date;
import java.util.List;

import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.budget.domain.project.M_MilestoneDetailFact;
import com.leonyip.budget.domain.project.M_Project;
import com.leonyip.budget.util.DateUtil;

public class M_DateFactAction extends M_BaseProjectAction {

	private static final long serialVersionUID = -7655485627540422122L;
	
	private long milestoneId;
	
	public String execute() {
		M_Milestone milestone = milestoneService.get(milestoneId);
		List<M_MilestoneDetailFact> factList = milestone.getDetailFactList();
		if(factList == null || factList.size() == 0){
			return SUCCESS;
		}
		
		Date firstBeginDate = factList.get(0).getBeginDate();
		Date lastEndDate = factList.get(0).getEndDate();
		for(int i = 0; i < factList.size(); i++){
			M_MilestoneDetailFact fact = (M_MilestoneDetailFact)factList.get(i);
			if(DateUtil.isBeforeTheDay(firstBeginDate, fact.getBeginDate())){
				firstBeginDate = fact.getBeginDate();
			}
			if(DateUtil.isAfterTheDay(lastEndDate, fact.getEndDate())){
				lastEndDate = fact.getEndDate();
			}
		}
		milestone.setBeginDateFact(firstBeginDate);
		milestone.setEndDateFact(lastEndDate);
		
		milestoneService.save(milestone);
		
		//当前时间超出项目的预计结束时间之后   计算项目的实际使用时间
		M_Project prj = projectService.get(milestone.getPrj().getPrjId());
		if(DateUtil.isAfterTheDay(prj.getEndDatePlan(), new Date())){
			List<M_Milestone> milestoneList = prj.getMilestoneList();
			
			if(milestoneList == null || milestoneList.size() == 0){
				return SUCCESS;
			}
			firstBeginDate = milestoneList.get(0).getBeginDateFact();
			lastEndDate = milestoneList.get(0).getEndDateFact();
			
			for(int i = 0; i < milestoneList.size(); i++){
				M_Milestone stone = (M_Milestone)milestoneList.get(i);
				if(DateUtil.isBeforeTheDay(firstBeginDate, stone.getBeginDateFact())){
					firstBeginDate = stone.getBeginDateFact();
				}
				if(DateUtil.isAfterTheDay(lastEndDate, stone.getEndDateFact())){
					lastEndDate = stone.getEndDateFact();
				}
			}
			prj.setBeginDateFact(firstBeginDate);
			prj.setEndDateFact(lastEndDate);
			
			projectService.save(prj);
			
		}
		return SUCCESS;
	}

	public long getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}
	
}
