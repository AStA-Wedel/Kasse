package org.fhw.asta.kasse.server.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.inject.Inject;

/**
 * Implementation of basic CRUD operations.
 * @author Chris
 *
 * @param <T> entity type
 */
public abstract class AbstractDao<T> implements Dao<T> {

    @Inject
    private EntityManager em;

    private Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public T create(T t) {
        em.persist(t);
        return t;
    }

    @Override
    public T update(T t) {
        return em.merge(t);
    }

    @Override
    public void delete(T t) {
        em.remove(t);
    }

    @Override
    public T getById(Object id) {
        return em.find(type, id);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(type);
        Root<T> root = query.from(type);
        CriteriaQuery<T> select = query.select(root);
        return em.createQuery(select).getResultList();
    }

    protected EntityManager getEntityManager() {
        return em;
    }
}
