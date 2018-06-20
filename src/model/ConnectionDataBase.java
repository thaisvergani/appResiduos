package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

	private static final String URL_MYSQL = "jdbc:postgresql://nutty-custard-apple.db.elephantsql.com:5432/mzkihhrx";
		
	private static final String DRIVER_CLASS = "org.postgresql.Driver";
		
	private static final String USER = "mzkihhrx";
	
	private static final String PASS = "OyXFF0rGsSHbUvtj_of14BDAYGT9vGLx";

	public static Connection getConnection() {
		System.out.println("Conectando ao Banco de Dados");

                try {
			Class.forName(DRIVER_CLASS);
			return DriverManager.getConnection(URL_MYSQL, USER, PASS);
		} catch (ClassNotFoundException e) {
			System.out.println("N�o foi poss�vel encontrar o driver JDBC");

			e.printStackTrace();
		} catch (SQLException e) {
                        System.out.println("N�o foi poss�vel conectar ao Banco de Dados");

			throw new RuntimeException(e);
		}
		return null;
	}
}