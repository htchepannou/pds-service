package com.tchepannou.pds.dao.impl;

import com.tchepannou.core.dao.impl.AbstractPersistentEnumDaoImpl;
import com.tchepannou.pds.dao.PartyRoleStatusCodeDao;
import com.tchepannou.pds.domain.PartyRoleStatusCode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartyRoleStatusCodeDaoImpl extends AbstractPersistentEnumDaoImpl<PartyRoleStatusCode> implements PartyRoleStatusCodeDao {

    public PartyRoleStatusCodeDaoImpl(final DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public PartyRoleStatusCode findDefault(final long typeId) {
        return queryForObject(
                "SELECT * FROM " + getTableName() + " S"
                + " JOIN t_party_role_default_status_code D"
                + " WHERE S.id=D.status_code_fk AND S.type_fk=?",
                new Object[]{typeId},
                getRowMapper()
        );
    }

    @Override
    public PartyRoleStatusCode findByPartyRole (final long partyRoleId) {
        try {
            return queryForObject(
                    "SELECT * FROM " + getTableName() + " SC"
                            + " JOIN t_party_role_status S ON S.status_code_fk=SC.id"
                            + " JOIN t_party_role P ON P.status_fk=S.id"
                            + " WHERE P.id=?",
                    new Object[]{partyRoleId},
                    getRowMapper()
            );
        } catch (EmptyResultDataAccessException e) {    // NOSONAR
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "t_party_role_status_code";
    }

    @Override
    protected PartyRoleStatusCode createPersistenEnum() {
        return new PartyRoleStatusCode();
    }

    @Override
    protected RowMapper<PartyRoleStatusCode> getRowMapper () {
        return new RowMapper<PartyRoleStatusCode>() {
            @Override
            public PartyRoleStatusCode mapRow(final ResultSet rs, final int i) throws SQLException {
                final PartyRoleStatusCode obj = createPersistenEnum();
                obj.setId(rs.getLong("id"));
                obj.setName(rs.getString("name"));
                obj.setActive(rs.getBoolean("active"));
                return obj;
            }
        };
    }

}
