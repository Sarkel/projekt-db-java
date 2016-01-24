package org.library.connection.db.sqlite;

import org.library.connection.db.DataBase;
import org.library.exceptions.ConnectionException;
import org.library.exceptions.CustomException;
import org.library.wrappers.TableFieldWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by sebas on 23.01.2016.
 */
public class CasheOperations extends DataBase {

    public CasheOperations() throws CustomException {
        super("jdbc:sqlite:cashe/cashBase.db");
    }

    public void createTable(String statement) throws CustomException {
        try {
            PreparedStatement st = this.con.prepareStatement(statement);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ConnectionException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ConnectionException(e.getMessage());
        }
    }
}
