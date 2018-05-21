DELIMITER $$
DROP PROCEDURE IF EXISTS createUser$$
CREATE PROCEDURE createUser (
    IN usrname VARCHAR(10),
    IN usrpass VARCHAR(20),
    IN usrcode VARCHAR(6),
    IN usrprivileges VARCHAR(15))
BEGIN
	
    SET @query = CONCAT("CREATE USER '",usrname,"'@'localhost';");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	
    SET @query = CONCAT("SET PASSWORD FOR '",usrname,"'@'localhost'=PASSWORD('",usrpass,"');");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    
    IF (usrprivileges = 'administrator') THEN 
    SET @query = CONCAT("GRANT administrator TO '", usrname ,"'@'localhost';");
    ELSEIF (usrprivileges = 'dyrektor') THEN
    SET @query = CONCAT("GRANT dyrektor TO '", usrname ,"'@'localhost';");
    ELSEIF (usrprivileges = 'sekretariat') THEN
    SET @query = CONCAT("GRANT sekretariat TO '", usrname ,"'@'localhost';");
    ELSEIF (usrprivileges = 'nauczyciel') THEN
    SET @query = CONCAT("GRANT nauczyciel TO '", usrname ,"'@'localhost';");
    END IF;
    
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
    
	SET @query = CONCAT("SET DEFAULT ROLE ",usrprivileges," FOR '",usrname,"'@'localhost';");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;  
    
     SET @query = CONCAT("FLUSH PRIVILEGES;");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;   
    
        
     SET @query = CONCAT("INSERT INTO uzytkownicy VALUES('",usrname,"','",usrcode,"','",usrprivileges,"');");
	PREPARE stmt FROM @query;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;   
    
END $$
DELIMITER ;


-- delete usr, modify usr
DELIMITER $$
DROP PROCEDURE IF EXISTS modifyUser$$
CREATE PROCEDURE modifyUser (
    IN usrlogin VARCHAR(10),
    IN coltochange VARCHAR(20),
    IN newval VARCHAR(20)
    )
BEGIN
	IF (coltochange = 'LOGIN') THEN
		SET @query = CONCAT("RENAME USER '",usrlogin,"'@'localhost' TO '", newval ,"'@'localhost';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        SET @query = CONCAT("UPDATE uzytkownicy SET LOGIN='",newval,"' WHERE LOGIN='",usrlogin,"';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
	ELSEIF (coltochange = 'Uprawnienia') THEN
		SET @query = CONCAT("GRANT ", newval ," TO'", usrlogin ,"'@'localhost';");

		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		
		SET @query = CONCAT("SET DEFAULT ROLE ", newval ," FOR '",usrlogin,"'@'localhost';");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		
		SET @query = CONCAT("FLUSH PRIVILEGES;");
		PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
		SET @query = CONCAT("UPDATE uzytkownicy SET uprawnienia='",newval,"' WHERE LOGIN='",usrlogin,"';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
	ELSEIF(coltochange = 'Haslo') THEN
		SET @query = CONCAT("SET PASSWORD FOR '",usrlogin,"'@'localhost' =PASSWORD('", newval ,"');");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
	END IF;
    
        
END $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS deleteUser$$
CREATE PROCEDURE deleteUser (
    IN usrname VARCHAR(10)
)
BEGIN
		SET @query = CONCAT("DROP USER '",usrname,"'@'localhost';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
        
        SET @query = CONCAT("DELETE FROM uzytkownicy WHERE LOGIN='",usrname,"';");
        PREPARE stmt FROM @query;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
END $$
DELIMITER ;