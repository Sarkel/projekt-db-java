package org.library;

import org.library.database.connection.sqlite.SqliteMemoryConnection;
import org.library.database.connection.sqlite.SqliteStandardConnection;

/**
 * Created by sebas on 10.02.2016.
 */
public class test {
    public static void main(String[] args){
        try {
            SqliteStandardConnection s = new SqliteStandardConnection();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
