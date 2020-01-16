package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import model.DriverManagerConnectionPool;
import beans.ProdottoB;
import java.util.LinkedHashSet;
import java.util.Set;
public class ProdottoDAO {
	private static final String TABLE_NAME="prodotto";
	static Logger log=Logger.getLogger("ProdottoMDebugger");
	
	
	public ProdottoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	//permette di salvare un nuovo prodotto
public void doSave(ProdottoB prodotto) throws SQLException {
		log.info("ProdottoDAO-> doSave");
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		log.info("doSave-> PreCondizione");
		if(prodotto==null || prodotto.getId()==null|| prodotto.getId().equals("")
						  || prodotto.getNome()==null || prodotto.getNome().equals("")
						  || prodotto.getDescrizione()==null || prodotto.getDescrizione().equals("")
						  || prodotto.getImmagine()==null|| prodotto.getImmagine().equals("")
						  || prodotto.getQt()<1 || prodotto.getPrezzo()<1)
						
			return;
		
		
			String insertSQL=" insert into " + ProdottoDAO.TABLE_NAME
				+ " (codice, prezzo, nome, descrizione, img, qt)"
				+ "values (?, ?, ?, ?, ?, ?)";

	

		try {
				connection=DriverManagerConnectionPool.getConnection();
				preparedStatement=connection.prepareStatement(insertSQL);
		
				preparedStatement.setString(1, prodotto.getId());
				preparedStatement.setDouble(2, prodotto.getPrezzo());
				preparedStatement.setString(3, prodotto.getNome());
				preparedStatement.setString(4, prodotto.getDescrizione());
				preparedStatement.setString(5, prodotto.getImmagine());
				preparedStatement.setInt(6, prodotto.getQt());
				
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
		log.info("ProdottoDAO -> doSaveTerminato");
	}
//permette di ottenere un prodotto in base al suo codice
public ProdottoB doRetrieveByCodice(String b) throws SQLException{
	log.info("ProdottoDAO -> doRetrieveByCodice");
	
	ProdottoB bean= new ProdottoB();
	
	log.info("doRetrieveByCodice -> verifica PreCondizione");
	if(b==null || b.equals(""))
		return null;
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	
	String selectSQL="select * from "+ ProdottoDAO.TABLE_NAME + " where codice=?";
	
	try{
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, b);

			ResultSet rs=preparedStatement.executeQuery();	
	
			while(rs.next()){
		
								bean.setId(rs.getString("codice"));
								bean.setPrezzo(rs.getDouble("prezzo"));
								bean.setNome(rs.getString("nome"));
								bean.setDescrizione(rs.getString("descrizione"));
								bean.setImmagine(rs.getString("img"));
								bean.setQt(rs.getInt("qt"));
		}
	
	}finally{
		
			try {
				if(preparedStatement!=null)
				preparedStatement.close();
		} 
			finally {
			
			DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	

		return bean;

	}

//permette di aggiornare(salvare) un prodotto
public boolean doUpdate(ProdottoB prodotto) throws SQLException {
	log.info("ProdottoDAO->doUpdate");
	
	Connection connection= null;
	PreparedStatement preparedStatement = null;

	log.info("doUpdate: verifica PreCondizione");
	if(prodotto==null || prodotto.getId()==null|| prodotto.getId().equals("")
			  || prodotto.getNome()==null || prodotto.getNome().equals("")
			  || prodotto.getDescrizione()==null || prodotto.getDescrizione().equals("")
			  || prodotto.getImmagine()==null|| prodotto.getImmagine().equals("")
			  || prodotto.getQt()<1 || prodotto.getPrezzo()<1
			  || doRetrieveByCodice(prodotto.getId())==null)
			  
			  return false;
	int result=0;
	
	String updateSQL= "update "+ ProdottoDAO.TABLE_NAME + " " + "set codice=?,"+" prezzo=?,"+" nome=?,"+" descrizione=?,"+" img=?,"+ " qt=?"+" where codice=?";
	
	try{
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);
		

			preparedStatement.setString(1, prodotto.getId());
			preparedStatement.setDouble(2, prodotto.getPrezzo());
			preparedStatement.setString(3, prodotto.getNome());
			preparedStatement.setString(4, prodotto.getDescrizione());
			preparedStatement.setString(5, prodotto.getImmagine());
			preparedStatement.setInt(6, prodotto.getQt());
			preparedStatement.setString(7, prodotto.getId());
			result=preparedStatement.executeUpdate();
			connection.commit();
	} 
			
			finally{
	
				try{
					
						if(preparedStatement!=null)
				
							preparedStatement.close();
				}
				finally{
					DriverManagerConnectionPool.releaseConnection(connection);
					}
			}
	log.info("ProdottoDAO -> doUpdate terminato");
	
	return (result!=0);
	}

//permette di eliminare un prodotto
public boolean doDelete(ProdottoB prodotto) throws SQLException {
		log.info("ProdottoDAO->doDelete");
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		log.info("verifica PreCondizione");
		if(prodotto==null || prodotto.getId()==null|| prodotto.getId().equals("")
				  || prodotto.getNome()==null || prodotto.getNome().equals("")
				  || prodotto.getDescrizione()==null || prodotto.getDescrizione().equals("")
				  || prodotto.getImmagine()==null|| prodotto.getImmagine().equals("")
				  || prodotto.getQt()<1 || prodotto.getPrezzo()<1
				  || doRetrieveByCodice(prodotto.getId())==null)
		return false;
		
		int result=0;

	String deleteSQL=" delete from " + ProdottoDAO.TABLE_NAME + " where codice=?";

		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, prodotto.getId());

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
		log.info("ProdottoDAO-> doDeleteTerminato");
		
		return (result!=0);
	}

//funzione che permette di prelevare tutti i prodotti
public static Set <ProdottoB> doRetrieveAll() throws SQLException {
	log.info("ProdottoDAO->doRetrieveAll");
	
	LinkedHashSet<ProdottoB> prodotti = new LinkedHashSet<ProdottoB>();
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	
	
	String selectSQL = "SELECT * FROM " + ProdottoDAO.TABLE_NAME;

	try {
		 connection = DriverManagerConnectionPool.getConnection();
		preparedStatement=connection.prepareStatement(selectSQL);
		
		

		ResultSet rs = preparedStatement.executeQuery();	
		
		while (rs.next()) {
			ProdottoB bean= new ProdottoB();

			bean.setId(rs.getString("codice"));
			bean.setPrezzo(rs.getDouble("prezzo"));
			bean.setNome(rs.getString("nome"));
			bean.setDescrizione(rs.getString("descrizione"));
			bean.setImmagine(rs.getString("img"));
			bean.setQt(rs.getInt("qt"));
			
			prodotti.add(bean);
				}
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
	
							return prodotti;
}
}