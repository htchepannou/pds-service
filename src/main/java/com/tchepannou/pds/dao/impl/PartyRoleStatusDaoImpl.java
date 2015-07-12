package com.tchepannou.pds.dao.impl;

import com.tchepannou.pds.dao.PartyRoleStatusDao;
import com.tchepannou.pds.domain.PartyRoleStatus;
import com.tchepannou.pds.util.DateUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PartyRoleStatusDaoImpl extends JdbcTemplate implements PartyRoleStatusDao {
    public PartyRoleStatusDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public PartyRoleStatus findById(long id) {
        try {
            return queryForObject(
                    "SELECT * FROM t_party_role_status WHERE id=?",
                    new Object[]{id},
                    getRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {    // NOSONAR
            return null;
        }
    }

    @Override
    public List<PartyRoleStatus> findByPartyRole(long id) {
        return query(
                "SELECT * FROM t_party_role_status WHERE user_fk=?",
                new Object[]{id},
                getRowMapper()
        );
    }

    @Override
    public long create(PartyRoleStatus status) {
        status.setDate(new Date());
        final KeyHolder holder = new GeneratedKeyHolder();
        update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final String sql = "INSERT INTO t_party_role_status(party_role_fk, status_code_fk, status_date, comment) VALUES(?,?,?,?)";
                final PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});

                ps.setLong(1, status.getPartyRoleId());
                ps.setLong(2, status.getStatusCodeId());
                ps.setTimestamp(3, DateUtils.asTimestamp(status.getDate()));
                ps.setString(4, status.getComment());

                return ps;
            }
        }, holder);

        long id = holder.getKey().longValue();
        status.setId(id);
        return id;
    }

    private RowMapper<PartyRoleStatus> getRowMapper(){
        return new RowMapper<PartyRoleStatus>() {
            @Override
            public PartyRoleStatus mapRow(ResultSet resultSet, int i) throws SQLException {
                PartyRoleStatus obj = new PartyRoleStatus();
                obj.setId(resultSet.getLong("id"));
                obj.setPartyRoleId(resultSet.getLong("party_role_fk"));
                obj.setStatusCodeId(resultSet.getLong("status_code_fk"));
                obj.setComment(resultSet.getString("comment"));
                obj.setDate(resultSet.getTimestamp("status_date"));
                return obj;
            }
        };
    }
}
