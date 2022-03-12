CREATE TABLE IF NOT EXISTS tb_bank_holder (
    id uuid NOT NULL,
    first_name varchar(20) NOT NULL,
    second_name varchar(20) NOT NULL,
    CONSTRAINT tb_bank_holder_pkey PRIMARY KEY (id)),

CREATE TABLE IF NOT EXISTS tb_bank_account
(
    id uuid NOT NULL,
    account varchar(8) NOT NULL,
    account_type integer NOT NULL,
    agency varchar(4) NOT NULL,
    bank_number varchar(3) NOT NULL,
    tb_bank_holder_id uuid,
    CONSTRAINT tb_bank_account_pkey PRIMARY KEY (id),
    CONSTRAINT uk_461lcub13drjv0h41m1atavol UNIQUE (account),
    CONSTRAINT fkq2apc02l3by1vh996f4udmb4j FOREIGN KEY (tb_bank_holder_id)
        REFERENCES tb_bank_holder (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS tb_bank
(
    "number" varchar(3)  NOT NULL,
    name varchar(60) NOT NULL,
    CONSTRAINT tb_bank_pkey PRIMARY KEY ("number")
);

INSERT INTO tb_bank(number, name) VALUES ('260', 'NU PAGAMENTOS');
INSERT INTO tb_bank(number, name) VALUES ('077', 'INTER');