create database orderbook;

use orderbook;

create table `order` (
  orderid varchar(255),
  symbol varchar(255),
  type varchar(255),
  price varchar(255),
  qty varchar(255),
  userid varchar(255),
  portfolioid varchar(255)
);
-- Application create portfolio and user tables
/*
create table portfolio (
  portfolio int,
  userid int,
  symbol varchar(4),
  qty int
);
*/
