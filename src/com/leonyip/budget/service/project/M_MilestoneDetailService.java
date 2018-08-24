package com.leonyip.budget.service.project;

import com.leonyip.budget.dao.project.M_MilestoneDetailDAO;
import com.leonyip.budget.domain.project.M_MilestoneDetail;
import com.leonyip.budget.domain.project.M_MilestoneDetailFact;
import com.leonyip.core.dao.support.Page;

public class M_MilestoneDetailService  {
	private M_MilestoneDetailDAO milestoneDetailDAO;
	
	
	public M_MilestoneDetailDAO getMilestoneDetailDAO() {
		return milestoneDetailDAO;
	}


	public void setMilestoneDetailDAO(M_MilestoneDetailDAO milestoneDetailDAO) {
		this.milestoneDetailDAO = milestoneDetailDAO;
	}


	/**
	 * 查询所有项目
	 * @param pageNo
	 * @return
	 */
	public Page getMilestoneDetailPage(int pageNo, long milestoneId){
		return milestoneDetailDAO.getMilestoneDetailPage(pageNo, milestoneId);
	}
	
	
	
	public M_MilestoneDetail get(long id){
		return (M_MilestoneDetail)milestoneDetailDAO.get(id);
	}
	
	public void save(M_MilestoneDetail m){
		milestoneDetailDAO.save(m);
	}
	
	public void save(M_MilestoneDetailFact m){
		milestoneDetailDAO.save(m);
	}	
	public void removeById(long id){
		milestoneDetailDAO.removeById(id);
	}
	
	public void clear(){
		milestoneDetailDAO.clear();
	}
}
