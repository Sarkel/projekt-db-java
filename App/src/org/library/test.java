package org.library;

import org.library.database.connection.sqlite.SqliteMemoryConnection;
import org.library.database.connection.sqlite.SqliteStandardConnection;
import org.library.gui.LoginDialog;
import org.library.gui.TotalBookView;
import org.library.model.User;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sebas on 10.02.2016.
 */
public class test {
    public static void main(String[] args){
        try {
            LoginDialog dialog = new LoginDialog();
            dialog.pack();
            dialog.setVisible(true);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
