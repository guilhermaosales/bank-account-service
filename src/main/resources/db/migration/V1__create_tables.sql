CREATE TABLE IF NOT EXISTS tb_bank (
    number varchar(3) not null,
    name varchar(60) not null,
    primary key (number)
);

INSERT INTO tb_bank(number, name) VALUES ('260', 'NU PAGAMENTOS');
INSERT INTO tb_bank(number, name) VALUES ('077', 'INTER');

CREATE TABLE IF NOT EXISTS tb_account_type (
    id integer NOT NULL,
    name varchar(9) NOT NULL
);

INSERT INTO tb_account_type (id, name) values (0, 'CHECKINGS');
INSERT INTO tb_account_type (id, name) values (1, 'SAVINGS');


CREATE TABLE IF NOT EXISTS tb_bank_holder (
    id uuid not null,
    first_name varchar(20) not null,
    second_name varchar(40) not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS tb_bank_account (
    id uuid not null,
    account varchar(8) not null,
    account_type int4 not null,
    agency varchar(4) not null,
    bank_number varchar(3) not null,
    accountType varchar(9) not null,
    registrationDate timestamp,
    lastUpdateDate timestamp,
    preferredAccount boolean,
    tb_bank_holder_id uuid,
    primary key (id),
    constraint fk_tb_bank_holder
        foreign key (tb_bank_holder_id)
            references tb_bank_holder (id)

);


