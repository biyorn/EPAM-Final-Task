package com.epam.web.dao;

import com.epam.web.builder.Builder;
import com.epam.web.builder.BuilderFactory;
import com.epam.web.entity.Identifable;
import com.epam.web.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifable> implements Dao<T> {

    private Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, Builder<T> builder, String... params) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            setStatementParameters(statement, params);
            ResultSet resultSet = statement.executeQuery();

            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }

            return entities;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected void executeUpdate(String query, String... params) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            setStatementParameters(statement, params);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    protected Optional<T> executeForSingleResult(String query, Builder<T> builder, String... params)
            throws DaoException {
        List<T> items = executeQuery(query, builder, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else {
            return Optional.empty();
        }
    }

    protected abstract String getTableName();

    @Override
    public List<T> getAll() throws DaoException {
        String table = getTableName();
        return executeQuery("SELECT * FROM " + table, BuilderFactory.get(table));
    }

    @Override
    public Optional<T> getById(Long id) throws DaoException {
        String table = getTableName();
        String select = "SELECT * FROM " + table + " WHERE id = ?";
        String localId = String.valueOf(id);
        return executeForSingleResult(select, BuilderFactory.get(table), localId);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        String table = getTableName();
        String delete = "DELETE FROM " + table + " WHERE id = ?";
        String tempId = String.valueOf(id);
        executeUpdate(delete, tempId);
    }

    private void setStatementParameters(PreparedStatement statement, String... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setString(i + 1, params[i]);
        }
    }
}
