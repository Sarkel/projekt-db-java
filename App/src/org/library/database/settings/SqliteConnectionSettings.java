package org.library.database.settings;

/**
 * Created by Sebastian Kubalski on 09.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class SqliteConnectionSettings {
    public String hostName;

    public String dbName;

    private static SqliteConnectionSettings ourInstance = new SqliteConnectionSettings();

    /**
     * @return instance of singleton
     */
    public static SqliteConnectionSettings getInstance() {
        return ourInstance;
    }

    private SqliteConnectionSettings() {
        this.dbName = "cashBase.db";
        this.hostName = "cashe";
    }
}