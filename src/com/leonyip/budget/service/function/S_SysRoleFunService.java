package com.leonyip.budget.service.function;

import java.util.List;

import com.leonyip.budget.dao.function.S_SysRoleFunDAO;
import com.leonyip.budget.domain.function.S_SysRoleFun;

public class S_SysRoleFunService  {
	private S_SysRoleFunDAO roleFunDAO;
	
	public S_SysRoleFunDAO getRoleFunDAO() {
		return roleFunDAO;
	}

	public void setRoleFunDAO(S_SysRoleFunDAO roleFunDAO) {
		this.roleFunDAO = roleFunDAO;
	}

	public List<S_SysRoleFun> getSysRoleFunctionListByRoleId(Object ... roldId){
		return roleFunDAO.getSysRoleFunctionListByRoleId(roldId);
	}
	
	public void deleteRoleFunction(long sysRoleId){
		roleFunDAO.deleteRoleFunction(sysRoleId);
	}
	
	public void save(S_SysRoleFun roleFun){
		roleFunDAO.save(roleFun);
	}
}
