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
    mailing_city VARCHAR(255),
	mailing_country VARCHAR(255),
	mailing_door VARCHAR(20),
	mailing_floor INT,
	mailing_number INT,
	mailing_postal_code VARCHAR(50),
	mailing_street VARCHAR(255),
	primary_city VARCHAR(255),
	primary_country VARCHAR(255),
	primary_door VARCHAR(20),
	primary_floor INT,
	primary_number INT,
	primary_postal_code VARCHAR(50),
	primary_street VARCHAR(255),
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
    currency VARCHAR(255),
    amount DECIMAL(19,2),
    primary_owner_id BIGINT,
    secondary_owner_id BIGINT,
    creation_date DATE,
    max_limit_transactions DECIMAL(19,2),
    PRIMARY KEY (id),
    FOREIGN KEY (primary_owner_id) REFERENCES account_holder(id),
    FOREIGN KEY (secondary_owner_id) REFERENCES account_holder(id)
) ENGINE InnoDB;

CREATE TABLE checking (
	id BIGINT NOT NULL AUTO_INCREMENT,
	secret_key VARCHAR(255),
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
    minimum_balance DECIMAL(19,2),
    status VARCHAR(30),
    interest_rate DECIMAL(19,2),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(id)
) ENGINE InnoDB;

CREATE TABLE credit_card (
	id BIGINT NOT NULL AUTO_INCREMENT,
    credit_limit DECIMAL(19,2),
    interest_rate DECIMAL(19,2),
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES account(id)
) ENGINE InnoDB;

CREATE TABLE transaction (
	id BIGINT NOT NULL AUTO_INCREMENT,
    sender_account_id BIGINT,
    receiver_account_id BIGINT,
    currency VARCHAR(255),
    amount DECIMAL(19,2),
    transaction_date DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (sender_account_id) REFERENCES account(id),
    FOREIGN KEY (receiver_account_id) REFERENCES account(id)
) ENGINE InnoDB;