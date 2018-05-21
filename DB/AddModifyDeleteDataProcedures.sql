DELIMITER $$
DROP PROCEDURE IF EXISTS addStudent$$
CREATE PROCEDURE addStudent (
	IN PESEL CHAR(11),
	IN imię VARCHAR(20),
	IN drugie_imię VARCHAR(20),
	IN nazwisko VARCHAR(30 ),
    IN data_urodzenia DATE,
	IN imię_ojca VARCHAR(20),
    IN imię_matki VARCHAR(20),
	IN adres VARCHAR(30),
	IN kod_pocztowy CHAR(6),
	IN miasto VARCHAR(20),
	IN telefon_kontaktowy CHAR(9),
    IN kod_klasy VARCHAR(4)) 
BEGIN
		SET @num = '0123456789';
		SET @x =0;
		WHILE @x = 0 DO
			SET @code = CONCAT("U", SUBSTRING(@num, FLOOR(RAND()*9)+1,1), SUBSTRING(@num, FLOOR(RAND()*9)+1,1), SUBSTRING(@num, FLOOR(RAND()*9)+1,1), SUBSTRING(@num, FLOOR(RAND()*9)+1,1), SUBSTRING(@num, FLOOR(RAND()*9)+1,1));
            IF ( @code NOT IN (SELECT kod FROM Uczniowie) ) THEN
				SET @x = 1;
			END IF;
        END WHILE;
		
		SET @result = CONCAT("INSERT INTO Uczniowie VALUES ('",@code,"','",PESEL,"','", imię,"','",drugie_imię,"','",nazwisko,"','",data_urodzenia,"','",imię_ojca,"','",imię_matki,"','",adres,"','",
        kod_pocztowy,"','",miasto,"','",telefon_kontaktowy,"','",kod_klasy,"');");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS addWorker$$
CREATE PROCEDURE addWorker (
	IN PESEL CHAR(11),
	IN imię VARCHAR(20),
	IN drugie_imię VARCHAR(20),
	IN nazwisko VARCHAR(30 ),
    IN data_urodzenia DATE,
	IN adres VARCHAR(30),
	IN kod_pocztowy CHAR(6),
	IN miasto VARCHAR(20),
	IN numer_konta CHAR(26),
	IN email VARCHAR(30),
	IN stanowisko VARCHAR(20), 		
	IN telefon_kontaktowy CHAR(9),
    IN podstawa_brutto DECIMAL(10,2)) 
BEGIN
		SET @num = '0123456789';
		SET @x = 0;
		WHILE @x = 0 DO
			SET @code = CONCAT("P", SUBSTRING(@num, FLOOR(RAND()*9)+1,1), SUBSTRING(@num, FLOOR(RAND()*9)+1,1), SUBSTRING(@num, FLOOR(RAND()*9)+1,1), SUBSTRING(@num, FLOOR(RAND()*9)+1,1), SUBSTRING(@num, FLOOR(RAND()*9)+1,1));
            IF ( @code NOT IN (SELECT kod FROM Pracownicy) ) THEN
				SET @x = 1;
			END IF;
        END WHILE;
		
		SET @result = CONCAT("INSERT INTO Pracownicy VALUES ('",@code,"','",PESEL,"','", imię,"','",drugie_imię,"','",nazwisko,"','",data_urodzenia,"','",adres,"','",
        kod_pocztowy,"','",miasto,"','",numer_konta,"','",email,"','",stanowisko,"','",telefon_kontaktowy,"');");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
        SET @result = CONCAT("INSERT INTO Wynagrodzenia VALUES ('",@code,"','",numer_konta,"','",podstawa_brutto,"');");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS addSubject$$
CREATE PROCEDURE addSubject (
	IN nazwa VARCHAR(30),
    IN nazwa_klasy VARCHAR(4),
    IN kod_nauczyciela CHAR(6),
    IN liczba_godzin INTEGER) 
BEGIN
		SET @result = CONCAT("INSERT INTO Przedmioty VALUES ('",nazwa,"', '",nazwa_klasy,"', '", kod_nauczyciela,"', ",liczba_godzin,");");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS addClass$$
CREATE PROCEDURE addClass (
	IN nazwa VARCHAR(4),
    IN wychowawca VARCHAR(6)) 
