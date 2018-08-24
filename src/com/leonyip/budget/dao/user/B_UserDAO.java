package com.leonyip.budget.dao.user;

import java.util.List;

import com.leonyip.budget.domain.user.B_User;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class B_UserDAO extends HibernateEntityDao<B_User> {
	/**
	 * 查询所有人员
	 * @param pageNo
	 * @return
	 */
	public Page getAllUserPage(int pageNo){
		return this.pagedQuery(" from B_User ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 查询同一部门的所有人员
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getUserByDeptPage(int pageNo, Object ... values){
		StringBuffer hql = new StringBuffer( " select user from B_User as user  " )
		.append(" join user.deptRole as deptroleref ")
		.append(" join deptroleref.dept as dept ")
		.append(" where dept.deptId = ? ");
		
		return this.pagedQuery( hql.toString(), pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getUserByKeywordsPage(int pageNo, Object values){
		return this.pagedQuery( " from B_User user where user.loginName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 登陆验证
	 * @param loginName
	 * @param loginPwd
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B_User userVerify(String loginName, String loginPwd){
		Object[] o = { loginName, loginPwd };
		String hql = " from B_User u where u.loginName = ? and u.loginPwd = ? ";
		List objList = this.find(hql, o);
		if(objList != null && objList.size()>0){
			B_User user = (B_User) objList.get(0);
			
			return user;
		}
		return null; 
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B_User getUserByName(Object ... values){
		List<B_User> list = this.find(" from B_User user where user.loginName = ? ", values);
		if( list.size() > 0 ){
			return (B_User)list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 检查某一角色下时候还有用户存在
	 * @param sysRoleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkUserSysRole(Object ... sysRoleId){
		List l = this.find(" from B_User user where user.sysRole.sysRoleId = ? ", sysRoleId);
		if(l != null && l.size()>0 ){
			return true;
		}else{
			return false;
		}
	}	
}