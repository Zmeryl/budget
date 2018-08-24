package com.leonyip.budget.dao.catalog;

import java.util.List;

import com.leonyip.budget.domain.catalog.B_BasePriceRes;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class B_BasePriceResDAO extends HibernateEntityDao<B_BasePriceRes> {
	/**
	 * 分页查询所有资源基价
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page getAllPriceResPage(int pageNo) {
		return this.pagedQuery(" from B_BasePriceRes ", pageNo,
				Constants.DEFAULT_PAGE_SIZE);
	}

	/**
	 * 查询某一类别下资源基价
	 * 
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceResPageByKind(int pageNo, Object... values) {
		return this.pagedQuery(
				" from B_BasePriceRes price where price.catalog.cataId=? ",
				pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}

	/**
	 * 根据关键字查询
	 * 
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceResPageByKeywords(int pageNo, Object values) {
		return this.pagedQuery(
				" from B_BasePriceRes price where price.cataName like '%"
						+ values + "%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}

	/**
	 * 根据名称查找
	 * 
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B_BasePriceRes getPriceByName(Object ... values) {
		List<B_BasePriceRes> list = this.find(
				" from B_BasePriceRes p where p.resName = ? ", values);
		if (list.size() > 0) {
			return (B_BasePriceRes) list.get(0);
		} else {
			return null;
		}
	}
}