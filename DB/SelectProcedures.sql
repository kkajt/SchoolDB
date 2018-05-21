DELIMITER $$
DROP PROCEDURE IF EXISTS selectAllStudents$$
CREATE PROCEDURE selectAllStudents ()
BEGIN
		SET @query = CONCAT("SELECT * FROM Uczniowie");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectAllWorkers$$
CREATE PROCEDURE selectAllWorkers ()
BEGIN
		SET @query = CONCAT("SELECT * FROM Pracownicy;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectStudentsReduced$$
CREATE PROCEDURE selectStudentsReduced ()
BEGIN
		SET @query = CONCAT("SELECT imię, nazwisko, K.Numer AS 'Klasa', K.Symbol, K.kod AS 'Kod klasy' FROM Uczniowie U JOIN Klasy K ON U.kod_klasy=K.kod;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectClass$$
CREATE PROCEDURE selectClass (
	IN classcode VARCHAR(4)
    )
BEGIN
		SET @query = CONCAT("SELECT imię AS 'Imię', drugie_imię AS 'Drugie imię', nazwisko AS 'Nazwisko', CONCAT(K.numer, K.symbol) AS 'Klasa', K.kod AS 'Kod klasy' FROM Uczniowie U JOIN Klasy K ON U.kod_klasy=K.kod WHERE U.kod_klasy='",classcode,"';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;


DELIMITER $$
DROP PROCEDURE IF EXISTS selectClassSubjects$$
CREATE PROCEDURE selectClassSubjects (
	IN classcode VARCHAR(4)
    )
BEGIN
		SET @query = CONCAT("SELECT CONCAT(K.numer, K.symbol) AS 'Klasa', P.Nazwa AS 'Przedmiot', CONCAT(Pr.imię, ' ',Pr.nazwisko) AS 'Nauczyciel', P.Kod_nauczyciela AS 'Kod nauczyciela', P.liczba_godzin AS 'Liczba godzin'  FROM Przedmioty P JOIN Klasy K ON P.kod_klasy=K.kod JOIN Pracownicy Pr ON P.kod_nauczyciela = Pr.kod WHERE P.kod_klasy='",classcode,"';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectTeachers$$
CREATE PROCEDURE selectTeachers ()
BEGIN
		SET @query = CONCAT("SELECT * FROM Pracownicy WHERE stanowisko='nauczyciel';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectWorkersExceptTeachers$$
CREATE PROCEDURE selectWorkersExceptTeachers ()
BEGIN
		SET @query = CONCAT("SELECT * FROM Pracownicy WHERE stanowisko<>'nauczyciel';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectClassWithTutors$$
CREATE PROCEDURE selectClassWithTutors ()
BEGIN
		SET @query = CONCAT("SELECT CONCAT(K.numer, K.symbol) AS 'Klasa', K.kod AS 'Kod klasy', CONCAT(P.imię, ' ', P.nazwisko) AS 'Wychowawca', P.kod AS 'Kod wychowawcy' FROM Pracownicy P JOIN Klasy K ON K.wychowawca = P.kod;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectTeacherInfo$$
CREATE PROCEDURE selectTeacherInfo (
	IN teachercode VARCHAR(6)
)
BEGIN
		SET @query = CONCAT("SELECT P.imię AS 'Imię', P.drugie_imię AS 'Drugie_imię', P.nazwisko AS 'Nazwisko', IF(P.kod IN (SELECT wychowawca FROM klasy), 'tak', 'nie') AS 'Wychowawstwo', SUM(Pr.liczba_godzin) AS 'Liczba godzin' FROM Pracownicy P JOIN Przedmioty Pr ON Pr.kod_nauczyciela = P.kod WHERE kod = '",teachercode,"' GROUP BY Pr.kod_nauczyciela;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @query = CONCAT("SELECT Prz.nazwa AS 'Przedmiot', K.nazwa AS 'Klasa', Prz.liczba_godzin AS 'Liczba godzin' FROM Przedmioty Prz JOIN Pracownicy P ON Prz.kod_nauczyciela=P.kod JOIN Klasy K ON K.kod=Prz.kod_klasy WHERE P.kod = '",teachercode,"';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectSalaries$$
CREATE PROCEDURE selectSalaries ()
BEGIN
		SET @query = CONCAT("SELECT CONCAT(P.imię, P.nazwisko) AS 'Pracownik', P.kod AS 'Kod pracownika', W.numer_konta AS 'Numer konta', W.podstawa_brutto AS 'Podstawa brutto' FROM Pracownicy P JOIN Wynagrodzenia W ON P.kod = W.kod_pracownika;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS selectUsers$$
CREATE PROCEDURE selectUsers ()
BEGIN
		SET @query = CONCAT("SELECT * FROM uzytkownicy;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;

