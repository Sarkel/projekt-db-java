--search books by title, autor first and last name
CREATE OR REPLACE FUNCTION Biblioteka.search_books(TEXT) RETURNS Biblioteka.search_books_view AS '
	SELECT * FROM Biblioteka.search_books_view WHERE tytul LIKE $1 || ''%'' ORDER BY Tytul;
' LANGUAGE sql;

--get detail information about selected book
CREATE OR REPLACE FUNCTION Biblioteka.detail_book(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 NUMERIC) AS '
	SELECT * FROM Biblioteka.detail_book WHERE id = $1;
' LANGUAGE sql; 

--get all authors for selected book
CREATE OR REPLACE FUNCTION Biblioteka.book_authors(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT) AS '
	SELECT * FROM Biblioteka.book_authors WHERE ksiazka = $1 ORDER BY nazwisko;
' LANGUAGE sql;

--get all authors for selected book
CREATE OR REPLACE FUNCTION Biblioteka.book_wyd(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT) AS '
	SELECT * FROM Biblioteka.book_wyd WHERE ksiazka = $1;
' LANGUAGE sql;

--get all books by author
--CREATE OR REPLACE FUNCTION Biblioteka.books_by_author(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 NUMERIC) AS '
--	SELECT k.Ksiazka_ID, k.Tytul, av.Url, rp.Nazwa, Biblioteka.avg_star(k.Ksiazka_ID) 
--	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Biblioteka.Ksiazka_Autor AS ka, Biblioteka.Autor AS a, Biblioteka.Rodzaj_powiazania as rp 
--	WHERE a.Autor_ID = $1 
--		AND av.Avatar_ID = k.Avatar 
--		AND ka.Ksiazka = k.Ksiazka_ID 
--		AND ka.Autor = a.Autor_ID 
--		AND ka.Rodzaj_powiazania = rp.Rodzaj_powiazania_ID 
--	ORDER BY k.Tytul;
--' LANGUAGE sql;

--get all books by wydawnictwo
--CREATE OR REPLACE FUNCTION Biblioteka.books_by_wydawnictwo(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 NUMERIC) AS '
--	SELECT k.Ksiazka_ID, k.Tytul, av.Url, Biblioteka.avg_star(k.Ksiazka_ID) 
--	FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Wydawnictwo AS wy 
--	WHERE wy.Wydawnictwo_ID = $1 
--		AND av.Avatar_ID = k.Avatar 
--		AND wy.Wydawnictwo_ID = k.Wydawnictwo 
--	ORDER BY k.Tytul;
--' LANGUAGE sql;

--get all comments on selected book
CREATE OR REPLACE FUNCTION Biblioteka.comments_by_book(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT,f4 TEXT, f5 TEXT, f6 TIMESTAMP, f7 INT, f8 TEXT) AS '
	SELECT * FROM Biblioteka.comments_by_book WHERE ksiazka = $1 ORDER BY data DESC;
' LANGUAGE sql;

--get all comments by selected user
--CREATE OR REPLACE FUNCTION Biblioteka.comments_by_user(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TIMESTAMP, f5 INT) AS '
--	SELECT kom.Komentarz_ID, k.Tytul, kom.Tekst, kom.Data  
--	FROM Biblioteka.Komentarz AS kom, Biblioteka.Ksiazka AS k, Biblioteka.Uzytkownik AS u 
--	WHERE u.Uzytkownik_ID = $1 
--		AND k.Ksiazka_ID = kom.Ksiazka 
--		AND u.Uzytkownik_ID = kom.Urzytkownik  
--	ORDER BY kom.Data DESC;
--' LANGUAGE sql;

--get user detail information
CREATE OR REPLACE FUNCTION Biblioteka.user_detail(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 TEXT, f7 INT, f8 INT, f9 TEXT, f10 TEXT, f11 TEXT, f12 TEXT, f13 TEXT, f14 TEXT, f15 BOOLEAN, f16 NUMERIC) AS '
	SELECT * FROM Biblioteka.user_detail WHERE id = $1;
' LANGUAGE sql;

--get all boos by user
CREATE OR REPLACE FUNCTION Biblioteka.books_by_user(INT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 TEXT, f7 NUMERIC, f8 TEXT, f9 TEXT, f10 TEXT) AS '
	SELECT * FROM Biblioteka.books_by_user WHERE user = $1 ORDER BY tytul;
' LANGUAGE sql;

CREATE OR REPLACE FUNCTION Biblioteka.debet_by_user(INT) RETURNS TABLE(f1 INT, f2 INT, f3 TEXT, f4 TEXT, f5 NUMERIC(10, 2)) AS '
	SELECT * FROM Biblioteka.debet_by_user WHERE user = $1 ORDER BY wartosc DESC;
' LANGUAGE sql;

CREATE OR REPLACE FUNCTION Biblioteka.login_user(TEXT) RETURNS TABLE(f1 INT, f2 TEXT, f3 TEXT, f4 TEXT, f5 TEXT, f6 TEXT) AS '
	SELECT * FROM Biblioteka.login_user WHERE email = $1;
' LANGUAGE sql;