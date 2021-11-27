create table seller
(
    id           bigserial
        constraint seller_pkey
            primary key,
    first_name   varchar(50) not null,
    last_name    varchar(50) not null,
    company_name varchar(50) not null,
    country      varchar(50) not null,
    city         varchar(50) not null,
    street       varchar(50) not null,
    postal_code  varchar(15),
    nip          varchar(10) not null,
    regon        varchar(14) not null
);

INSERT INTO seller (id, first_name, last_name, company_name, country, city, street, postal_code, nip, regon) VALUES (1, 'Bailey', 'Dunthorne', 'Yakidoo', 'Portugal', 'Vila Moreira', '7140 Orin Parkway', '2380-638', '2372800459', '26765293944844');

create table company
(
    id           bigserial
        constraint company_pkey
            primary key,
    company_name varchar(50) not null,
    country      varchar(50) not null,
    city         varchar(50) not null,
    street       varchar(50) not null,
    postal_code  varchar(15),
    nip          varchar(10) not null,
    regon        varchar(14) not null
);

INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (2, 'Roombo', 'China', 'Longfeng', 'Jackson', '366041', '1096810192', '693610026');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (3, 'Reallinks', 'Japan', 'Matsutō', 'John Wall', '929-0203', '5347068072', '693610026');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (4, 'Skyba', 'Vietnam', 'Thị Trấn Nho Quan', 'Carey', '366041', '1169915609', '076434653');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (5, 'Geba', 'Argentina', 'San Antonio', 'Northview', '4503', '1159006420', '711991718');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (6, 'Quinu', 'Portugal', 'Campinho', 'Dryden', '7200-505', '5354118374', '714974063');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (8, 'Dynava', 'Poland', 'Lipie', 'Sloan', '42-165', '1096810192', '693610026');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (9, 'Eazzy', 'Argentina', 'San José de Feliciano', 'Northfield', '3187', '5354118374', '711991718');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (10, 'Camimbo', 'Russia', 'Prokhladnyy', 'Elmside', '366041', '1169915609', '714974063');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (11, 'Wordpedia', 'Mexico', 'La Esperanza', 'Mandrake', '87076', '1159006420', '693610026');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (12, 'Dabshots', 'Russia', 'Novoshakhtinsk', 'Saint Paul', '346930', '5347068072', '852690730');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (13, 'Yadel', 'Portugal', 'Praia de Mira', 'Old Gate', '3070-725', '1159006420', '714974063');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (14, 'Jayo', 'China', 'Mamu', 'Becker', '445564', '5292669645', '291819869');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (16, 'Mynte', 'South Korea', 'Fuyo', 'Loomis', '445564', '5354118374', '374700130');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (17, 'Oozz', 'Russia', 'Severomorsk', 'Acker', '445564', '1159006420', '291819869');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (18, 'Youtags', 'Brazil', 'Igarapava', 'Pine View', '14540-000', '1169915609', '076434653');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (19, 'Photobean', 'Indonesia', 'Babakankiray', 'Hazelcrest', '445564', '5347068072', '291819869');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (20, 'Gigazoom', 'United States', 'Amarillo', 'Fuller', '79171', '1159006420', '711991718');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (15, 'Voonte', 'France', 'Saint-Quentin-en-Yvelines', 'Cambridge', '78067', '1096810192', '714974063');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (7, 'Obaa', 'China', 'Shuibian', 'Cottonwood', '366041', '5354118371', '076434652');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (1, 'Google', 'Poland', 'Cisaat', 'North Pass', '40893', '1389431167', '16804632212896');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (23, 'Asd', 'Albania', 'Asd', 'Asd', '132', '123', '1323');
INSERT INTO company (id, company_name, country, city, street, postal_code, nip, regon) VALUES (24, 'Asd', 'Albania', 'Asd', 'Asd', '123', '1233123', '1234535');


create table bank_account
(
    id                  bigserial
        constraint bank_account_pkey
            primary key,
    bank_swift          varchar(8)  not null,
    bank_account_number varchar(28) not null,
    bank_name           varchar(50) not null,
    seller_id           integer
        constraint bank_account_seller_fk
            references seller,
    company_id          integer
        constraint bank_account_company_fk
            references company
);

INSERT INTO bank_account (id, bank_swift, bank_account_number, bank_name, seller_id, company_id) VALUES (1, 'INGBPLPW', 'PL26109024023581471617763254', 'ING Bank Śląski', null, 1);
INSERT INTO bank_account (id, bank_swift, bank_account_number, bank_name, seller_id, company_id) VALUES (2, 'INGF', 'PL56109024023852652826413581', 'Bank', null, 23);

create table currency
(
    id   bigserial
        constraint currency_pkey
            primary key,
    name varchar(15) not null,
    code varchar(5)  not null
);


INSERT INTO currency (id, name, code) VALUES (3, 'Yuan Renminbi', 'CNY');
INSERT INTO currency (id, name, code) VALUES (7, 'Rupiah', 'IDR');
INSERT INTO currency (id, name, code) VALUES (9, 'Baht', 'THB');
INSERT INTO currency (id, name, code) VALUES (12, 'Balboa', 'PAB');
INSERT INTO currency (id, name, code) VALUES (15, 'Krona', 'SEK');
INSERT INTO currency (id, name, code) VALUES (17, 'Leone', 'SLL');
INSERT INTO currency (id, name, code) VALUES (18, 'Zloty', 'PLN');
INSERT INTO currency (id, name, code) VALUES (20, 'Birr', 'ETB');
INSERT INTO currency (id, name, code) VALUES (23, 'Euro', 'EUR');
INSERT INTO currency (id, name, code) VALUES (24, 'Real', 'BRL');
INSERT INTO currency (id, name, code) VALUES (25, 'Ruble', 'RUB');
INSERT INTO currency (id, name, code) VALUES (26, 'Dirham', 'MAD');
INSERT INTO currency (id, name, code) VALUES (28, 'Koruna', 'CZK');
INSERT INTO currency (id, name, code) VALUES (29, 'Franc', 'XOF');
INSERT INTO currency (id, name, code) VALUES (32, 'Lempira', 'HNL');
INSERT INTO currency (id, name, code) VALUES (34, 'Dollar', 'USD');
INSERT INTO currency (id, name, code) VALUES (35, 'Hryvnia', 'UAH');
INSERT INTO currency (id, name, code) VALUES (37, 'Rupee', 'PKR');
INSERT INTO currency (id, name, code) VALUES (38, 'Lev', 'BGN');
INSERT INTO currency (id, name, code) VALUES (40, 'Cordoba', 'NIO');
INSERT INTO currency (id, name, code) VALUES (43, 'Pound', 'EGP');
INSERT INTO currency (id, name, code) VALUES (45, 'Quetzal', 'GTQ');
INSERT INTO currency (id, name, code) VALUES (46, 'Peso', 'UYU');
INSERT INTO currency (id, name, code) VALUES (6, 'Dram', 'AMD');

create table payment_type
(
    id                bigserial
        constraint payment_type_pkey
            primary key,
    payment_type_name varchar(24) not null
);

INSERT INTO payment_type (id, payment_type_name) VALUES (1, 'Cash');
INSERT INTO payment_type (id, payment_type_name) VALUES (3, 'Debit card');
INSERT INTO payment_type (id, payment_type_name) VALUES (4, 'Credit cards');
INSERT INTO payment_type (id, payment_type_name) VALUES (5, 'Mobile payment');
INSERT INTO payment_type (id, payment_type_name) VALUES (6, 'Electronic bank transfer');
INSERT INTO payment_type (id, payment_type_name) VALUES (2, 'Check');


create table status
(
    id   bigserial
        constraint status_pkey
            primary key,
    name varchar(24) not null
);

create table "user"
(
    id          bigserial
        constraint user_pkey
            primary key,
    username    varchar(50)           not null,
    password    varchar(150)          not null,
    email       varchar(50)           not null,
    first_name  varchar(50)           not null,
    last_name   varchar(50)           not null,
    country     varchar(50)           not null,
    city        varchar(50)           not null,
    street      varchar(50)           not null,
    postal_code varchar(50),
    phone       varchar(50)           not null,
    company_id  integer
        constraint user_company_fk
            references company,
    locked      boolean default false not null,
    enabled     boolean default false not null
);


