package com.leonyip.budget.service.user;

import com.leonyip.budget.dao.user.B_UserDAO;
import com.leonyip.budget.domain.user.B_User;
import com.leonyip.core.dao.support.Page;

public class B_UserService {

	private B_UserDAO userDAO;

	public B_UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(B_UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 查询所有人员
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page getAllUserPage(int pageNo) {
		 return userDAO.getAllUserPage(pageNo);
		
	}

	/**
	 * 查询同一部门的所有人员
	 * 
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getUserByDeptPage(int pageNo, Object values) {
		return userDAO.getUserByDeptPage(pageNo, values);
	}

	/**
	 * 根据关键字查询
	 * 
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getUserByKeywordsPage(int pageNo, Object values) {
		return userDAO.getUserByKeywordsPage(pageNo, values);
	}

	/**
	 * 登陆验证
	 * 
	 * @param loginName
	 * @param loginPwd
	 * @return
	 */
	public B_User userVerify(String loginName, String loginPwd) {
		return userDAO.userVerify(loginName, loginPwd);
	}

	/**
	 * 根据名称查找
	 * 
	 * @param values
	 * @return
	 */
	public B_User getUserByName(Object values) {
		return userDAO.getUserByName(values);
	}

	/**
	 * 检查某一角色下时候还有用户存在
	 * 
	 * @param sysRoleId
	 * @return
	 */
	public boolean checkUserSysRole(long sysRoleId) {
		return userDAO.checkUserSysRole(sysRoleId);
	}
	
	/**
	 * 保存用户信息
	 * 
	 * @param values
	 * @return
	 */
	public void save(B_User user){
		userDAO.save(user);
	}
	
	
	/**
	 * 根据用户编号查询对应用户信息
	 * 
	 * @param values
	 * @return
	 */
	public B_User get(long userId){
		return (B_User)userDAO.get(userId);
	}
	
	public void clear(){
		userDAO.clear();
	}
	
	public void removeById(long id){
		userDAO.removeById(id);
	}
}