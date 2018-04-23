
package Database;

/**
 *
 * @author apurvakatti
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JdbcRetrieve {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/abc?verifyServerCertificate=false&useSSL=true";
        String user = "root";
        String password = "Bangalore@007";
        
        String query = "SELECT title FROM paper";

        try (Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement pst = con.prepareStatement(query);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                System.out.print(rs.getString(1));
                System.out.println();
                //System.out.print(": ");
                //System.out.println(rs.getString(2));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(JdbcRetrieve.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
    


