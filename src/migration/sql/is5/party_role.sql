insert into t_party_role_type(id, name) values(1, 'person');
insert into t_party_role_type(id, name) values(2, 'household');
insert into t_party_role_type(id, name) values(3, 'team');
insert into t_party_role_type(id, name) values(4, 'club');

INSERT INTO t_party_role_status_code(id, name, active) values(1, 'new', false);
INSERT INTO t_party_role_status_code(id, name, active) values(2, 'active', true);
INSERT INTO t_party_role_status_code(id, name, active) values(3, 'suspended', false);

INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(1, 2);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(2, 2);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(3, 1);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(4, 1);
