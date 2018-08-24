package com.leonyip.budget.web.action.user;

import com.leonyip.budget.domain.user.B_User;
import com.leonyip.budget.service.user.B_UserService;
import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.core.web.action.BaseAction;

public class B_EditUserPasswordAction extends BaseAction {

	private static final long serialVersionUID = -7994521886692480532L;
	
	private String newPwd;
	
	private B_UserService userService;


	public String execute() throws Exception {
		String result = SUCCESS;
		if(this.getSessionObj(BudgetDict.SESSION_USER) == null){
			result = "error";
			return result;
		}
		B_User sessionUser = (B_User)this.getSessionObj(BudgetDict.SESSION_USER);
		B_User user = userService.get(sessionUser.getUserId());
		user.setLoginPwd(newPwd);
		userService.save(user);
		return result;
	}
	
	public String showEditUserPasswordJSP(){
		return "showEditUserPasswordJSP";
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public B_UserService getUserService() {
		return userService;
	}

	public void setUserService(B_UserService userService) {
		this.userService = userService;
	}	
}