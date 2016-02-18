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