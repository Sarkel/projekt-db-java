package org.library.model;

import com.sun.istack.internal.NotNull;
import org.library.database.connection.postgresql.PostgresqlConnection;
import org.library.wrappers.AddressWrapper;
import org.library.wrappers.PhoneWrapper;
import org.library.wrappers.UserWrapper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Sebastian Kubalski on 17.02.2016.
 * @author Sebastian Kubalski
 * @version 1.0
 */
public class User {
    /**
     * @return all active users from entire organization
     * @throws SQLException
     */
    public static ArrayList<UserWrapper.User> getAll() throws SQLException {
        ArrayList<UserWrapper.User> results = new ArrayList<>();
        String query = "SELECT * FROM Biblioteka.all_books";
        ResultSet rs = new PostgresqlConnection().select(query);
        while (rs.next()) {
            results.add(new UserWrapper.User(rs.getInt("id"), rs.getString("email"), rs.getString("nazwisko"),
                    rs.getString("imie"), rs.getString("avatar"), rs.getString("typ"), rs.getBoolean("aktywny")));
        }
        return results;
    }

    /**
     * @param id id of selected user
     * @return details for selected user
     * @throws SQLException
     */
    public static UserWrapper.UserDetails getUserDetails(@NotNull Integer id) throws SQLException {
        String query = "SELECT * FROM Biblioteka.user_detail WHERE id = ?";
        ResultSet rs = new PostgresqlConnection().select(query, id);
        rs.next();
        UserWrapper.User user = new UserWrapper.User(rs.getInt("id"), rs.getString("email"),
                rs.getString("nazwisko"), rs.getString("imie"), rs.getString("avatar"), rs.getString("typ"),
                rs.getBoolean("aktywny"));
        PhoneWrapper.Phone phone = new PhoneWrapper.Phone(rs.getString("komorka"), rs.getString("stacjonarny"));
        AddressWrapper.Address address = new AddressWrapper.Address(rs.getString("ulica"), rs.getInt("numerDomu"),
                rs.getInt("numerMieszkania"), rs.getString("kodPocztowy"), rs.getString("miejscowosc"),
                rs.getString("kraj"));
        return new UserWrapper.UserDetails(user, address, phone, rs.getDouble("debet"));
    }

    /**
     * @param login user's login
     * @param pwdChars user's password
     * @return true if user has been logged correctly, otherwise false
     */
    public static boolean login(@NotNull String login, @NotNull char[] pwdChars) {
        String query = "SELECT * FROM Biblioteka.login_user WHERE email = ?;";
        try {
            ResultSet rs = new PostgresqlConnection().select(query, login);
            if (!rs.next()) {
                return false;
            }
            UserWrapper.LoginData loginData = new UserWrapper.LoginData(rs.getInt("id"), rs.getString("email"),
                    rs.getString("haslo"), rs.getString("seed"), rs.getString("typ"), rs.getString("avatar"));

            String password = new String(pwdChars);
            if (checkCredentials(password, loginData.haslo, loginData.seed)) {
                UserWrapper.UserId.getInstance().setId(loginData.id);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * @param currentPwd password set by user
     * @param pwd password selected from data base
     * @param seed MD5 seed
     * @return true if credentials are correct, otherwise false
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private static boolean checkCredentials(@NotNull String currentPwd, @NotNull String pwd, @NotNull String seed)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String hashString = "";
        for (byte digest : md.digest((currentPwd + seed).getBytes("UTF-8"))) {
            hashString += String.valueOf(digest);
        }
        return pwd.equals(hashString);
    }
}
