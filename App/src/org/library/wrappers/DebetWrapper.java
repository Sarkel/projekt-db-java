package org.library.wrappers;

/**
 * Created by sebas on 17.02.2016.
 */
public class DebetWrapper {
    public static class Debet {
        public Integer id;
        public Integer ksiazka;
        public String tytul;
        public String opis;
        public Double wartosc;

        public Debet(Integer id, Integer ksiazka, String tytul, String opis, Double wartosc){
            this.id = id;
            this.ksiazka = ksiazka;
            this.opis = opis;
            this.tytul = tytul;
            this.wartosc = wartosc;
        }
    }

}
