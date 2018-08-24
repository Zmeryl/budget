package com.leonyip.budget.domain.function;

import java.util.List;

public class S_SysRole {
	private long sysRoleId;
	
	private String sysRoleName;
	
	private String sysRoleDesc;
	
	private List<S_SysRoleFun> sysRoleFunList;

	public long getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getSysRoleName() {
		return sysRoleName;
	}

	public void setSysRoleName(String sysRoleName) {
		this.sysRoleName = sysRoleName;
	}

	public String getSysRoleDesc() {
		return sysRoleDesc;
	}

	public void setSysRoleDesc(String sysRoleDesc) {
		this.sysRoleDesc = sysRoleDesc;
	}

	public List<S_SysRoleFun> getSysRoleFunList() {
		return sysRoleFunList;
	}

	public void setSysRoleFunList(List<S_SysRoleFun> sysRoleFunList) {
		this.sysRoleFunList = sysRoleFunList;
	}
	
	
}
