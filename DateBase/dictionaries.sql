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
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('12test', 2015, 1, 1);

INSERT INTO Biblioteka.Autor(Imie, Nazwisko) VALUES('dupa1', 'dupa2');
INSERT INTO Biblioteka.Ksiazka_autor(Ksiazka, Autor, Rodzaj_powiazania) VALUES(1, 1, 1);