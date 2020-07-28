create sequence hibernate_sequence start 1 increment 1

create table bottle
(
    id           int8 not null,
    coupon_code  varchar(255),
    product_name varchar(255),
    company_id   int8,
    primary key (id)
)
create table campaign
(
    id            int8 not null,
    campaign_name varchar(255),
    end_date      date,
    start_date    date,
    company_id    int8,
    primary key (id)
)
create table company
(
    id           int8 not null,
    company_name varchar(255),
    primary key (id)
)
create table customer
(
    id               int8 not null,
    apartment_number int4 not null,
    country          varchar(255),
    street           varchar(255),
    age              int4 not null,
    coupon_code      varchar(255),
    email            varchar(255),
    name             varchar(255),
    primary key (id)
)
create table log_entry
(
    id             int8    not null,
    coupon_code    varchar(255),
    email          varchar(255),
    is_winner      boolean not null,
    submitted_date date,
    territory      varchar(255),
    primary key (id)
)
create table rule
(
    id                      int8 not null,
    maximum_winners_per_day int4 not null,
    nth_entry               int4 not null,
    number_of_products      int4 not null,
    territory_id            int8,
    primary key (id)
)
create table territory
(
    id             int8 not null,
    territory_name varchar(255),
    campaign_id    int8,
    primary key (id)
)
alter table bottle
    add constraint Fk_COMPANY_PRODUCT_ID foreign key (company_id) references company
alter table campaign
    add constraint Fk_COMPANY_CAMPAIGN_ID foreign key (company_id) references company
alter table rule
    add constraint Fk_RULE_TERRITORY_ID foreign key (territory_id) references territory
alter table territory
    add constraint Fk_CAMPAIGN_TERRITORY_ID foreign key (campaign_id) references campaign
create sequence hibernate_sequence start 1 increment 1
create table bottle (id int8 not null, coupon_code varchar(255), product_name varchar(255), company_id int8, primary key (id))
create table campaign (id int8 not null, campaign_name varchar(255), end_date date, start_date date, company_id int8, primary key (id))
create table company (id int8 not null, company_name varchar(255), primary key (id))
create table customer (id int8 not null, apartment_number int4 not null, country varchar(255), street varchar(255), age int4 not null, coupon_code varchar(255), email varchar(255), name varchar(255), primary key (id))
create table log_entry (id int8 not null, coupon_code varchar(255), email varchar(255), is_winner boolean not null, submitted_date date, territory varchar(255), primary key (id))
create table rule (id int8 not null, maximum_winners_per_day int4 not null, nth_entry int4 not null, number_of_products int4 not null, territory_id int8, primary key (id))
create table territory (id int8 not null, territory_name varchar(255), campaign_id int8, primary key (id))
alter table bottle add constraint Fk_COMPANY_PRODUCT_ID foreign key (company_id) references company
alter table campaign add constraint Fk_COMPANY_CAMPAIGN_ID foreign key (company_id) references company
alter table rule add constraint Fk_RULE_TERRITORY_ID foreign key (territory_id) references territory
alter table territory add constraint Fk_CAMPAIGN_TERRITORY_ID foreign key (campaign_id) references campaign
create sequence hibernate_sequence start 1 increment 1
create table campaign (id int8 not null, campaign_name varchar(255), end_date date, start_date date, company_id int8, primary key (id))
create table company (id int8 not null, company_name varchar(255), primary key (id))
create table customer (id int8 not null, apartment_number int4 not null, country varchar(255), street varchar(255), age int4 not null, coupon_code varchar(255), email varchar(255), name varchar(255), primary key (id))
create table log_entry (id int8 not null, coupon_code varchar(255), email varchar(255), is_winner boolean not null, submitted_date date, territory varchar(255), primary key (id))
create table product (id int8 not null, coupon_code varchar(255), product_name varchar(255), company_id int8, primary key (id))
create table rule (id int8 not null, maximum_winners_per_day int4 not null, nth_entry int4 not null, number_of_products int4 not null, territory_id int8, primary key (id))
create table territory (id int8 not null, territory_name varchar(255), campaign_id int8, primary key (id))
alter table campaign add constraint Fk_COMPANY_CAMPAIGN_ID foreign key (company_id) references company
alter table product add constraint Fk_COMPANY_PRODUCT_ID foreign key (company_id) references company
alter table rule add constraint Fk_RULE_TERRITORY_ID foreign key (territory_id) references territory
alter table territory add constraint Fk_CAMPAIGN_TERRITORY_ID foreign key (campaign_id) references campaign
