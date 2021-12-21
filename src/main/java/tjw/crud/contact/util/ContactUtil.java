package tjw.crud.contact.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContactUtil {
	private static String URL = System.getenv("DATABASE_URL").toString();
	private static String USER = System.getenv("DATABASE_USER").toString();
	private static String PASSWORD = System.getenv("DATABASE_PASSWORD").toString();
	private final static String DRIVER = "org.postgresql.Driver";
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("tudo certo");
		} catch (SQLException e) {
			System.out.println(" algo errado");

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
