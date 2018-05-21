CREATE TABLE IF NOT EXISTS Pracownicy(
	Kod CHAR(6) NOT NULL,
	PESEL CHAR(11) NOT NULL,
	Imię VARCHAR(20) NOT NULL,
	Drugie_imię VARCHAR(20),
	Nazwisko VARCHAR(30) NOT NULL,
    Data_urodzenia DATE NOT NULL,
	Adres VARCHAR(30) NOT NULL,
	Kod_pocztowy CHAR(6) NOT NULL,
	Miasto VARCHAR(20) NOT NULL,
	Numer_konta CHAR(26),
	Email VARCHAR(30),
	Stanowisko VARCHAR(20), 		
	Telefon_kontaktowy CHAR(9),
	PRIMARY KEY(Kod),					
	CONSTRAINT validate_negvalues CHECK (PESEL >= 0),
    CONSTRAINT validate_pesel CHECK (
        SUBSTRING(PESEL, 1, 2) = SUBSTRING(YEAR(data_urodzenia), 3, 2) AND
        SUBSTRING(PESEL, 5, 2) = DAY(data_urodzenia) AND
        ((SUBSTRING(PESEL, 3, 2) = MONTH(data_urodzenia) AND (CAST(YEAR(data_urodzenia) AS unsigned) <= 1999))
        OR
        (SUBSTRING(PESEL, 3, 2) = MONTH(data_urodzenia)+20 AND (CAST(YEAR(data_urodzenia) AS unsigned) >= 2000 )))
        )
);

CREATE TABLE IF NOT EXISTS Uzytkownicy(
	LOGIN VARCHAR(15) NOT NULL,
	Kod_pracownika CHAR(6) NOT NULL,
	Uprawnienia ENUM('administrator', 'dyrektor', 'sekretariat', 'nauczyciel') NOT NULL,
	FOREIGN KEY (Kod_pracownika)
		REFERENCES Pracownicy(Kod),
	PRIMARY KEY (LOGIN)
);


CREATE TABLE IF NOT EXISTS Wynagrodzenia(
	Kod_pracownika CHAR(6) NOT NULL,
    Numer_konta CHAR(26),
    Podstawa_brutto DECIMAL(10,2),
    PRIMARY KEY(Kod_pracownika),
    FOREIGN KEY(Kod_pracownika)
		REFERENCES Pracownicy(Kod)
);

CREATE TABLE IF NOT EXISTS Przedmioty(
	Nazwa VARCHAR(30) NOT NULL,
    Kod_klasy VARCHAR(4) NOT NULL,
    Kod_nauczyciela CHAR(6),
    Liczba_godzin INTEGER NOT NULL,
    PRIMARY KEY(Nazwa, Kod_klasy),
    FOREIGN KEY(Kod_nauczyciela)
		REFERENCES Pracownicy(Kod)
);

CREATE TABLE IF NOT EXISTS Klasy(
	Kod VARCHAR(4) NOT NULL,
    Numer INTEGER,
	Symbol VARCHAR(3) NOT NULL,
    Wychowawca VARCHAR(6),
    CONSTRAINT validate_classnum CHECK (Numer = 1 OR Numer = 2 OR Numer = 3),
    PRIMARY KEY(Kod),
    FOREIGN KEY(Wychowawca)
    REFERENCES Pracownicy(Kod)
);

CREATE TABLE IF NOT EXISTS Uczniowie(
	Kod CHAR(6) NOT NULL,
    PESEL CHAR(11) NOT NULL,
    Imię VARCHAR(20) NOT NULL,
    Drugie_imię VARCHAR(20),
	Nazwisko VARCHAR(30) NOT NULL,
    Data_urodzenia DATE NOT NULL,
    Imię_ojca VARCHAR(20),
    Imię_matki VARCHAR(20),
	Adres VARCHAR(30) NOT NULL,
	Kod_pocztowy CHAR(6) NOT NULL,
	Miasto VARCHAR(20) NOT NULL,
	Telefon_kontaktowy CHAR(9),
    Kod_klasy VARCHAR(4),
    PRIMARY KEY(Kod),
    FOREIGN KEY (Kod_klasy)
		REFERENCES Klasy(Kod),
	CONSTRAINT validate_negvalues CHECK (PESEL >= 0), #?
	CONSTRAINT validate_pesel CHECK (
        SUBSTRING(PESEL, 1, 2) = SUBSTRING(YEAR(data_urodzenia), 3, 2) AND
        SUBSTRING(PESEL, 5, 2) = DAY(data_urodzenia) AND
        ((SUBSTRING(PESEL, 3, 2) = MONTH(data_urodzenia) AND (CAST(YEAR(data_urodzenia) AS unsigned) <= 1999))
        OR
        (SUBSTRING(PESEL, 3, 2) = MONTH(data_urodzenia)+20 AND (CAST(YEAR(data_urodzenia) AS unsigned) >= 2000 )))
        )
);