BEGIN
		SET @char = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        SET @x = 0;
        WHILE @x = 0 DO
			SET @code = CONCAT(SUBSTRING(@char, FLOOR(RAND()*35)+1,1), SUBSTRING(@char, FLOOR(RAND()*35)+1,1), SUBSTRING(@char, FLOOR(RAND()*35)+1,1), SUBSTRING(@char, FLOOR(RAND()*35)+1,1));
            IF ( @code NOT IN (SELECT kod FROM Klasy) ) THEN
				SET @x = 1;
			END IF;
        END WHILE;
        SET @classnum = SUBSTRING(nazwa,1,1);
        SET @classsym = SUBSTRING(nazwa,2,3);
		SET @result = CONCAT("INSERT INTO Klasy VALUES ('",@code,"',",@classnum,", '",@classsym,"', '",wychowawca,"');");
		PREPARE stmt2 FROM @result;
		EXECUTE stmt2;
		DEALLOCATE PREPARE stmt2;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS modifyStudent$$
CREATE PROCEDURE modifyStudent (
    IN kod VARCHAR(6),
    IN kol VARCHAR(20),
    IN newval VARCHAR(30))
BEGIN
		
        IF (kol = "data_urodzenia") THEN
			SET @val = CAST(newval AS date);
		ELSE SET @val = newval;
		END IF;
		SET @result = CONCAT("UPDATE Uczniowie SET ",kol,"='",@val,"' WHERE kod ='",kod,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS modifyWorker$$
CREATE PROCEDURE modifyWorker (
    IN kod VARCHAR(6),
    IN kol VARCHAR(20),
    IN newval VARCHAR(30))
BEGIN
        IF (kol = "data_urodzenia") THEN
			SET @val = CAST(newval AS DATE);
            SET @result = CONCAT("UPDATE Pracownicy SET ",kol,"=",@val," WHERE kod ='",kod,"';");
		ELSEIF (kol = "podstawa_brutto") THEN
			SET @val = CONVERT(newval, DECIMAL(10,2));
            SET @result = CONCAT("UPDATE Wynagrodzenia SET podstawa_brutto=",@val," WHERE kod_pracownika ='",kod,"';");
		ELSE 
			SET @val = newval;
			SET @result = CONCAT("UPDATE Pracownicy SET ",kol,"='",@val,"' WHERE kod ='",kod,"';");
		END IF;
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS modifySubject$$
CREATE PROCEDURE modifySubject (
    IN pkey1 VARCHAR(30),
    IN pkey2 VARCHAR(4),
    IN col VARCHAR(30),
    IN newval VARCHAR(30))
BEGIN
        IF (col = "liczba_godzin") THEN
			SET @val = CAST(newval AS UNSIGNED);
		ELSE 
			SET @val = newval;
		END IF;
		SET @result = CONCAT("UPDATE Przedmioty SET ",col,"='",@val,"' WHERE  nazwa='",pkey1,"' AND nazwa_klasy='",pkey2,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS modifyClass$$
CREATE PROCEDURE modifyClass (
    IN pkey VARCHAR(4),
    IN col VARCHAR(30),
    IN newval VARCHAR(30))
BEGIN

		SET @val = newval;
		SET @result = CONCAT("UPDATE Klasy SET ",col,"='",@val,"' WHERE kod='",pkey,"';");

		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteStudent$$
CREATE PROCEDURE deleteStudent (
    IN kod VARCHAR(6))
BEGIN
		SET @result = CONCAT("DELETE FROM Uczniowie WHERE kod='",kod,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteWorker$$
CREATE PROCEDURE deleteWorker (
    IN kod VARCHAR(6))
BEGIN
		SET @result = CONCAT("DELETE FROM Pracownicy WHERE kod='",kod,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;



DELIMITER $$
DROP PROCEDURE IF EXISTS deleteClass$$
CREATE PROCEDURE deleteClass (
    IN pkey VARCHAR(4))
BEGIN
		SET @result = CONCAT("DELETE FROM Klasy WHERE kod='",pkey,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteSubject$$
CREATE PROCEDURE deleteSubject (
    IN nazwa VARCHAR(30),
    IN nazwa_klasy VARCHAR(4))
BEGIN
		SET @result = CONCAT("DELETE FROM Przedmioty WHERE Nazwa='",nazwa,"' AND Kod_klasy='",nazwa_klasy,"';");
		PREPARE stmt FROM @result;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;
