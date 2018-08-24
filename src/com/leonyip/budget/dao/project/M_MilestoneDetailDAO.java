package com.leonyip.budget.dao.project;

import com.leonyip.budget.domain.project.M_MilestoneDetail;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class M_MilestoneDetailDAO extends HibernateEntityDao<M_MilestoneDetail> {
	/**
	 * 查询所有项目
	 * @param pageNo
	 * @return
	 */
	public Page getMilestoneDetailPage(int pageNo, Object ... milestoneId){
		return this.pagedQuery(" from M_MilestoneDetail detail where detail.milestone.milestoneId = ? ", pageNo, Constants.DEFAULT_PAGE_SIZE, milestoneId);
	}
}
