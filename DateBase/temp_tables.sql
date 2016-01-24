--temporary table for adres used in user inser trigger
CREATE TEMPORARY TABLE IF NOT EXISTS Adres_temp (LIKE Biblioteka.Adres INCLUDING DEFAULTS);