package org.library.database.settings;

/**
 * Created by Sebastian Kubalski on 09.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class PostgresqlConnectionSettings {

    private String user;

    private String password;

    private String dataBaseName;

    private String hostName;

    private static PostgresqlConnectionSettings ourInstance = new PostgresqlConnectionSettings();

    /**
     * @return instance of singleton
     */
    public static PostgresqlConnectionSettings getInstance() {
        return ourInstance;
    }

    private PostgresqlConnectionSettings() {
        this.user = "u3kubalski";
        this.password = "3kubalski";
        this.dataBaseName = "u3kubalski";
        this.hostName = "localhost";
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
