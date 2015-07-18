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

-- createInTeam
insert into t_party(id, name, kind) values (300, 'NYSC', 'O');

insert into t_party_role(id, party_fk, type_fk, from_date) values(
  300, 300, 100, '1973-12-27 10:30:45'
);

INSERT INTO t_party_role_relationship_type(id, from_type_fk, to_type_fk, name) VALUES(300, null, null, 'is-member-of');

-- test_link
insert into t_party(id, name, kind) values (400, 'NYSC', 'O');
insert into t_party(id, name, kind) values (410, 'NYSC', 'O');

insert into t_party_role(id, party_fk, type_fk, from_date) values(
  400, 400, 100, '1973-12-27 10:30:45'
);
insert into t_party_role(id, party_fk, type_fk, from_date) values(
  410, 410, 100, '1973-12-27 10:30:45'
);

INSERT INTO t_party_role_relationship_type(id, from_type_fk, to_type_fk, name) VALUES(400, null, null, 'is-friend-of');

-- test_unlink
insert into t_party(id, name, kind) values (500, 'NYSC', 'O');
insert into t_party(id, name, kind) values (510, 'NYSC', 'O');

insert into t_party_role(id, party_fk, type_fk, from_date) values(
  500, 500, 100, '1973-12-27 10:30:45'
);
insert into t_party_role(id, party_fk, type_fk, from_date) values(
  510, 510, 100, '1973-12-27 10:30:45'
);

INSERT INTO t_party_role_relationship_type(id, from_type_fk, to_type_fk, name) VALUES(500, null, null, 'is-parent-of');
INSERT INTO t_party_role_relationship(id, from_fk, to_fk, type_fk, from_date) VALUES(500, 500, 510, 500, now());
