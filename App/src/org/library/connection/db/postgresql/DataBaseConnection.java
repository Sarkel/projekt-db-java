package org.library.connection.db.postgresql;

import org.library.connection.db.DataBase;
import org.library.exceptions.ConnectionException;
import org.library.exceptions.CustomException;
import org.library.exceptions.DMLException;
import org.library.wrappers.TableFieldWrapper;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by sebas on 20.01.2016.
 */
public class DataBaseConnection extends DataBase {

    public DataBaseConnection() throws CustomException {
        super("jdbc:postgresql://" + ConnectionCredentials.getInstance().hostName + "/" +
                        ConnectionCredentials.getInstance().dataBaseName,
                ConnectionCredentials.getInstance().user, ConnectionCredentials.getInstance().password);
    }

    public void insert(String tableName, ArrayList<TableFieldWrapper> values) throws CustomException {
        try {
            String fields = "";
            for (TableFieldWrapper field : values) {
                fields += field.name;
            }
            String query = "INSERT INTO Biblioteka." + tableName + "(" + fields + ")"
                    + " VALUES(" + numberOfValues(values.size()) + ");";

            PreparedStatement ps = this.setQueryValues(query, values);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DMLException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public void delete(String tableName, TableFieldWrapper<Integer> limit) throws CustomException {
        try {
            String query = "DELETE FROM " + tableName + " WHERE " + limit.name + "=?;";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, limit.value);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DMLException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public void update(String tableName, ArrayList<TableFieldWrapper> values,
                       TableFieldWrapper<Integer> limit) throws CustomException {
        try {
            String query = "UPDATE " + tableName + " SET ";
            for (TableFieldWrapper field : values) {
                query += field.name + "=?,";
            }
            query = query.substring(0, query.length() - 1);
            query += " WHERE " + limit.name + "=?;";
            PreparedStatement ps = this.setQueryValues(query, values);
            ps.setInt(values.size() + 1, limit.value);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DMLException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public ResultSet select(String query) throws CustomException {
        try {
            Statement st = this.con.createStatement();
            return st.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    private String numberOfValues(Integer num) {
        String values = "";
        for (int i = 0; i < num; i++) {
            values += "?,";
        }
        return values.substring(0, values.length() - 1);
    }

    private PreparedStatement setQueryValues(String query, ArrayList<TableFieldWrapper> values) throws Exception {
        PreparedStatement ps = this.con.prepareStatement(query);
        int counter = 1;
        for (TableFieldWrapper field : values) {
            if (field.value instanceof Integer) {
                ps.setInt(counter, (Integer) field.value);
            } else if (field.value instanceof String) {
                ps.setString(counter, (String) field.value);
            } else if (field.value instanceof Double) {
                ps.setDouble(counter, (Double) field.value);
            }
            counter++;
        }
        return ps;
    }
}
