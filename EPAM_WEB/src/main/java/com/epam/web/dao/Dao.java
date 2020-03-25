package com.epam.web.dao;

import com.epam.web.entity.Identifable;
import com.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * Class {@code Dao} is designed to work
 * with database. It is a DAO pattern.
 *
 * @param <T>
 * @author Pavel Orlovski
 * @see com.epam.web.dao.Dao
 * @since 1.1
 */
public interface Dao<T extends Identifable> {

    /**
     * This method is designed to find object
     * by id.
     *
     * @param id
     * @return <code>Optional<T></code>
     * @throws DaoException
     */
    Optional<T> getById(Long id) throws DaoException;

    /**
     * This method is designed to find all
     * specific objects from database.
     *
     * @return a new List<code>T</code> which contain
     * all objects.
     * @throws DaoException
     */
    List<T> getAll() throws DaoException;

    /**
     * This method is designed to save
     * specific object. You should give
     * concrete object for save it in database.
     *
     * @param item
     * @throws DaoException
     */
    void save(T item) throws DaoException;

    /**
     * If you want to delete object by id
     * you should use this method. It is
     * designed to delete objects in database.
     *
     * @param id
     * @throws DaoException
     */
    void removeById(Long id) throws DaoException;
}
