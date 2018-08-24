package com.leonyip.budget.web.action.project;
import com.leonyip.budget.util.dict.BudgetDict;
public class M_PageTurnAction extends M_BaseProjectAction {

	private static final long serialVersionUID = 7372108043666394733L;
	
	private String isAddMore;
	
	public String execute() {
		String result = SUCCESS;
		if(isAddMore != null){
			if(isAddMore.equals(BudgetDict.IS_ADD_MORE_TRUE)){
				result = "addDetailMore";
			}else if(isAddMore.equals(BudgetDict.IS_ADD_MORE_FALSE)){
				result = "addDetail";
			}
		}
		return result;
	}

	public String getIsAddMore() {
		return isAddMore;
	}

	public void setIsAddMore(String isAddMore) {
		this.isAddMore = isAddMore;
	}
}
