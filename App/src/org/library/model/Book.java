package org.library.model;

import com.sun.istack.internal.NotNull;
import org.library.collections.Pair;
import org.library.database.connection.postgresql.PostgresqlConnection;
import org.library.wrappers.BookWrapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sebastian Kubalski on 17.02.2016.
 * @author  Sebastian Kubalski
 * @version 1.0
 */
public class Book {
    /**
     * @return all books from data base
     * @throws SQLException
     */
    public static ArrayList<BookWrapper.Books> getAll() throws SQLException {
        String query = "SELECT * FROM Biblioteka.all_books;";
        ResultSet rs = new PostgresqlConnection().select(query);
        ArrayList<BookWrapper.Books> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new BookWrapper.Books(rs.getInt("id"), rs.getString("tytul"),
                    rs.getString("avatar"), rs.getDouble("star")));
        }
        return results;
    }

    /**
     * @param searchParam search phrase, looking only for matching titles
     * @return results of search
     * @throws SQLException
     */
    public static ArrayList<BookWrapper.Books> getSearchResults(@NotNull String searchParam) throws SQLException{
        String search = searchParam + "%";
        String query = "SELECT * FROM Biblioteka.all_books WHERE tytul LIKE ? ORDER BY tytul;";
        ResultSet rs = new PostgresqlConnection().select(query, search);
        ArrayList<BookWrapper.Books> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new BookWrapper.Books(rs.getInt("id"), rs.getString("tytul"),
                    rs.getString("avatar"), rs.getDouble("star")));
        }
        return results;
    }

    /**
     * @param id id of selected book
     * @return details of selected book
     * @throws SQLException
     */
    public static BookWrapper.BookDetail getBookDetails(@NotNull Integer id) throws SQLException{
        String query = "SELECT * FROM Biblioteka.detail_book WHERE id = ?";
        ResultSet rs = new PostgresqlConnection().select(query, id);
        rs.next();
        return new BookWrapper.BookDetail(rs.getInt("id"), rs.getString("tytul"),
                rs.getString("avatar"), rs.getDouble("star"), rs.getInt("rok"), rs.getString("isbn"));
    }

    /**
     * @param id id of selected user
     * @return all books borrowed by selected user
     * @throws SQLException
     */
    public static ArrayList<BookWrapper.Books> getBooksByUser(@NotNull Integer id) throws SQLException{
        String query = "SELECT * FROM Biblioteka.books_by_user WHERE user = ? ORDER BY tytul;";
        ResultSet rs = new PostgresqlConnection().select(query, id);
        ArrayList<BookWrapper.Books> results = new ArrayList<>();
        while (rs.next()) {
            results.add(new BookWrapper.Books(rs.getInt("id"), rs.getString("tytul"),
                    rs.getString("avatar"), rs.getDouble("star")));
        }
        return results;
    }

    /**
     * @param book id of selected book
     * @param user id of current logged user
     * @throws SQLException
     */
    public static void borrowBook(@NotNull Integer book, @NotNull Integer user) throws SQLException{
        String tableName = "Biblioteka.Wypozyczona_ksiazka";
        ArrayList<Pair> values = new ArrayList<>();
        values.add(new Pair<>("uzytkownik", user));
        values.add(new Pair<>("ksiazka", book));
        Date today = new Date();
        values.add(new Pair<>("data_wypozyczenia", new java.sql.Date(today.getTime())));
        //values.add(new Pair<>("data_oddania", new java.sql.Date()));
        new PostgresqlConnection().insert(tableName, values);
    }
}
