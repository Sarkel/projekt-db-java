package org.library.model;

import com.sun.istack.internal.NotNull;
import org.library.database.connection.postgresql.PostgresqlConnection;
import org.library.wrappers.CommentsWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sebas on 17.02.2016.
 */
public class Comments {
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
}
