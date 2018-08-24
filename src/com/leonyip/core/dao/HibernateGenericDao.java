package com.leonyip.core.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.leonyip.core.dao.support.Page;
import com.leonyip.core.util.BeanUtils;

public class HibernateGenericDao extends HibernateDaoSupport {
	public <T> Object get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	public <T> List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public <T> List<T> getAll(Class<T> entityClass, String orderBy,
			boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc) {
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(
							Order.asc(orderBy)));
		}

		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(entityClass).addOrder(
						Order.desc(orderBy)));
	}

	public void save(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
	}

	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	public <T> void removeById(Class<T> entityClass, Serializable id) {
		remove(get(entityClass, id));
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	public Query createQuery(String hql, Object[] values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; ++i)
			query.setParameter(i, values[i]);

		return query;
	}

	public <T> Criteria createCriteria(Class<T> entityClass,
			Criterion[] criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		Criterion[] arr$ = criterions;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; ++i$) {
			Criterion c = arr$[i$];
			criteria.add(c);
		}
		return criteria;
	}

	public <T> Criteria createCriteria(Class<T> entityClass, String orderBy,
			boolean isAsc, Criterion[] criterions) {
		Assert.hasText(orderBy);

		Criteria criteria = createCriteria(entityClass, criterions);

		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else {
			criteria.addOrder(Order.desc(orderBy));
		}

		return criteria;
	}

	public List find(String hql, Object[] values) {
		Assert.hasText(hql);
		return getHibernateTemplate().find(hql, values);
	}

	public List getListFromCache(String hql, Object[] values) {
		Assert.hasText(hql);
		List list = new ArrayList();
		Iterator iterator = getHibernateTemplate().iterate(hql, values);
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	public Object findObjByHql(String hql, Object[] values) {
		List list = find(hql, values);
		if (list.isEmpty())
			return null;

		return list.get(0);
	}

	public Object findUnique(Class entityClass, Projection projection,
			Collection<Criterion> conditions) {
		Criteria criteria = createCriteria(entityClass,
				(Criterion[]) conditions.toArray(new Criterion[conditions
						.size()]));

		Assert.notNull(projection);
		return criteria.setProjection(projection).uniqueResult();
	}

	public Object findUnique(Class entityClass, Collection<Criterion> conditions) {
		Criteria criteria = createCriteria(entityClass,
				(Criterion[]) conditions.toArray(new Criterion[conditions
						.size()]));

		Assert.notNull(criteria);
		return criteria.uniqueResult();
	}

	private Integer getCount(Class entityClass, Criterion[] criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		Assert.notNull(criteria);
		return ((Integer) (Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult());
	}

	public <T> List<T> find(Class<T> entityClass,
			Collection<Criterion> conditions) {
		if (conditions == null)
			conditions = new ArrayList();
		Criteria criteria = createCriteria(entityClass,
				(Criterion[]) conditions.toArray(new Criterion[conditions
						.size()]));

		Assert.notNull(criteria);
		return criteria.list();
	}

	public <T> List<T> find(Class<T> entityClass,
			Collection<Criterion> conditions, Order order) {
		if (conditions == null)
			conditions = new ArrayList();
		Criteria criteria = createCriteria(entityClass,
				(Criterion[]) conditions.toArray(new Criterion[conditions
						.size()]));

		criteria.addOrder(order);
		Assert.notNull(criteria);
		return criteria.list();
	}

	public Integer getCount(Class entityClass, Collection<Criterion> conditions) {
		if (conditions == null)
			conditions = new ArrayList();
		return getCount(entityClass, (Criterion[]) conditions
				.toArray(new Criterion[conditions.size()]));
	}

	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass,
				new Criterion[] { Restrictions.eq(propertyName, value) })
				.list();
	}

	public <T> List<T> findBy(Class<T> entityClass, String propertyName,
			Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(entityClass, orderBy, isAsc,
				new Criterion[] { Restrictions.eq(propertyName, value) })
				.list();
	}

	public <T> Object findUniqueBy(Class<T> entityClass, String propertyName,
			Object value) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass,
				new Criterion[] { Restrictions.eq(propertyName, value) })
				.uniqueResult();
	}

	public Page pagedQuery(String hql, int pageNo, int pageSize, Object[] values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(hql));

		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = ((Long) countlist.get(0)).longValue();

		if (totalCount < 1L) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		List list = query.list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	public Page pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		List orderEntries;
		Assert.notNull(criteria);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		CriteriaImpl impl = (CriteriaImpl) criteria;

		Projection projection = impl.getProjection();
		try {
			orderEntries = (List) BeanUtils.forceGetProperty(impl,
					"orderEntries");

			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		long totalCount = ((Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult()).intValue();

		criteria.setProjection(projection);
		if (projection == null)
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		if (totalCount < 1L)
			return new Page();

		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List list = criteria.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	public Page pagedQuery(Class entityClass, int pageNo, int pageSize,
			Criterion[] criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		return pagedQuery(criteria, pageNo, pageSize);
	}

	public Page pagedQuery(Class entityClass, int pageNo, int pageSize,
			Collection<Criterion> conditions) {
		if (conditions == null)
			conditions = new ArrayList();
		return pagedQuery(entityClass, pageNo, pageSize,
				(Criterion[]) conditions.toArray(new Criterion[conditions
						.size()]));
	}

	public Page pagedQuery(Class entityClass, int pageNo, int pageSize,
			Collection<Criterion> conditions, Order order) {
		if (conditions == null)
			conditions = new ArrayList();
		Criteria criteria = createCriteria(entityClass,
				(Criterion[]) conditions.toArray(new Criterion[conditions
						.size()]));

		criteria.addOrder(order);
		return pagedQuery(criteria, pageNo, pageSize);
	}

	public Page pagedQuery(Class entityClass, int pageNo, int pageSize,
			String orderBy, boolean isAsc, Criterion[] criterions) {
		Criteria criteria = createCriteria(entityClass, orderBy, isAsc,
				criterions);

		return pagedQuery(criteria, pageNo, pageSize);
	}

	public Page pagedQuery(String hql, int pageNo, int pageSize) {
		Object[] values = new Object[0];
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(hql));

		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = ((Long) countlist.get(0)).longValue();

		if (totalCount < 1L) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	public <T> boolean isUnique(Class<T> entityClass, Object entity,
			String names) {
		Assert.hasText(names);
		Criteria criteria = createCriteria(entityClass, new Criterion[0])
				.setProjection(Projections.rowCount());

		String[] nameList = names.split(",");
		try {
			String[] arr$ = nameList;
			int len$ = arr$.length;
			for (int i$ = 0; i$ < len$; ++i$) {
				String name = arr$[i$];
				criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(
						entity, name)));
			}

			String idName = getIdName(entityClass);

			Serializable id = getId(entityClass, entity);

			if (id != null)
				criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return (((Integer) criteria.uniqueResult()).intValue() == 0);
	}

	public Serializable getId(Class entityClass, Object entity)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(entityClass);
		return ((Serializable) PropertyUtils.getProperty(entity,
				getIdName(entityClass)));
	}

	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz
				+ " not define in hibernate session factory.");

		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName()
				+ " has no identifier property define.");

		return idName;
	}

	public Page pagedExecute(String sql, int pageNo, int pageSize) {
		SQLQuery query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);

		ScrollableResults scrollableResults = query
				.scroll(ScrollMode.SCROLL_SENSITIVE);

		scrollableResults.last();
		int totalCount = scrollableResults.getRowNumber();

		return getPageResult(query, totalCount + 1, pageNo, pageSize);
	}

	public Page pagedExecute(String sql, int pageNo, int pageSize,
			Object[] params) {
		SQLQuery query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);

		if (params != null)
			for (int i = 0; i < params.length; ++i)
				query.setParameter(i, params[i]);

		ScrollableResults scrollableResults = query
				.scroll(ScrollMode.SCROLL_SENSITIVE);

		scrollableResults.last();
		int totalCount = scrollableResults.getRowNumber();

		return getPageResult(query, totalCount + 1, pageNo, pageSize);
	}

	public List findExecute(String sql, Object[] params) {
		SQLQuery query = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createSQLQuery(sql);

		if (params != null)
			for (int i = 0; i < params.length; ++i)
				query.setParameter(i, params[i]);

		return query.list();
	}

	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue((beginPos != -1) ? true : false, " hql : " + hql
				+ " must has a keyword 'from'");

		return hql.substring(beginPos);
	}

	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);

		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find())
			m.appendReplacement(sb, "");

		m.appendTail(sb);
		return sb.toString();
	}

	private Page getPageResult(Query query, int totalCount, int pageNo,
			int pageSize) {
		if (totalCount < 1) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();

		return new Page(startIndex, totalCount, pageSize, list);
	}

	public void bulkUpdate(String hql, Object[] values) {
		getHibernateTemplate().bulkUpdate(hql, values);
	}

	public HibernateTemplate getTemplate() {
		return getHibernateTemplate();
	}
}