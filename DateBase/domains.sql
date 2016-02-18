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
CREATE DOMAIN Biblioteka.year AS INTEGER CONSTRAINT valid_value CHECK (VALUE >= 0 AND VALUE <= 9999);
COMMENT ON DOMAIN Biblioteka.year IS 'Domain represent valid year from 0 to 3048.';

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