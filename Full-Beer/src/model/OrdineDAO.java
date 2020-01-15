package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import java.util.logging.Logger;

import beans.ComposizioneB;
import beans.OrdineB;

public class OrdineDAO {
	private static final String TABLE_NAME="ordine";
	static Logger log=Logger.getLogger("OrdineDAODebugger");
	public OrdineDAO() {
		// TODO Auto-generated constructor stub
	}

//permette di salvare un ordine
public void doSave(OrdineB ordine) throws SQLException {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	ComposizioneDAO composizioneModel=new ComposizioneDAO();
	
	String insertSQL="insert into " + OrdineDAO.TABLE_NAME
			+ " (fattura, data, importo, username, Stato, prodotto) "
			+ "values (?, ?, ?, ?, ?, ?)";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(insertSQL);

		preparedStatement.setString(1, ordine.getN_fattura());
		preparedStatement.setString(2, ordine.getDate());
		preparedStatement.setDouble(3, ordine.getImporto());
		preparedStatement.setString(4, ordine.getUsername());
		preparedStatement.setString(5, ordine.getStato());
		
		preparedStatement.executeUpdate();
		
		for(ComposizioneB comp: ordine.getComposizione()) {
			composizioneModel.doSave(comp);
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
}	


//permette di modificare lo stato di un ordine
public void modificaStato(OrdineB ordine, String stato) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String updateSQL="update " + OrdineDAO.TABLE_NAME + " "
				   + " set Stato=? "
				   + " where fattura=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(updateSQL);
		
		preparedStatement.setString(1, stato);
		preparedStatement.setString(2, ordine.getN_fattura());
		
		preparedStatement.executeUpdate();
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
public OrdineB doRetrieveByNumero(int numero) throws SQLException {
	
	OrdineB bean=new OrdineB();
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	ComposizioneDAO composizioneDAO=new ComposizioneDAO();
	
	String selectSQL = "select * from " + OrdineDAO.TABLE_NAME + " where fattura=?";

	try {
		connection=DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);
		preparedStatement.setInt(1, numero);

		ResultSet rs=preparedStatement.executeQuery();

		while (rs.next()) {
			bean.setN_fattura(rs.getString("fattura"));
			bean.setDate(rs.getString("data"));
			bean.setImporto(rs.getFloat("importo"));
			bean.setUsername(rs.getString("username"));
			bean.setStato(rs.getString("Stato"));
			ComposizioneDAO composizioneDAO1=null;
			bean.setComposizione(composizioneDAO1.doRetrieveByOrdine(bean));
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
public Set<OrdineB> doRetrieveIfAttivi(String order) throws SQLException{
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();

	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	ComposizioneDAO composizioneModel=new ComposizioneDAO();
	
	String selectSQL="select * from " + OrdineDAO.TABLE_NAME + " where Stato=? and stato=?";
	
	if (order!=null && !order.equals("")) {
		selectSQL+=" order by " + order;
	}

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
			ComposizioneDAO composizioneDAO=null;
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



public Set<OrdineB> doRetrieveAll(String order) throws SQLException{
	LinkedHashSet<OrdineB> ordini=new LinkedHashSet<OrdineB>();
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;

	ComposizioneDAO composizioneModel=new ComposizioneDAO();
	
	String selectSQL="select * from " + OrdineDAO.TABLE_NAME;
	
	if (order!=null && !order.equals("")) {
		selectSQL+=" order by " + order;
	}

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
			ComposizioneDAO composizioneDAO = null;
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

}
