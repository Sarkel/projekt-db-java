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
			UPDATE Biblioteka.Ksiazka SET Wypozyczona = TRUE WHERE Ksiazka_ID = bookId;
			RAISE NOTICE ''Status changed.'';
			RETURN OLD;  
		ELSEIF (TG_OP = ''DELETE'') THEN
			bookId := old.Ksiazka;
			UPDATE Biblioteka.Ksiazka SET Wypozyczona = TRUE WHERE Ksiazka_ID = bookId;
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
			RETURN NEW;
		END IF; 
	END;
' LANGUAGE plpgsql;



CREATE TRIGGER Uzytkownik_trigger BEFORE UPDATE OR INSERT ON Biblioteka.Uzytkownik FOR EACH ROW EXECUTE PROCEDURE Biblioteka.vlidate_user();
CREATE TRIGGER Wiadomosc_trigger BEFORE UPDATE OR DELETE ON Biblioteka.Wiadomosc FOR EACH ROW EXECUTE PROCEDURE Biblioteka.validate_message();
CREATE TRIGGER Wypozyczona_ksiazka_trigger BEFORE INSERT OR DELETE ON Biblioteka.Wypozyczona_ksiazka FOR EACH ROW EXECUTE PROCEDURE Biblioteka.validate_borrowed_book();
CREATE TRIGGER Ksiazka_trigger BEFORE DELETE ON Biblioteka.Ksiazka FOR EACH ROW EXECUTE PROCEDURE Biblioteka.validate_book();
CREATE TRIGGER Autor_trigger BEFORE DELETE ON Biblioteka.Autor FOR EACH ROW EXECUTE PROCEDURE Biblioteka.validate_author();