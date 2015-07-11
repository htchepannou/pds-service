package com.tchepannou.pds.dao.impl;

import com.tchepannou.pds.dao.AbstractContactMechanismDao;
import com.tchepannou.pds.domain.ContactMechanism;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractContactMechanismDaoImpl<T extends ContactMechanism>
        extends JdbcTemplate
        implements AbstractContactMechanismDao<T>
{
    public AbstractContactMechanismDaoImpl(final DataSource dataSource) {
        super(dataSource);
    }

    //-- Abstract methods
    protected abstract RowMapper<T> getRowMapper ();

    protected abstract String getTableName ();

    protected abstract PreparedStatement preparedStatement(T address, Connection connection) throws SQLException;


    //-- AbstractContactMechanismDao overrides
    @Override
    public T findById (long id) {
        try {
            return queryForObject(
                    String.format("SELECT * FROM %s WHERE id=?", getTableName()),
                    new Object[]{id},
                    getRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {    // NOSONAR
            return null;
        }
    }

    @Override
    public T findByHash (String hash) {
        try {
            return queryForObject(
                    String.format("SELECT * FROM %s WHERE hash=?", getTableName()),
                    new Object[]{hash},
                    getRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {    // NOSONAR
            return null;
        }
    }

    @Override
    public List<T> findByIds(Collection<? extends Long> ids) {
        if (ids.isEmpty()){
            return Collections.emptyList();
        }

        final StringBuilder sql = new StringBuilder(
                String.format("SELECT * FROM %s WHERE id IN (", getTableName())
        );
        for (int i=0, size=ids.size() ; i<size ; i++) {
            if (i>0){
                sql.append(',');
            }
            sql.append('?');
        }
        sql.append(")");

        return query(sql.toString(), ids.toArray(), getRowMapper());
    }

    @Override
    public long create(final T address) {
        final KeyHolder holder = new GeneratedKeyHolder();
        update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                return preparedStatement(address, connection);
            }
        }, holder);

        long id = holder.getKey().longValue();
        address.setId(id);
        return id;
    }
}
