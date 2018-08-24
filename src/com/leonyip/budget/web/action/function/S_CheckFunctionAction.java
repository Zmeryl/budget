package com.leonyip.budget.web.action.function;

import java.util.List;

import com.leonyip.budget.util.dict.BudgetDict;
import com.leonyip.core.web.action.BaseAction;

public class S_CheckFunctionAction extends BaseAction {

	private static final long serialVersionUID = 2884735847235451536L;

	public String getAuthStr() {
		return null;
	}

	public String execute() {
		return SUCCESS;
	}

	/**
	 * 检查用户是否具有某一种权限
	 * 
	 * @param resName
	 * @param resDetail
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean checkUserFunction(String resName, String actionName) {
		List<String> functionCodeList = (List<String>) this
				.getSessionObj(BudgetDict.SESSION_FUNCTION_LIST);
		//********8
		if (functionCodeList != null) {
			for (String functionStr : functionCodeList) {
				String[] functionArray = functionStr.split("@");
				if (functionArray[0].equals(resName)
						&& functionArray[1].equals(actionName)) {
					return true;
				}
			}
		}
		return false;
	}
}