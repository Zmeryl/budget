package com.leonyip.budget.domain.function;

import java.util.Date;

public class S_SysRoleFun {
	private long refId;
	
	private S_SysFunction sysFunction;
	
	private String funName;
	
	private S_SysRole sysRole;
	
	private String sysRoleName;
	
	private Date createDate;
	
	private Date updateDate;

	public long getRefId() {
		return refId;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}


	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getSysRoleName() {
		return sysRoleName;
	}

	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
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

	public S_SysFunction getSysFunction() {
		return sysFunction;
	}

	public void setSysFunction(S_SysFunction sysFunction) {
		this.sysFunction = sysFunction;
	}

	public S_SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(S_SysRole sysRole) {
		this.sysRole = sysRole;
	}

	
}
