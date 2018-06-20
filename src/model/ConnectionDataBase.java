package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

	private static final String URL_MYSQL = "jdbc:postgres://mzkihhrx:OyXFF0rGsSHbUvtj_of14BDAYGT9vGLx@nutty-custard-apple.db.elephantsql.com:5432/mzkihhrx";
		
	private static final String DRIVER_CLASS = "org.postgresql.Driver";
		
	private static final String USER = "mzkihhrx";
	
	private static final String PASS = "OyXFF0rGsSHbUvtj_of14BDAYGT9vGLx";

	public static Connection getConnection() {
		System.out.println("Conectando ao Banco de Dados");

                try {
			Class.forName(DRIVER_CLASS);
			return DriverManager.getConnection(URL_MYSQL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}