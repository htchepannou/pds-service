insert into t_party_role_type(id, name) values(100, 'team');
insert into t_party_role_type(id, name) values(110, 'club');
insert into t_party_role_type(id, name) values(200, 'member');

INSERT INTO t_party_role_status_code(id, name, active) values(1, 'new', false);
INSERT INTO t_party_role_status_code(id, name, active) values(2, 'active', true);
INSERT INTO t_party_role_status_code(id, name, active) values(3, 'suspended', false);

INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(100, 1);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(110, 1);
INSERT INTO t_party_role_default_status_code(type_fk, status_code_fk) VALUES(200, 2);


-- findById
insert into t_party(id, name, first_name, last_name, prefix, suffix, birth_date, gender, kind, height, weight) values (
  100, 'Ray Sponsible', 'Ray', 'Sponsible', 'Mr', 'PHD', '1973-12-27', 'M', 'P', 182, 250);

insert into t_party_role(id, party_fk, type_fk, from_date) values(
  100, 100, 200, '1973-12-27 10:30:45'
);

insert into t_party_role_status(id, party_role_fk, status_code_fk, status_date, comment) values (
    100, 100, 2, '1973-12-27 10:30:45', 'Initial'
);
update t_party_role set status_fk=100 where id=100;

-- setStatus
insert into t_party(id, name, first_name, last_name, prefix, suffix, birth_date, gender, kind, height, weight) values (
  200, 'Ray Sponsible', 'Ray', 'Sponsible', 'Mr', 'PHD', '1973-12-27', 'M', 'P', 182, 250);

insert into t_party_role(id, party_fk, type_fk, from_date) values(
  200, 200, 200, '1973-12-27 10:30:45'
);

insert into t_party_role_status(id, party_role_fk, status_code_fk, status_date, comment) values (
  200, 200, 1, '1973-12-27 10:30:45', 'Initial'
);
update t_party_role set status_fk=200 where id=200;
