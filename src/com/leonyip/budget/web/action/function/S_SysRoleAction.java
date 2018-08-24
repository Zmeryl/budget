package com.leonyip.budget.web.action.function;

import java.util.List;

import com.leonyip.budget.domain.function.S_SysRole;
import com.leonyip.budget.service.function.S_SysRoleService;
import com.leonyip.budget.service.user.B_UserService;
import com.leonyip.core.dao.support.Page;
import com.leonyip.core.web.action.BaseAction;

public class S_SysRoleAction extends BaseAction {

	private static final long serialVersionUID = -6381763414151605443L;

	private static final String SYS_RES_NAME = "sysrole";

	private long sysRoleId;

	private String sysRoleName;

	private String sysRoleDesc;

	private List<S_SysRole> sysRoleFunList;

	private S_SysRoleService roleService;

	private int pageNo;

	private String searchType;

	private String searchValue;

	private String deleteValue;

	private B_UserService userService;

	public String execute() throws Exception {

		String action = "view";
		if (!new S_CheckFunctionAction()
				.checkUserFunction(SYS_RES_NAME, action)) {
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}

		if (pageNo < 1) {
			pageNo = 1;
		}
		Page page;
		if (searchType == null || searchType.equals("")
				|| searchType.equals("1")) {
			page = roleService.getAllRolePage(pageNo);
		} else {
			page = roleService.getRolePageByKeywords(pageNo, searchValue);
		}

		this.setRequestAttribute("page", page);
		this.setRequestAttribute("searchValue", searchValue);
		return SUCCESS;
	}

	public String addRole() throws Exception {
		String action = "add";
		if (!new S_CheckFunctionAction()
				.checkUserFunction(SYS_RES_NAME, action)) {
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}

		if (sysRoleName == null || sysRoleName.trim().length() == 0) {
			throw new NullPointerException("roleName is null...");
		}

		if (!checkName(sysRoleName)) {
			this.setRequestAttribute("_error_message", "角色名称已存在，请检查后重新操作！");
			return "error_function";
		}

		String result = "addRole";
		S_SysRole role = new S_SysRole();
		role.setSysRoleName(sysRoleName);
		role.setSysRoleDesc(sysRoleDesc);
		roleService.save(role);
		return result;
	}

	public String deleteRole() {
		String action = "delete";
		if (!new S_CheckFunctionAction()
				.checkUserFunction(SYS_RES_NAME, action)) {
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}

		String result = "deleteRole";
		String[] deleteArray = deleteValue.split(",");
		for (int i = 0; i < deleteArray.length; i++) {
			long deleteId = Long.parseLong(deleteArray[i]);
			if (userService.checkUserSysRole(deleteId)) {
				S_SysRole role = roleService.get(deleteId);
				this.setRequestAttribute("_error_message", "系统角色'"
						+ role.getSysRoleName() + "'还有所属用户，无法删除！");
				return "error_function";
			}
		}

		for (int i = 0; i < deleteArray.length; i++) {
			long deleteId = Long.parseLong(deleteArray[i]);
			roleService.removeById(deleteId);
		}
		return result;
	}

	public String updateRole() {
		String result = "updateRole";
		S_SysRole role = new S_SysRole();
		role.setSysRoleId(sysRoleId);
		role.setSysRoleName(sysRoleName);
		role.setSysRoleDesc(sysRoleDesc);
		roleService.save(role);
		return result;
	}

	public String showEditRoleJSP() {
		String action = "edit";
		if (!new S_CheckFunctionAction()
				.checkUserFunction(SYS_RES_NAME, action)) {
			this.setRequestAttribute("_error_message", "您无权查看该页面！");
			return "error_function";
		}

		String result = "showEditRoleJSP";
		S_SysRole role = roleService.get(sysRoleId);
		this.setRequestAttribute("role", role);
		return result;
	}

	public String viewRole() {
		String result = "viewRole";
		S_SysRole role = roleService.get(sysRoleId);
		this.setRequestAttribute("role", role);
		return result;
	}

	public String showAddRoleJSP(){
		return "showAddRoleJSP";
	}
	
	private boolean checkName(String name) {
		if (name == null) {
			return true;
		}
		S_SysRole role = roleService.getSysRoleByName(name);
		if (role == null) {
			return true;
		} else {
			return false;
		}
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

	public String getSysRoleDesc() {
		return sysRoleDesc;
	}

	public void setSysRoleDesc(String sysRoleDesc) {
		this.sysRoleDesc = sysRoleDesc;
	}

	public List<S_SysRole> getSysRoleFunList() {
		return sysRoleFunList;
	}

	public void setSysRoleFunList(List<S_SysRole> sysRoleFunList) {
		this.sysRoleFunList = sysRoleFunList;
	}

	public S_SysRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(S_SysRoleService roleService) {
		this.roleService = roleService;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getDeleteValue() {
		return deleteValue;
	}

	public void setDeleteValue(String deleteValue) {
		this.deleteValue = deleteValue;
	}

	public B_UserService getUserService() {
		return userService;
	}

	public void setUserService(B_UserService userService) {
		this.userService = userService;
	}
}