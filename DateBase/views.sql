CREATE VIEW Biblioteka.all_books AS 
	SELECT k.Ksiazka_ID AS id, k.Tytul AS tytul, a.Url AS avatar, Biblioteka.avg_star(k.Ksiazka_ID) AS star 
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS a 
	WHERE k.Wypozyczona = false AND a.Avatar_ID = k.Avatar;

CREATE VIEW Biblioteka.all_users AS 
	SELECT u.Uzytkownik_ID AS id, u.Email AS email, u.Nazwisko AS nazwisko, u.Imie AS imie, av.Url AS avatar, ty.Nazwa AS typ, u.Aktywny AS aktywny
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av, Biblioteka.Rodzaj_uzytkownika AS ty 
	WHERE u.Typ = ty.Rodzaj_uzytkownika_ID 
		AND u.Avatar = av.Avatar_ID 
	ORDER BY u.Nazwisko;

CREATE VIEW Biblioteka.detail_book AS 
	SELECT k.Ksiazka_ID AS id, k.Tytul AS tytul, k.Rok_wydania AS rok, k.isbn AS isbn, av.Url AS avatar, Biblioteka.avg_star(k.Ksiazka_ID) AS star
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Biblioteka.Wydawnictwo AS wy 
	WHERE av.Avatar_ID = k.Avatar AND wy.Wydawnictwo_ID = k.Wydawnictwo;

CREATE VIEW Biblioteka.book_authors AS 
	SELECT a.Autor_ID AS id , a.Imie AS imie, a.Nazwisko AS nazwisko, a.Kraj_pochodzenia AS kraj, rp.Nazwa AS powiazanie, k.Ksiazka_ID AS ksiazka 
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Ksiazka_Autor AS ka, Biblioteka.Autor AS a, Biblioteka.Rodzaj_powiazania AS rp 
	WHERE ka.Ksiazka = k.Ksiazka_ID 
		AND ka.Autor = a.Autor_ID 
		AND rp.Rodzaj_powiazania_ID = ka.Rodzaj_powiazania;

CREATE VIEW Biblioteka.book_wyd AS 
	SELECT wy.Wydawnictwo_ID AS id, wy.Nazwa AS nazwa, wy.Kraj_pochodzenia AS kraj, k.Ksiazka_ID AS ksiazka
	FROM Biblioteka.Wydawnictwo AS wy, Biblioteka.Ksiazka AS k  
	WHERE k.Wydawnictwo = wy.Wydawnictwo_ID;

CREATE VIEW Biblioteka.comments_by_book AS
	SELECT kom.Komentarz_ID AS id, u.Imie AS imie, u.Nazwisko AS nazwisko, kom.Tekst AS content, kom.Data AS data, k.Ksiazka_ID AS ksiazka 
	FROM Biblioteka.Komentarz AS kom, Biblioteka.Ksiazka AS k, Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av 
	WHERE k.Ksiazka_ID = kom.Ksiazka 
		AND u.Uzytkownik_ID = kom.Urzytkownik 
		AND u.Uzytkownik_ID = av.Avatar_ID;

CREATE VIEW Biblioteka.user_detail AS
	SELECT u.Uzytkownik_ID AS id, u.Email AS email, u.Nazwisko AS nazwisko, u.Imie AS imie, av.Url AS avatar, ad.Ulica AS ulica, ad.Numer_domu AS numerDomu, 
		ad.Numer_mieszkania AS numerMieszkania, p.Kod_pocztowy AS kodPocztowy, p.Miejscowosc AS miejscowosc, p.Kraj AS Kraj, 
		ty.Nazwa AS Typ, u.Telefon_komorkowy AS komorka, u.Telefon_stacjonarny AS stacjonarny, u.Aktywny AS aktywny, Biblioteka.get_debet() AS debet 
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av, Biblioteka.Adres AS ad, Biblioteka.Poczta AS p, Biblioteka.Rodzaj_uzytkownika AS ty 
	WHERE u.Typ = ty.Rodzaj_uzytkownika_ID 
		AND u.Adres = ad.Adres_ID 
		AND u.Avatar = av.Avatar_ID 
		AND ad.Kod_pocztowy = p.Poczta_ID;

CREATE VIEW Biblioteka.books_by_user AS
	SELECT k.Ksiazka_ID AS id, k.Tytul AS tytul, av.Url AS avatar, Biblioteka.avg_star(k.Ksiazka_ID) AS star, u.Uzytkownik_ID AS user
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Uzytkownik AS u, Biblioteka.Wypozyczona_ksiazka AS wk, Biblioteka.Avatar AS av
	WHERE wk.Uzytkownik = u.Uzytkownik_ID 
		AND wk.Ksiazka = k.Ksiazka_ID
		AND u.Avatar = av.Avatar_ID;

CREATE VIEW Biblioteka.debet_by_user AS
	SELECT n.Naleznosc_ID AS id, k.Ksiazka_ID As ksiazka, k.Tytul AS tytul, n.Opis AS opis, n.Wartosc AS wartosc, u.Uzytkownik_ID AS user
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Ksiazka AS k, Biblioteka.Naleznosc AS n 
	WHERE u.Uzytkownik_ID = n.Uzytkownik OR k.Ksiazka_ID = n.Ksiazka;

CREATE VIEW Biblioteka.login_user AS
	SELECT u.Uzytkownik_ID AS id, u.Email AS email, u.Haslo AS haslo, u.Pwd_seed AS seed, ru.Nazwa AS typ, av.Url AS avatar 
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av, Biblioteka.Rodzaj_uzytkownika AS ru 
	WHERE u.Aktywny = true;

CREATE VIEW Biblioteka.comment_detail AS 
	SELECT kom.Komentarz_ID AS id, kom.Tekst AS content, kom.Data AS data, kom.Ilosc_gwiazdek AS star
	FROM Biblioteka.Komentarz AS kom