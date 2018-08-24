package com.leonyip.budget.dao.catalog;

import java.util.List;

import com.leonyip.budget.domain.catalog.B_BasePriceOther;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class B_BasePriceOtherDAO extends HibernateEntityDao<B_BasePriceOther> {
	/**
	 * 分页查询所有其他资源基价
	 * @param pageNo
	 * @return
	 */
	public Page getAllPriceOtherPage(int pageNo){
		return this.pagedQuery(" from B_BasePriceOther ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 查询某一类别下资源基价基价
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceOtherPageByKind(int pageNo, Object ... values){
		return this.pagedQuery( " from B_BasePriceOther price where price.catalog.cataId=? ", pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceOtherPageByKeywords(int pageNo, Object values){
		return this.pagedQuery( " from B_BasePriceOther price where price.cataName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B_BasePriceOther getPriceByName(Object... values){
		List<B_BasePriceOther> list = this.find(" from B_BasePriceOther p where p.resName = ? ", values);
		if( list.size() > 0 ){
			return (B_BasePriceOther)list.get(0);
		}else{
			return null;
		}
	}
}