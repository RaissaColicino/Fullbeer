package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.logging.Logger;

import beans.ComposizioneB;
import beans.OrdineB;
import beans.UtenteB;

public class OrdineDAO {
	private static final String TABLE_NAME="ordine";
	static Logger log=Logger.getLogger("OrdineDAODebugger");
	public OrdineDAO() {
		// TODO Auto-generated constructor stub
	}

//permette di salvare un ordine
public void doSave(OrdineB ordine) throws SQLException {
	log.info("OrdineDAO->doSave");
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	log.info("doSave -> verifio pre-condizioni");
	if(ordine==null || ordine.getN_fattura()==null ||ordine.getN_fattura().equals("")
					|| ordine.getStato()==null ||ordine.getStato().equals("")
					|| ordine.getDate()==null ||ordine.getDate().equals("")
					||ordine.getUsername()==null ||ordine.getUsername().equals("")
					||ordine.getConsegna()==null || ordine.getConsegna().equals("")
					||ordine.getImporto()<1||ordine.getComposizione()==null)
					
		
					return ;
	
	ComposizioneDAO composizioneDAO=new ComposizioneDAO();
	
	String insertSQL="insert into " + OrdineDAO.TABLE_NAME
			+ " (fattura, data, importo, username, Stato, consegna) "
			+ "values (?, ?, ?, ?, ?, ?)";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(insertSQL);

		preparedStatement.setString(1, ordine.getN_fattura());
		preparedStatement.setString(2, ordine.getDate());
		preparedStatement.setDouble(3, ordine.getImporto());
		preparedStatement.setString(4, ordine.getUsername());
		preparedStatement.setString(5, ordine.getStato());
		preparedStatement.setString(6, ordine.getConsegna());
		
		preparedStatement.executeUpdate();
		connection.commit();
		
		log.info("doSave -> ordine salvato, procedo a salvare la composizione dell'ordine");
		for(ComposizioneB comp: ordine.getComposizione()) {
			composizioneDAO.doSave(comp);
		}
		
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
	log.info("OrdineDAO -> doSave terminato");
}	


//permette di modificare lo stato di un ordine
public void aggiornaStato(OrdineB ordine) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	
	
	log.info("aggiornaStato -> verifico pre-condizioni");
	if(ordine==null || ordine.getN_fattura()==null || ordine.getN_fattura().equals("")
			|| ordine.getStato()==null || ordine.getStato().equals("")
			||ordine.getConsegna()==null || ordine.getConsegna().equals("")
			|| doRetrieveByNumero(ordine.getN_fattura())==null)
		return;
	
	
	
	log.info("aggiornaStato -> eseguo query");
	String updateSQL="update " + OrdineDAO.TABLE_NAME + " "
				  + " set Stato=? "
				  + " consegna=?"
				   + " where fattura=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(updateSQL);
		
