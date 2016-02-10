package org.library.database.connection.sqlite;

import org.library.database.connection.DateBaseSqliteExtension;
import org.library.database.settings.SqliteConnectionSettings;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Sebastian Kubalski on 09.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class SqliteStandardConnection extends DateBaseSqliteExtension {

    /**
     * @throws SQLException
     */
    public SqliteStandardConnection() throws SQLException{
        SqliteConnectionSettings settings = SqliteConnectionSettings.getInstance();
        this.con = DriverManager.getConnection("jdbc:sqlite:" + settings.hostName + "/" + settings.dbName);
    }
}
