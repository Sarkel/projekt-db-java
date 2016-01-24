package org.library.connection.db;

import org.library.exceptions.ConnectionException;
import org.library.exceptions.CustomException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by sebas on 24.01.2016.
 */
public abstract class DataBase {

    protected Connection con;

    public DataBase(String dataBasePath) throws CustomException {
        try {
            this.con = DriverManager.getConnection(dataBasePath);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ConnectionException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public DataBase(String dataBasePath, String user, String pwd) throws CustomException {
        try {
            this.con = DriverManager.getConnection(dataBasePath, user, pwd);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ConnectionException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public Boolean closeConnection() throws CustomException {
        try {
            this.con.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }
}