		preparedStatement.setString(1, ordine.getStato());
		preparedStatement.setString(2,ordine.getConsegna());
		preparedStatement.setString(3, ordine.getN_fattura());
		
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

//permette di pttenere un ordine specificando il numero
public OrdineB doRetrieveByNumero(String numero) throws SQLException {
	
	OrdineB bean=new OrdineB();
	
	log.info("doRetrieveByNumero -> verifico che il  numero sia corretto");
	if(numero==null || numero.equals(""))
		return null;
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	ComposizioneDAO composizioneDAO=new ComposizioneDAO();
	
	String selectSQL = "select * from " + OrdineDAO.TABLE_NAME + " where fattura=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, numero);

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			bean.setN_fattura(rs.getString("fattura"));
			bean.setDate(rs.getString("data"));
			bean.setImporto(rs.getDouble("importo"));
			bean.setUsername(rs.getString("username"));
			bean.setStato(rs.getString("Stato"));
			bean.setConsegna(rs.getString("consegna"));
			
			bean.setComposizione(composizioneDAO.doRetrieveByOrdine(bean));
			
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





//permette di generare la data di sottomissione dell'ordine
public String generatoreSottomissione() {
	log.info("Imposto la data di sottomissione come la data odierna");
	Date d=(Date) Calendar.getInstance().getTime();
	String format="yyyy-MM-dd";
	DateFormat df=new SimpleDateFormat(format);
	String sottomissione=df.format(d);
	
	return sottomissione;
}



//permette di ottenere gli ordini attivi
public Set<OrdineB> doRetrieveIfAttivi() throws SQLException{
	
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();

	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	ComposizioneDAO composizioneDAO=new ComposizioneDAO();
	
	
	String selectSQL="select * from " + OrdineDAO.TABLE_NAME + " where Stato=? or Stato=?";
	

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, OrdineB.ELABORAZIONE);
		preparedStatement.setString(2, OrdineB.SPEDIZIONE);

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			OrdineB bean=new OrdineB();
			bean.setN_fattura(rs.getString("fattura"));
			bean.setDate(rs.getString("data"));
			bean.setImporto(rs.getFloat("importo"));
			bean.setUsername(rs.getString("username"));
			bean.setStato(rs.getString("Stato"));
			bean.setConsegna(rs.getString("consegna"));
			
			bean.setComposizione(composizioneDAO.doRetrieveByOrdine(bean));
			
			ordini.add(bean);
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
	
	return ordini;
}



public Set<OrdineB> doRetrieveAll() throws SQLException{
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	ComposizioneDAO composizioneDAO=new ComposizioneDAO();
	
	String selectSQL="select * from " + OrdineDAO.TABLE_NAME;
	

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			OrdineB bean=new OrdineB();

			bean.setN_fattura(rs.getString("fattura"));
			bean.setDate(rs.getString("data"));
			bean.setImporto(rs.getInt("importo"));
			bean.setUsername(rs.getString("username"));
			bean.setStato(rs.getString("Stato"));
			bean.setConsegna(rs.getString("consegna"));
			
		
			bean.setComposizione(composizioneDAO.doRetrieveByOrdine(bean));
			
			ordini.add(bean);
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
	
	return ordini;
}

public Set<OrdineB> doRetrieveByUtente(UtenteB utente) throws SQLException {
	log.info("OrdineModel -> doRetrieveByUtente");
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();
	
	log.info("doRetrieveByUtente -> verifico pre-condizioni");
	if(utente==null || utente.getUsername()==null || utente.getUsername().equals(""))
		return null;
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	ComposizioneDAO composizioneDAO=new ComposizioneDAO();
	
	log.info("doRetrieveByUtente -> eseguo query");
	String selectSQL="select * from " + OrdineDAO.TABLE_NAME + " where username=?";
	


	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);
		preparedStatement.setString(1, utente.getUsername());

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			OrdineB bean=new OrdineB();

			bean.setN_fattura(rs.getString("fattura"));
			bean.setDate(rs.getString("data"));
			bean.setImporto(rs.getInt("importo"));
			bean.setUsername(rs.getString("username"));
			bean.setStato(rs.getString("Stato"));
			bean.setConsegna(rs.getString("consegna"));
			
			log.info("doRetrieveByUtente -> ottengo composizione ordine");
			bean.setComposizione(composizioneDAO.doRetrieveByOrdine(bean));
			
			ordini.add(bean);
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
	log.info("OrdineModel -> doRetrieveByUtente terminato");
	
	return ordini;
}
	public String generatoreNumero() throws SQLException {		
		log.info("generatoreNumero -> eseguo doCount");
		int numeroOrdini=doCount();
		numeroOrdini++;
		
		return String.format("%06d", numeroOrdini);
	}

	
	
	
	public int doCount() throws SQLException {
		log.info("OrdineModel -> doCount");
		int numeroOrdini=0;
				
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		log.info("doCount -> eseguo query");
		String selectSQL = "select count(*) as numeroRecord from " + OrdineDAO.TABLE_NAME;
		
		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			
			ResultSet rs=preparedStatement.executeQuery();
			
			while(rs.next()) {
				numeroOrdini=rs.getInt("numeroRecord");
			}
		}
	    finally {
	    	try {
	    		if (preparedStatement != null)
	    			preparedStatement.close();
	    	} 
	    	finally {
	    		DriverManagerConnectionPool.releaseConnection(connection);
	    	}
	    }
		log.info("OrdineModel -> doCount terminato");
		
		return numeroOrdini;
	}
	public String generatoreConsegna() {
		log.info("Imposto la data di consegna");
    	Calendar cal=Calendar.getInstance();
    	cal.add(Calendar.DATE, 3);
    	String formatOne="yyyy-MM-dd";
		DateFormat dfOne=new SimpleDateFormat(formatOne);
		String consegna=dfOne.format(cal.getTime());
		
		return consegna;
	}
}
	


