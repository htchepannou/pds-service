INSERT INTO t_party(id, kind, name, from_date)
    SELECT party_id, 'O', party_id, party_creation_date FROM is5.party WHERE party_type_fk in (3,4);


UPDATE t_party P
    JOIN is5.pattr A ON P.id=A.pattr_party_fk
SET P.gender=IF(A.pattr_value='B', 'M', IF(A.pattr_value='G', 'F', A.pattr_value))
WHERE A.pattr_name='gender' AND P.kind='O';

UPDATE t_party P
    JOIN is5.pattr A ON P.id=A.pattr_party_fk
    SET P.name=A.pattr_value
    WHERE A.pattr_name='name' AND P.kind='O';
