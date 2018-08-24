package com.leonyip.budget.domain.function;

import java.util.List;

public class S_SysFunction {
	private long funId;
	
	private String funName;
	
	private String funCode;
	
	private String funDesc;
	
	private List<S_SysRoleFun> sysRoleFunList;

	public long getFunId() {
		return funId;
	}

	public void setFunId(long funId) {
		this.funId = funId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getFunDesc() {
		return funDesc;
	}

	public void setFunDesc(String funDesc) {
		this.funDesc = funDesc;
	}

	public List<S_SysRoleFun> getSysRoleFunList() {
		return sysRoleFunList;
	}

	public void setSysRoleFunList(List<S_SysRoleFun> sysRoleFunList) {
		this.sysRoleFunList = sysRoleFunList;
	}
	
	
}
