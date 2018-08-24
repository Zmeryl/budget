package com.leonyip.budget.dao.department;

import java.io.Serializable;
import java.util.List;

import com.leonyip.budget.domain.department.B_Department;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class B_DepartmentDAO extends HibernateEntityDao<B_Department>{

	/**
	 * 查询所有部门内容
	 * @param pageNo
	 * @return
	 */
	public Page getAllDepartmentPage(int pageNo){
		return this.pagedQuery(" from B_Department ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 查询父一级部门的所有子部门
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getDepartmentPageByKind(int pageNo, Object ... values){
		return this.pagedQuery("from B_Department dept where dept.superId=? ", pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getDepartmentPageByKeywords(int pageNo, Object values){
		return this.pagedQuery("from B_Department dept where dept.deptName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B_Department getDeptByName(Object ...values){
		List<B_Department> list = this.find(" from B_Department dept where dept.deptName = ? ", values);
		if( list.size() > 0 ){
			return (B_Department)list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public B_Department get(Serializable paramSerializable) {		
		B_Department instance = (B_Department) getHibernateTemplate().get(
				B_Department.class, paramSerializable);
		return instance;
	}
	
	public void save(Object obj){
		super.save(obj);
	}
	
	public List<B_Department> getAllDepartment(){
		return this.getAll();
	}
	
	public void delete(Object obj){
		super.remove(obj);
	}
	
	public void update(Object obj){
		super.save(obj);
	}	
}