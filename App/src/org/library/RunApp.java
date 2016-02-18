package org.library;

import org.library.gui.LoginDialog;

/**
 * Created by Sebastian Kubalski on 10.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class RunApp {
    /**
     * @param args command line parameters
     */
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
