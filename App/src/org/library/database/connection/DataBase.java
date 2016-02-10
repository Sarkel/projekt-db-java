package org.library.database.connection;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.library.collections.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sebastian Kubalski on 09.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public abstract class DataBase {

    protected Connection con;

    /**
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        this.con.close();
    }

    /**
     * @param tableName name of table where data will be inserted
     * @param values list of fields where data will be inserted
     * @throws SQLException
     */
    public void insert(@NotNull String tableName, @NotNull ArrayList<Pair> values) throws SQLException {
        String fields = "";
        for (Pair field : values) {
            fields += field.getKey();
        }
        String query = "INSERT INTO " + tableName + "(" + fields + ")"
                + " VALUES(" + numberOfValues(values.size()) + ");";
        PreparedStatement ps = this.setQueryValues(query, values);
        ps.executeUpdate();
    }

    /**
     * @param tableName name of table where data will be inserted
     * @param values list of fields where data will be inserted
     * @param limit content of where statement
     * @throws SQLException
     */
    public void update(@NotNull String tableName, @NotNull ArrayList<Pair> values,
                       @Nullable Pair<String, Integer> limit) throws SQLException {
        String query = "UPDATE " + tableName + " SET ";
        for (Pair field : values) {
            query += field.getKey() + "=?,";
        }
        query = query.substring(0, query.length() - 1);
        if (limit != null) {
            query += " WHERE " + limit.getKey() + "=?;";
        }else {
            query += ";";
        }
        PreparedStatement ps = this.setQueryValues(query, values);
        if (limit != null) {
            ps.setInt(values.size() + 1, limit.getValue());
        }
        ps.executeUpdate();
    }

    /**
     * @param tableName name of table where data will be inserted
     * @param limit content of where statement
     * @throws SQLException
     */
    public void delete(@NotNull String tableName, @Nullable Pair<String, Integer> limit) throws SQLException {
        String query = "DELETE FROM " + tableName;
        if (limit != null) {
            query += " WHERE " + limit.getKey() + "=?;";
        } else {
            query += ";";
        }
        PreparedStatement ps = this.con.prepareStatement(query);
        if (limit != null) {
            ps.setInt(1, limit.getValue());
        }
        ps.executeUpdate();
    }

    /**
     * @param query entire data base query
     * @return result of data base select statement
     * @throws SQLException
     */
    public ResultSet select(@NotNull String query) throws SQLException {
        return this.con.createStatement().executeQuery(query);
    }

    /**
     * @param num number of parameter in DML statement
     * @return symbols for field values
     */
    String numberOfValues(@NotNull Integer num) {
        String values = "";
        for (int i = 0; i < num; i++) {
            values += "?,";
        }
        return values.substring(0, values.length() - 1);
    }

    /**
     * @param query query for preparing statement
     * @param values values of fields
     * @return prepared statement for data base query
     * @throws SQLException
     */
    private PreparedStatement setQueryValues(@NotNull String query, @NotNull ArrayList<Pair> values)
            throws SQLException {
        PreparedStatement ps = this.con.prepareStatement(query);
        int counter = 1;
        for (Pair field : values) {
            if (field.getValue() instanceof Integer) {
                ps.setInt(counter, (Integer) field.getValue());
            } else if (field.getValue() instanceof String) {
                ps.setString(counter, (String) field.getValue());
            } else if (field.getValue() instanceof Double) {
                ps.setDouble(counter, (Double) field.getValue());
            }
            counter++;
        }
        return ps;
    }
}
