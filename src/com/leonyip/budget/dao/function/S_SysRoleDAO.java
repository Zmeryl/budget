package com.leonyip.budget.dao.function;

import java.util.List;

import com.leonyip.budget.domain.function.S_SysRole;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class S_SysRoleDAO extends HibernateEntityDao<S_SysRole> {
	/**
	 * 查询所有权限功能
	 * @param pageNo
	 * @return
	 */
	public Page getAllRolePage(int pageNo){
		return this.pagedQuery(" from S_SysRole ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getRolePageByKeywords(int pageNo, Object values){
		return this.pagedQuery( " from S_SysRole role where role.sysRoleName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public S_SysRole getSysRoleByName(Object... values){
		List<S_SysRole> list = this.find(" from S_SysRole role where role.sysRoleName = ? ", values);
		if( list.size() > 0 ){
			return (S_SysRole)list.get(0);
		}else{
			return null;
		}
	}
}