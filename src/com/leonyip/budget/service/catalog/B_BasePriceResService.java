package com.leonyip.budget.service.catalog;

import java.util.List;

import com.leonyip.budget.dao.catalog.B_BasePriceResDAO;
import com.leonyip.budget.domain.catalog.B_BasePriceRes;
import com.leonyip.core.dao.support.Page;

public class B_BasePriceResService {
	
	private B_BasePriceResDAO priceResDAO;
	
	
	
	public B_BasePriceResDAO getPriceResDAO() {
		return priceResDAO;
	}

	public void setPriceResDAO(B_BasePriceResDAO priceResDAO) {
		this.priceResDAO = priceResDAO;
	}

	/**
	 * 分页查询所有资源基价
	 * @param pageNo
	 * @return
	 */
	public Page getAllPriceResPage(int pageNo){
		return priceResDAO.getAllPriceResPage(pageNo);
	}
	
	/**
	 * 查询某一类别下资源基价
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceResPageByKind(int pageNo, Object values){
		return priceResDAO.getPriceResPageByKind(pageNo, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getPriceResPageByKeywords(int pageNo, Object values){
		return priceResDAO.getPriceResPageByKeywords(pageNo, values);
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	public B_BasePriceRes getPriceByName(Object values){
		return priceResDAO.getPriceByName(values);
	}
	
	
	public void save(B_BasePriceRes o){
		priceResDAO.save(o);
	}
	
	public B_BasePriceRes get(long id){
		return (B_BasePriceRes)priceResDAO.get(id);
	}
	
	public List<B_BasePriceRes> getAll(){
		return priceResDAO.getAll();
	}
	
	public void removeById(long id){
		priceResDAO.removeById(id);
	}
}