package com.epam.web.builder;

import com.epam.web.entity.Identifable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface {@code Builder<T>} is the builder.
 * Every class implements this interface
 * has to override build method and implement it.
 *
 * @param <T>
 * @author Pavel Orlovski
 * @see com.epam.web.builder.Builder
 * @since 1.1
 */
public interface Builder<T extends Identifable> {

    /**
     * Gets ResultSet for create object defined by class
     * as generic. ResultSet is coming from database
     * at sql query.
     *
     * @param resultSet
     * @return a new object <code>T extend Identifable</code>
     * @exception SQLException if a ResultSet do not have
     * specific field which you called.
     */
    T build(ResultSet resultSet) throws SQLException;
}
