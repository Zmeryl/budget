package com.leonyip.budget.dao.project;

import java.util.List;

import com.leonyip.budget.domain.project.M_Milestone;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class M_MilestoneDAO extends HibernateEntityDao<M_Milestone> {
	/**
	 * 查询所有项目
	 * @param pageNo
	 * @return
	 */
	public Page getMilestonePageByPrj(int pageNo, Object ... prjId){
		return this.pagedQuery(" from M_Milestone stone where stone.prj.prjId = ? ", pageNo, Constants.DEFAULT_PAGE_SIZE, prjId);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public M_Milestone getMilestoneByName(long prjId, String name){
		Object[] o= {prjId,name};
		List<M_Milestone> list = this.find(" from M_Milestone stone where stone.prj.prjId = ? and stone.milestoneName = ? ", o);
		if( list.size() > 0 ){
			return (M_Milestone)list.get(0);
		}else{
			return null;
		}
	}
}
