CREATE TABLE IF NOT EXISTS tb_bank (
    number varchar(3) not null,
    name varchar(60) not null,
    primary key (number)
);

CREATE TABLE IF NOT EXISTS tb_account_type (
    id integer NOT NULL,
    name varchar(9) NOT NULL
);

CREATE TABLE tb_bank_holder (
    id uuid not null,
    first_name varchar(20) not null,
    second_name varchar(40) not null,
    primary key (id)
);

CREATE TABLE tb_bank_account (
    id uuid not null,
    account varchar(8) not null,
    account_type integer NOT NULL,
    agency varchar(4) not null,
    bank_number varchar(3) not null,
    last_update_date timestamp without time zone NOT NULL,
    preferred_account boolean NOT NULL,
    registration_date timestamp without time zone NOT NULL,
    tb_bank_holder_id uuid,
    primary key (id),
    constraint fk_tb_bank_holder
        foreign key (tb_bank_holder_id)
            references tb_bank_holder (id)

);

INSERT INTO tb_bank (number, name) VALUES ('260', 'NU PAGAMENTOS');
INSERT INTO tb_bank (number, name) VALUES ('077', 'INTER');

INSERT INTO tb_account_type (id, name) values (0, 'CHECKINGS');
INSERT INTO tb_account_type (id, name) values (1, 'SAVINGS');