INSERT INTO "user" (id, username, password, email, first_name, last_name, country, city, street, postal_code, phone, company_id, locked, enabled) VALUES (20, 'asdsad', '$2a$12$FPbp/1x1ewcRiWP8xi.t9etZy.Dzfrtke0mQC65YmcpoaJdripkpK', 'michal1gorul@gmail.com', 'michalgorul@gmail.co', '+48692195554', 'Polska', 'Katowice', 'Paderewskiego 9', '40-281', '+4812122112', null, false, true);
INSERT INTO "user" (id, username, password, email, first_name, last_name, country, city, street, postal_code, phone, company_id, locked, enabled) VALUES (19, 'asdasd', '$2a$12$3CdscNo1zpjNhYrNyRct7Og7Kg0Sqv1ey1ZxOOcrr5OmvYBWnbqYi', 'michalgorul@gmail.com', 'michalgorul@gmail.com', '+48692195554', 'Polska', 'Katowice', 'Paderewskiego 9', '40-281', '+48692195554', 23, false, true);
INSERT INTO "user" (id, username, password, email, first_name, last_name, country, city, street, postal_code, phone, company_id, locked, enabled) VALUES (1, 'admin', '$2a$10$ocYCca1ClRCppPb41t9dj.1FfRVUzFVxiUVDTV0hwtJALvWm8VAie', 'test@test.com', 'John', 'Wick', 'United States', 'New York', 'Waywood Circle', '93144', '855-414-6048', 1, false, true);

create table confirmation_token
(
    id           bigserial
        constraint confirmation_token_pkey
            primary key,
    token        varchar(150) not null,
    created_at   timestamp    not null,
    expires_at   timestamp    not null,
    confirmed_at timestamp,
    user_id      integer
        constraint confirmation_token_user_fk
            references "user"
);


INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (15, 'f831bb93-c77d-42c0-b5f2-1ba6e7609a63', '2021-10-24 02:46:47.125126', '2021-10-24 03:01:47.125126', '2021-10-24 02:51:17.496401', 19);
INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (16, '1a267c3a-8e30-4af7-82dd-3831d902ce99', '2021-10-24 12:58:01.621866', '2021-10-24 13:13:01.621866', null, 20);
INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (17, 'bb5269d9-785c-4e8e-ac9d-05f0c4bc6d5f', '2021-10-27 21:31:02.642914', '2021-10-27 22:01:02.643914', '2021-10-27 21:32:47.656515', 19);
INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (18, 'fe0f1d74-b014-4b7e-ac93-9e0d44c00de8', '2021-10-27 21:38:08.355978', '2021-10-27 22:08:08.356978', null, 19);
INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (19, 'd651e3ae-da35-47e1-9a40-397257095f12', '2021-10-27 22:47:21.704944', '2021-10-27 23:17:21.704944', null, 19);
INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (20, 'de144598-b02e-42bb-a167-4d1647b24322', '2021-10-27 22:47:58.116170', '2021-10-27 23:17:58.117170', null, 19);
INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (21, 'cd6fa8d1-5465-470a-9cee-282601236251', '2021-10-27 22:53:07.455447', '2021-10-27 23:23:07.455447', '2021-10-27 22:53:53.293070', 19);
INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (22, '61baac62-6a4f-4878-a6c6-ddd6f811e8a7', '2021-10-27 23:14:18.713123', '2021-10-27 23:44:18.713123', '2021-10-27 23:14:58.242147', 19);
INSERT INTO confirmation_token (id, token, created_at, expires_at, confirmed_at, user_id) VALUES (23, 'f5597607-e0ef-4a18-80af-b4204adae2a6', '2021-10-27 23:21:46.385478', '2021-10-27 23:51:46.385478', null, 19);


create table credit
(
    id                    bigserial
        constraint credit_pkey
            primary key,
    credit_number         varchar(15)   not null,
    left_to_pay           numeric(8, 2) not null,
    amount                numeric(8, 2) not null,
    next_payment          numeric(8, 2) not null,
    installments          integer       not null,
    balance               numeric(8, 2) not null,
    rate_of_interest      numeric(4, 2) not null,
    next_payment_date     date          not null,
    creation_date         date          not null,
    last_installment_date date          not null,
    user_id               integer       not null
        constraint invoice_user_fk
            references "user",
    status                varchar(50)
);




INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (1, '#d0e034', 26851.83, 49241.46, 74842.96, 86, 93290.62, 1.37, '2021-08-24', '2020-12-16', '2021-09-30', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (2, '#b63574', 25352.35, 46684.74, 56604.12, 37, 99814.35, 1.01, '2021-02-24', '2021-01-19', '2021-08-06', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (3, '#89bbad', 62096.89, 7173.01, 16609.79, 56, 90764.21, 6.55, '2021-02-26', '2021-08-05', '2020-12-03', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (4, '#523d79', 75901.87, 57059.09, 51667.31, 57, 56749.41, 3.82, '2021-09-26', '2020-11-25', '2020-12-23', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (5, '#ae1e73', 69141.02, 11009.27, 41221.79, 83, 21753.64, 6.47, '2021-09-07', '2021-02-04', '2021-06-17', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (6, '#071324', 78708.75, 52069.28, 67521.18, 3, 74889.82, 6.69, '2021-03-09', '2021-09-10', '2020-11-07', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (7, '#6c0920', 47578.25, 78363.73, 46124.70, 56, 87582.92, 5.46, '2021-07-18', '2020-12-29', '2021-04-18', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (8, '#e7ac7d', 65034.79, 63004.39, 10857.23, 21, 71217.10, 6.92, '2021-08-08', '2021-09-25', '2021-08-31', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (9, '#ada834', 2769.48, 8051.41, 2576.28, 15, 90829.54, 3.28, '2021-05-29', '2020-11-16', '2021-07-25', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (10, '#e04cac', 43124.29, 30262.65, 44534.95, 32, 96414.03, 6.74, '2021-09-28', '2021-09-03', '2021-06-12', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (11, '#af9ab3', 25642.58, 39522.33, 16650.81, 77, 34221.31, 3.13, '2020-12-14', '2021-06-02', '2021-01-06', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (12, '#26f0d7', 60507.60, 24223.44, 50602.99, 50, 99093.91, 4.93, '2021-07-14', '2021-04-12', '2021-05-12', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (13, '#1cf611', 1550.28, 80364.22, 63315.54, 11, 70187.22, 1.39, '2021-05-22', '2021-01-24', '2021-09-30', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (14, '#9c6629', 16549.35, 51480.32, 55696.22, 16, 82965.90, 1.68, '2021-03-10', '2021-07-07', '2021-08-14', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (15, '#b8c2f2', 24244.27, 43263.59, 69775.62, 26, 79525.97, 8.46, '2020-11-28', '2020-10-31', '2021-08-24', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (16, '#85af08', 70200.71, 63584.70, 87203.62, 40, 59196.27, 6.08, '2021-07-02', '2021-06-23', '2021-05-25', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (17, '#eab63a', 52100.58, 39128.85, 1016.16, 97, 50755.64, 5.38, '2021-09-28', '2021-03-04', '2020-11-29', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (18, '#27fa82', 2033.33, 54710.20, 58156.30, 47, 34147.84, 4.78, '2021-03-09', '2020-10-14', '2021-03-29', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (19, '#c52053', 79723.05, 11440.99, 23409.57, 5, 12278.97, 3.52, '2020-12-15', '2020-12-01', '2021-07-17', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (20, '#0d2f40', 43370.93, 34794.62, 31602.94, 87, 54602.18, 5.44, '2021-02-26', '2020-10-29', '2020-12-20', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (21, '#9298b3', 27390.42, 3947.00, 84124.15, 62, 25079.89, 6.99, '2020-11-02', '2021-04-24', '2021-09-20', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (22, '#8dbb75', 48047.00, 62765.45, 26941.17, 66, 64561.42, 3.02, '2021-07-14', '2021-05-23', '2020-12-08', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (23, '#2f02a3', 21175.14, 51758.88, 90588.22, 14, 40673.62, 7.08, '2020-10-24', '2020-11-19', '2021-01-20', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (24, '#30a9ae', 34742.69, 26676.78, 38228.49, 34, 20563.97, 1.87, '2020-12-28', '2021-04-07', '2021-02-14', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (25, '#0526f8', 95064.00, 3176.86, 22652.85, 2, 63477.05, 2.84, '2021-04-14', '2020-10-28', '2021-07-24', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (26, '#f38cfd', 81489.08, 74171.53, 19302.93, 52, 44937.44, 1.36, '2021-01-29', '2021-02-25', '2021-01-12', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (27, '#3c803b', 81732.08, 51794.43, 97452.86, 66, 31777.45, 3.90, '2020-10-27', '2021-06-17', '2021-09-10', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (28, '#c89688', 9698.09, 90608.52, 12784.92, 3, 53958.23, 1.43, '2021-07-29', '2021-05-22', '2020-10-18', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (29, '#e92e68', 87530.18, 47536.93, 61398.24, 25, 10167.22, 4.47, '2021-08-07', '2021-09-22', '2020-10-24', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (30, '#633a16', 27649.89, 66896.62, 41113.73, 51, 73335.88, 4.67, '2021-05-09', '2020-11-25', '2021-08-30', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (31, '#8d2960', 52310.03, 19192.93, 39128.58, 57, 20689.27, 8.41, '2021-03-03', '2021-07-15', '2021-08-24', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (32, '#eae08e', 78846.96, 81623.43, 31117.71, 59, 67725.97, 2.38, '2021-06-09', '2021-03-10', '2021-02-14', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (33, '#389684', 10025.55, 55448.98, 99893.29, 28, 53102.97, 5.28, '2021-03-29', '2021-09-04', '2021-04-09', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (34, '#4c145b', 55537.46, 27303.53, 17919.79, 62, 36637.10, 8.86, '2020-12-23', '2021-04-06', '2021-06-06', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (35, '#743e78', 13204.76, 34049.09, 6161.52, 45, 65109.52, 2.71, '2021-08-29', '2021-01-23', '2021-02-23', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (36, '#23de0b', 67675.42, 30364.77, 85958.57, 28, 44543.35, 5.99, '2020-11-22', '2021-09-15', '2020-11-01', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (37, '#bf172e', 14779.43, 51227.27, 70812.89, 62, 82311.88, 8.46, '2021-04-03', '2020-10-21', '2021-04-02', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (38, '#01e5ae', 27777.47, 72744.20, 41932.71, 42, 22408.39, 8.73, '2021-05-06', '2021-09-22', '2021-02-05', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (39, '#c33088', 23072.47, 33442.16, 94075.05, 63, 4085.45, 1.11, '2021-06-03', '2021-04-04', '2021-06-03', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (40, '#0273f9', 41920.10, 1481.92, 11677.14, 83, 59120.71, 3.05, '2021-02-05', '2021-06-11', '2021-06-28', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (41, '#90ac31', 64679.55, 63078.41, 51700.01, 88, 50558.15, 4.03, '2020-11-19', '2020-12-07', '2021-05-01', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (42, '#c54cf8', 96700.39, 80749.88, 64147.20, 65, 9441.66, 3.57, '2020-12-24', '2021-09-14', '2021-07-14', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (43, '#51628d', 99090.32, 83444.62, 43006.63, 51, 55526.67, 1.27, '2021-05-18', '2021-08-16', '2021-02-03', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (44, '#b92add', 44266.77, 19995.35, 8144.08, 53, 94682.05, 2.64, '2021-08-18', '2020-10-29', '2021-06-27', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (45, '#83d8b8', 66113.44, 30771.49, 41344.03, 25, 55467.97, 8.90, '2021-01-31', '2021-06-15', '2021-06-18', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (46, '#7d83b9', 65393.46, 47936.47, 65286.43, 18, 64449.50, 2.42, '2021-04-28', '2021-09-09', '2021-07-15', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (47, '#658f76', 2666.31, 35211.33, 65448.26, 84, 33848.98, 2.47, '2021-09-03', '2021-05-26', '2020-11-10', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (48, '#60284a', 33167.08, 16049.64, 97747.92, 87, 83720.86, 8.46, '2021-07-09', '2021-03-10', '2020-11-09', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (49, '#e3e9a3', 53955.31, 74796.34, 37803.70, 32, 81262.73, 1.75, '2021-03-13', '2021-03-19', '2020-12-08', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (50, '#79a4b4', 86225.38, 89894.81, 57678.61, 78, 78838.82, 4.18, '2021-08-23', '2021-05-10', '2021-03-31', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (51, '#6eef41', 86406.97, 77563.60, 13551.80, 2, 54094.85, 3.91, '2020-10-29', '2021-05-17', '2021-08-23', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (52, '#9dbe10', 58130.61, 33511.54, 63723.33, 42, 13393.00, 1.95, '2020-11-05', '2021-03-25', '2020-11-27', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (53, '#48b41f', 30860.54, 86578.98, 53542.45, 25, 3845.49, 3.78, '2021-03-29', '2021-04-04', '2020-12-19', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (54, '#db4d59', 9939.45, 56173.96, 65697.42, 72, 63778.72, 7.16, '2021-03-21', '2021-08-04', '2021-03-11', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (55, '#8d8bd5', 63048.13, 51793.42, 42576.25, 1, 13998.93, 8.64, '2021-02-23', '2020-12-14', '2021-04-18', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (56, '#df567d', 96903.89, 4819.22, 12742.69, 65, 54324.64, 4.74, '2021-09-12', '2021-05-18', '2021-09-22', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (57, '#09f14e', 10940.38, 98750.15, 47728.78, 97, 2987.67, 5.50, '2020-12-17', '2021-05-28', '2021-09-29', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (58, '#a6bc1f', 72306.03, 91816.95, 49888.13, 66, 91147.04, 3.39, '2020-12-05', '2020-12-13', '2021-05-15', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (59, '#b58f15', 39956.55, 35774.14, 69181.09, 87, 74897.50, 3.70, '2021-10-09', '2021-06-22', '2021-07-31', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (60, '#d8d449', 11821.71, 98035.38, 55843.81, 12, 54364.01, 7.36, '2021-09-28', '2021-06-22', '2021-04-29', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (61, '#8e4cdd', 43475.52, 27184.68, 93304.99, 54, 11909.20, 4.98, '2021-01-28', '2021-07-10', '2021-04-01', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (62, '#ce3a10', 80476.63, 80522.43, 56785.67, 9, 97002.92, 2.36, '2020-11-16', '2021-04-16', '2021-09-20', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (63, '#16886d', 13485.76, 31579.58, 90141.12, 11, 65080.67, 4.97, '2021-03-28', '2021-05-16', '2021-04-28', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (64, '#8fe3fc', 26419.53, 5217.03, 1688.71, 99, 45974.28, 6.85, '2020-12-18', '2021-08-24', '2021-08-17', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (65, '#26b1f0', 59138.60, 89050.61, 11846.45, 13, 68357.48, 7.64, '2021-07-28', '2021-04-12', '2021-07-16', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (66, '#54da4c', 75012.21, 90336.26, 15777.40, 35, 1403.94, 2.82, '2020-12-27', '2021-06-02', '2020-11-24', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (67, '#fca9d1', 67452.53, 79074.99, 49545.21, 60, 54519.46, 7.62, '2021-08-25', '2020-11-21', '2020-11-24', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (68, '#07daae', 64392.59, 15870.25, 26821.20, 20, 81222.71, 2.77, '2021-06-24', '2021-01-11', '2021-02-09', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (69, '#ec6f5a', 81967.61, 59211.19, 82847.84, 15, 8579.25, 8.93, '2021-08-25', '2021-08-22', '2020-10-16', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (70, '#b22f85', 15782.50, 73004.45, 83583.19, 78, 69623.12, 2.77, '2020-11-02', '2021-01-28', '2021-05-03', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (71, '#c12007', 74881.88, 92146.91, 98054.33, 43, 44284.60, 4.76, '2021-08-19', '2021-10-09', '2021-09-26', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (72, '#eaae72', 41354.12, 98628.92, 62564.70, 97, 17291.66, 3.15, '2021-05-18', '2021-03-27', '2021-03-31', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (73, '#c16fb1', 14246.66, 66126.50, 19124.49, 3, 24173.69, 2.70, '2020-11-17', '2020-11-08', '2021-10-03', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (74, '#edea82', 77319.65, 63019.88, 50543.94, 14, 26081.07, 2.89, '2021-02-02', '2021-02-03', '2021-07-24', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (75, '#57492d', 24853.39, 65974.51, 60718.03, 57, 3449.83, 1.29, '2021-07-19', '2021-07-03', '2021-03-25', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (76, '#99b7e1', 76248.98, 92359.80, 97702.57, 28, 47884.17, 2.38, '2021-03-30', '2021-01-28', '2020-12-19', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (77, '#89adea', 74101.42, 47477.00, 22618.60, 35, 22848.76, 7.66, '2021-03-17', '2021-05-13', '2020-10-20', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (78, '#7b27bc', 85099.67, 11532.94, 49564.11, 19, 46202.67, 5.12, '2021-04-08', '2021-09-03', '2021-07-08', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (79, '#4877fa', 86519.44, 28519.69, 35803.77, 8, 57576.32, 2.03, '2020-12-08', '2021-04-21', '2021-07-30', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (80, '#b11218', 51736.71, 32979.52, 91722.79, 18, 25276.15, 2.34, '2021-09-17', '2021-06-18', '2020-11-19', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (81, '#d39998', 45212.59, 56686.78, 50653.61, 63, 3275.92, 6.23, '2021-05-13', '2021-03-28', '2020-11-05', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (82, '#c5d076', 26515.37, 59661.25, 91679.36, 13, 15631.95, 8.80, '2021-09-15', '2021-09-07', '2021-01-17', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (83, '#2b18a9', 90597.12, 13379.24, 71404.50, 50, 144.36, 2.90, '2021-09-29', '2021-09-15', '2021-01-27', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (84, '#7c4b1a', 81976.77, 49446.56, 99798.08, 84, 27754.08, 3.59, '2021-01-09', '2021-04-12', '2020-11-18', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (85, '#07450b', 42813.92, 80798.09, 88607.40, 12, 14775.30, 5.44, '2021-09-01', '2020-11-20', '2020-12-22', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (86, '#7677d3', 9950.90, 40500.85, 93667.24, 46, 13151.15, 7.51, '2021-05-19', '2021-04-23', '2021-03-31', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (87, '#0f3cda', 92714.52, 9574.60, 39828.09, 12, 62351.81, 1.05, '2021-07-21', '2021-08-02', '2021-09-15', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (88, '#d83602', 99410.43, 21096.94, 62730.81, 9, 10219.15, 6.05, '2021-03-31', '2021-10-05', '2021-08-02', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (89, '#20e6d8', 58996.00, 97823.17, 11689.56, 21, 56230.93, 1.15, '2021-08-02', '2021-02-14', '2021-07-28', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (90, '#0854b6', 26933.85, 55790.85, 36923.19, 61, 6142.05, 7.18, '2021-09-25', '2020-11-26', '2021-07-26', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (91, '#b24ead', 33002.51, 20051.16, 17975.73, 91, 15646.97, 6.90, '2021-04-15', '2021-07-06', '2020-11-25', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (92, '#1e1243', 52901.98, 80211.82, 45594.78, 61, 5481.60, 1.43, '2021-02-25', '2020-10-18', '2021-09-06', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (93, '#7480dd', 17333.80, 30385.07, 28308.16, 84, 21932.49, 5.59, '2020-11-21', '2021-07-21', '2021-01-03', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (94, '#186831', 75190.90, 90801.51, 39089.29, 30, 27571.06, 1.09, '2021-07-16', '2021-04-21', '2021-09-16', 1, 'processing');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (95, '#251028', 91215.88, 26405.97, 80573.56, 80, 60115.12, 6.09, '2021-08-18', '2021-09-26', '2021-01-03', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (96, '#40274f', 15948.25, 80618.65, 103.61, 5, 13743.64, 8.80, '2021-02-25', '2021-10-10', '2021-07-30', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (97, '#fc876f', 77168.14, 30621.39, 71688.63, 91, 55334.79, 4.58, '2021-05-17', '2020-12-13', '2021-01-02', 1, 'funded');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (98, '#43c7dd', 12619.21, 10213.58, 72094.43, 19, 94535.83, 2.79, '2020-10-13', '2021-07-27', '2020-11-11', 1, 'active');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (99, '#9ab95e', 46562.55, 45122.54, 69118.84, 78, 21305.34, 7.21, '2021-02-28', '2021-02-28', '2021-03-09', 1, 'review');
INSERT INTO credit (id, credit_number, left_to_pay, amount, next_payment, installments, balance, rate_of_interest, next_payment_date, creation_date, last_installment_date, user_id, status) VALUES (100, '#50b874', 64654.75, 54610.28, 49297.53, 85, 94020.48, 3.01, '2021-09-16', '2020-12-03', '2021-08-11', 1, 'processing');




create table customer
(
    id           bigserial
        constraint customer_pkey
            primary key,
    first_name   varchar(50)  not null,
    last_name    varchar(50)  not null,
    company_name varchar(50)  not null,
    country      varchar(50)  not null,
    city         varchar(50)  not null,
    street       varchar(50)  not null,
    postal_code  varchar(15),
    phone        varchar(15)  not null,
    blacklisted  boolean      not null,
    user_id      integer
        constraint customer_user_fk
            references "user",
    email        varchar(100) not null,
    company_id   integer
        constraint customer_company_fk
            references company
);


INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (15, 'Susanne', 'Haverty', 'Wordware', 'Kazakhstan', 'Borovskoy', 'Bluestem Pass', '88815', '294-314-2973', false, 1, 'test101@test.com', null);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (30, 'Michalgorul', 'Asdf', 'Adf', 'Algeria', 'Adfs', 'Adf', '16515', '+48692195554', false, 19, 'michalgorul@gmail.com', 24);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (7, 'Ingemar', 'Verrall', 'Quatz', 'Angola', 'Luanda', '2240 Crownhardt Street', '88815', '656-929-7181', false, 1, 'test2@test.com', 11);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (19, 'Nettle', 'Haine', 'Oyoloo', 'Belarus', 'Horad Krychaw', '4 Maple Parkway', '6506', '496-477-1000', false, 1, 'test14@test.com', 15);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (8, 'Loleta', 'Hartles', 'Quire', 'Ethiopia', 'Debre Sīna', '86 Homewood Pass', '88815', '682-786-4649', false, 1, 'test3@test.com', 7);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (16, 'Evelyn', 'Dowty', 'Oodoo', 'Ukraine', 'Hubynykha', '948 Brickson Park Plaza', '6506', '912-426-1663', false, 1, 'test11@test.com', 1);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (6, 'Gaby', 'Hughlin', 'Kwideo', 'Indonesia', 'Pandean', '82074 Iowa Junction', '88815', '117-480-2591', true, 1, 'test1@test.com', 5);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (27, 'Koo', 'Follows', 'Tanoodle', 'Poland', 'Chodzież', '5 Tennyson Junction', '64-800', '734-486-7799', true, 1, 'test22@test.com', 6);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (20, 'Audrye', 'Aslie', 'Dynabox', 'Indonesia', 'Tenggun Dajah', '7595 Burrows Avenue', '6506', '303-716-3562', false, 1, 'test15@test.com', 2);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (28, 'Kattie', 'Davio', 'Vinder', 'Malaysia', 'Kuantan', '5 Carioca Plaza', '25200', '394-826-8061', false, 1, 'test23@test.com', 19);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (21, 'Ellene', 'Yeatman', 'Brainverse', 'Norway', 'Kristiansund N', '710 Londonderry Plaza', '6506', '563-131-1292', true, 1, 'test16@test.com', 13);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (22, 'Sarette', 'Littlepage', 'Brainverse', 'Malaysia', 'Sandakan', '896 Main Plaza', '90736', '537-122-2464', false, 1, 'test17@test.com', 14);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (18, 'Hal', 'Stickells', 'Kwinu', 'Mongolia', 'Huurch', '26639 Hovde Crossing', '6506', '381-381-2281', true, 1, 'test13@test.com', 20);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (24, 'Shannan', 'Wanek', 'Latz', 'China', 'Yangzhuang', '444 Karstens Court', '6506', '189-749-7687', true, 1, 'test19@test.com', 4);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (5, 'Leann', 'Vann', 'Zoovu', 'Saudi Arabia', 'Ţubarjal', '4 Susan Place', '88815', '671-735-9741', true, 1, 'test@test.com', 8);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (23, 'Ezra', 'Lyst', 'Kwinu', 'Philippines', 'Panikihan', '28 Pine View Court', '4307', '623-872-9678', false, 1, 'test18@test.com', 12);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (13, 'Adrien', 'Nel', 'Oodoo', 'Norway', 'Rennebu', '7816 Di Loreto Terrace', '7398', '856-812-8363', true, 1, 'test8@test.com', 4);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (10, 'Fredek', 'Attkins', 'Plajo', 'Costa Rica', 'San Juan de Dios', '40708 Lerdahl Alley', '10303', '342-689-3793', false, 1, 'test5@test.com', 1);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (11, 'Ulla', 'Caiger', 'BlogXS', 'Ukraine', 'Serednye', '53 Holmberg Lane', '88815', '911-430-7125', false, 1, 'test6@test.com', 2);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (12, 'Clarey', 'Brimacombe', 'Realbuzz', 'Philippines', 'Banag', '4802 Crest Line Lane', '4234', '821-447-1886', false, 1, 'test7@test.com', 5);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (26, 'Rodney', 'Clausewitz', 'Bluezoom', 'China', 'Nanzamu', '24354 Artisan Place', '6506', '418-868-3763', false, 1, 'test21@test.com', 18);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (25, 'Urson', 'Topes', 'Zoonoodle', 'Poland', 'Wleń', '8 Meadow Valley Drive', '59-610', '658-756-8147', false, 1, 'test20@test.com', 10);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (17, 'Ilyse', 'Crim', 'Vinte', 'Vietnam', 'Bến Tre', '39 Carberry Alley', '6506', '467-673-1220', false, 1, 'test12@test.com', 16);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (14, 'Vale', 'Priter', 'Eamia', 'Jordan', 'Al Azraq ash Shamālī', '7 Sullivan Plaza', '88815', '659-935-4256', false, 1, 'test9@test.com', 9);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (9, 'Raleigh', 'Hannon', 'Gabtype', 'Mexico', 'Buenavista', '16 Longview Trail', '88815', '442-792-5781', true, 1, 'test4@test.com', 17);
INSERT INTO customer (id, first_name, last_name, company_name, country, city, street, postal_code, phone, blacklisted, user_id, email, company_id) VALUES (29, 'Kenon', 'Purshouse', 'Bluezoom', 'China', 'Wukang', '523 Becker Hill', '6506', '859-783-0407', false, 1, 'test24@test.com', 3);




create table file
(
    id           uuid         not null
        constraint file_pk
            primary key,
    name         varchar(255) not null,
    size         bigint       not null,
    content_type varchar(255) not null,
    data         oid          not null,
    user_id      integer      not null
        constraint "file_user_FK"
            references "user",
    catalog      varchar(50)  not null
);

create table product
(
    id           bigserial
        constraint product_pkey
            primary key,
    name         varchar(50) not null,
    pkwiu        varchar(10) not null,
    measure_unit varchar(8)  not null
);






INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (1, 'sofa', '123.123.1', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (2, 'Whmis - Spray Bottle Trigger', '10.11.99.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (3, 'Fib N9 - Prague Powder', '13.95.10.0', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (4, 'Pepper - Sorrano', '26.30.40.0', 'meters');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (5, 'Chicken - Breast, 5 - 7 Oz', '01.16.19.0', 'litres');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (6, 'Beer - Rickards Red', '19.20.23.0', 'litres');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (7, 'Bread Fig And Almond', '14.13.35.0', 'seconds');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (8, 'Foam Espresso Cup Plain White', '10.89.16.0', 'grams');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (9, 'Spice - Pepper Portions', '26.30.40.0', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (10, 'Stock - Fish', '26.30.40.0', 'grams');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (11, 'Sloe Gin - Mcguinness', '01.26.12.0', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (12, 'Pepper - Cayenne', '10.39.17.0', 'litres');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (13, 'Loquat', '02.10.11.0', 'kilowatt');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (14, 'Carrots - Jumbo', '17.12.34.0', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (15, 'Ginger - Ground', '19.20.23.0', 'grams');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (16, 'Squash - Butternut', '25.94.99.0', 'meters');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (17, 'Pasta - Penne Primavera, Single', '03.00.42.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (18, 'Assorted Desserts', '03.00.42.0', 'kilowatt');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (19, 'Cheese - Mozzarella', '25.94.99.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (20, 'Sobe - Lizard Fuel', '13.95.10.0', 'grams');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (21, 'Soup - Campbellschix Stew', '10.61.12.0', 'seconds');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (22, 'Salmon - Fillets', '16.10.13.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (23, 'Tray - 16in Rnd Blk', '19.20.23.0', 'meters');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (24, 'Bacardi Breezer - Tropical', '25.29.99.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (25, 'Sour Puss Sour Apple', '14.13.35.0', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (26, 'Star Fruit', '14.13.35.0', 'seconds');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (27, 'Tea - Decaf 1 Cup', '26.30.40.0', 'litres');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (28, 'Syrup - Monin - Passion Fruit', '01.45.30.0', 'grams');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (29, 'Hickory Smoke, Liquid', '01.26.12.0', 'grams');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (30, 'Gherkin', '01.26.12.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (31, 'Grapefruit - White', '01.26.12.0', 'litres');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (32, 'Yogurt - Peach, 175 Gr', '01.26.12.0', 'kilowatt');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (33, 'Salmon Atl.whole 8 - 10 Lb', '01.45.30.0', 'litres');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (34, 'Syrup - Monin - Blue Curacao', '01.45.30.0', 'kilowatt');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (35, 'Eggs - Extra Large', '10.61.12.0', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (36, 'Water - Spring 1.5lit', '10.61.12.0', 'seconds');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (37, 'Wine - Trimbach Pinot Blanc', '16.10.13.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (38, 'Oranges - Navel, 72', '13.95.10.0', 'kilowatt');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (39, 'Pork Casing', '14.13.35.0', 'litres');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (40, 'Ice Cream - Turtles Stick Bar', '01.45.30.0', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (41, 'Garam Marsala', '01.45.30.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (42, 'Fib N9 - Prague Powder', '26.30.40.0', 'kilowatt');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (43, 'Wine - White, Pelee Island', '17.12.34.0', 'galons');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (44, 'Bacardi Breezer - Tropical', '01.26.12.0', 'meters');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (45, 'Butter - Salted, Micro', '01.16.19.0', 'kilowatt');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (46, 'Nut - Hazelnut, Ground, Natural', '01.28.19.0', 'litres');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (47, 'Broom And Brush Rack Black', '08.11.12.0', 'number');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (48, 'Bread - White, Sliced', '01.28.19.0', 'seconds');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (49, 'Shrimp - 16 - 20 Cooked, Peeled', '01.16.19.0', 'meters');
INSERT INTO product (id, name, pkwiu, measure_unit) VALUES (50, 'Mushroom - Oyster, Fresh', '01.45.30.0', 'grams');



create table invoice
(
    id               bigserial
        constraint invoice_pkey
            primary key,
    invoice_number   varchar(50)   not null,
    creation_date    date          not null,
    sale_date        date          not null,
    payment_deadline date          not null,
    to_pay           numeric(8, 2) not null,
    to_pay_in_words  text          not null,
    paid             numeric(7, 2) not null,
    left_to_pay      numeric(7, 2) not null,
    remarks          text,
    seller_id        integer       not null
        constraint invoice_seller_fk
            references seller,
    customer_id      integer       not null,
    payment_type_id  integer       not null
        constraint invoice_payment_type_fk
            references payment_type,
    currency_id      integer       not null
        constraint invoice_currency_fk
            references currency,
    status           varchar(50)   not null,
    user_id          integer
        constraint invoice_user_fk
            references "user"
);




INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (1, '#3e456a', '2020-12-20', '2021-06-24', '2021-08-28', 302.58, 'suspendisse potenti nullam porttitor', 5790.36, 5047.72, 'ut volutpat sapien arcu sed augue aliquam erat volutpat', 1, 26, 1, 25, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (16, '#5f1af4', '2021-08-04', '2020-10-03', '2021-08-20', 6847.38, 'id ornare', 724.50, 6524.53, null, 1, 5, 4, 23, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (64, '#17a2fc', '2021-05-12', '2021-05-17', '2021-03-30', 4769.20, 'nibh in', 6527.01, 4655.06, 'sit amet eleifend', 1, 14, 5, 9, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (33, '#5611a9', '2021-08-24', '2021-02-02', '2021-01-19', 7210.01, 'interdum eu tincidunt in', 5104.83, 5766.43, 'nisi venenatis tristique fusce congue diam id ornare', 1, 15, 2, 34, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (22, '#7712c7', '2020-10-16', '2020-09-07', '2021-09-01', 144.42, 'lectus pellentesque eget', 763.60, 1639.48, 'ac lobortis', 1, 10, 2, 6, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (63, '#3fd634', '2021-05-09', '2020-10-10', '2020-11-29', 9059.89, 'tincidunt lacus at velit', 8439.32, 6224.48, 'volutpat erat quisque erat', 1, 20, 2, 28, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (28, '#d5142a', '2020-09-13', '2021-04-14', '2020-09-07', 646.00, 'suspendisse ornare', 190.65, 4750.22, null, 1, 13, 2, 32, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (45, '#e9d437', '2021-07-06', '2020-11-03', '2021-05-21', 3525.83, 'posuere felis sed', 5032.09, 9346.34, 'donec posuere metus vitae ipsum aliquam non', 1, 22, 1, 32, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (30, '#849f61', '2020-11-20', '2021-03-28', '2021-04-02', 7464.90, 'integer pede justo', 9754.44, 7491.36, 'aliquam augue quam sollicitudin vitae consectetuer eget', 1, 16, 3, 38, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (59, '#7bb2a8', '2021-07-10', '2021-05-05', '2021-02-15', 6186.49, 'nulla mollis molestie', 8741.50, 5900.19, 'morbi vestibulum velit id pretium iaculis', 1, 20, 1, 43, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (29, '#b34148', '2021-01-24', '2021-07-10', '2020-12-19', 4218.10, 'velit id pretium iaculis', 1361.88, 5725.43, null, 1, 7, 3, 25, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (38, '#faaa94', '2021-05-05', '2020-12-26', '2020-12-14', 4448.41, 'risus dapibus augue', 4105.50, 2776.30, 'sit amet sapien dignissim vestibulum vestibulum ante', 1, 13, 1, 25, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (53, '#a60194', '2021-01-16', '2021-05-25', '2020-12-03', 1717.66, 'velit nec nisi vulputate', 3623.36, 427.26, 'sed tristique', 1, 10, 2, 25, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (14, '#c06e06', '2021-03-06', '2021-08-10', '2020-11-17', 3574.56, 'quisque ut erat curabitur', 8707.12, 7696.89, 'amet nulla', 1, 22, 1, 7, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (17, '#e15824', '2021-06-01', '2021-04-04', '2021-02-08', 7777.01, 'etiam vel augue vestibulum', 1706.72, 8977.12, 'pretium quis lectus', 1, 28, 3, 18, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (49, '#37326a', '2021-03-28', '2021-07-13', '2020-12-31', 1634.69, 'nisl duis ac', 9712.33, 8072.76, 'est risus auctor sed tristique in tempus sit amet', 1, 10, 2, 18, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (6, '#b0eeb9', '2021-07-27', '2021-08-31', '2020-12-09', 4469.02, 'est risus', 395.41, 3946.78, 'tempus vel pede morbi porttitor lorem id ligula suspendisse ornare', 1, 9, 4, 26, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (7, '#bb1630', '2021-04-21', '2020-11-10', '2020-11-20', 4817.30, 'rhoncus aliquet pulvinar', 6330.47, 8923.37, null, 1, 6, 5, 15, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (60, '#128523', '2020-10-06', '2021-06-14', '2021-07-06', 9504.22, 'neque sapien', 4779.45, 548.18, 'vestibulum sed', 1, 26, 5, 26, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (56, '#48568d', '2021-03-11', '2021-02-23', '2021-02-21', 6370.70, 'et tempus semper', 5176.32, 5872.85, null, 1, 14, 3, 32, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (57, '#e34edb', '2020-10-25', '2020-11-28', '2020-12-27', 3609.26, 'pede venenatis non', 4129.58, 6736.75, null, 1, 12, 1, 7, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (54, '#8f9f5e', '2021-06-01', '2021-06-07', '2021-04-26', 1669.88, 'aliquam convallis nunc proin', 596.25, 4698.82, null, 1, 28, 5, 34, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (55, '#e401e5', '2021-04-20', '2021-07-14', '2021-03-13', 7156.23, 'ante ipsum primis', 1492.73, 2416.55, 'nulla nunc purus phasellus in felis donec semper sapien', 1, 8, 1, 3, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (58, '#67d502', '2020-12-10', '2021-05-31', '2021-03-24', 6525.59, 'morbi vestibulum velit', 4912.84, 651.21, 'tortor quis turpis sed', 1, 8, 5, 12, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (34, '#17df44', '2021-07-29', '2021-06-16', '2021-01-24', 3964.27, 'volutpat convallis morbi odio', 5618.48, 5856.95, 'nulla ultrices aliquet maecenas leo odio condimentum id luctus', 1, 12, 1, 9, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (8, '#9d10f1', '2021-03-30', '2021-09-02', '2020-10-10', 3429.80, 'augue a suscipit', 240.84, 4060.37, null, 1, 17, 2, 17, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (9, '#fd7da9', '2020-09-11', '2021-08-11', '2021-01-13', 2148.04, 'fusce posuere felis', 6997.16, 7237.60, 'fermentum donec ut mauris eget massa', 1, 17, 5, 7, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (11, '#0e330b', '2020-11-19', '2021-07-02', '2021-01-10', 7978.67, 'at nibh', 1178.75, 8647.25, 'in hac habitasse', 1, 27, 5, 28, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (12, '#e3063b', '2021-07-26', '2020-10-27', '2021-02-09', 9610.78, 'morbi non quam nec', 9988.36, 3429.63, 'ante ipsum primis in faucibus orci luctus et', 1, 25, 2, 7, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (13, '#2f97cf', '2021-02-20', '2021-07-30', '2021-07-02', 3358.74, 'dolor sit amet', 2333.55, 7137.46, 'in hac habitasse platea dictumst morbi vestibulum velit id', 1, 22, 5, 6, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (15, '#754cb7', '2021-04-04', '2021-01-07', '2021-08-17', 1656.14, 'nisl duis', 2238.01, 8558.44, 'eu interdum eu tincidunt in leo maecenas pulvinar lobortis', 1, 9, 1, 18, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (18, '#83ada5', '2021-03-08', '2021-02-17', '2021-08-22', 4092.63, 'pharetra magna', 7554.83, 8565.14, 'odio condimentum id luctus nec molestie sed justo pellentesque', 1, 28, 3, 29, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (19, '#6ffc91', '2021-02-23', '2020-10-21', '2020-11-23', 9038.22, 'tellus nulla', 6458.62, 4868.40, 'lectus pellentesque', 1, 25, 3, 37, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (20, '#da4294', '2021-05-24', '2020-12-20', '2021-09-05', 764.91, 'lacinia sapien', 6966.12, 4397.81, 'curabitur gravida nisi at nibh in hac habitasse', 1, 9, 1, 46, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (21, '#65cb3c', '2020-11-03', '2020-11-29', '2021-08-10', 1495.37, 'ut tellus nulla ut', 4924.65, 7633.56, 'ut volutpat sapien arcu', 1, 23, 4, 3, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (23, '#eb2205', '2020-11-19', '2021-05-29', '2021-04-02', 4901.47, 'nulla eget', 5213.49, 8930.57, 'eu magna vulputate luctus cum sociis', 1, 22, 3, 32, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (24, '#4ddec5', '2021-03-05', '2021-01-14', '2021-01-26', 2799.13, 'erat vestibulum sed', 3588.67, 2214.32, 'amet nunc viverra dapibus nulla suscipit ligula in lacus curabitur', 1, 22, 5, 23, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (25, '#f6e1c4', '2020-10-17', '2020-09-28', '2020-10-22', 7503.70, 'justo morbi', 7851.22, 6176.43, null, 1, 13, 2, 7, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (26, '#f48cb9', '2021-07-25', '2021-03-17', '2021-05-12', 9161.47, 'cras pellentesque volutpat', 333.82, 180.70, null, 1, 16, 4, 23, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (27, '#05d34c', '2021-08-03', '2020-10-09', '2021-01-11', 2650.40, 'massa tempor convallis', 7220.61, 4258.18, 'morbi vestibulum velit id pretium iaculis diam erat fermentum', 1, 13, 2, 45, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (31, '#1ddfee', '2020-11-25', '2021-03-17', '2021-04-09', 8308.44, 'egestas metus', 1230.15, 2561.98, null, 1, 12, 5, 20, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (35, '#919877', '2021-04-23', '2020-09-09', '2020-10-04', 808.80, 'odio in hac', 8204.66, 9111.34, 'porttitor lacus at turpis', 1, 25, 4, 17, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (37, '#3f44f9', '2020-10-01', '2021-01-26', '2021-05-23', 4640.58, 'nulla elit ac', 2506.16, 3632.51, 'vel nisl duis ac nibh fusce lacus purus aliquet', 1, 15, 1, 17, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (39, '#c4e2fd', '2020-11-17', '2021-08-24', '2021-03-21', 1416.05, 'quis odio', 8406.49, 3580.90, null, 1, 5, 1, 38, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (40, '#0faea4', '2021-04-04', '2020-11-03', '2021-07-02', 5258.42, 'in consequat ut', 9007.69, 6197.08, 'adipiscing elit proin', 1, 8, 4, 15, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (41, '#adc697', '2021-07-22', '2021-02-25', '2021-02-14', 1731.18, 'nulla dapibus', 5363.01, 9682.82, null, 1, 19, 1, 12, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (42, '#f6763e', '2021-01-10', '2021-08-28', '2020-12-03', 6247.51, 'sodales sed tincidunt', 4587.68, 8236.03, 'ultrices mattis odio donec vitae', 1, 15, 2, 9, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (43, '#dc2cb2', '2021-06-12', '2020-09-28', '2021-08-17', 9478.70, 'convallis morbi odio odio', 2091.79, 4311.68, 'ligula vehicula consequat morbi a ipsum integer a nibh', 1, 16, 4, 37, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (44, '#e6325b', '2021-04-02', '2021-01-11', '2020-10-02', 8538.23, 'turpis integer', 5601.81, 6503.55, 'eros elementum pellentesque quisque porta volutpat erat', 1, 9, 1, 38, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (46, '#5cc602', '2021-03-02', '2020-11-21', '2021-03-10', 1313.64, 'convallis morbi odio odio', 3832.76, 7168.91, 'vitae ipsum aliquam non', 1, 14, 1, 29, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (47, '#c09f13', '2021-05-05', '2021-03-17', '2020-11-27', 5322.15, 'vel ipsum', 8362.20, 838.77, null, 1, 12, 3, 32, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (48, '#4f8255', '2021-03-19', '2020-09-10', '2020-10-14', 490.41, 'etiam pretium iaculis', 8228.39, 942.58, 'ut erat curabitur gravida', 1, 8, 1, 26, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (50, '#37d2ca', '2021-03-05', '2021-07-21', '2021-05-08', 1412.23, 'diam nam tristique', 1741.94, 9879.74, null, 1, 20, 4, 46, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (52, '#78f43d', '2021-02-15', '2020-12-08', '2021-07-28', 2437.26, 'praesent id massa id', 9164.25, 5414.17, 'ut tellus nulla', 1, 11, 2, 32, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (51, '#cb73b1', '2021-02-07', '2020-09-19', '2020-09-16', 2780.05, 'ipsum primis', 2964.60, 4618.31, 'sapien placerat ante', 1, 5, 3, 45, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (61, '#02ce49', '2020-09-15', '2020-11-02', '2020-11-25', 5205.31, 'vel dapibus at', 2539.13, 4224.48, null, 1, 22, 4, 46, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (62, '#9008fb', '2021-01-13', '2020-09-26', '2021-09-02', 6993.04, 'a pede', 9603.87, 8617.97, 'dis parturient montes nascetur ridiculus mus', 1, 20, 5, 38, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (65, '#38f1c8', '2021-07-12', '2020-11-16', '2020-12-06', 5288.59, 'vitae mattis nibh', 5708.32, 7603.44, 'in eleifend quam a odio in', 1, 9, 3, 25, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (66, '#b0f724', '2021-04-15', '2021-07-10', '2021-07-11', 3019.67, 'in tempus sit', 295.79, 4646.37, null, 1, 15, 2, 15, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (4, '#0ade4f', '2021-05-11', '2021-08-22', '2021-06-04', 5619.76, 'eu mi', 1211.09, 3606.43, 'sit amet diam in', 1, 5, 2, 6, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (69, '#dbc301', '2021-02-20', '2021-07-28', '2021-06-21', 6524.67, 'aliquam non mauris', 6397.65, 5915.35, null, 1, 14, 4, 3, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (70, '#a686f0', '2021-08-01', '2021-05-25', '2021-02-03', 4475.24, 'quam turpis adipiscing lorem', 154.56, 5147.65, null, 1, 7, 5, 25, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (71, '#4cdfe2', '2021-07-02', '2021-03-19', '2021-05-05', 7098.24, 'ut blandit', 5596.35, 5406.70, 'justo in blandit ultrices enim', 1, 28, 2, 9, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (74, '#2cd17e', '2021-05-24', '2021-07-19', '2021-01-06', 8971.71, 'id ornare imperdiet sapien', 4090.19, 11.26, null, 1, 10, 4, 7, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (75, '#4702c2', '2020-10-05', '2021-08-31', '2020-12-23', 7041.51, 'quis tortor id nulla', 4543.03, 3803.15, 'ut ultrices vel', 1, 13, 1, 29, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (79, '#337b81', '2021-05-29', '2020-11-26', '2021-07-04', 76.47, 'posuere cubilia', 6784.41, 9031.78, 'felis fusce posuere', 1, 28, 4, 37, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (83, '#6ff498', '2020-09-10', '2021-03-10', '2020-10-27', 7365.34, 'nibh in', 4123.35, 326.78, null, 1, 26, 4, 17, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (85, '#19a2ca', '2021-03-04', '2021-05-28', '2021-08-11', 2993.80, 'quisque porta', 5977.40, 4756.47, 'consequat dui nec nisi volutpat eleifend donec ut dolor', 1, 8, 1, 43, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (87, '#c82d18', '2021-03-31', '2020-09-09', '2021-06-03', 4963.03, 'malesuada in imperdiet et', 3104.00, 1029.44, 'facilisi cras non velit nec nisi vulputate nonummy', 1, 23, 5, 15, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (88, '#372c58', '2020-10-22', '2021-01-12', '2021-01-24', 8100.34, 'imperdiet nullam orci', 2382.66, 9356.17, 'dui vel nisl duis ac nibh fusce lacus purus', 1, 23, 1, 38, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (89, '#7c2386', '2021-09-01', '2021-05-03', '2020-11-26', 5960.42, 'dolor sit amet consectetuer', 139.13, 356.17, 'id pretium iaculis diam erat fermentum justo', 1, 13, 3, 15, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (90, '#cf9989', '2021-03-29', '2021-06-03', '2021-02-14', 1856.37, 'adipiscing elit proin risus', 9189.23, 9267.25, 'integer ac neque', 1, 20, 4, 23, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (92, '#421830', '2021-05-20', '2020-11-17', '2021-01-29', 4602.87, 'non lectus aliquam', 2931.19, 135.77, 'vivamus vel nulla', 1, 28, 5, 26, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (93, '#799e25', '2021-07-04', '2020-10-10', '2020-11-15', 9533.41, 'turpis elementum ligula vehicula', 2533.60, 2679.52, null, 1, 29, 3, 18, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (95, '#4ca5af', '2021-04-28', '2021-06-30', '2021-01-30', 1655.18, 'risus semper porta volutpat', 9272.25, 8329.96, 'posuere cubilia curae mauris viverra diam vitae', 1, 8, 5, 3, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (96, '#bb5f08', '2021-06-30', '2020-10-01', '2020-11-05', 5414.16, 'vivamus vestibulum', 5209.75, 4599.92, 'suspendisse potenti', 1, 11, 3, 38, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (99, '#948691', '2021-07-27', '2021-07-20', '2021-02-20', 199.65, 'pretium iaculis', 331.05, 3276.35, 'in sagittis dui vel nisl', 1, 13, 3, 43, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (68, '#d3a626', '2021-02-28', '2021-05-03', '2021-05-16', 7946.33, 'vel enim sit amet', 3808.63, 202.72, 'nec condimentum', 1, 19, 4, 43, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (78, '#10233e', '2021-07-28', '2020-11-04', '2021-07-06', 6235.58, 'consequat nulla nisl nunc', 8937.15, 6626.53, 'sapien iaculis congue vivamus metus arcu adipiscing', 1, 5, 4, 9, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (5, '#fe77ff', '2021-04-03', '2021-04-16', '2020-11-29', 1947.17, 'diam vitae quam', 929.28, 1664.08, 'ultricies eu nibh quisque id justo sit amet', 1, 5, 3, 45, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (73, '#4f8e0b', '2020-11-20', '2020-11-30', '2021-02-14', 4537.29, 'mauris eget massa', 6285.60, 6795.46, null, 1, 12, 1, 29, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (36, '#c8909e', '2020-11-19', '2021-04-25', '2021-05-27', 3960.20, 'enim leo', 6973.85, 9688.30, null, 1, 5, 5, 23, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (94, '#53b33d', '2021-07-29', '2020-12-11', '2021-04-07', 9611.50, 'cursus urna', 8668.74, 1103.23, null, 1, 21, 2, 9, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (32, '#83d3f6', '2020-09-18', '2021-03-18', '2020-12-31', 4553.06, 'orci eget', 448.92, 5150.91, 'risus semper porta volutpat', 1, 5, 2, 9, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (76, '#32358c', '2021-01-27', '2020-11-30', '2020-10-02', 823.44, 'orci vehicula condimentum curabitur', 7508.18, 4033.49, 'fermentum justo nec condimentum neque sapien placerat ante', 1, 15, 3, 20, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (67, '#7e6850', '2021-02-12', '2020-09-22', '2021-01-03', 410.16, 'consectetuer adipiscing elit', 8575.39, 1323.35, 'curae nulla dapibus dolor vel est donec odio justo', 1, 16, 4, 35, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (77, '#3d67d9', '2020-11-29', '2020-10-29', '2020-10-19', 7273.08, 'orci mauris', 5774.40, 7007.35, null, 1, 5, 3, 35, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (91, '#e7dabc', '2021-04-22', '2021-03-17', '2021-08-22', 5951.12, 'ipsum aliquam non', 1235.67, 9556.08, 'mauris eget massa tempor convallis nulla neque libero convallis eget', 1, 5, 5, 17, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (72, '#3addd8', '2020-11-05', '2021-02-02', '2021-07-02', 7192.08, 'in porttitor pede justo', 6671.28, 1479.36, 'parturient montes nascetur ridiculus', 1, 5, 2, 46, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (82, '#324985', '2020-09-15', '2021-07-22', '2020-10-03', 6143.35, 'eu felis fusce', 3959.26, 7438.71, 'pellentesque volutpat dui maecenas', 1, 19, 1, 46, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (10, '#ecda47', '2020-12-06', '2021-01-28', '2021-06-06', 6646.48, 'mi integer ac neque', 7353.86, 8926.90, 'iaculis justo in', 1, 5, 5, 43, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (84, '#765b7a', '2021-01-08', '2020-11-07', '2021-02-09', 7017.31, 'suspendisse potenti cras', 2461.69, 2628.28, 'congue diam id ornare', 1, 18, 3, 25, 'funded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (100, '#3d1458', '2021-08-04', '2020-11-29', '2021-03-23', 4698.63, 'auctor sed', 8527.31, 3106.80, null, 1, 11, 2, 25, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (80, '#458aa2', '2021-03-14', '2020-12-19', '2021-04-25', 1164.23, 'sagittis dui vel nisl', 3926.69, 6564.48, null, 1, 15, 3, 25, 'unfunded', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (81, '#f67db8', '2021-04-29', '2021-07-19', '2021-08-14', 9595.75, 'non mauris morbi non', 8059.79, 2423.65, 'posuere metus vitae ipsum aliquam', 1, 25, 3, 7, 'active', 1);
INSERT INTO invoice (id, invoice_number, creation_date, sale_date, payment_deadline, to_pay, to_pay_in_words, paid, left_to_pay, remarks, seller_id, customer_id, payment_type_id, currency_id, status, user_id) VALUES (86, '#5ebd8e', '2021-07-10', '2021-08-04', '2021-04-28', 5391.39, 'velit vivamus vel', 4727.04, 7833.91, null, 1, 15, 5, 18, 'unfunded', 1);




create table invoice_item
(
    id          bigserial
        constraint invoice_item_pkey
            primary key,
    quentity    integer       not null,
    net_price   numeric(8, 2) not null,
    net_value   numeric(8, 2) not null,
    vat_rate    numeric(8, 2) not null,
    vat_value   numeric(8, 2) not null,
    gross_value numeric(8, 2) not null,
    product_id  integer       not null
        constraint invoice_item_product_fk
            references product,
    invoice_id  integer       not null
        constraint invoice_item_invoice_fk
            references invoice
);





INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (1, 2, 123.00, 246.00, 23.00, 56.58, 302.58, 1, 1);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (2, 55, 223.57, 12296.35, 0.10, 1229.64, 13525.99, 40, 7);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (3, 56, 5561.03, 311417.68, 0.18, 56055.18, 367472.86, 36, 4);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (8, 12, 5468.30, 65619.60, 0.16, 10499.14, 76118.74, 21, 8);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (10, 55, 2888.02, 158841.10, 0.10, 15884.11, 174725.21, 42, 9);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (13, 67, 7122.54, 477210.18, 0.17, 81125.73, 558335.91, 5, 37);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (14, 69, 7228.47, 498764.43, 0.04, 19950.58, 518715.01, 50, 47);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (15, 46, 6072.58, 279338.68, 0.12, 33520.64, 312859.32, 29, 38);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (17, 15, 568.69, 8530.35, 0.06, 511.82, 9042.17, 12, 10);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (19, 36, 6219.51, 223902.36, 0.18, 40302.42, 264204.78, 23, 32);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (21, 24, 2599.07, 62377.68, 0.05, 3118.88, 65496.56, 29, 45);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (22, 86, 4526.89, 389312.54, 0.18, 70076.26, 459388.80, 26, 15);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (24, 85, 7966.29, 677134.65, 0.08, 54170.77, 731305.42, 46, 44);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (25, 40, 1432.40, 57296.00, 0.11, 6302.56, 63598.56, 26, 38);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (28, 93, 3769.75, 350586.75, 0.15, 52588.01, 403174.76, 48, 33);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (29, 28, 5243.67, 146822.76, 0.06, 8809.37, 155632.13, 40, 46);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (30, 77, 3707.38, 285468.26, 0.05, 14273.41, 299741.67, 1, 5);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (33, 54, 7347.14, 396745.56, 0.21, 83316.57, 480062.13, 6, 16);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (34, 57, 6026.53, 343512.21, 0.14, 48091.71, 391603.92, 8, 35);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (35, 7, 9924.17, 69469.19, 0.21, 14588.53, 84057.72, 1, 14);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (36, 2, 4307.02, 8614.04, 0.11, 947.54, 9561.58, 46, 39);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (37, 37, 4501.45, 166553.65, 0.14, 23317.51, 189871.16, 13, 42);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (39, 97, 164.12, 15919.64, 0.19, 3024.73, 18944.37, 39, 34);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (45, 14, 849.42, 11891.88, 0.21, 2497.29, 14389.17, 16, 24);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (47, 26, 1455.86, 37852.36, 0.12, 4542.28, 42394.64, 47, 36);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (48, 26, 2991.90, 77789.40, 0.19, 14779.99, 92569.39, 32, 49);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (50, 58, 4742.23, 275049.34, 0.04, 11001.97, 286051.31, 32, 50);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (51, 9, 9196.01, 82764.09, 0.22, 18208.10, 100972.19, 15, 23);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (52, 34, 918.07, 31214.38, 0.20, 6242.88, 37457.26, 47, 12);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (54, 28, 9524.23, 266678.44, 0.04, 10667.14, 277345.58, 30, 18);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (56, 23, 9898.78, 227671.94, 0.08, 18213.76, 245885.70, 4, 41);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (58, 83, 2717.73, 225571.59, 0.06, 13534.30, 239105.89, 35, 28);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (59, 79, 4655.54, 367787.66, 0.21, 77235.41, 445023.07, 16, 25);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (60, 59, 7049.07, 415895.13, 0.13, 54066.37, 469961.50, 2, 26);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (61, 23, 8147.15, 187384.45, 0.04, 7495.38, 194879.83, 10, 43);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (38, 2, 8595.79, 17191.58, 0.01, 171.92, 17363.50, 23, 6);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (12, 86, 6609.58, 568423.88, 0.05, 28421.19, 596845.07, 9, 11);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (49, 94, 1231.80, 115789.20, 0.14, 16210.49, 131999.69, 33, 20);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (57, 85, 4177.34, 355073.90, 0.18, 63913.30, 418987.20, 2, 17);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (27, 61, 9410.97, 574069.17, 0.15, 86110.38, 660179.55, 14, 19);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (32, 5, 1675.09, 8375.45, 0.17, 1423.83, 9799.28, 43, 29);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (18, 43, 7849.34, 337521.62, 0.13, 43877.81, 381399.43, 38, 27);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (16, 46, 6953.60, 319865.60, 0.06, 19191.94, 339057.54, 10, 30);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (44, 77, 1507.54, 116080.58, 0.21, 24376.92, 140457.50, 20, 60);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (43, 1, 5486.61, 5486.61, 0.01, 54.87, 5541.48, 15, 31);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (42, 2, 6893.37, 13786.74, 0.03, 413.60, 14200.34, 5, 55);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (46, 17, 4293.37, 72987.29, 0.17, 12407.84, 85395.13, 46, 58);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (53, 75, 2487.60, 186570.00, 0.09, 16791.30, 203361.30, 35, 56);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (11, 75, 3410.05, 255753.75, 0.01, 2557.54, 258311.29, 45, 48);
INSERT INTO invoice_item (id, quentity, net_price, net_value, vat_rate, vat_value, gross_value, product_id, invoice_id) VALUES (55, 42, 1518.06, 63758.52, 0.17, 10838.95, 74597.47, 37, 57);



create table "order"
(
    id                 bigserial
        constraint order_pkey
            primary key,
    order_number       varchar(50)   not null,
    order_type         text          not null,
    order_date         date          not null,
    first_pay_ammount  numeric(8, 2) not null,
    second_pay_ammount numeric(7, 2) not null,
    commission_rate    numeric(6, 2) not null,
    commission_value   numeric(6, 2) not null,
    invoice_id         integer       not null
        constraint order_invoice_fk
            references invoice,
    user_id            integer       not null
        constraint order_user_fk
            references "user",
    status_id          integer       not null
        constraint order_status_fk
            references status
);




create table transaction
(
    id               bigserial
        constraint transaction_pkey
            primary key,
    transaction_date date          not null,
    value            numeric(8, 2) not null,
    status_id        integer       not null
        constraint transaction_status_fk
            references status,
    customer_id      integer       not null,
    invoice_id       integer       not null
        constraint transaction_invoice_fk
            references invoice,
    currency_id      integer       not null
        constraint transaction_currency_fk
            references currency
);






























