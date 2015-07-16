insert into t_party_role_type(id, name) values(1, 'person');
insert into t_party_role_type(id, name) values(2, 'household');
insert into t_party_role_type(id, name) values(3, 'team');
insert into t_party_role_type(id, name) values(4, 'club');

INSERT INTO t_party_role_status_code(id, name, active) values(1, 'new',       false);
INSERT INTO t_party_role_status_code(id, name, active) values(2, 'active',    true);
INSERT INTO t_party_role_status_code(id, name, active) values(3, 'suspended', false);

INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(1, 2);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(2, 2);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(3, 1);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(4, 1);

-- parties
INSERT INTO t_party_role(id, party_fk, type_fk, from_date)
  SELECT party_id, party_id, party_type_fk, party_creation_date FROM is5.party;

INSERT INTO t_party_role_status(party_role_fk, status_date, status_code_fk)
  SELECT party_id, party_creation_date, IF(party_status=1, 2, 3) FROM is5.party;

UPDATE t_party_role R
  JOIN t_party_role_status S ON R.id=S.party_role_fk
SET R.status_fk=S.id;
