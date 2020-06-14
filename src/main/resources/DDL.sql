create database AUTO_RIA;

create table users
(
    id         serial  PRIMARY KEY,
    email VARCHAR(50) UNIQUE,
    password VARCHAR NOT NULL DEFAULT '123',
    user_role  VARCHAR(15),
    user_status     VARCHAR(10)
);

create table search_history
(
    id         serial  PRIMARY KEY,
    email VARCHAR(50) ,
    car_body int ,
    car_brand  int,
    car_model    int
);