SET AUTOCOMMIT=0;
-- Inserting values into table "pracownicy".
INSERT INTO pracownicy VALUES ('P00001','78081908963', 'Jan', 'Paweł', 'Kowalski', '1978-08-19', 'Piłsudskiego 2/11', '55-200' ,'Oława',
								'00000000000000000000000001','jp@op.pl', 'nauczyciel', '666666666');
INSERT INTO pracownicy VALUES ('P00002','86022608965', 'Maciej', 'Paweł', 'Nowak', '1986-02-26', 'Leśna 2/3', '56-201' ,'Wrocław',
								'00000000000000000000000002','mn@op.pl', 'nauczyciel', '555555555');
INSERT INTO pracownicy VALUES ('P00003','53093008965', 'Paweł', 'Maciej', 'Kowalkowski', '1953-09-30', 'Wyspiańskiego 2/3', '55-200' ,'Oława',
								'00000000000000000000000003','pkmlsr@op.pl', 'nauczyciel', '444444444');
INSERT INTO pracownicy VALUES ('P00004','54093008965', 'Anna', 'Maciej', 'Kowalska', '1954-09-30', 'Wyspiańskiego 2/3', '55-200' ,'Oława',
								'00000000000000000000000004','pkmlsr@op.pl', 'sekretarka', '444444444');
                                
-- Inserting values into table 'klasy'.
INSERT INTO klasy VALUES ('GF4S','1','A', 'P00001');
INSERT INTO klasy VALUES ('GR2R','2','A', 'P00002');
INSERT INTO klasy VALUES ('SF32','3','A', 'P00003');

-- Inserting values into table 'przedmioty'.
INSERT INTO przedmioty VALUES ('Bazy danych', 'GF4S', 'P00001', 6);
INSERT INTO przedmioty VALUES ('Przyroda', 'GF4S', 'P00002', 6);
INSERT INTO przedmioty VALUES ('Analiza maetmatyczna', 'SF32', 'P00003', 6);

-- Inserting values into table 'uzytkownicy'.
INSERT INTO uzytkownicy VALUES ('P00001', 'P00001', 'administrator');
INSERT INTO uzytkownicy VALUES ('P00002', 'P00002', 'dyrektor');
INSERT INTO uzytkownicy VALUES ('P00003', 'P00003', 'nauczyciel');
INSERT INTO uzytkownicy VALUES ('P00004', 'P00004', 'sekretariat');

-- Inserting values into table 'wynagrodzenia'.
INSERT INTO wynagrodzenia VALUES ('P00001', '00000000000000000000000001', '2137.0');
INSERT INTO wynagrodzenia VALUES ('P00002', '00000000000000000000000002', '1234.0');
INSERT INTO wynagrodzenia VALUES ('P00003', '00000000000000000000000003', '4496.0');
INSERT INTO wynagrodzenia VALUES ('P00004', '00000000000000000000000004', '3237.0');

                                
-- Inserting values into table 'uczniowie'.
INSERT INTO uczniowie VALUES ('U00001', '99010100000', 'Piotr', 'Robert', 'Kowalkowski', '1999-01-01', 'Paweł', 'Małgorzata', 
								'Szkolna 21/37', '55-200', 'Oława', '999999999', 'GF4S');
INSERT INTO uczniowie VALUES ('U00002', '99020200000', 'Jan', 'Maciej', 'Zawadzki', '1999-02-02', 'Maciej', 'Małgorzata', 
								'Wittiga 21/37', '55-200', 'Oława', '888888888', 'GR2R');
INSERT INTO uczniowie VALUES ('U00003', '99030300000', 'Michał', 'Piotr', 'Stary', '1999-03-03', 'Paweł', 'Małgorzata', 
								'Szkolna 21/37', '55-200', 'Oława', '666666666', 'SF32');
INSERT INTO uczniowie VALUES ('U00004', '99040400000', 'Adam', 'Piotr', 'Nowy', '1999-04-04', 'Paweł', 'Małgorzata', 
								'Szkolna 21/37', '55-200', 'Oława', '666666666', 'SF32');
