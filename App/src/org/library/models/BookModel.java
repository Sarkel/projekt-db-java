package org.library.models;

import org.library.connection.db.postgresql.DataBaseConnection;
import org.library.exceptions.SelectException;
import org.library.wrappers.DataBaseWrappers;
import org.library.wrappers.TableFieldWrapper;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by sebas on 21.01.2016.
 */
public class BookModel {
    public static ArrayList<DataBaseWrappers> searchBooks(String searchParam) throws SelectException{
        String query = "SELECT k.Ksiazka_ID as id, k.Tytul as tytul, av.Url as url, " +
                "Biblioteka.avg_star(k.Ksiazka_ID) as star" +
                " FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Biblioteka.Ksiazka_Autor AS ka," +
                " Biblioteka.Autor AS a " +
                " WHERE k.Wypozyczona = false" +
                " AND (av.Avatar_ID = k.Avatar" +
                " OR ka.Ksiazka = k.Ksiazka_ID" +
                " OR ka.Autor = a.Autor_ID )" +
                " AND (k.Tytul LIKE '" + searchParam +"%' OR a.Imie LIKE '" + searchParam +
                "%' OR a.Nazwisko LIKE '" + searchParam +"%'" +
                " ORDER BY k.Tytul;";
        try{
            ResultSet rs = new DataBaseConnection().select(query);
            ArrayList<DataBaseWrappers> result = new ArrayList<>();
            while(rs.next()){
                ArrayList<TableFieldWrapper> row = new ArrayList<>();
                row.add(new TableFieldWrapper<Integer>("id", rs.getInt("id")));
                row.add(new TableFieldWrapper<String>("tytyl", rs.getString("tytul")));
                row.add(new TableFieldWrapper<String>("url", rs.getString("url")));
                row.add(new TableFieldWrapper<Double>("star", rs.getDouble("star")));
                result.add(new DataBaseWrappers(row));
            }
            return result;
        } catch(Exception e){
            throw new SelectException(e.getMessage());
        }
    }

    public static ArrayList<DataBaseWrappers> allBooks() throws Exception{
        try{
            ResultSet rs = new DataBaseConnection().select("SELECT * FROM Biblioteka.all_books");
            ArrayList<DataBaseWrappers> result = new ArrayList<>();
            while (rs.next()){
                ArrayList<TableFieldWrapper> row = new ArrayList<>();
                row.add(new TableFieldWrapper<Integer>("id", rs.getInt("id")));
                row.add(new TableFieldWrapper<String>("tytul", rs.getString("tytul")));
                row.add(new TableFieldWrapper<String>("url", rs.getString("url")));
                row.add(new TableFieldWrapper<Double>("star", rs.getDouble("star")));
                result.add(new DataBaseWrappers(row));
            }
            return result;
        }catch (Exception e){
            throw new SelectException(e.getMessage());
        }
    }

    public static ArrayList<DataBaseWrappers> detailBook(Integer bookId) throws Exception{
        String query = "SELECT k.Ksiazka_ID AS id, k.Tytul AS tytul, k.Rok_wydania AS rokWydania, k.isbn AS isbn, " +
                " av.Url AS url, Biblioteka.avg_star(k.Ksiazka_ID) AS star" +
                " FROM Biblioteka.Ksiazka AS k, Biblioteka.Avatar AS av, Biblioteka.Wydawnictwo AS wy" +
                " WHERE k.Ksiazka_ID = " + bookId +
                " AND av.Avatar_ID = k.Avatar" +
                " AND wy.Wydawnictwo_ID = k.Wydawnictwo;";
        try{
            ResultSet rs = new DataBaseConnection().select(query);
            ArrayList<DataBaseWrappers> result = new ArrayList<>();
            while (rs.next()){
                ArrayList<TableFieldWrapper> row = new ArrayList<>();
                row.add(new TableFieldWrapper<Integer>("id", rs.getInt("id")));
                row.add(new TableFieldWrapper<String>("tytul", rs.getString("tytul")));
                row.add(new TableFieldWrapper<String>("rokWydania", rs.getString("rokWydania")));
                row.add(new TableFieldWrapper<String>("isbn", rs.getString("isbn")));
                row.add(new TableFieldWrapper<String>("url", rs.getString("url")));
                row.add(new TableFieldWrapper<Double>("star", rs.getDouble("star")));
                result.add(new DataBaseWrappers(row));
            }
            return result;
        } catch (Exception e){
            throw new SelectException(e.getMessage());
        }
    }
}
