use orderbook;

INSERT INTO portfolio
select @rownum := @rownum +1  as portfolioid, sum(qty),symbol,userid from `order` cross join (select @rownum := 0) r GROUP BY symbol,userid;

UPDATE `order` LEFT JOIN portfolio ON `order`.userid = portfolio.userid and `order`.symbol = portfolio.symbol SET `order`.portfolioid=portfolio.portfolioid;

ALTER TABLE portfolio ADD PRIMARY KEY(portfolioid);
