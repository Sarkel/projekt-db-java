package org.library.wrappers;

/**
 * Created by sebas on 17.02.2016.
 */
public class AuthorWrapper {
    public static class Author {
        public Integer id;
        public String imie;
        public String nazwisko;
        public String kraj;
        public String powiazanie;

        public Author(Integer id, String imie, String nazwisko, String kraj, String powiazanie){
            this.id = id;
            this.imie = imie;
            this.kraj = kraj;
            this.nazwisko = nazwisko;
            this.powiazanie = powiazanie;
        }
    }
}
