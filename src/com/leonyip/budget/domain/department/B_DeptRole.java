package com.leonyip.budget.domain.department;

public class B_DeptRole {
	private long deptRoleId;
	
	private B_Department dept;
	
	private String deptRoleName;
	
	private double deptRolePrice;
	
	private String deptRoleInfo;

	public long getDeptRoleId() {
		return deptRoleId;
	}

	public void setDeptRoleId(long deptRoleId) {
		this.deptRoleId = deptRoleId;
	}

	public B_Department getDept() {
		return dept;
	}

	public void setDept(B_Department dept) {
		this.dept = dept;
	}

	public String getDeptRoleName() {
		return deptRoleName;
	}

	public void setDeptRoleName(String deptRoleName) {
		this.deptRoleName = deptRoleName;
	}

	public double getDeptRolePrice() {
		return deptRolePrice;
	}

	public void setDeptRolePrice(double deptRolePrice) {
		this.deptRolePrice = deptRolePrice;
	}

	public String getDeptRoleInfo() {
		return deptRoleInfo;
	}

	public void setDeptRoleInfo(String deptRoleInfo) {
		this.deptRoleInfo = deptRoleInfo;
	}
}
