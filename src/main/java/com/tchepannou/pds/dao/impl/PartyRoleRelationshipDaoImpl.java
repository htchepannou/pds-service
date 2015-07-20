package com.tchepannou.pds.dao.impl;

import com.tchepannou.pds.dao.PartyRoleRelationshipDao;
import com.tchepannou.pds.domain.PartyRoleRelationship;
import com.tchepannou.pds.util.DateUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PartyRoleRelationshipDaoImpl extends JdbcTemplate implements PartyRoleRelationshipDao {
    public PartyRoleRelationshipDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override 
    public PartyRoleRelationship findById(final long id) {
        try {
            return queryForObject(
                    "SELECT * FROM t_party_role_relationship WHERE id=?",
                    new Object[]{id},
                    getRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {    // NOSONAR
            return null;
        }
    }

    @Override
    public PartyRoleRelationship findByFromByToByType (final long fromId, final long toId, final long typeId) {
        try {
            return queryForObject(
                    "SELECT * FROM t_party_role_relationship WHERE from_fk=? AND to_fk=? AND type_fk=?",
                    new Object[]{fromId, toId, typeId},
                    getRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {    // NOSONAR
            return null;
        }
    }

    @Override
    public List<PartyRoleRelationship> findByFromId(final long fromId) {
        return query(
                "SELECT * FROM t_party_role_relationship WHERE from_fk=?",
                new Object[]{fromId},
                getRowMapper()
        );
    }

    @Override
    public long create(final PartyRoleRelationship relationship) {
        final KeyHolder holder = new GeneratedKeyHolder();
        update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final String sql = "INSERT INTO t_party_role_relationship(from_fk, to_fk, type_fk, from_date)"
                        + "VALUES(?,?,?,?)";
                final PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});

                ps.setLong(1, relationship.getFromId());
                ps.setLong(2, relationship.getToId());
                ps.setLong(3, relationship.getTypeId());
                ps.setTimestamp(4, DateUtils.asTimestamp(relationship.getFromDate()));
                return ps;
            }
        }, holder);

        long id = holder.getKey().longValue();
        relationship.setId(id);
        return id;
    }

    @Override 
    public void delete(final long id) {
        update("DELETE FROM t_party_role_relationship WHERE id=?", id);
    }

    protected RowMapper<PartyRoleRelationship> getRowMapper() {
        return new RowMapper() {
            @Override
            public PartyRoleRelationship mapRow(ResultSet rs, int i) throws SQLException {
                PartyRoleRelationship obj = new PartyRoleRelationship();
                obj.setId(rs.getLong("id"));
                obj.setFromDate(rs.getDate("from_date"));
                obj.setFromId(rs.getLong("from_fk"));
                obj.setToId(rs.getLong("to_fk"));
                obj.setTypeId(rs.getLong("type_fk"));
                return obj;
            }
        };
    }
    
}
