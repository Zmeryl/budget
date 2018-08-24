package com.leonyip.budget.web.action.user;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.leonyip.budget.domain.function.S_SysRole;
import com.leonyip.budget.domain.function.S_SysRoleFun;
import com.leonyip.budget.domain.user.B_User;
import com.leonyip.budget.service.user.B_UserService;
import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.core.util.MD5;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class B_LoginControllerAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String loginName;

	private String loginPwd;

	private B_UserService userService;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public B_UserService getUserService() {
		return userService;
	}

	public void setUserService(B_UserService userService) {
		this.userService = userService;
	}

	public HttpServletRequest getHttpServletRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getHttpServletResponse() {
		return ServletActionContext.getResponse();
	}

	public Map<String, Object> getSession() {
		return ActionContext.getContext().getSession();
	}

	public void setRequestAttribute(String key, Object value) {
		getHttpServletRequest().setAttribute(key, value);
	}

	public void setSessionObj(String key, Object value) {
		getSession().put(key, value);
	}

	public String execute() throws Exception {
		String result = SUCCESS;
		if (loginName == null || loginName.equals("")) {
			result = "error_input";
			this.setRequestAttribute("_error_message", "用户名或密码输入有误");
			return result;
		}
		if (loginPwd == null || loginPwd.equals("")) {
			result = "error_input";
			this.setRequestAttribute("_error_message", "用户名或密码输入有误");
			return result;
		}

		loginPwd = MD5.crypt(loginPwd);
		B_User user = userService.userVerify(loginName, loginPwd);
		if (user == null) {
			result = "error_input";
			this.setRequestAttribute("_error_message", "用户名或密码输入有误");
			return result;
		} else {
			LinkedList<String> list = new LinkedList<String>();
			this.setSessionObj(BudgetDict.SESSION_USER, user);
			S_SysRole role = user.getSysRole();
			 List<S_SysRoleFun> roleFunList = role.getSysRoleFunList();
			 for (S_SysRoleFun roleFun : roleFunList) {
				 list.add(roleFun.getSysFunction().getFunDesc() + "@" + roleFun.getSysFunction().getFunCode());
			 }
			this.setSessionObj(BudgetDict.SESSION_FUNCTION_LIST, list);
			return result;
		}
	}
	public String logout() {
		this.getSession().remove(BudgetDict.SESSION_USER);
		this.getSession().remove(BudgetDict.SESSION_FUNCTION_LIST);
		return SUCCESS;
	}
}