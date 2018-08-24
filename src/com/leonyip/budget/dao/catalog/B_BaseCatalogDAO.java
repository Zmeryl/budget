package com.leonyip.budget.dao.catalog;

import java.util.List;

import com.leonyip.budget.domain.catalog.B_BaseCatalog;
import com.leonyip.core.configuration.Constants;
import com.leonyip.core.dao.HibernateEntityDao;
import com.leonyip.core.dao.support.Page;

public class B_BaseCatalogDAO extends HibernateEntityDao<B_BaseCatalog> {
	/**
	 * 分页查询所有分类信息
	 * @param pageNo
	 * @return
	 */
	public Page getAllCatalogPage(int pageNo){
		return this.pagedQuery(" from B_BaseCatalog ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 查询父一级分类下的所有子分类
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getCatalogPageByKind(int pageNo, Object ... values){
		return this.pagedQuery( " from B_BaseCatalog cata where cata.superId=? ", pageNo, Constants.DEFAULT_PAGE_SIZE, values);
	}
	
	/**
	 * 根据关键字查询
	 * @param pageNo
	 * @param values
	 * @return
	 */
	public Page getCatalogPageByKeywords(int pageNo, Object values){
		return this.pagedQuery( " from B_BaseCatalog cata where cata.cataName like '%"+ values +"%' ", pageNo, Constants.DEFAULT_PAGE_SIZE);
	}
	
	@SuppressWarnings("unchecked")
	public List<B_BaseCatalog> getAllByCataType(String ... cataType){
		String hql = " from B_BaseCatalog cata where cata.cataType = ? ";
		List<B_BaseCatalog> list = find(hql, cataType);
		return list;
	}
	
	/**
	 * 根据名称查找
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public B_BaseCatalog getCatalogByName(Object ... values){
		List<B_BaseCatalog> list = this.find(" from B_BaseCatalog p where p.cataName = ? ", values);
		if( list.size() > 0 ){
			return (B_BaseCatalog)list.get(0);
		}else{
			return null;
		}
	}
}