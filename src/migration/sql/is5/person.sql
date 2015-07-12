-- party
INSERT INTO t_party(id, kind, name, from_date)
    SELECT party_id, 'P', party_id, party_creation_date FROM is5.party WHERE party_type_fk=1;

UPDATE t_party P
  JOIN is5.pattr A ON P.id=A.pattr_party_fk
SET P.birth_date=STR_TO_DATE(A.pattr_value, '%Y/%c/%d')
WHERE A.pattr_name in ('birth_date', 'birthdate') AND A.pattr_value REGEXP '^[0-9]{4}/[0-9]{2}/[0-9]{2}$';

UPDATE t_party P
  JOIN is5.pattr A ON P.id=A.pattr_party_fk
SET P.birth_date=STR_TO_DATE(A.pattr_value, '%Y-%c-%d')
WHERE A.pattr_name in ('birth_date', 'birthdate') AND A.pattr_value REGEXP '^[0-9]{4}-[0-9]{2}-[0-9]{2}$';


UPDATE t_party P
  JOIN is5.pattr A ON P.id=A.pattr_party_fk
SET P.gender=IF(A.pattr_value='B', 'M', IF(A.pattr_value='G', 'F', A.pattr_value))
WHERE A.pattr_name='gender';


UPDATE t_party P
    JOIN is5.pattr A ON P.id=A.pattr_party_fk
    SET P.first_name=A.pattr_value
    WHERE A.pattr_name='first_name';

UPDATE t_party P
  JOIN is5.pattr A ON P.id=A.pattr_party_fk
SET P.first_name=A.pattr_value
WHERE A.pattr_name='first name';


UPDATE t_party P
  JOIN is5.pattr A ON P.id=A.pattr_party_fk
  SET P.last_name=A.pattr_value
  WHERE A.pattr_name='last_name';

UPDATE t_party P
  JOIN is5.pattr A ON P.id=A.pattr_party_fk
SET P.last_name=A.pattr_value
WHERE A.pattr_name='last name';


UPDATE t_party SET name=CONCAT(IFNULL(first_name, ''), ' ', IFNULL(last_name, ''));
