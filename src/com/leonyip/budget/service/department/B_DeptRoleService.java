package com.leonyip.budget.service.department;

import java.util.List;

import com.leonyip.budget.dao.department.B_DeptRoleDAO;
import com.leonyip.budget.domain.department.B_DeptRole;
import com.leonyip.core.dao.support.Page;

public class B_DeptRoleService  {
	private B_DeptRoleDAO deptRoleDAO;

	public B_DeptRoleDAO getDeptRoleDAO() {
		return deptRoleDAO;
	}

	public void setDeptRoleDAO(B_DeptRoleDAO deptRoleDAO) {
		this.deptRoleDAO = deptRoleDAO;
	}

	public void addDepartmentRole(Object obj){
		deptRoleDAO.addDepartmentRole(obj);
	}
	
	public void updateDepartmentRole(Object obj){
		deptRoleDAO.updateDepartmentRole(obj);
	}
	
	public void deleteDepartmentRole(Object obj){
		deptRoleDAO.deleteDepartmentRole(obj);
	}
	
	public List<B_DeptRole> findAllDeptRole(){
		return deptRoleDAO.findAllDeptRole();
	}
	
	public List<B_DeptRole> findDeptRoleByName(String deptRoleName){
		return deptRoleDAO.findDeptRoleByName(deptRoleName);
	}
	
	public B_DeptRole getDeptRoleById(long deptRoleId){
		return deptRoleDAO.getDeptRoleById(deptRoleId);
	}
	
	/**
	 * 查询所有部门角色
	 * @param pageNo
	 * @return
	 */
	public Page getAllDeptRolePage(int pageNo){
		return deptRoleDAO.getAllDeptRolePage(pageNo);
	}
	
	/**
	 * 查询同一部门的所有角色
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getDeptRolePageByKind(int pageNo, Object values){
		return deptRoleDAO.getDeptRolePageByKind(pageNo, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getDeptRolePageByKeywords(int pageNo, Object values){
		return deptRoleDAO.getDeptRolePageByKeywords(pageNo, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public B_DeptRole getDeptRoleByName(Object values){
		return deptRoleDAO.getDeptRoleByName(values);
	}

	public B_DeptRole get(long deptRoleId){
		return (B_DeptRole)deptRoleDAO.get(deptRoleId);
	}

	public List<B_DeptRole> getAll(){
		return deptRoleDAO.getAll();
	}
	
	public void removeById(long deptRoleId){
		deptRoleDAO.removeById(deptRoleId);
	}
}