INSERT INTO t_party_role(id, party_fk, type_fk, from_date)
  SELECT party_id, party_id, 4, party_creation_date FROM is5.party WHERE party_type_fk=4;

INSERT INTO t_party_role_status(party_role_fk, status_date, status_code_fk)
    SELECT party_id, party_creation_date, IF(party_status=1, 2, 3) FROM is5.party WHERE party_type_fk=4;

UPDATE t_party_role R
    JOIN t_party_role_status S ON R.id=S.party_role_fk
    SET R.status_fk=S.id;
