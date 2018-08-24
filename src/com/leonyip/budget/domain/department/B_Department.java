package com.leonyip.budget.domain.department;

import java.util.List;

import com.leonyip.budget.domain.department.B_DeptRole;
import com.leonyip.budget.domain.project.M_Project;

public class B_Department {
	private long deptId;
	
	private long superId;
	
	private String deptName;
	
	private String deptInfo;
	
	private List<B_DeptRole> deptRole;
	
	private List<M_Project> projectList;

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public long getSuperId() {
		return superId;
	}

	public void setSuperId(long superId) {
		this.superId = superId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptInfo() {
		return deptInfo;
	}

	public void setDeptInfo(String deptInfo) {
		this.deptInfo = deptInfo;
	}

	public List<B_DeptRole> getDeptRole() {
		return deptRole;
	}

	public void setDeptRole(List<B_DeptRole> deptRole) {
		this.deptRole = deptRole;
	}

	public List<M_Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<M_Project> projectList) {
		this.projectList = projectList;
	}
	
	
}
