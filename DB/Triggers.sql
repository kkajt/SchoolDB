DROP TRIGGER IF EXISTS beforeDeleteOnPracownicy;
DELIMITER $$
CREATE TRIGGER beforeDeleteOnPracownicy BEFORE DELETE ON pracownicy FOR EACH ROW
BEGIN
	-- Removing record from table 'wynagrodzenia'.
    DELETE FROM wynagrodzenia 
    WHERE Kod_pracownika = OLD.Kod;
    
    -- Updating table 'przedmioty'.
    UPDATE przedmioty
    SET Kod_nauczyciela = NULL
    WHERE Kod_nauczyciela = OLD.Kod;
    
    -- Deleting usuer from tabler 'uzytkownicy'.
    CALL deleteUser(OLD.kod);
    
    -- Updating table 'klasy'.
    UPDATE klasy
    SET Wychowawca = NULL
    WHERE Wychowawca = OLD.Kod;
    
    
END $$
DELIMITER ;

DROP TRIGGER IF EXISTS afterDeleteOnKlasy;
DELIMITER $$
CREATE TRIGGER afterDeleteOnKlasy AFTER DELETE ON klasy FOR EACH ROW
BEGIN
	DELETE FROM przedmioty
    WHERE Kod_klasy = OLD.Kod;
    
END $$
DELIMITER ;




