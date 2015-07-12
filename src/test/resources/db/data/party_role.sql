insert into t_party_role_type(id, name) values(100, 'team');
insert into t_party_role_type(id, name) values(110, 'club');
insert into t_party_role_type(id, name) values(200, 'member');

INSERT INTO t_party_role_status_code(id, name, active) values(1, 'new', false);
INSERT INTO t_party_role_status_code(id, name, active) values(2, 'active', true);
INSERT INTO t_party_role_status_code(id, name, active) values(3, 'suspended', false);

INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(100, 1);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(110, 1);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(200, 2);
