package com.leonyip.budget.service.catalog;

import java.util.List;

import com.leonyip.budget.dao.catalog.B_BasePriceOtherDAO;
import com.leonyip.budget.domain.catalog.B_BasePriceOther;
import com.leonyip.core.dao.support.Page;

public class B_BasePriceOtherService{
	
	private B_BasePriceOtherDAO priceOtherDAO;
	
	
	
	public B_BasePriceOtherDAO getPriceOtherDAO() {
		return priceOtherDAO;
	}

	public void setPriceOtherDAO(B_BasePriceOtherDAO priceOtherDAO) {
		this.priceOtherDAO = priceOtherDAO;
	}

	/**
	 * 分页查询所有其他资源基价
	 * @param pageNo
	 * @return
	 */
	public Page getAllPriceOtherPage(int pageNo){
		return priceOtherDAO.getAllPriceOtherPage(pageNo);
	}
	
	/**
	 * 查询某一类别下资源基价基价
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceOtherPageByKind(int pageNo, Object values){
		return priceOtherDAO.getPriceOtherPageByKind(pageNo, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceOtherPageByKeywords(int pageNo, Object values){
		return priceOtherDAO.getPriceOtherPageByKeywords(pageNo, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public B_BasePriceOther getPriceByName(Object values){
		return priceOtherDAO.getPriceByName(values);
	}

	public void save(B_BasePriceOther o){
		priceOtherDAO.save(o);
	}
	
	public B_BasePriceOther get(long id){
		return (B_BasePriceOther)priceOtherDAO.get(id);
	}
	
	public List<B_BasePriceOther> getAll(){
		return priceOtherDAO.getAll();
	}
	
	public void removeById(long id){
		priceOtherDAO.removeById(id);
	}
}