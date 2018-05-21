	DELIMITER $$
DROP PROCEDURE IF EXISTS test$$
CREATE PROCEDURE test () 
BEGIN
	
    START TRANSACTION;
    
	DELETE FROM Uczniowie WHERE kod_klasy IN (SELECT  kod FROM Klasy WHERE numer=3);
    DELETE FROM Klasy WHERE numer=3;
    
    UPDATE Klasy
    SET numer = numer+1;
    
    COMMIT;

END; $$
DELIMITER ;




