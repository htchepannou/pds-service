package com.tchepannou.pds.dao.impl;

import com.tchepannou.core.dao.impl.AbstractPersistentEnumDaoImpl;
import com.tchepannou.pds.dao.PartyRoleRelationshipTypeDao;
import com.tchepannou.pds.domain.PartyRelationshipType;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartyRoleRelationshipTypeDaoImpl extends AbstractPersistentEnumDaoImpl<PartyRelationshipType> implements PartyRoleRelationshipTypeDao {
    public PartyRoleRelationshipTypeDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getTableName() {
        return "t_party_role_relationship_type";
    }

    @Override
    protected PartyRelationshipType createPersistenEnum() {
        return new PartyRelationshipType();
    }

    @Override
    protected RowMapper<PartyRelationshipType> getRowMapper() {
        return new RowMapper() {
            public PartyRelationshipType mapRow(ResultSet rs, int i) throws SQLException {
                PartyRelationshipType obj = (PartyRelationshipType)createPersistenEnum();
                obj.setId(rs.getLong("id"));
                obj.setName(rs.getString("name"));
                obj.setFromTypeId(rs.getLong("from_type_fk"));
                obj.setToTypeId(rs.getLong("to_type_fk"));
                return obj;
            }
        };
    }

}
