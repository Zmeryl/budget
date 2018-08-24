package com.leonyip.budget.dao.catalog;

import java.util.List;

import com.leonyip.budget.domain.catalog.B_BasePriceHuman;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class B_BasePriceHumanDAO extends HibernateEntityDao<B_BasePriceHuman> {
	/**
	 * 分页查询所有人员基价
	 * @param pageNo
	 * @return
	 */
	public Page getAllPriceHumanPage(int pageNo){
		return this.pagedQuery(" from B_BasePriceHuman ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 查询某一类别下人员基价
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceHumanPageByKind(int pageNo, Object ... values){
		return this.pagedQuery( " from B_BasePriceHuman price where price.catalog.cataId = ? ", pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceHumanPageByKeywords(int pageNo, Object values){
		return this.pagedQuery( " from B_BasePriceHuman price where price.cataName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B_BasePriceHuman getPriceByName(Object ... values){
		List<B_BasePriceHuman> list = this.find(" from B_BasePriceHuman p where p.userName = ? ", values);
		if( list.size() > 0 ){
			return (B_BasePriceHuman)list.get(0);
		}else{
			return null;
		}
	}	
}