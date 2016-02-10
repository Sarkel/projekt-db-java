package org.library.database.settings;

/**
 * Created by Sebastian Kubalski on 09.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class PostgresqlConnectionSettings {

    public String user;

    public String password;

    public String dataBaseName;

    public String hostName;

    private static PostgresqlConnectionSettings ourInstance = new PostgresqlConnectionSettings();

    /**
     * @return instance of singleton
     */
    public static PostgresqlConnectionSettings getInstance() {
        return ourInstance;
    }

    private PostgresqlConnectionSettings() {
        this.user = "library_admin";
        this.password = "admin";
        this.dataBaseName = "library";
        this.hostName = "localhost";
    }
}
