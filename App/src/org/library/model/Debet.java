package org.library.model;

import com.sun.istack.internal.NotNull;
import org.library.database.connection.postgresql.PostgresqlConnection;
import org.library.wrappers.DebetWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sebastian Kubalski on 17.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class Debet {
    /**
     * @param user id of selected user
     * @return all penalty debit for selected user
     * @throws SQLException
     */
    public static ArrayList<DebetWrapper.Debet> getUserDebets(@NotNull Integer user) throws SQLException {
        ArrayList<DebetWrapper.Debet> results = new ArrayList<>();
        String query = "SELECT * FROM Biblioteka.debet_by_user WHERE user = ? ORDER BY wartosc DESC;";
        ResultSet rs = new PostgresqlConnection().select(query, user);
        while (rs.next()){
            results.add(new DebetWrapper.Debet(rs.getInt("id"), rs.getInt("ksiazka"), rs.getString("tytul"),
                    rs.getString("opis"), rs.getDouble("wartosc")));
        }
        return results;
    }
}
