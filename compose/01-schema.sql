create database orderbook;

use orderbook;

-- Application create portfolio and user tables

create table `user` (
  userid varchar(255) primary key,
  username varchar(255)
);

create table portfolio (
  portfolioid bigint, -- primary key,
  qty int null,
  symbol varchar(6) null,
  userid varchar(255) null
  -- foreign key(userid) references user(userid)
);

create table `order` (
  orderid varchar(255) primary key,
  symbol varchar(6),
  `type` varchar(10),
  price numeric(8,2),
  qty int,
  userid varchar(255),
  portfolioid bigint null,
  entrydate DATETIME DEFAULT CURRENT_TIMESTAMP()
  -- foreign key(portfolioid) references portfolio(portfolioid),
  -- foreign key(userid) references user(userid)
);

CREATE TABLE `pwu` (
  pwu varchar(255)
);
