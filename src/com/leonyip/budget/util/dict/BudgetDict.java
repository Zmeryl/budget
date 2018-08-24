package com.leonyip.budget.util.dict;

public class BudgetDict {
	//list页面的搜索类型
	public static final String SEARCH_TYPE_ALL = "1";
	public static final String SEARCH_TYPE_BY_KIND = "2";
	public static final String SEARCH_TYPE_BY_KEYWORD = "3";
	
	//基价分类 类别
	public static final String CATALOG_TYPE_HUMAN = "1";
	public static final String CATALOG_TYPE_RES = "2";
	public static final String CATALOG_TYPE_OTHER = "3";
	
	//费用类型
	public static final String PRICE_TYPE_FIX = "1";
	public static final String PRICE_TYPE_RATE = "2";
	
	//请求结果返回到继续工作页面
	public static final String IS_ADD_MORE_FALSE = "0";
	public static final String IS_ADD_MORE_TRUE = "1"; 
	
	//人月天数
	public static final int HUMAN_WORK_DAYS_PRE_MONTH = 21;
	
	//session obj
	public static final String SESSION_USER = "_S__SESSION_USER_OBJ";
	public static final String SESSION_FUNCTION_LIST = "_S__FUNCTION_LIST";
}
