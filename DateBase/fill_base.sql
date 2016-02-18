INSERT INTO Biblioteka.Wydawnictwo(Nazwa) VALUES('Test Wydawnictwo');
INSERT INTO Biblioteka.Avatar(Url) VALUES('test.pl');
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('test', 2015, 1, 1);
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, Avatar, Wydawnictwo) VALUES('12test', 2015, 1, 1);

INSERT INTO Biblioteka.Autor(Imie, Nazwisko) VALUES('testt1', 'testt2');
INSERT INTO Biblioteka.Ksiazka_autor(Ksiazka, Autor, Rodzaj_powiazania) VALUES(1, 1, 1);
INSERT INTO Biblioteka.Wydawnictwo(Nazwa) VALUES('test');
INSERT INTO Biblioteka.Avatar(Url) VALUES('test');
INSERT INTO Biblioteka.Ksiazka(Tytul, Rok_wydania, ISBN, Avatar, Wydawnictwo) VALUES('test', 2015, 'test', 1, 1);
INSERT INTO Biblioteka.Poczta(Kod_pocztowy, Miejscowosc) VALUES('test', 'test');
INSERT INTO Biblioteka.Adres(Kod_pocztowy, Numer_domu) VALUES(1, 1);
INSERT INTO Biblioteka.Uzytkownik(Email, Haslo, Nazwisko, Imie, Pwd_Seed, Typ, Adres) VALUES('test', '5-90113-58106-17-221876-64-117118-2210948-69', 'test', 'test', 'test', 1, 1);