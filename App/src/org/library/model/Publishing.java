package org.library.model;

import com.sun.istack.internal.NotNull;
import org.library.database.connection.postgresql.PostgresqlConnection;
import org.library.wrappers.PublishingWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sebastian Kubalski on 17.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class Publishing {
    /**
     * @param book id of selected book
     * @return all publishing houses for selected book
     * @throws SQLException
     */
    public static ArrayList<PublishingWrapper.Publishing> getBookPublishing(@NotNull Integer book) throws SQLException {
        ArrayList<PublishingWrapper.Publishing> results = new ArrayList<>();
        String query = "SELECT * FROM Biblioteka.book_wyd WHERE ksiazka = ? ORDER BY nazwa;";
        ResultSet rs = new PostgresqlConnection().select(query, book);
        while (rs.next()){
            results.add(new PublishingWrapper.Publishing(rs.getInt("id"), rs.getString("nazwa"), rs.getString("kraj")));
        }
        return results;
    }
}
