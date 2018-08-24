package com.leonyip.budget.dao.function;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.leonyip.budget.domain.function.S_SysRoleFun;
import com.leonyip.core.dao.HibernateEntityDao;

public class S_SysRoleFunDAO extends HibernateEntityDao<S_SysRoleFun> {
	@SuppressWarnings("unchecked")
	public List<S_SysRoleFun> getSysRoleFunctionListByRoleId(Object ... roldId){
		return this.find(" from S_SysRoleFun rolefun where rolefun.sysRole.sysRoleId = ? ", roldId);
	}
	
	public void deleteRoleFunction(long sysRoleId){
		String hql = "delete from S_SysRoleFun where sysRoleId = "+sysRoleId;
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.executeUpdate();
	}
}