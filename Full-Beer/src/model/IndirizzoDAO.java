package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.logging.Logger;

import beans.IndirizzoB;
import beans.UtenteB;

public class IndirizzoDAO {
	private static final String TABLE_NAME="indirizzo";
	static Logger log=Logger.getLogger("IndirizzoMDebugger");
	
	public IndirizzoDAO() {
		// TODO Auto-generated constructor stub
	}

	//permette di salvare un indirizzo
public void doSave(IndirizzoB indirizzo) throws SQLException {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	log.info("doSave -> verica correttezza");
	
	if(indirizzo ==null || indirizzo.getUsername()==null || indirizzo.getUsername().equals("")
						|| indirizzo.getVia()==null|| indirizzo.getVia().equals("")
						|| indirizzo.getCap()==null|| indirizzo.getCap().equals("")
						|| indirizzo.getCitta()==null || indirizzo.getCitta().equals(""))
		
				return ;
	log.info("esecuzione query");
	String insertSQL="insert into " + IndirizzoDAO.TABLE_NAME
			+ " (via, cap, citta, username) "
			+ "values (?, ?, ?, ?)";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(insertSQL);

		preparedStatement.setString(1, indirizzo.getVia());
		preparedStatement.setString(2, indirizzo.getCap());
		preparedStatement.setString(3, indirizzo.getCitta());
		preparedStatement.setString(4, indirizzo.getUsername());

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
		log.info("IndirizzoDAO-> doSave Terminato");
}
	
	


/*
 * permette di ottenere tutti gli indirizzi di un utente
 */
public Set<IndirizzoB> doRetrieveByUtente(UtenteB utente) throws SQLException{
	
	log.info("IndirizzoDAO-> doRetriveByUtente");
	LinkedHashSet<IndirizzoB> indirizzi=new LinkedHashSet<IndirizzoB>();

	log.info("verifica correttezza username riferito all'utente");
	
	if(utente==null || utente.getUsername()==null || utente.getUsername().equals(""))
		return null;
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	String selectSQL = "select * from " + IndirizzoDAO.TABLE_NAME + " where username=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, utente.getUsername());

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			IndirizzoB temp=new IndirizzoB();
			
			temp.setVia(rs.getString("via"));
			temp.setCap(rs.getString("cap"));
			temp.setCittà(rs.getString("citta"));
			temp.setUsername(rs.getString("username"));
			
			
			indirizzi.add(temp);
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
	
	log.info("IndirizzoDAO -> doRetrieveByUtente è terminato");
	return indirizzi ;

}

/*
 * permette di cancellare un indirizzo
 */
public boolean doDelete(IndirizzoB indirizzo)throws SQLException {
	log.info("IndirizzoDAO->doDelete");
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	log.info("verifica correttezza indirizzo");
	if(indirizzo==null)
		return false;
	
	int result=0;

	String deleteSQL="delete from " + IndirizzoDAO.TABLE_NAME + " where username=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(deleteSQL);
		preparedStatement.setString(1, indirizzo.getUsername());

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
		log.info("IndirizzoDAO-> doDelete terminato");
	return result!=0;
}

}
