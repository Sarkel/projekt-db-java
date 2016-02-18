package org.library.model;

import com.sun.istack.internal.NotNull;
import org.library.database.connection.postgresql.PostgresqlConnection;
import org.library.wrappers.AuthorWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sebastian Kubalski on 17.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class Author {
    /**
     * @param book id of selected book
     * @return results of data base search
     * @throws SQLException
     */
    public static ArrayList<AuthorWrapper.Author> getBookAuthors(@NotNull Integer book) throws SQLException {
        ArrayList<AuthorWrapper.Author> results = new ArrayList<>();
        String query = "SELECT * FROM Biblioteka.book_authors WHERE ksiazka = ? ORDER BY nazwisko";
        ResultSet rs = new PostgresqlConnection().select(query, book);
        while (rs.next()) {
            results.add(new AuthorWrapper.Author(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"),
                    rs.getString("kraj"), rs.getString("powiazanie")));
        }
        return results;
    }
}
