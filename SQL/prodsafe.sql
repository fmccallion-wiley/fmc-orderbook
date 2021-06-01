-- https://www.mysqltutorial.org/mysql-triggers/working-mysql-scheduled-event/

\! echo $MYSQL_ROOT_PASSWORD >/tmp/pw.txt
set @pw=REPLACE(CONVERT(LOAD_FILE('/tmp/pw.txt') USING utf8),'\n','');

CREATE TABLE `pwu` (
  pwu varchar(255)
);

INSERT INTO pwu VALUES(@pw);

DELIMITER //

CREATE PROCEDURE pwu()
BEGIN
  DECLARE thepw varchar(255);
  SELECT @mypw:= pwu FROM pwu;

  set thepw=IF(STRCMP(@mypw,'secret123') = 0, 'outoforder123', 'secret123');
  set @stm=CONCAT("ALTER USER 'root'@'localhost' IDENTIFIED BY '",thepw,"';");
  prepare stmt from @stm;
  execute stmt;
  deallocate prepare stmt;
  set @stm=CONCAT("ALTER USER 'root'@'%' IDENTIFIED BY '",thepw,"';");
  prepare stmt from @stm;
  execute stmt;
  deallocate prepare stmt;
  FLUSH PRIVILEGES;
  SELECT thepw;
  UPDATE pwu SET pwu=thepw;
  CALL refreshconn();
END //

CREATE PROCEDURE refreshconn()
BEGIN
  DECLARE done TINYINT DEFAULT FALSE;
  DECLARE procid int;
  DECLARE curs CURSOR FOR SELECT id FROM INFORMATION_SCHEMA.PROCESSLIST WHERE Host != 'localhost';
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  OPEN curs;

  my_loop:
  LOOP
    FETCH NEXT FROM curs INTO procid;
    IF done THEN
      LEAVE my_loop;
    ELSE
      KILL procid;
    END IF;
  END LOOP;

  CLOSE curs;
END //

DELIMITER ;

SET GLOBAL event_scheduler = ON;
CREATE EVENT IF NOT EXISTS app1
ON SCHEDULE EVERY 2 MINUTE
STARTS CURRENT_TIMESTAMP
DO
  CALL pwu();
