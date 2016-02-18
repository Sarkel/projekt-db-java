package org.library.database.connection;

import com.sun.istack.internal.NotNull;
import org.library.exceptions.CustomException;

import java.io.File;
import java.sql.SQLException;

/**
 * Created by Sebastian Kubalski on 10.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public abstract class DateBaseSqliteExtension extends DataBase {
    /**
     * @param statement entire statement for creation of data base table
     * @throws SQLException
     */
    public void createTable(@NotNull String statement) throws SQLException{
        this.con.createStatement().execute(statement);
    }

    /**
     * @param path path to Sqlite data base
     * @throws CustomException
     * @throws SQLException
     */
    public void dropDataBase(@NotNull String path) throws CustomException, SQLException{
        this.closeConnection();
        File file = new File(path);
        if(!file.delete()){
            throw new CustomException("Data base couldn't be droped.");
        }
    }

    /**
     * @param tableName name of table to be dropped
     * @throws SQLException
     */
    public void dropTable(@NotNull String tableName) throws SQLException{
        this.con.createStatement().execute("DROP TABLE " + tableName + ";");
    }
}
