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