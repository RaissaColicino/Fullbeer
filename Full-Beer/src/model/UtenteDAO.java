package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.UtenteB;
import java.util.logging.Logger;
public class UtenteDAO {
	static Logger log=Logger.getLogger("UtenteDAODebugger");
	private static final String TABLE_NAME="utente";
	public UtenteDAO() {
		// TODO Auto-generated constructor stub
	}
	
//funzionalità che permette di salvare un nuovo utente
public void doSave(UtenteB utente) throws SQLException{
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	String insertSQL="insert into " + UtenteDAO.TABLE_NAME
			+ " (nome, cognome, mail, password_, username) "
			+ "values (?, ?, ?, ?, ?)";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(insertSQL);

		preparedStatement.setString(1, utente.getNome());
		preparedStatement.setString(2, utente.getCognome());
		preparedStatement.setString(3, utente.getMail());
		preparedStatement.setString(4, utente.getPassword());
		preparedStatement.setString(5, utente.getUsername());
	

		preparedStatement.executeUpdate();

		connection.commit();
	} 
	finally {
		try {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	 
		finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	}

//permette di verificare che l'utente sia già registrato con delle specifiche credenziali
public UtenteB validate(UtenteB utente) throws SQLException {
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	IndirizzoDAO indirizzoModel=new IndirizzoDAO();
	
	
	String selectSQL="select * from " + UtenteDAO.TABLE_NAME;

	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			UtenteB bean=new UtenteB();
			bean.setUsername(rs.getString("username"));
			bean.setPassword(rs.getString("pwd"));
			
			if(bean.getUsername().equals(utente.getUsername())
					&& bean.getPassword().equals(utente.getPassword())) {
				
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setMail(rs.getString("mail"));
				IndirizzoDAO indirizzo= new IndirizzoDAO();
				bean.setIndirizzi(indirizzo.doRetrieveByUtente(bean));
				
				
				return bean;

			}
		}
	} 
	finally {
		try {
			if(preparedStatement!= null)
				preparedStatement.close();
		} 
		finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
	return null;
}


//permette di ottenere un utente specificando l'username
public UtenteB doRetrieveByUsername(String username)throws SQLException {
	UtenteB bean=new UtenteB();

	Connection connection=null;
	PreparedStatement preparedStatement=null;

	IndirizzoDAO indirizzoModel=new IndirizzoDAO();
	
	
	String selectSQL = "select * from " + UtenteDAO.TABLE_NAME + " where username=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, username);

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			bean.setUsername(rs.getString("username"));
			bean.setPassword(rs.getString("password"));
			bean.setNome(rs.getString("nome"));
			bean.setCognome(rs.getString("cognome"));
			bean.setMail(rs.getString("mail"));
			IndirizzoDAO indirizzo= new IndirizzoDAO();
			bean.setIndirizzi(indirizzo.doRetrieveByUtente(bean));
			
			
		}
	} 
	finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
	return bean;
}


//permette di aggiornare i dati di un utente già memorizzato
public void doUpdate(UtenteB utente)  throws SQLException {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	String updateSQL="update " + UtenteDAO.TABLE_NAME + " "
			   + " set nome=?, "
			   + " cognome=?, "
			   + " mail=?, "
			   + " pasword=?, "
			   + " username=?, "
			 
			   + " where username=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(updateSQL);

		preparedStatement.setString(1, utente.getNome());
		preparedStatement.setString(2, utente.getCognome());
		preparedStatement.setString(3, utente.getMail());
		preparedStatement.setString(4, utente.getPassword());
		preparedStatement.setString(5, utente.getUsername());
		
		preparedStatement.setString(8, utente.getUsername());

		preparedStatement.executeUpdate();

		connection.commit();
	} 
	finally {
		try {
			if(preparedStatement!=null)
				preparedStatement.close();
		}	 
		finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
}

//permette di eliminare un utente
public boolean doDelete(UtenteB utente)  throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	int result=0;

	String deleteSQL="delete from " + UtenteDAO.TABLE_NAME + " where username=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(deleteSQL);
		preparedStatement.setString(1, utente.getUsername());

		result=preparedStatement.executeUpdate();
	} 
	finally {
		try {
			if(preparedStatement!=null)
				preparedStatement.close();
		} 
		finally {
			DriverManagerConnectionPool.releaseConnection(connection);
		}
	}
	
	return result!=0;
}
}
