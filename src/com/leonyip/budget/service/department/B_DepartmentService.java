package com.leonyip.budget.service.department;

import java.io.Serializable;
import java.util.List;

import com.leonyip.budget.dao.department.B_DepartmentDAO;
import com.leonyip.budget.domain.department.B_Department;
import com.leonyip.core.dao.support.Page;

public class B_DepartmentService  {
	private B_DepartmentDAO departmentDAO;
	
	public B_DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(B_DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	public void save(Object obj){
		departmentDAO.save(obj);
	}
	
	public List<B_Department> getAllDepartment(){
//		return departmentDAO.getAll();
		return departmentDAO.getAllDepartment();
	}
	
	public void delete(Object obj){
		departmentDAO.delete(obj);
	}
	
	public void update(Object obj){
		departmentDAO.update(obj);
	}
	
	/**
	 * 查询所有部门内容
	 * @param pageNo
	 * @return
	 */
	public Page getAllDepartmentPage(int pageNo){
		//return departmentDAO.pagedQuery(" from B_Department ", pageNo, Constants.DEFAULT_PAGE_SIZE);
		return departmentDAO.getAllDepartmentPage(pageNo);
	}
	
	//************************
	/**
	 * 查询父一级部门的所有子部门
	 * @param pageNo
	 * @param values
	 * @return
	 */
	/*******************/
	public Page getDepartmentPageByKind(int pageNo, Object ... values){
//		return departmentDAO.pagedQuery("from B_Department dept where dept.superId=? ", pageNo, Constants.DEFAULT_PAGE_SIZE, values);
		return departmentDAO.getDepartmentPageByKind(pageNo, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getDepartmentPageByKeywords(int pageNo, Object values){
		//return departmentDAO.pagedQuery("from B_Department dept where dept.deptName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
		return departmentDAO.getDepartmentPageByKeywords(pageNo, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public B_Department getDeptByName(Object ...values){
//		List<B_Department> list = departmentDAO.find(" from B_Department dept where dept.deptName = ? ", values);
//		if( list.size() > 0 ){
//			return (B_Department)list.get(0);
//		}else{
//			return null;
//		}
		return departmentDAO.getDeptByName(values);
	}
	
	public B_Department get(Serializable paramSerializable) {		
		return departmentDAO.get(paramSerializable);
	}
	public void removeById(Serializable id) {
		departmentDAO.removeById(id);
	}
	
	public List<B_Department> getAll(){
		return departmentDAO.getAll();
	}
}