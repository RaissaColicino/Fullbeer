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
	log.info("UtenteDao -> doSave");
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	log.info("verifica correttezza username");
	
	if(utente==null || utente.getUsername()==null || utente.getUsername().equals("")
					|| utente.getPassword()==null || utente.getPassword().equals("")
					|| utente.getNome()==null|| utente.getNome().equals("")
					|| utente.getCognome()==null||utente.getCognome().equals("")
					|| utente.getMail()==null || utente.getMail().equals(""))
		
			return;
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
		
		log.info("doSave controllo terminato");
	}
	}

//permette di verificare che l'utente sia già registrato con delle specifiche credenziali
public UtenteB validate(UtenteB utente) throws SQLException {
	log.info("UtenteDAO-> validate");
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	log.info("validate-> verifica pre-condizione");
	if(utente==null || utente.getUsername()==null || utente.getUsername().equals("")
				   || utente.getPassword()==null || utente.getPassword().equals(""))
			return null;
	
	RuoloDAO ruoloDAO = new RuoloDAO();
	IndirizzoDAO indirizzoModel=new IndirizzoDAO();
	
	
	String selectSQL="select * from " + UtenteDAO.TABLE_NAME;

	try {
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			UtenteB bean=new UtenteB();
			bean.setUsername(rs.getString("username"));
			bean.setPassword(rs.getString("password_"));
			
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
	log.info("validate utente non presente");
	return null;
}


//permette di ottenere un utente specificando l'username
public UtenteB doRetrieveByUsername(String username)throws SQLException {
	log.info("utenteDAO->doRetrieveByUsername");
	UtenteB bean=new UtenteB();

	
	log.info("controllo username corretto");
	if(username==null || username.equals(""))
			return null;
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	RuoloDAO ruoloDAO = new RuoloDAO();
	IndirizzoDAO indirizzoModel=new IndirizzoDAO();
	
	
	String selectSQL = "select * from " + UtenteDAO.TABLE_NAME + " where username=?";

	
	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, username);

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			bean.setUsername(rs.getString("username"));
			bean.setPassword(rs.getString("password_"));
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
	log.info("doRetrieveByUsername eseguito e terminato");
	return bean;

}


//permette di aggiornare i dati di un utente già memorizzato
public boolean doUpdate(UtenteB utente)  throws SQLException {
	log.info("utenteDAO -> doUpdate");
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	int result=0;
	
	log.info("doUpdate -> verifico correttezza username");
	if(utente==null || utente.getUsername()==null || utente.getUsername().equals("")
			|| utente.getPassword()==null || utente.getPassword().equals("")
			|| utente.getNome()==null || utente.getNome().equals("")
			|| utente.getCognome()==null || utente.getCognome().equals("")
			|| utente.getMail()==null || utente.getMail().equals("")
			|| doRetrieveByUsername(utente.getUsername())==null)
		return false;
	
	String updateSQL="update " + UtenteDAO.TABLE_NAME + " "
			   + " set nome=?, "
			   + " cognome=?, "
			   + " mail=?, "
			   + " password_=?, "
			   + " username=? "
			   + " where username=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(updateSQL);

		preparedStatement.setString(1, utente.getNome());
		preparedStatement.setString(2, utente.getCognome());
		preparedStatement.setString(3, utente.getMail());
		preparedStatement.setString(4, utente.getPassword());
		preparedStatement.setString(5, utente.getUsername());
		
		preparedStatement.setString(6, utente.getUsername());

		result=preparedStatement.executeUpdate();

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
	log.info("doUpdate eseguito e terminato");
	
	return (result!=0);
}

//permette di eliminare un utente
public boolean doDelete(UtenteB utente)  throws SQLException {
	log.info("UtenteDAO -> doDelete");
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	
	if(utente==null || doRetrieveByUsername(utente.getUsername())==null)
		return false;
	
	
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
	log.info("doDelete eseguito e terminato");
	return (result!=0);
	
}
}
