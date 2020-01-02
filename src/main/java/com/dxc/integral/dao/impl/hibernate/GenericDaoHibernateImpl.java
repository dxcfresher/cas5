package com.dxc.integral.dao.impl.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.integral.dao.GenericDao;

/**
 * Abstract Hibernate implementation for {@link GenericDao}.
 */
@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public abstract class GenericDaoHibernateImpl<T, ID extends Serializable>
		implements GenericDao<T, ID> {

	private Class<T> persistentClass;
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public GenericDaoHibernateImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Class<T> getPersistentClass() {
		return persistentClass;
	}

//	@SuppressWarnings("unchecked")
//	public List<T> findByExample(T exampleInstance, String... excludeProperty) {
//		Criteria crit = getSession().createCriteria(getPersistentClass());
//		Example example = Example.create(exampleInstance);
//		for (String exclude : excludeProperty) {
//			example.excludeProperty(exclude);
//		}
//		crit.add(example);
//		return crit.list();
//	}

	@SuppressWarnings("unchecked")
	protected List<T> findByDetachedCriteria(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());
		return criteria.list();
	}

	public void create(T newInstance) {
		getSession().saveOrUpdate(newInstance);
	}

	@SuppressWarnings("unchecked")
	public T read(ID id) {
		return (T) getSession().load(getPersistentClass(), id);
	}

	public void update(T transientObject) {
		getSession().saveOrUpdate(transientObject);
	}

	public void delete(T persistentObject) {
		getSession().delete(persistentObject);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
