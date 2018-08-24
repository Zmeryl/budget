package com.leonyip.budget.service.catalog;

import java.util.List;

import com.leonyip.budget.dao.catalog.B_BaseCatalogDAO;
import com.leonyip.budget.domain.catalog.B_BaseCatalog;
import com.leonyip.core.dao.support.Page;

public class B_BaseCatalogService {
	private B_BaseCatalogDAO catalogDAO;

	public B_BaseCatalogDAO getCatalogDAO() {
		return catalogDAO;
	}

	public void setCatalogDAO(B_BaseCatalogDAO catalogDAO) {
		this.catalogDAO = catalogDAO;
	}

	/**
	 * 分页查询所有分类信息
	 * 
	 * @param pageNo
	 * @return
	 */
	public Page getAllCatalogPage(int pageNo) {
		return catalogDAO.getAllCatalogPage(pageNo);
	}

	/**
	 * 查询父一级分类下的所有子分类
	 * 
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getCatalogPageByKind(int pageNo, Object values) {
		return catalogDAO.getCatalogPageByKind(pageNo, values);
	}

	/**
	 * 根据关键字查询
	 * 
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getCatalogPageByKeywords(int pageNo, Object values) {
		return catalogDAO.getCatalogPageByKeywords(pageNo, values);
	}

	public List<B_BaseCatalog> getAllByCataType(String cataType) {
		return catalogDAO.getAllByCataType(cataType);
	}

	/**
	 * 根据名称查找
	 * 
	 * @param values
	 * @return
	 */
	public B_BaseCatalog getCatalogByName(Object values) {
		return catalogDAO.getCatalogByName(values);
	}

	public void save(B_BaseCatalog o){
		catalogDAO.save(o);
	}
	
	public B_BaseCatalog get(long id){
		return (B_BaseCatalog)catalogDAO.get(id);
	}
	
	public List<B_BaseCatalog> getAll(){
		return catalogDAO.getAll();
	}
	
	public void removeById(long id){
		catalogDAO.removeById(id);
	}
}