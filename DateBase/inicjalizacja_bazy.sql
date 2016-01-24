--tworzenie schematu
CREATE SCHEMA Biblioteka;
COMMENT ON SCHEMA Biblioteka IS 'Namespace for library datebase.';

--emial
--CREATE DOMAIN Biblioteka.email AS TEXT CONSTRAINT valid_value CHECK (VALUE LIKE '.+@.+');
CREATE DOMAIN Biblioteka.email AS TEXT;
COMMENT ON DOMAIN Biblioteka.email IS 'Domain represent valid email.';

--numer telefonu
--CREATE DOMAIN Biblioteka.phone_number AS TEXT CONSTRAINT valid_value CHECK (VALUE LIKE '^[0-9]*$');
CREATE DOMAIN Biblioteka.phone_number AS TEXT;
COMMENT ON DOMAIN Biblioteka.phone_number IS 'Domain represent phone number.';

--rok 1812-3048
--CREATE DOMAIN Biblioteka.year AS TEXT CONSTRAINT valid_value CHECK (VALUE LIKE '^(181[2-9]|18[2-9]\d|19\d\d|2\d{3}|30[0-3]\d|304[0-8])$');
CREATE DOMAIN Biblioteka.year AS TEXT;
COMMENT ON DOMAIN Biblioteka.year IS 'Domain represent valid year from 1812 to 3048.';

--isbn
--CREATE DOMAIN Biblioteka.isbn AS TEXT CONSTRAINT valid_value CHECK (VALUE LIKE '^(?:ISBN(?:-1[03])?:?\ )?(?=[0-9X]{10}$|(?=(?:[0-9]+[-\ ]){3})[-\ 0-9X]{13}$|97[89][0-9]{10}$|(?=(?:[0-9]+[-\ ]){4})[-\ 0-9]{17}$)(?:97[89][-\ ]?)?[0-9]{1,5}[-\ ]?[0-9]+[-\ ]?[0-9]+[-\ ]?[0-9X]$');
CREATE DOMAIN Biblioteka.isbn AS TEXT;
COMMENT ON DOMAIN Biblioteka.isbn IS 'Domain represent isbn-10 and isbn-13 number.';

--url
--CREATE DOMAIN Biblioteka.url AS TEXT CONSTRAINT valid_value CHECK (VALUE LIKE '.+');
CREATE DOMAIN Biblioteka.url AS TEXT;
COMMENT ON DOMAIN Biblioteka.url IS 'Domain represent valid url';

--gwiazdka
CREATE DOMAIN Biblioteka.star AS int CONSTRAINT valid_value CHECK (VALUE >= 0 AND VALUE <=5);
COMMENT ON DOMAIN Biblioteka.star IS 'Domain represent total number of stars';

