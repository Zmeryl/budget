package com.leonyip.budget.dao.function;

import java.util.List;

import com.leonyip.budget.domain.function.S_SysFunction;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class S_SysFunctionDAO extends HibernateEntityDao<S_SysFunction> {
	/**
	 * 查询所有权限功能
	 * @param pageNo
	 * @return
	 */
	public Page getAllFunctionPage(int pageNo){
		return this.pagedQuery(" from S_SysFunction ", pageNo, 100);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getFunctionPageByKeywords(int pageNo, Object values){
		return this.pagedQuery( " from S_SysFunction fun where fun.funName like '%"+ values +"%' ", pageNo, 100);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public S_SysFunction getFunctionByName(Object ... values){
		List<S_SysFunction> list = this.find(" from S_SysFunction func where func.funName = ? ", values);
		if( list.size() > 0 ){
			return (S_SysFunction)list.get(0);
		}else{
			return null;
		}
	}	
}