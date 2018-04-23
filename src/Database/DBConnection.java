package Database;

/**
 *
 * @author apurvakatti
 */
import java.sql.*;

public class DBConnection {
	// Change the parameters accordingly.
	//private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/dblp?useUnicode=true&characterEncoding=utf-8";
	private static String dbUrl = "jdbc:mysql://localhost:3306/abc?verifyServerCertificate=false&useSSL=true";

	private static String user = "root";
	private static String password = "Bangalore@007";

	public static Connection getConn() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			return DriverManager.getConnection(dbUrl, user, password);
		} catch (Exception e) {
			System.out.println("Error while opening a conneciton to database server: "
								+ e.getMessage());
			return null;
		}
	}
}

