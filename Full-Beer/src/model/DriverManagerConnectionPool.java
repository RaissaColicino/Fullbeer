   package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverMaagerConnectionPool  {

	private static List<Connection> freeDbConnections;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	//private
	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String db = "fullbeer_db";
		String username = "root";
		String password = "antonio97";
		newConnection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fullbeer_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",username,password);
		// newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db, username, password);
		newConnection.setAutoCommit(false);
		return newConnection;
	}


	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			DriverMaagerConnectionPool.freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = DriverMaagerConnectionPool.getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = DriverMaagerConnectionPool.getConnection();
			}
		} else 
			connection = DriverMaagerConnectionPool.createDBConnection();		
		

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		DriverMaagerConnectionPool.freeDbConnections.add(connection);
	}
}
