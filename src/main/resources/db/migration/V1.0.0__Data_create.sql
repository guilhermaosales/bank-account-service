CREATE TABLE IF NOT EXISTS tb_bank (
    number varchar(3) not null,
    name varchar(60) not null,
    primary key (number)
);

CREATE TABLE IF NOT EXISTS tb_account_type (
    id integer NOT NULL,
    name varchar(9) NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_bank_holder (
    id bigint not null,
    full_name varchar(50) not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS tb_bank_account (
    id bigint not null,
    account varchar(8) not null,
    account_type integer NOT NULL,
    agency varchar(4) not null,
    bank_number varchar(3) not null,
    last_update_date timestamp without time zone NOT NULL,
    preferred_account boolean NOT NULL,
    registration_date timestamp without time zone NOT NULL,
    tb_bank_holder_id bigint,
    primary key (id),
    constraint fk_tb_bank_holder
        foreign key (tb_bank_holder_id)
            references tb_bank_holder (id)

);

INSERT INTO tb_bank (number, name) VALUES ('260', 'NU PAGAMENTOS');
INSERT INTO tb_bank (number, name) VALUES ('077', 'INTER');

INSERT INTO tb_account_type (id, name) values (0, 'CHECKINGS');
INSERT INTO tb_account_type (id, name) values (1, 'SAVINGS');

--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572671, 'Giovanna Momo');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572672, 'Alfredina Francisca');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572673, 'Alfredo Jorge');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572674, 'Berenice Vieira');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572675, 'Geraldo Agostinni');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572676, 'Yugoslavo da Silva');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572677, 'Armando Craudinei Filis');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572678, 'Priscila Santarosa');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572679, 'Iliada Alfenas');
--
--INSERT INTO public.tb_bank_holder(
--    id, full_name)
--    values (1040748500796572680, 'Cristiano Araujo');
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478331', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572671);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478332', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572672);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478333', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572673);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478334', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572674);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478335', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572675);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478336', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572676);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478337', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572677);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478338', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572678);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478339', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572679);
--
--INSERT INTO public.tb_bank_account(
--	id, account, account_type, agency, bank_number, last_update_date, preferred_account, registration_date, tb_bank_holder_id)
--	VALUES (select id_generator() FROM generate_series(0,1000000,1)), '5478339', 0, '0001', '160', '2023-03-05 00:47:17.363581', FALSE, '2023-03-05 00:47:17.363581', 1040748500796572680);