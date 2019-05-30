CREATE DATABASE IF NOT EXISTS querydsl_demo;

use querydsl_demo;

create table user (
  id int primary key ,
  name varchar(50) not null
) ENGINE=InnoDB default charset=utf8;