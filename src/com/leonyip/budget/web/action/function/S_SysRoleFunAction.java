package com.leonyip.budget.web.action.function;

import java.util.Date;
import java.util.List;

import com.leonyip.budget.domain.function.S_SysFunction;
import com.leonyip.budget.domain.function.S_SysRole;
import com.leonyip.budget.domain.function.S_SysRoleFun;
import com.leonyip.budget.service.function.S_SysFunctionService;
import com.leonyip.budget.service.function.S_SysRoleFunService;
import com.leonyip.budget.service.function.S_SysRoleService;
import com.leonyip.core.web.action.BaseAction;

public class S_SysRoleFunAction extends BaseAction {

	private static final long serialVersionUID = -4346695662130537443L;
	
	private long refId;
	
	private long funId;
	
	private String funName;
	
	private long sysRoleId;
	
	private String sysRoleName;
	
	private Date createDate;
	
	private Date updateDate;
	
	private String addFunValue;
	
	private S_SysRoleFunService roleFunService;
	
	private S_SysFunctionService functionService;
	
	private S_SysRoleService roleService;
	
	public String execute() throws Exception {
		roleFunService.deleteRoleFunction(sysRoleId);
		String[] addFunIdArray = addFunValue.split(",");
		for(int i = 0; i < addFunIdArray.length; i++){
			funId = Long.parseLong(addFunIdArray[i].toString());
			S_SysFunction function = functionService.get(funId);
			function.setFunId(funId);
			S_SysRole sysRole = roleService.get(sysRoleId);
			S_SysRoleFun roleFun = new S_SysRoleFun();
			roleFun.setSysFunction(function);
			roleFun.setFunName(function.getFunName());
			roleFun.setSysRole(sysRole);
			roleFun.setSysRoleName(sysRole.getSysRoleName());
			roleFun.setCreateDate(new Date());
			roleFun.setUpdateDate(new Date());
			roleFunService.save(roleFun);
		}
		
		return SUCCESS;
	}
	
	public String getRoleFunList(){
		S_SysRole sysRole = (S_SysRole)this.getHttpServletRequest().getAttribute("role");
		String result = "getRoleFunList";
		//List<S_SysRoleFun> roleFunList = roleFunService.getSysRoleFunctionListByRoleId(sysRole.getSysRoleId());
		List<S_SysRoleFun> roleFunList = sysRole.getSysRoleFunList();
		this.setRequestAttribute("roleFunList", roleFunList);
		return result;
	}

	public long getRefId() {
		return refId;
	}

	public void setRefId(long refId) {
		this.refId = refId;
	}

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

	public String getAddFunValue() {
		return addFunValue;
	}

	public void setAddFunValue(String addFunValue) {
		this.addFunValue = addFunValue;
	}

	public S_SysRoleFunService getRoleFunService() {
		return roleFunService;
	}

	public void setRoleFunService(S_SysRoleFunService roleFunService) {
		this.roleFunService = roleFunService;
	}

	public S_SysFunctionService getFunctionService() {
		return functionService;
	}

	public void setFunctionService(S_SysFunctionService functionService) {
		this.functionService = functionService;
	}

	public S_SysRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(S_SysRoleService roleService) {
		this.roleService = roleService;
	}	
}
