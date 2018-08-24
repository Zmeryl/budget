package com.leonyip.budget.service.catalog;

import java.util.List;

import com.leonyip.budget.dao.catalog.B_BasePriceHumanDAO;
import com.leonyip.budget.domain.catalog.B_BasePriceHuman;
import com.leonyip.core.dao.support.Page;

public class B_BasePriceHumanService  {
	private B_BasePriceHumanDAO priceHumanDAO;
	
	
	
	public B_BasePriceHumanDAO getPriceHumanDAO() {
		return priceHumanDAO;
	}

	public void setPriceHumanDAO(B_BasePriceHumanDAO priceHumanDAO) {
		this.priceHumanDAO = priceHumanDAO;
	}

	/**
	 * 分页查询所有人员基价
	 * @param pageNo
	 * @return
	 */
	public Page getAllPriceHumanPage(int pageNo){
		return priceHumanDAO.getAllPriceHumanPage(pageNo);
	}
	
	/**
	 * 查询某一类别下人员基价
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceHumanPageByKind(int pageNo, Object values){
		return priceHumanDAO.getPriceHumanPageByKind(pageNo, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceHumanPageByKeywords(int pageNo, Object values){
		return priceHumanDAO.getPriceHumanPageByKeywords(pageNo, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public B_BasePriceHuman getPriceByName(Object values){
		return priceHumanDAO.getPriceByName(values);
	}
	
	public void save(B_BasePriceHuman o){
		priceHumanDAO.save(o);
	}
	
	public B_BasePriceHuman get(long id){
		return (B_BasePriceHuman)priceHumanDAO.get(id);
	}
	
	public List<B_BasePriceHuman> getAll(){
		return priceHumanDAO.getAll();
	}
	
	public void removeById(long id){
		priceHumanDAO.removeById(id);
	}
}