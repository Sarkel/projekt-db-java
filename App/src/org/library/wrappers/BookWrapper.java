package org.library.wrappers;

/**
 * Created by Sebastian Kubalski on 17.02.2016.
 * @author Sebastian Kubalski
 * @version 1.0
 */
public class BookWrapper {
    public static class Books {
        public Integer id;
        public String tytul;
        public String avatar;
        public Double star;

        public Books(Integer id, String tytul, String avatar, Double star){
            this.id = id;
            this.star = star;
            this.tytul = tytul;
            this.avatar = avatar;
        }
    }

    public static class BookDetail extends Books {
        public Integer rok;
        public String isbn;

        public BookDetail(Integer id, String tytul, String avatar, Double star, Integer rok, String isbn){
            super(id, tytul, avatar, star);
            this.isbn = isbn;
            this.rok = rok;
        }
    }
}