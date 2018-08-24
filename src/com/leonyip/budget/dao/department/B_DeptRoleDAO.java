package com.leonyip.budget.dao.department;

import java.util.List;

import com.leonyip.budget.domain.department.B_DeptRole;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class B_DeptRoleDAO extends HibernateEntityDao<B_DeptRole> {
	public void addDepartmentRole(Object obj){
		super.save(obj);
	}
	
	public void updateDepartmentRole(Object obj){
		super.save(obj);
	}
	
	public void deleteDepartmentRole(Object obj){
		super.remove(obj);
	}
	
	public List<B_DeptRole> findAllDeptRole(){
		return this.getAll();
	}
	
	@SuppressWarnings("unchecked")
	public List<B_DeptRole> findDeptRoleByName(String ... deptRoleName){
		String hql = "from B_DeptRole dr where dr.deptRoleName=? ";
		return this.find(hql, deptRoleName);
	}
	
	public B_DeptRole getDeptRoleById(long deptRoleId){
		return (B_DeptRole)this.get(deptRoleId);
	}
	
	/**
	 * 查询所有部门角色
	 * @param pageNo
	 * @return
	 */
	public Page getAllDeptRolePage(int pageNo){
		return this.pagedQuery(" from B_DeptRole ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 查询同一部门的所有角色
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getDeptRolePageByKind(int pageNo, Object ... values){
		return this.pagedQuery(" from B_DeptRole deptRole where deptRole.dept.deptId=? ", pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getDeptRolePageByKeywords(int pageNo, Object values){
		return this.pagedQuery( " from B_DeptRole deptRole where deptRole.deptRoleName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B_DeptRole getDeptRoleByName(Object ... values){
		List<B_DeptRole> list = this.find(" from B_DeptRole deptRole where deptRole.deptRoleName = ? ", values);
		if( list.size() > 0 ){
			return (B_DeptRole)list.get(0);
		}else{
			return null;
		}
	}
}