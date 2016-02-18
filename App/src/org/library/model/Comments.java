package org.library.model;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.library.collections.Pair;
import org.library.database.connection.postgresql.PostgresqlConnection;
import org.library.wrappers.CommentsWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sebastian Kubalski on 17.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class Comments {
    /**
     * @param book id of selected book
     * @return all comments for selected book
     * @throws SQLException
     */
    public static ArrayList<CommentsWrapper.Comment> getBookComments(@NotNull Integer book) throws SQLException {
        ArrayList<CommentsWrapper.Comment> results = new ArrayList<>();
        String query = "SELECT * FROM Biblioteka.comments_by_book WHERE ksiazka = ? ORDER BY data DESC;";
        ResultSet rs = new PostgresqlConnection().select(query, book);
        while (rs.next()){
            results.add(new CommentsWrapper.Comment(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"),
                    rs.getString("content"), rs.getTimestamp("data")));
        }
        return results;
    }

    /**
     * @param comment id of selected comment
     * @return details for selected comments
     * @throws SQLException
     */
    public static CommentsWrapper.CommentDetail getCommentDetails(@NotNull Integer comment) throws SQLException {
        String query = "SELECT * FROM Biblioteka.comment_detail WHERE id = ?;";
        ResultSet rs = new PostgresqlConnection().select(query, comment);
        rs.next();
        return new CommentsWrapper.CommentDetail(rs.getInt("id"), rs.getString("content"), rs.getTimestamp("data"),
                rs.getDouble("star"));
    }

    /**
     * @param user id of current logged user
     * @param book id of selected book
     * @param star number of stars
     * @param content content of comment
     * @throws SQLException
     */
    public static void createComment(@NotNull Integer user, @NotNull Integer book, @NotNull Double star,
                                     @Nullable String content) throws SQLException {
        String tableName = "Biblioteka.Komentarz";
        ArrayList<Pair> values = new ArrayList<>();
        values.add(new Pair<>("Urzytkownik", user));
        values.add(new Pair<>("ksiazka", book));
        values.add(new Pair<>("Tekst", content));
        values.add(new Pair<>("Ilosc_gwiazdek", star));
        values.add(new Pair<>("Data", new Timestamp(new Date().getTime())));
        new PostgresqlConnection().insert(tableName, values);
    }
}
