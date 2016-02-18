package org.library.wrappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by sebas on 17.02.2016.
 */
public class CommentsWrapper {
    public static class Comment {
        public Integer id;
        public String imie;
        public String nazwisko;
        public String content;
        public LocalDateTime data;

        public Comment(Integer id, String imie, String nazwisko, String content, Timestamp data){
            this.content = content;
            this.id = id;
            this.imie = imie;
            this.nazwisko = nazwisko;
            this.data = data.toLocalDateTime();
        }
    }
}
