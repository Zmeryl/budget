package com.leonyip.budget.service.function;

import java.util.List;

import com.leonyip.budget.dao.function.S_SysRoleDAO;
import com.leonyip.budget.domain.function.S_SysRole;
import com.leonyip.core.dao.support.Page;

public class S_SysRoleService  {
	
	private S_SysRoleDAO roleDAO;
	
	
	
	
	public S_SysRoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(S_SysRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	/**
	 * 查询所有权限功能
	 * @param pageNo
	 * @return
	 */
	public Page getAllRolePage(int pageNo){
		return roleDAO.getAllRolePage(pageNo);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getRolePageByKeywords(int pageNo, Object values){
		return roleDAO.getRolePageByKeywords(pageNo, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public S_SysRole getSysRoleByName(Object values){
		return roleDAO.getSysRoleByName(values);
	}

	public void save(S_SysRole role) {
		roleDAO.save(role);
	}

	public void removeById(long deleteId) {
		roleDAO.removeById(deleteId);
		
	}

	public S_SysRole get(long sysRoleId) {
		return (S_SysRole)roleDAO.get(sysRoleId);
	}
	
	public List<S_SysRole> getAll(){
		return roleDAO.getAll();
	}
}