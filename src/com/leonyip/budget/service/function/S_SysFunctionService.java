package com.leonyip.budget.service.function;

import com.leonyip.budget.dao.function.S_SysFunctionDAO;
import com.leonyip.budget.domain.function.S_SysFunction;
import com.leonyip.core.dao.support.Page;

public class S_SysFunctionService  {
	private S_SysFunctionDAO functionDAO;
	
	
	
	
	public S_SysFunctionDAO getFunctionDAO() {
		return functionDAO;
	}

	public void setFunctionDAO(S_SysFunctionDAO functionDAO) {
		this.functionDAO = functionDAO;
	}

	/**
	 * 查询所有权限功能
	 * @param pageNo
	 * @return
	 */
	public Page getAllFunctionPage(int pageNo){
		return functionDAO.getAllFunctionPage(pageNo);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getFunctionPageByKeywords(int pageNo, Object values){
		return functionDAO.getFunctionPageByKeywords(pageNo, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public S_SysFunction getFunctionByName(Object ... values){
		return functionDAO.getFunctionByName(values);
	}
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return S_SysFunction
	 */
	public S_SysFunction get(long id){
		return (S_SysFunction)functionDAO.get(id);
	}
	
	/**
	 * 保存
	 * @param function
	 * @return
	 */
	public void save(S_SysFunction function){
		functionDAO.save(function);
	}
	
	/**
	 * 根据ID删除
	 * @param deleteId
	 * @return
	 */
	public void removeById(long deleteId){
		functionDAO.removeById(deleteId);
	}
}
