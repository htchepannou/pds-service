CREATE TABLE t_party(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    from_date DATETIME,

    name VARCHAR(100) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    prefix VARCHAR(10),
    suffix VARCHAR(10),
    birth_date date,
    gender CHAR(1),
    kind CHAR(1),
    height INT,
    weight INT
);


CREATE TABLE t_contact_mechanism_type(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE t_contact_mechanism_purpose(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE t_eaddress(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    hash VARCHAR(32) NOT NULL UNIQUE,
    address TEXT NOT NULL
);

CREATE TABLE t_paddress(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    hash VARCHAR(32) NOT NULL UNIQUE,
    street1 VARCHAR(100),
    street2 VARCHAR(100),
    city VARCHAR(50),
    zip_code VARCHAR(30),
    state_code VARCHAR(30),
    country_code VARCHAR(3)
);

CREATE TABLE t_phone(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    hash VARCHAR(32) NOT NULL UNIQUE,
    country_code VARCHAR(3),
    number VARCHAR(20),
    extension VARCHAR(10)
);


CREATE TABLE t_party_contact_mechanism(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    party_fk BIGINT NOT NULL,
    type_fk BIGINT NOT NULL,
    purpose_fk BIGINT,
    paddress_fk BIGINT,
    eaddress_fk BIGINT,
    phone_fk BIGINT,

    no_solicitation BIT(1),
    privacy CHAR(1),

    UNIQUE(party_fk, type_fk, purpose_fk, paddress_fk, eaddress_fk, phone_fk),

    CONSTRAINT fk_party_contact_mechanism__party FOREIGN KEY (party_fk) REFERENCES t_party(id),
    CONSTRAINT fk_party_contact_mechanism__type FOREIGN KEY (type_fk) REFERENCES t_contact_mechanism_type(id),
    CONSTRAINT fk_party_contact_mechanism__purpose FOREIGN KEY (purpose_fk) REFERENCES t_contact_mechanism_purpose(id),
    CONSTRAINT fk_party_contact_mechanism__paddress FOREIGN KEY (paddress_fk) REFERENCES t_paddress(id),
    CONSTRAINT fk_party_contact_mechanism__eaddress FOREIGN KEY (eaddress_fk) REFERENCES t_eaddress(id),
    CONSTRAINT fk_party_contact_mechanism__phone FOREIGN KEY (phone_fk) REFERENCES t_phone(id)
);

CREATE TABLE t_party_role_type(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE t_party_role_status_code(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(100) NOT NULL UNIQUE,
    active BIT(1)
) ENGINE=INNODB;

CREATE TABLE t_party_role_default_status_code(
    status_code_fk BIGINT NOT NULL,
    type_fk BIGINT NOT NULL,

    PRIMARY KEY (status_code_fk, type_fk),

    CONSTRAINT fk_party_role_default_status__status_code FOREIGN KEY (status_code_fk) REFERENCES t_party_role_status_code(id),
    CONSTRAINT fk_party_role_default_status__type_fk FOREIGN KEY (type_fk) REFERENCES t_party_role_type(id)
) ENGINE=INNODB;


CREATE TABLE t_party_role(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    type_fk BIGINT NOT NULL,
    party_fk BIGINT NOT NULL,

    from_date DATETIME,

    UNIQUE(type_fk, party_fk),

    CONSTRAINT fk_party_role__party FOREIGN KEY (party_fk) REFERENCES t_party(id),
    CONSTRAINT fk_party_role__type FOREIGN KEY (type_fk) REFERENCES t_party_role_type(id)
);

CREATE TABLE t_party_role_status (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    party_role_fk BIGINT NOT NULL,
    status_code_fk BIGINT NOT NULL,

    status_date DATETIME,
    comment TEXT,

    CONSTRAINT fk_party_role_status__party_role FOREIGN KEY (party_role_fk) REFERENCES t_party_role(id),
    CONSTRAINT fk_party_role_status__status_code FOREIGN KEY (status_code_fk) REFERENCES t_party_role_status_code(id)
) ENGINE=INNODB;

ALTER TABLE t_party_role ADD COLUMN status_fk BIGINT REFERENCES t_party_role_status(id);


CREATE TABLE t_party_role_relationship_type(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    from_type_fk BIGINT,
    to_type_fk BIGINT,

    name VARCHAR(100) NOT NULL UNIQUE,

    CONSTRAINT fk_party_relationship_type__from_type FOREIGN KEY (from_type_fk) REFERENCES t_party_role_type(id),
    CONSTRAINT fk_party_relationship_type__to_type FOREIGN KEY (to_type_fk) REFERENCES t_party_role_type(id)
);

CREATE TABLE t_party_role_relationship(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,

    from_fk BIGINT NOT NULL,
    to_fk BIGINT NOT NULL,
    type_fk BIGINT NOT NULL,
    
    from_date DATETIME,

    UNIQUE(from_fk, to_fk, type_fk),

    CONSTRAINT fk_party_role_relationship__from FOREIGN KEY (from_fk) REFERENCES t_party_role(id),
    CONSTRAINT fk_party_role_relationship__to FOREIGN KEY (to_fk) REFERENCES t_party_role(id),
    CONSTRAINT fk_party_role_relationship__type FOREIGN KEY (type_fk) REFERENCES t_party_role_relationship_type(id)
);




