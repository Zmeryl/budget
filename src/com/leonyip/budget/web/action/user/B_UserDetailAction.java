package com.leonyip.budget.web.action.user;

import java.util.Date;

import com.leonyip.budget.domain.user.B_User;
import com.leonyip.budget.domain.user.B_UserDetail;
import com.leonyip.budget.service.user.B_UserDetailService;
import com.leonyip.budget.service.user.B_UserService;
import com.leonyip.core.web.action.BaseAction;

public class B_UserDetailAction extends BaseAction {

	private static final long serialVersionUID = 2443607527102930064L;
	
	private long userId;
	
	private String realName;
	
	private String sex;
	
	private Date joinTime;
	
	private String userType;
	
	private double userPrice;
	
	private String userDesc;
	
	private int userRate;
	
	private String workStatus;
	
	private B_UserDetailService userDetailService;
	
	private B_UserService userService;
	
	public String execute() throws Exception {
		B_UserDetail userDetail = userDetailService.get(userId);
		this.setRequestAttribute( "userDetail", userDetail );
		B_User user = userService.get(userId);
		double rolePrice = user.getDeptRole().getDeptRolePrice();
		this.setRequestAttribute("_rolePrice", rolePrice);
		return SUCCESS;
	}
	
	public String addUserDetailAuto(){
		String result = "addUserDetailAuto";
		B_User user = (B_User)this.getHttpServletRequest().getAttribute("user");
		B_UserDetail userDetail = new B_UserDetail();
		userDetail.setUserId(user.getUserId());
		userDetail.setRealName(user.getLoginName());
		userDetailService.save(userDetail);
		return result;
	}
	
	public String updateUserDetail(){
		String result="updateUserDetail";
		B_UserDetail userDetail = userDetailService.get(userId);
		userDetail.setRealName(realName);
		userDetail.setSex(sex);
		userDetail.setJoinTime(joinTime);
		userDetail.setUserType(userType);
		userDetail.setUserPrice(userPrice);
		userDetail.setUserDesc(userDesc);
		userDetailService.save(userDetail);
		return result;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public double getUserPrice() {
		return userPrice;
	}

	public void setUserPrice(double userPrice) {
		this.userPrice = userPrice;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public int getUserRate() {
		return userRate;
	}

	public void setUserRate(int userRate) {
		this.userRate = userRate;
	}

	public String getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	public B_UserDetailService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(B_UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	public B_UserService getUserService() {
		return userService;
	}

	public void setUserService(B_UserService userService) {
		this.userService = userService;
	}
	
	
}
