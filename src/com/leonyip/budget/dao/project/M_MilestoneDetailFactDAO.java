package com.leonyip.budget.dao.project;

import java.util.Date;
import java.util.List;

import com.leonyip.budget.domain.project.M_MilestoneDetailFact;
import com.leonyip.core.dao.HibernateEntityDao;

public class M_MilestoneDetailFactDAO extends HibernateEntityDao<M_MilestoneDetailFact> {
	
	@SuppressWarnings("unchecked")
	public List<M_MilestoneDetailFact> getDetailFactListByMilestoneId(Object ... milestoneId){
		String hql = " from M_MilestoneDetailFact as fact where fact.milestone.milestoneId = ?";
		List<M_MilestoneDetailFact> list = this.find(hql, milestoneId);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<M_MilestoneDetailFact> getWordedUserList(long userId, Date now){
		Object[] o = {userId,now};
		String hql = " from M_MilestoneDetailFact as fact where fact.resId = ? and fact.endDate >= ? ";
		List<M_MilestoneDetailFact> list = this.find(hql, o );
		return list;
	}
}