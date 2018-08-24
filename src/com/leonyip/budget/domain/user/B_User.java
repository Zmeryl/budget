package com.leonyip.budget.domain.user;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.leonyip.budget.domain.department.B_DeptRole;
import com.leonyip.budget.domain.function.S_SysRole;

public class B_User {
	private long userId;
	
	private B_DeptRole deptRole;
	
	private String deptRoleName;
	
	private S_SysRole sysRole;
	
	private String sysRoleName;
	
	private String loginName;
	
	private String loginPwd;
	
	private String status;
	
	private Date lastLoginTime;
	
	private Date stopTime;
	
	private Date createDate;
	
	private Date updateDate;
	
	private List<B_UserDetail> userDetailList = new LinkedList<B_UserDetail>();
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	public B_DeptRole getDeptRole() {
		return deptRole;
	}

	public void setDeptRole(B_DeptRole deptRole) {
		this.deptRole = deptRole;
	}

	public String getDeptRoleName() {
		return deptRoleName;
	}

	public void setDeptRoleName(String deptRoleName) {
		this.deptRoleName = deptRoleName;
	}

	public S_SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(S_SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public String getSysRoleName() {
		return sysRoleName;
	}

	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<B_UserDetail> getUserDetailList() {
		return userDetailList;
	}

	public void setUserDetailList(List<B_UserDetail> userDetailList) {
		this.userDetailList = userDetailList;
	}

}
