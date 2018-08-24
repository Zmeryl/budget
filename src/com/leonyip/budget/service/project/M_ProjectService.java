package com.leonyip.budget.service.project;

import com.leonyip.budget.dao.project.M_ProjectDAO;
import com.leonyip.budget.domain.project.M_Project;
import com.leonyip.core.dao.support.Page;

public class M_ProjectService  {
	
	private M_ProjectDAO projectDAO;
	
	
	
	public M_ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(M_ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/**
	 * 查询所有项目
	 * @param pageNo
	 * @return
	 */
	public Page getAllProjectPage(int pageNo){
		return projectDAO.getAllProjectPage(pageNo);
	}
	
	/**
	 * 查询同一部门的所有项目
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getProjectPageByKind(int pageNo, Object values){
		return projectDAO.getProjectPageByKind(pageNo, Long.parseLong(values.toString()));
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getProjectPageByKeywords(int pageNo, Object values){
		return projectDAO.getProjectPageByKeywords(pageNo, values);
	}
	
	/**
	 * 查询用户负责的项目
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getProjectPageByUserId(int pageNo, Object values){
		return projectDAO.getProjectPageByUserId(pageNo, values);
	}
	
	/**
	 * 查询某一部门的项目
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getProjectPageByDept(int pageNo, Object values){
		return projectDAO.getProjectPageByDept(pageNo, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public M_Project getPrjByName(Object values){
		return projectDAO.getPrjByName(values);
	}
	
	public M_Project get(long values){
		return (M_Project)projectDAO.get(values);
	}
	
	public void save(M_Project m){
		projectDAO.save(m);
	}
	
	public void removeById(long id){
		projectDAO.removeById(id);
	}
}
