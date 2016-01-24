CREATE VIEW Biblioteka.all_books AS 
	SELECT k.Ksiazka_ID AS id, k.Tytul AS tytul, a.Url AS url, Biblioteka.avg_star(k.Ksiazka_ID) AS star 
	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS a 
	WHERE k.Wypozyczona = false AND a.Avatar_ID = k.Avatar;

CREATE VIEW Biblioteka.all_users AS 
	SELECT u.Uzytkownik_ID AS id, u.Email AS email, u.Nazwisko AS nazwisko, u.Imie AS imie, av.Url AS url, ty.Nazwa AS typ, u.Aktywny AS aktywny
	FROM Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av, Biblioteka.Rodzaj_uzytkownika AS ty 
	WHERE u.Typ = ty.Rodzaj_uzytkownika_ID 
		AND u.Avatar = av.Avatar_ID 
	ORDER BY u.Nazwisko;