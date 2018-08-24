package com.leonyip.budget.service.project;

import com.leonyip.budget.dao.project.M_MilestoneDAO;
import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.core.dao.support.Page;

public class M_MilestoneService  {
	private M_MilestoneDAO milestoneDAO;
	
	
	public M_MilestoneDAO getMilestoneDAO() {
		return milestoneDAO;
	}

	public void setMilestoneDAO(M_MilestoneDAO milestoneDAO) {
		this.milestoneDAO = milestoneDAO;
	}

	/**
	 * 查询所有项目
	 * @param pageNo
	 * @return
	 */
	public Page getMilestonePageByPrj(int pageNo, long prjId){
		return milestoneDAO.getMilestonePageByPrj(pageNo, prjId);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public M_Milestone getMilestoneByName(long prjId, String name){
		return milestoneDAO.getMilestoneByName(prjId,name);
	}

	public M_Milestone get(long milestoneId){
		return (M_Milestone)milestoneDAO.get(milestoneId);
	}
	
	public void save(M_Milestone m){
		milestoneDAO.save(m);
	}
	
	public void removeById(long id){
		milestoneDAO.removeById(id);
	}
	
	public void clear(){
		milestoneDAO.clear();
	}
}