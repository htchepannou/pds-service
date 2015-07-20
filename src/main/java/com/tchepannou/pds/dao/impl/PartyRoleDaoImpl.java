package com.tchepannou.pds.dao.impl;

import com.tchepannou.pds.dao.PartyRoleDao;
import com.tchepannou.pds.domain.PartyRole;
import com.tchepannou.pds.util.DateUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;

public class PartyRoleDaoImpl extends JdbcTemplate implements PartyRoleDao {
    public PartyRoleDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public PartyRole findById(long id) {
        try {
            return queryForObject(
                    "SELECT * FROM t_party_role WHERE id=?",
                    new Object[]{id},
                    getRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {    // NOSONAR
            return null;
        }
    }

    @Override
    public long create(PartyRole party) {
        final KeyHolder holder = new GeneratedKeyHolder();
        update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final String sql = "INSERT INTO t_party_role(party_fk, type_fk, status_fk, from_date)"
                        + "VALUES(?,?,?,?)";
                final PreparedStatement ps = connection.prepareStatement(sql, new String[] {"id"});

                ps.setLong(1, party.getPartyId());
                ps.setLong(2, party.getTypeId());
                if (party.getStatusId() == 0){
                    ps.setNull(3, Types.BIGINT);
                } else {
                    ps.setLong(3, party.getStatusId());
                }
                ps.setTimestamp(4, DateUtils.asTimestamp(party.getFromDate()));

                return ps;
            }
        }, holder);

        long id = holder.getKey().longValue();
        party.setId(id);
        return id;
    }

    @Override
    public void update(PartyRole partyRole) {
        update(
                "UPDATE t_party_role SET status_fk=? WHERE id=?",
                partyRole.getStatusId(),
                partyRole.getId()
        );
    }

    private RowMapper<PartyRole> getRowMapper (){
        return new RowMapper<PartyRole>() {
            @Override
            public PartyRole mapRow(final ResultSet rs, final int i) throws SQLException {
                final PartyRole obj = new PartyRole ();
                obj.setId(rs.getLong("id"));

                obj.setTypeId(rs.getLong("type_fk"));
                obj.setStatusId(rs.getLong("status_fk"));
                obj.setPartyId(rs.getLong("party_fk"));

                obj.setFromDate(rs.getTimestamp("from_date"));

                return obj;
            }
        };
    }
}
