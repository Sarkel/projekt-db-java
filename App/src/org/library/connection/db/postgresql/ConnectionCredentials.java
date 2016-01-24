package org.library.connection.db.postgresql;

/**
 * Created by sebas on 23.01.2016.
 */
public class ConnectionCredentials {
    public String user;
    public String password;
    public String dataBaseName;
    public String hostName;

    private static ConnectionCredentials ourInstance = new ConnectionCredentials();

    public static ConnectionCredentials getInstance() {
        return ourInstance;
    }

    private ConnectionCredentials() {
        this.user = "library_admin";
        this.password = "admin";
        this.dataBaseName = "library";
        this.hostName = "localhost";
    }
}