--tworzenie tabel
CREATE TABLE Biblioteka.Uzytkownik (
	Uzytkownik_ID serial NOT NULL,
	Email Biblioteka.email NOT NULL UNIQUE,
	Haslo TEXT NOT NULL,
	Nazwisko VARCHAR(255) NOT NULL,
	Imie VARCHAR(255) NOT NULL,
	Avatar int,
	Adres int NOT NULL,
	Pwd_Seed TEXT NOT NULL,
	Typ int NOT NULL,
	Telefon_komorkowy Biblioteka.phone_number,
	Telefon_stacjonarny Biblioteka.phone_number,
	Aktywny BOOLEAN NOT NULL DEFAULT TRUE,
	CONSTRAINT Uzytkownik_pk PRIMARY KEY (Uzytkownik_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Ksiazka (
	Ksiazka_ID serial NOT NULL,
	Tytul TEXT NOT NULL,
	Rok_wydania Biblioteka.year NOT NULL,
	ISBN Biblioteka.isbn,
	Avatar int NOT NULL,
	Wydawnictwo int NOT NULL,
	Wypozyczona BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT Ksiazka_pk PRIMARY KEY (Ksiazka_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Adres (
	Adres_ID serial NOT NULL,
	Kod_pocztowy int NOT NULL,
	Ulica VARCHAR(255),
	Numer_domu int NOT NULL CHECK (Numer_domu > 0),
	Numer_mieszkania int CHECK (Numer_mieszkania > 0),
	CONSTRAINT Adres_pk PRIMARY KEY (Adres_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Autor (
	Autor_ID serial NOT NULL,
	Imie VARCHAR(255) NOT NULL,
	Nazwisko VARCHAR(255) NOT NULL,
	Kraj_pochodzenia VARCHAR(255),
	CONSTRAINT Autor_pk PRIMARY KEY (Autor_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Naleznosc (
	Naleznosc_ID serial NOT NULL,
	Ksiazka int,
	Uzytkownik int NOT NULL,
	Wyporzyczona_ksiazka int,
	Opis VARCHAR(80) NOT NULL,
	Wartosc numeric(10,2) NOT NULL CHECK (Wartosc > 0),
	Uiszczona BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT Naleznosc_pk PRIMARY KEY (Naleznosc_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Wypozyczona_ksiazka (
	Uzytkownik int NOT NULL UNIQUE,
	Ksiazka int NOT NULL UNIQUE,
	Data_wypozyczenia DATE NOT NULL,
	Data_oddania DATE NOT NULL,
	CONSTRAINT Wypozyczona_ksiazka_pk PRIMARY KEY (Uzytkownik, Ksiazka),
	CONSTRAINT Poprawne_daty CHECK (Data_wypozyczenia <= Data_oddania)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Wydawnictwo (
	Wydawnictwo_ID serial NOT NULL,
	Nazwa VARCHAR(255) NOT NULL,
	Kraj_pochodzenia VARCHAR(255) DEFAULT 'Polska',
	CONSTRAINT Wydawnictwo_pk PRIMARY KEY (Wydawnictwo_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Avatar (
	Avatar_ID serial NOT NULL,
	Url Biblioteka.url NOT NULL,
	Opis VARCHAR(80),
	CONSTRAINT Avatar_pk PRIMARY KEY (Avatar_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Ksiazka_Autor (
	Ksiazka int NOT NULL,
	Autor int NOT NULL,
	Rodzaj_powiazania int NOT NULL,
	CONSTRAINT Ksiazka_Autor_pk PRIMARY KEY (Ksiazka, Autor)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Poczta (
	Poczta_ID serial NOT NULL,
	Kod_pocztowy VARCHAR(20) NOT NULL,
	Miejscowosc VARCHAR(255) NOT NULL,
	Kraj VARCHAR(255) NOT NULL DEFAULT 'Polska',
	CONSTRAINT Poczta_pk PRIMARY KEY (Poczta_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Rodzaj_powiazania (
	Rodzaj_powiazania_ID serial NOT NULL,
	Nazwa VARCHAR(80) NOT NULL,
	CONSTRAINT Rodzaj_powiazania_pk PRIMARY KEY (Rodzaj_powiazania_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Komentarz (
	Komentarz_ID serial NOT NULL,
	Urzytkownik int NOT NULL,
	Ksiazka int NOT NULL,
	Tekst VARCHAR(255),
	Data TIMESTAMP NOT NULL,
	Ilosc_gwiazdek Biblioteka.star NOT NULL DEFAULT 0,
	CONSTRAINT Komentarz_pk PRIMARY KEY (Komentarz_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Wiadomosc (
	Wiadomosc_ID serial NOT NULL,
	Adresat int NOT NULL,
	Odbiorca int NOT NULL,
	Tekst TEXT NOT NULL,
	Data TIMESTAMP NOT NULL,
	CONSTRAINT Wiadomosc_pk PRIMARY KEY (Wiadomosc_ID)
) WITH (
  OIDS=FALSE
);



CREATE TABLE Biblioteka.Rodzaj_uzytkownika (
	Rodzaj_uzytkownika_ID serial NOT NULL,
	Nazwa TEXT NOT NULL,
	CONSTRAINT Rodzaj_uzytkownika_pk PRIMARY KEY (Rodzaj_uzytkownika_ID)
) WITH (
  OIDS=FALSE
);


--tworzenie relacji
ALTER TABLE Biblioteka.Uzytkownik ADD CONSTRAINT Uzytkownik_fk0 FOREIGN KEY (Avatar) REFERENCES Biblioteka.Avatar(Avatar_ID);
ALTER TABLE Biblioteka.Uzytkownik ADD CONSTRAINT Uzytkownik_fk1 FOREIGN KEY (Adres) REFERENCES Biblioteka.Adres(Adres_ID);
ALTER TABLE Biblioteka.Uzytkownik ADD CONSTRAINT Uzytkownik_fk2 FOREIGN KEY (Typ) REFERENCES Biblioteka.Rodzaj_uzytkownika(Rodzaj_uzytkownika_ID);

ALTER TABLE Biblioteka.Ksiazka ADD CONSTRAINT Ksiazka_fk0 FOREIGN KEY (Avatar) REFERENCES Biblioteka.Avatar(Avatar_ID);
ALTER TABLE Biblioteka.Ksiazka ADD CONSTRAINT Ksiazka_fk1 FOREIGN KEY (Wydawnictwo) REFERENCES Biblioteka.Wydawnictwo(Wydawnictwo_ID);

ALTER TABLE Biblioteka.Adres ADD CONSTRAINT Adres_fk0 FOREIGN KEY (Kod_pocztowy) REFERENCES Biblioteka.Poczta(Poczta_ID);



ALTER TABLE Biblioteka.Naleznosc ADD CONSTRAINT Naleznosc_fk0 FOREIGN KEY (Ksiazka) REFERENCES Biblioteka.Ksiazka(Ksiazka_ID);
ALTER TABLE Biblioteka.Naleznosc ADD CONSTRAINT Naleznosc_fk1 FOREIGN KEY (Uzytkownik) REFERENCES Biblioteka.Uzytkownik(Uzytkownik_ID);

ALTER TABLE Biblioteka.Wypozyczona_ksiazka ADD CONSTRAINT Wypozyczona_ksiazka_fk0 FOREIGN KEY (Uzytkownik) REFERENCES Biblioteka.Uzytkownik(Uzytkownik_ID);
ALTER TABLE Biblioteka.Wypozyczona_ksiazka ADD CONSTRAINT Wypozyczona_ksiazka_fk1 FOREIGN KEY (Ksiazka) REFERENCES Biblioteka.Ksiazka(Ksiazka_ID);



ALTER TABLE Biblioteka.Ksiazka_Autor ADD CONSTRAINT Ksiazka_Autor_fk0 FOREIGN KEY (Ksiazka) REFERENCES Biblioteka.Ksiazka(Ksiazka_ID);
ALTER TABLE Biblioteka.Ksiazka_Autor ADD CONSTRAINT Ksiazka_Autor_fk1 FOREIGN KEY (Autor) REFERENCES Biblioteka.Autor(Autor_ID);
ALTER TABLE Biblioteka.Ksiazka_Autor ADD CONSTRAINT Ksiazka_Autor_fk2 FOREIGN KEY (Rodzaj_powiazania) REFERENCES Biblioteka.Rodzaj_powiazania(Rodzaj_powiazania_ID);



ALTER TABLE Biblioteka.Komentarz ADD CONSTRAINT Komentarz_fk0 FOREIGN KEY (Urzytkownik) REFERENCES Biblioteka.Uzytkownik(Uzytkownik_ID);
ALTER TABLE Biblioteka.Komentarz ADD CONSTRAINT Komentarz_fk1 FOREIGN KEY (Ksiazka) REFERENCES Biblioteka.Ksiazka(Ksiazka_ID);

ALTER TABLE Biblioteka.Wiadomosc ADD CONSTRAINT Wiadomosc_fk0 FOREIGN KEY (Adresat) REFERENCES Biblioteka.Uzytkownik(Uzytkownik_ID);
ALTER TABLE Biblioteka.Wiadomosc ADD CONSTRAINT Wiadomosc_fk1 FOREIGN KEY (Odbiorca) REFERENCES Biblioteka.Uzytkownik(Uzytkownik_ID);


--index on Ksiazka 
--on Tytuł
CREATE INDEX Ksiazka_tytul_idx ON Biblioteka.Ksiazka (Tytul);

--index on Uzytkownik
--on Nazwa
CREATE INDEX Uzytkownik_nazwisko_idx ON Biblioteka.Uzytkownik (Nazwisko);
--on Imie
CREATE INDEX Uzytkownik_imie_idx ON Biblioteka.Uzytkownik (Imie);

--index on Poczta
CREATE INDEX Poczta_kod_pocztowy_idx ON Biblioteka.Poczta (Kod_pocztowy);

--index on Wydawnictwo
--on Nazwa
CREATE INDEX Wydawnictwo_nazwa_idx ON Biblioteka.Wydawnictwo (Nazwa);

--index on Autor
--on Nazwa
CREATE INDEX Autor_nazwisko_idx ON Biblioteka.Autor (Nazwisko);
--on Imie
CREATE INDEX Autor_imie_idx ON Biblioteka.Autor (Imie);

--get avarage star number
CREATE OR REPLACE FUNCTION Biblioteka.avg_star(INT) RETURNS NUMERIC AS '
	SELECT avg(kom.Ilosc_gwiazdek) 
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Komentarz AS kom 
	WHERE k.Ksiazka_ID = kom.Ksiazka
		AND k.Ksiazka_ID = $1;
' LANGUAGE sql;

--get entire debet for user
CREATE OR REPLACE FUNCTION Biblioteka.get_debet() RETURNS NUMERIC(10, 2) AS '
	SELECT sum(n.Wartosc) 
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Naleznosc AS n 
	WHERE n.Naleznosc_ID = u.Uzytkownik_ID;
' LANGUAGE sql;

--search books by title, autor first and last name
CREATE OR REPLACE FUNCTION Biblioteka.search_books(TEXT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 NUMERIC) AS '
	SELECT k.Ksiazka_ID, k.Tytul, av.Url, Biblioteka.avg_star(k.Ksiazka_ID)
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Biblioteka.Ksiazka_Autor AS ka, Biblioteka.Autor AS a 
	WHERE k.Wypozyczona = false 
		AND (av.Avatar_ID = k.Avatar 
			OR ka.Ksiazka = k.Ksiazka_ID 
			OR ka.Autor = a.Autor_ID ) 
		AND (k.Tytul LIKE $1 || ''%''  OR a.Imie LIKE $1 || ''%'' OR a.Nazwisko LIKE $1 || ''%'') 
	ORDER BY k.Tytul;
' LANGUAGE sql;

--get detail information about selected book
CREATE OR REPLACE FUNCTION Biblioteka.detail_book(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 NUMERIC) AS '
	SELECT k.Ksiazka_ID, k.Tytul, k.Rok_wydania, k.isbn, av.Url, Biblioteka.avg_star(k.Ksiazka_ID)
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Biblioteka.Wydawnictwo AS wy 
	WHERE k.Ksiazka_ID = $1 
		AND av.Avatar_ID = k.Avatar 
		AND wy.Wydawnictwo_ID = k.Wydawnictwo;
' LANGUAGE sql; 

--get all authors for selected book
CREATE OR REPLACE FUNCTION Biblioteka.book_authors(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT) AS '
	SELECT a.Autor_ID, a.Imie, a.Nazwisko, a.Kraj_pochodzenia, rp.Nazwa 
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Ksiazka_Autor AS ka, Biblioteka.Autor AS a, Biblioteka.Rodzaj_powiazania AS rp 
	WHERE k.Ksiazka_ID = $1 
		AND ka.Ksiazka = k.Ksiazka_ID 
		AND ka.Autor = a.Autor_ID 
		AND rp.Rodzaj_powiazania_ID = ka.Rodzaj_powiazania 
	ORDER BY a.Nazwisko;
' LANGUAGE sql;

--get all authors for selected book
CREATE OR REPLACE FUNCTION Biblioteka.book_wyd(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT) AS '
	SELECT wy.Wydawnictwo_ID, wy.Nazwa, Kraj_pochodzenia 
	FROM Biblioteka.Wydawnictwo AS wy, Biblioteka.Ksiazka AS k  
	WHERE k.Ksiazka_ID = $1 
		AND k.Wydawnictwo = wy.Wydawnictwo_ID;
' LANGUAGE sql;

--get all books by author
CREATE OR REPLACE FUNCTION Biblioteka.books_by_author(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 NUMERIC) AS '
	SELECT k.Ksiazka_ID, k.Tytul, av.Url, rp.Nazwa, Biblioteka.avg_star(k.Ksiazka_ID) 
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Biblioteka.Ksiazka_Autor AS ka, Biblioteka.Autor AS a, Biblioteka.Rodzaj_powiazania as rp 
	WHERE a.Autor_ID = $1 
		AND av.Avatar_ID = k.Avatar 
		AND ka.Ksiazka = k.Ksiazka_ID 
		AND ka.Autor = a.Autor_ID 
		AND ka.Rodzaj_powiazania = rp.Rodzaj_powiazania_ID 
	ORDER BY k.Tytul;
' LANGUAGE sql;

--get all books by wydawnictwo
CREATE OR REPLACE FUNCTION Biblioteka.books_by_wydawnictwo(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 NUMERIC) AS '
	SELECT k.Ksiazka_ID, k.Tytul, av.Url, Biblioteka.avg_star(k.Ksiazka_ID) 
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Biblioteka.Wydawnictwo AS wy 
	WHERE wy.Wydawnictwo_ID = $1 
		AND av.Avatar_ID = k.Avatar 
		AND wy.Wydawnictwo_ID = k.Wydawnictwo 
	ORDER BY k.Tytul;
' LANGUAGE sql;

--get all comments on selected book
CREATE OR REPLACE FUNCTION Biblioteka.comments_by_book(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT,f4 TEXT, f5 TIMESTAMP) AS '
	SELECT kom.Komentarz_ID, u.Imie, u.Nazwisko, kom.Tekst, kom.Data 
	FROM Biblioteka.Komentarz AS kom, Biblioteka.Ksiazka AS k, Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av 
	WHERE k.Ksiazka_ID = $1 
		AND k.Ksiazka_ID = kom.Ksiazka 
		AND u.Uzytkownik_ID = kom.Urzytkownik 
		AND u.Uzytkownik_ID = av.Avatar_ID 
	ORDER BY kom.Data DESC;
' LANGUAGE sql;

--get all comments by selected user
CREATE OR REPLACE FUNCTION Biblioteka.comments_by_user(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TIMESTAMP) AS '
	SELECT kom.Komentarz_ID, k.Tytul, kom.Tekst, kom.Data  
	FROM Biblioteka.Komentarz AS kom, Biblioteka.Ksiazka AS k, Biblioteka.Uzytkownik AS u 
	WHERE u.Uzytkownik_ID = $1 
		AND k.Ksiazka_ID = kom.Ksiazka 
		AND u.Uzytkownik_ID = kom.Urzytkownik  
	ORDER BY kom.Data DESC;
' LANGUAGE sql;

--get user detail information
CREATE OR REPLACE FUNCTION Biblioteka.user_detail(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 TEXT, f7 INT, f8 INT, f9 TEXT, f10 TEXT, f11 TEXT, f12 TEXT, f13 TEXT, f14 TEXT, f15 BOOLEAN, f16 NUMERIC) AS '
	SELECT u.Uzytkownik_ID, u.Email, u.Nazwisko, u.Imie, av.Url, ad.Ulica, ad.Numer_domu, ad.Numer_mieszkania, p.Kod_pocztowy, p.Miejscowosc, p.Kraj, 
		ty.Nazwa, u.Telefon_komorkowy, u.Telefon_stacjonarny, u.Aktywny, Biblioteka.get_debet() 
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av, Biblioteka.Adres AS ad, Biblioteka.Poczta AS p, Biblioteka.Rodzaj_uzytkownika AS ty 
	WHERE u.Uzytkownik_ID = $1 
		AND u.Typ = ty.Rodzaj_uzytkownika_ID 
		AND u.Adres = ad.Adres_ID 
		AND u.Avatar = av.Avatar_ID 
		AND ad.Kod_pocztowy = p.Poczta_ID
' LANGUAGE sql;

--get all boos by user
CREATE OR REPLACE FUNCTION Biblioteka.books_by_user(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 TEXT, f7 NUMERIC, f8 TEXT, f9 TEXT, f10 TEXT) AS '
	SELECT Biblioteka.detail_book(k.Ksiazka_ID)
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Uzytkownik AS u, Biblioteka.Wypozyczona_ksiazka AS wk 
	WHERE u.Uzytkownik_ID = $1 
		AND wk.Uzytkownik = u.Uzytkownik_ID 
		AND wk.Ksiazka = k.Ksiazka_ID
	ORDER BY k.Tytul;
' LANGUAGE sql;

CREATE OR REPLACE FUNCTION Biblioteka.debet_by_user(INT) RETURNS TABLE(f1 INT, f2 INT, f3 TEXT, f4 TEXT, f5 NUMERIC(10, 2)) AS '
	SELECT n.Naleznosc_ID, k.Ksiazka_ID, k.Tytul, n.Opis, n.Wartosc 
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Ksiazka AS k, Biblioteka.Naleznosc AS n 
	WHERE u.Uzytkownik_ID = $1 
		AND u.Uzytkownik_ID = n.Uzytkownik  
		OR k.Ksiazka_ID = n.Ksiazka 
	ORDER BY n.Wartosc DESC;
' LANGUAGE sql;

CREATE OR REPLACE FUNCTION Biblioteka.login_user(TEXT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 TEXT) AS '
	SELECT u.Uzytkownik_ID, u.Email, u.Haslo, u.Pwd_seed, ru.Nazwa, av.Url 
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av, Biblioteka.Rodzaj_uzytkownika AS ru 
	WHERE u.Email = $1 AND u.Aktywny = true;
' LANGUAGE sql;

--wiadomosc
CREATE FUNCTION Biblioteka.validate_message() RETURNS TRIGGER AS '
	DECLARE
		o timestamp;
		ct timestamp;
	BEGIN
		o := old.Data;
		ct := (SELECT now());
		IF ((TG_OP = ''UPDATE'') AND (ct - o < 300)) THEN
			RAISE NOTICE ''Update is avaliable.'';
			RETURN NEW;
		ELSEIF ((TG_OP = ''DELETE'') AND (ct - o < 300)) THEN
			RAISE NOTICE ''Delete is avaliable.'';
			RETURN OLD;
		ELSE
			RAISE NOTICE ''Record connot be changed.'';
			RETURN NULL;
		END IF;
	END;
' LANGUAGE plpgsql;


--ksiazka
CREATE FUNCTION Biblioteka.validate_book() RETURNS TRIGGER AS '
	DECLARE
		bookId integer;
	BEGIN
		bookId := old.Ksiazka_ID;
		IF (TG_OP = ''DELETE'') THEN
			DELETE FROM Biblioteka.Komentarz AS k WHERE k.Ksiazka = bookId;
			DELETE FROM Biblioteka.Ksiazka_Autor AS ka WHERE ka.Ksiazka = bookId;
			RAISE NOTICE ''Relations removed.'';
			RETURN OLD;   
		END IF;
	END;
' LANGUAGE plpgsql;


--autor
CREATE FUNCTION Biblioteka.validate_author() RETURNS TRIGGER AS '
	DECLARE
		authorId integer;
	BEGIN
		authorId := old.Autor_ID;
		IF (TG_OP = ''DELETE'') THEN
			DELETE FROM Biblioteka.Ksiazka_Autor AS ka WHERE ka.Autor = authorId;
			RAISE NOTICE ''Relations removed.'';
			RETURN OLD;   
		END IF;
	END;
' LANGUAGE plpgsql;


--wypozyczona_ksiazka
CREATE FUNCTION Biblioteka.validate_borrowed_book() RETURNS TRIGGER AS '
	DECLARE
		bookId integer;
	BEGIN
		IF (TG_OP = ''INSERT'') THEN
			bookId := new.Ksiazka;
			UPDATE Biblioteka.Ksiazka AS k SET k.Wypozyczona = TRUE WHERE k.Ksiazka_ID = bookId;
			RAISE NOTICE ''Status changed.'';
			RETURN OLD;  
		ELSEIF (TG_OP = ''DELETE'') THEN
			bookId := old.Ksiazka;
			UPDATE Biblioteka.Ksiazka AS k SET k.Wypozyczona = TRUE WHERE k.Ksiazka_ID = bookId;
			RAISE NOTICE ''Status changed.'';
			RETURN OLD;  
		END IF;
	END;
' LANGUAGE plpgsql;


--uzytkownik
CREATE FUNCTION Biblioteka.vlidate_user() RETURNS TRIGGER AS '
	DECLARE
		oStatus boolean;
		nStatus boolean;
		userId integer;
		postCode integer;
		street varchar(255);
		homeNo integer;
		flatNo integer;
		adressID integer;
	BEGIN
		IF (TG_OP = ''UPDATE'') THEN
			oStatus := old.Aktywny;
			nStatus := new.Aktywny;
			userId := old.Uzytkownik_ID;
			IF (oStatus != nStatus) THEN
				IF (EXISTS (SELECT n.Naleznosc_ID FROM Biblioteka.Naleznosc AS n WHERE n.Uzytkownik = userId)) THEN
					RETURN NULL;
				ELSEIF (EXISTS (SELECT * FROM Wypozyczona_ksiazka AS wk WHERE wk.Uzytkownik = userId)) THEN
					RETURN NULL;
				ELSE
					RETURN NEW;
				END IF;
			ELSE
				RETURN NEW;
			END IF;
		ELSEIF (TG_OP = ''INSERT'') THEN

			SELECT at.Kod_pocztowy INTO postCode FROM Adres_temp AS at;
			SELECT at.Ulica INTO street FROM Adres_temp AS at;
			SELECT at.Numer_domu INTO homeNo FROM Adres_temp AS at;
			SELECT at.Numer_mieszkania INTO flatNo FROM Adres_temp AS at;

			INSERT INTO Biblioteka.Adres (Kod_pocztowy, Ulica, Numer_domu, Numer_mieszkania) VALUES (postCode, street, homeNo, flatNo);
			
			SELECT a.Adres_ID INTO adressID FROM Biblioteka.Adres AS a 
				WHERE a.Kod_pocztowy = postCode AND a.Ulica = street AND a.Numer_domu = homeNo AND a.Numer_mieszkania = flatNo;
			new.Adres := adressID;
			RETURN NEW;
		END IF; 
	END;
' LANGUAGE plpgsql;



CREATE TRIGGER Uzytkownik_trigger BEFORE UPDATE OR INSERT ON Biblioteka.Uzytkownik FOR EACH ROW EXECUTE PROCEDURE Biblioteka.vlidate_user();
CREATE TRIGGER Wiadomosc_trigger BEFORE UPDATE OR DELETE ON Biblioteka.Wiadomosc FOR EACH ROW EXECUTE PROCEDURE Biblioteka.validate_message();
CREATE TRIGGER Wypozyczona_ksiazka_trigger BEFORE INSERT OR DELETE ON Biblioteka.Wypozyczona_ksiazka FOR EACH ROW EXECUTE PROCEDURE Biblioteka.validate_borrowed_book();
CREATE TRIGGER Ksiazka_trigger BEFORE DELETE ON Biblioteka.Ksiazka FOR EACH ROW EXECUTE PROCEDURE Biblioteka.validate_book();
CREATE TRIGGER Autor_trigger BEFORE DELETE ON Biblioteka.Autor FOR EACH ROW EXECUTE PROCEDURE Biblioteka.validate_author();

--temporary table for adres used in user inser trigger
CREATE TEMPORARY TABLE IF NOT EXISTS Adres_temp (LIKE Biblioteka.Adres INCLUDING DEFAULTS);

CREATE VIEW Biblioteka.all_books AS 
	SELECT k.Ksiazka_ID, k.Tytul, a.Url, Biblioteka.avg_star(k.Ksiazka_ID) 
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS a 
	WHERE k.Wypozyczona = false AND a.Avatar_ID = k.Avatar;

CREATE VIEW Biblioteka.all_users AS 
	SELECT u.Uzytkownik_ID, u.Email, u.Nazwisko, u.Imie, av.Url, ty.Nazwa, u.Aktywny
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av, Biblioteka.Rodzaj_uzytkownika AS ty 
	WHERE u.Typ = ty.Rodzaj_uzytkownika_ID 
		AND u.Avatar = av.Avatar_ID 
	ORDER BY u.Nazwisko;

--Rodzaj_uzytkownika
INSERT INTO Biblioteka.Rodzaj_uzytkownika (Nazwa) VALUES ('ADMIN');
INSERT INTO Biblioteka.Rodzaj_uzytkownika (Nazwa) VALUES ('BIBLIOTEKARZ');
INSERT INTO Biblioteka.Rodzaj_uzytkownika (Nazwa) VALUES ('UZYTKOWNIK');

--Rodzaj_powiazania
INSERT INTO Biblioteka.Rodzaj_powiazania (Nazwa) VALUES ('AUTOR');
INSERT INTO Biblioteka.Rodzaj_powiazania (Nazwa) VALUES ('WSPOLAUTOR');


--data
INSERT INTO Biblioteka.Wydawnictwo(Nazwa) VALUES('Test Wydawnictwo');
INSERT INTO Biblioteka.Avatar(Url) VALUES('test.pl');
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);

INSERT INTO Biblioteka.Autor(Imie, Nazwisko) VALUES('dupa1', 'dupa2');
INSERT INTO Biblioteka.Ksiazka_autor(Ksiazka, Autor, Rodzaj_powiazania) VALUES(1, 1, 1);