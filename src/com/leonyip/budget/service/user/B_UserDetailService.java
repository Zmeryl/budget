package com.leonyip.budget.service.user;

import com.leonyip.budget.dao.user.B_UserDetailDAO;
import com.leonyip.budget.domain.user.B_UserDetail;


public class B_UserDetailService {
	private B_UserDetailDAO userDetailDAO;

	public B_UserDetailDAO getUserDetailDAO() {
		return userDetailDAO;
	}

	public void setUserDetailDAO(B_UserDetailDAO userDetailDAO) {
		this.userDetailDAO = userDetailDAO;
	}
	
	
	public B_UserDetail get(long id){
		return (B_UserDetail)userDetailDAO.get(id);
	}
	
	
	public void removeById(long id){
		userDetailDAO.removeById(id);
	}
	
	public void save(B_UserDetail o){
		userDetailDAO.save(o);
	}
	
}