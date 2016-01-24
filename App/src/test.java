import org.library.connection.db.postgresql.DataBaseConnection;
import org.library.connection.db.sqlite.CasheOperations;
import org.library.exceptions.CustomException;

/**
 * Created by sebas on 23.01.2016.
 */
public class test {
    public static void main(String[] args){
        try{
            CasheOperations c = new CasheOperations();
            DataBaseConnection cd = new DataBaseConnection();
            System.out.println("dziala");
        } catch (CustomException e){
            System.out.println(e.getMessage());
        }
    }
}
