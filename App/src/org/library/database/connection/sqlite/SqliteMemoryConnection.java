package org.library.database.connection.sqlite;

import org.library.database.connection.DateBaseSqliteExtension;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Sebastian Kubalski on 09.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class SqliteMemoryConnection extends DateBaseSqliteExtension {

    /**
     * @throws SQLException
     */
    public SqliteMemoryConnection() throws SQLException {
        this.con = DriverManager.getConnection("jdbc:sqlite::memory:");
    }
}
