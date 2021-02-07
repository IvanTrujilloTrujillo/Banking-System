DROP SCHEMA IF EXISTS banking_system;
CREATE SCHEMA banking_system;
USE banking_system;

CREATE TABLE user (
	id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE InnoDB;

CREATE TABLE account_holder (
	id BIGINT NOT NULL AUTO_INCREMENT,
    primary_address VARCHAR(255),
    mailing_address VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user(id)
) ENGINE InnoDB;

CREATE TABLE third_party (
	id BIGINT NOT NULL AUTO_INCREMENT,
    hashed_key VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user(id)
) ENGINE InnoDB;

CREATE TABLE role (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255),
    user_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE InnoDB;

CREATE TABLE account (
	id BIGINT NOT NULL AUTO_INCREMENT,
    balance DECIMAL,
    primary_owner_id BIGINT,
    secundary_owner_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (primary_owner_id) REFERENCES account_holder(id),
    FOREIGN KEY (secundary_owner_id) REFERENCES account_holder(id)
) ENGINE InnoDB;

CREATE TABLE checking (
	id BIGINT NOT NULL AUTO_INCREMENT,
	secret_key VARCHAR(255),
    minimum_balance INT,
    monthly_maintenance_fee INT,
    status VARCHAR(30),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(id)
) ENGINE InnoDB;

CREATE TABLE student_checking (
	id BIGINT NOT NULL AUTO_INCREMENT,
	secret_key VARCHAR(255),
    status VARCHAR(30),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(id)
) ENGINE InnoDB;

CREATE TABLE saving (
	id BIGINT NOT NULL AUTO_INCREMENT,
	secret_key VARCHAR(255),
    status VARCHAR(30),
    interest_rate DECIMAL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(id)
) ENGINE InnoDB;

CREATE TABLE credit_card (
	id BIGINT NOT NULL AUTO_INCREMENT,
    credit_limit INT,
    interest_rate DECIMAL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(id)
) ENGINE InnoDB;