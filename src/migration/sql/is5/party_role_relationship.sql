-- types
INSERT INTO t_party_role_relationship_type(id, from_type_fk, to_type_fk, name) VALUES(1,   1,    1,    'is-child-of');
INSERT INTO t_party_role_relationship_type(id, from_type_fk, to_type_fk, name) VALUES(4,   3,    4,    'is-club-of');
INSERT INTO t_party_role_relationship_type(id, from_type_fk, to_type_fk, name) VALUES(10,  null, null, 'is-member-of');
INSERT INTO t_party_role_relationship_type(id, from_type_fk, to_type_fk, name) VALUES(11,  1,    null, 'is-owner-of');

-- relations
INSERT INTO t_party_role_relationship(from_fk, to_fk, type_fk)
    SELECT prel_source_fk, prel_dest_fk, prel_type_fk FROM is5.prel;
