delete from t_party_contact_mechanism;
delete from t_eaddress;
delete from t_paddress;
delete from t_phone;
delete from t_contact_mechanism_type;
delete from t_contact_mechanism_purpose;


delete from t_party_role_relationship;
delete from t_party_role_relationship_type;

delete from t_party_role_default_status_code;
update t_party_role set status_fk=null;
delete from t_party_role_status;
delete from t_party_role;

delete from t_party_role_status_code;
delete from t_party_role_type;

delete from t_party;


