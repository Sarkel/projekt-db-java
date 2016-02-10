package org.library.database.connection.postgresql;

import com.sun.istack.internal.NotNull;
import org.library.database.connection.DataBase;
import org.library.database.settings.PostgresqlConnectionSettings;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sebastian Kubalski on 09.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class PostgresqlConnection extends DataBase {

    /**
     * @throws SQLException
     */
    public PostgresqlConnection() throws SQLException {
        PostgresqlConnectionSettings settings = PostgresqlConnectionSettings.getInstance();
        String path = "jdbc:postgresql://" + settings.hostName + "/" + settings.dataBaseName;
        this.con = DriverManager.getConnection(path, settings.user, settings.password);
    }

    /**
     * @param functionName name of data base procedure to run
     * @param id data base procedure argument
     * @return result of data base select statement
     * @throws SQLException
     */
    public ResultSet select(@NotNull String functionName, @NotNull Integer id) throws SQLException {
        PreparedStatement ps = this.createQuery(functionName);
        ps.setInt(1, id);
        return ps.executeQuery();
    }

    /**
     * @param functionName name of data base procedure to run
     * @param text data base procedure argument
     * @return result of data base select statement
     * @throws SQLException
     */
    public ResultSet select(@NotNull String functionName, @NotNull String text) throws SQLException {
        PreparedStatement ps = this.createQuery(functionName);
        ps.setString(1, text);
        return ps.executeQuery();
    }

    /**
     * @param functionName name of data base procedure to run
     * @return prepared statement for data base query
     * @throws SQLException
     */
    private PreparedStatement createQuery(@NotNull String functionName) throws SQLException {
        String query = "SELECT " + functionName + "(?);";
        return this.con.prepareStatement(query);
    }
}
