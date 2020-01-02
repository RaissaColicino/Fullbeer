package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.istack.internal.logging.Logger;

import beans.ProdottoB;

public class ProdottoDAO {
	private static final String TABLE_NAME="prodotto";
	static Logger log=Logger.getLogger("ProdottoMDebugger", null);
	
	
	public ProdottoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	//permette di salvare un nuovo prodotto
public void doSave(ProdottoB prodotto) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="insert into" + ProdottoDAO.TABLE_NAME
				+ " (codice, prezzo, nome, descrizione, img, qt,)"
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
	}
//permette di ottenere un prodotto in base al suo codice
public ProdottoB doRetrieveByCodice(String codice) throws SQLException{
	
	ProdottoB bean= new ProdottoB();
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	
	String selectSQL="select * from"+ ProdottoDAO.TABLE_NAME + "where codice=?";
	
	try{
	connection=DriverManagerConnectionPool.getConnection();
	preparedStatement=connection.prepareStatement(selectSQL);
	preparedStatement.setString(1, codice);

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
public void doUpdate(ProdottoB prodotto) throws SQLException {
	Connection connection= null;
	PreparedStatement preparedStatement = null;

	String updateSQL= "update"+ProdottoDAO.TABLE_NAME+"prezzo=?,"+"nome=?,"+"descrizione=?,"+"img=?,"+"qt=?"+"where codice=?";
	
	try{
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(updateSQL);
		

			preparedStatement.setString(1, prodotto.getId());
			preparedStatement.setDouble(2, prodotto.getPrezzo());
			preparedStatement.setString(3, prodotto.getNome());
			preparedStatement.setString(4, prodotto.getDescrizione());
			preparedStatement.setString(5, prodotto.getImmagine());
			preparedStatement.setInt(6, prodotto.getQt());
		
			preparedStatement.executeUpdate();
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
	
	
	}

//permette di eliminare un prodotto
public boolean doDelete(ProdottoB prodotto) throws SQLException {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result=0;

	String deleteSQL="delete from " + ProdottoDAO.TABLE_NAME + " where codice=?";

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
	
		return result!=0;
	}
}
