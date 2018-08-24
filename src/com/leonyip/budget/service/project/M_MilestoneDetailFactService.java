package com.leonyip.budget.service.project;

import java.util.Date;
import java.util.List;

import com.leonyip.budget.dao.project.M_MilestoneDetailFactDAO;
import com.leonyip.budget.domain.project.M_MilestoneDetailFact;

public class M_MilestoneDetailFactService  {
	
	private M_MilestoneDetailFactDAO milestoneDetailFactDAO;
	
	
	
	
	public M_MilestoneDetailFactDAO getMilestoneDetailFactDAO() {
		return milestoneDetailFactDAO;
	}

	public void setMilestoneDetailFactDAO(
			M_MilestoneDetailFactDAO milestoneDetailFactDAO) {
		this.milestoneDetailFactDAO = milestoneDetailFactDAO;
	}

	public List<M_MilestoneDetailFact> getDetailFactListByMilestoneId(long milestoneId){
		return milestoneDetailFactDAO.getDetailFactListByMilestoneId(milestoneId);
	}
	
	public List<M_MilestoneDetailFact> getWordedUserList(long userId, Date now){
		return milestoneDetailFactDAO.getWordedUserList(userId, now);
	}
	
	
	public void removeById(long id){
		milestoneDetailFactDAO.removeById(id);
	}


	public M_MilestoneDetailFact get(long id){
		return (M_MilestoneDetailFact)milestoneDetailFactDAO.get(id);
	}
	
	public void save(M_MilestoneDetailFact m){
		milestoneDetailFactDAO.save(m);
	}
	
	public void clear(){
		milestoneDetailFactDAO.clear();
	}

}