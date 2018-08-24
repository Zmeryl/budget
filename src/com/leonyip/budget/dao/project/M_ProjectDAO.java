package com.leonyip.budget.dao.project;

import java.util.List;

import com.leonyip.budget.domain.project.M_Project;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class M_ProjectDAO extends HibernateEntityDao<M_Project> {
	/**
	 * 查询所有项目
	 * @param pageNo
	 * @return
	 */
	public Page getAllProjectPage(int pageNo){
		return this.pagedQuery(" from M_Project ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 查询同一部门的所有项目
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getProjectPageByKind(int pageNo, Object ... values){
		return this.pagedQuery( " from M_Project prj where prj.dept.deptId=? ", pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getProjectPageByKeywords(int pageNo, Object values){
		return this.pagedQuery( " from M_Project prj where prj.prjName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 查询用户负责的项目
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getProjectPageByUserId(int pageNo, Object ... values){
		return this.pagedQuery( " from M_Project prj where prj.manager.userId=? ", pageNo, Constants.DEFAULT_PAGE_SIZE,values);
	}
	
	/**
	 * 查询某一部门的项目
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getProjectPageByDept(int pageNo, Object ... values){
		return this.pagedQuery( " from M_Project prj where prj.dept.deptId=?" , pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public M_Project getPrjByName(Object ... values){
		List<M_Project> list = this.find(" from M_Project prj where prj.prjName = ? ", values);
		if( list.size() > 0 ){
			return (M_Project)list.get(0);
		}else{
			return null;
		}
	}
}
