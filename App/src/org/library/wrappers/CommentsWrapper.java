package org.library.wrappers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Sebastian Kubalski on 17.02.2016.
 * @author Sebastian Kubalski
 * @version 1.0
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

    public static class CommentDetail{
        public Double star;
        public Integer id;
        public String content;
        public LocalDateTime data;

        public CommentDetail(Integer id, String content, Timestamp data, Double star){
            this.star = FunctionalityHelper.round(star, 1);
            this.data = data.toLocalDateTime();
            this.content = content;
            this.id = id;
        }
    }
}
