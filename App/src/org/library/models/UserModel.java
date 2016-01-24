package org.library.models;

import org.library.connection.db.postgresql.DataBaseConnection;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;

/**
 * Created by sebas on 21.01.2016.
 */
public class UserModel {
    public static Boolean login(String login, String password){
        String query = "SELECT u.Uzytkownik_ID AS id, u.Email AS login, u.Haslo AS pwd, u.Pwd_seed AS pwdSeed" +
                " FROM Biblioteka.Uzytkownik AS u, Biblioteka.Avatar AS av, Biblioteka.Rodzaj_uzytkownika AS ru" +
                " WHERE u.Email = '" + login +"' AND u.Aktywny = true;";
        try{
            ResultSet rs = new DataBaseConnection().select(query);
            while (rs.next()){
                String pwd = rs.getString("pwd");
                String seed = rs.getString("pwdSeed");
                if(checkPassword(password + seed, pwd)){
                    return true;
                }
            }
        } catch(Exception e){
            return false;
        }
        return false;
    }

    private static boolean checkPassword(String cpwd, String pwd) throws Exception{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(cpwd.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashText = bigInt.toString(16);
        while(hashText.length() < 32 ){
            hashText = "0"+hashText;
        }
        return hashText.equals(pwd);
    }
}
