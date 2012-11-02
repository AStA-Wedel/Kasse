package org.fhw.asta.kasse.server.dao;

import java.util.List;

/**
 * Generic doa interface representing the usual CRUD-operations
 * @author Chris
 *
 * @param <T> entity type
 */
public interface Dao<T> {
    T create(T t);

    T update(T t);

    void delete(T id);

    T getById(Object id);

    List<T> getAll();
}
