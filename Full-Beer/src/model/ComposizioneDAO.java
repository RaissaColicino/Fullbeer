package model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.sun.istack.internal.logging.Logger;

import beans.ComposizioneB;
import beans.OrdineB;

public class ComposizioneDAO {
	private static final String TABLE_NAME="composizione";
	static Logger log=Logger.getLogger("ComposizioneDAODebugger", null);
	public ComposizioneDAO() {
		// TODO Auto-generated constructor stub
	}
	public void doSave(ComposizioneB composizione) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		String insertSQL="insert into " + ComposizioneDAO.TABLE_NAME
				+ " (ordinen_fattura, prodotto_codice, nome_prodotto, prezzo, quantita) "
				+ "values (?, ?, ?, ?, ?)";

		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, composizione.getN_fattura());
			preparedStatement.setString(2, composizione.getProdottoCodice());
			preparedStatement.setString(3, composizione.getNome_prodotto());
			preparedStatement.setDouble(4, composizione.getPrezzo());
			preparedStatement.setInt(6, composizione.getQuantità());
		

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
		public 	Set<ComposizioneB> doRetrieveByOrdine(OrdineB ordine) throws SQLException{
				LinkedHashSet<ComposizioneB> composizione=new LinkedHashSet<ComposizioneB>();

				Connection connection=null;
				PreparedStatement preparedStatement=null;
				
				String selectSQL="select * from " + ComposizioneDAO.TABLE_NAME + " where ordinen_fattura=?";
				

				try {
					connection=DriverManagerConnectionPool.getConnection();
					preparedStatement=connection.prepareStatement(selectSQL);
					preparedStatement.setInt(1, ordine.getN_fattura());

					ResultSet rs=preparedStatement.executeQuery();

					while(rs.next()) {
							ComposizioneB bean=new ComposizioneB();
						
							bean.setN_fattura(rs.getInt("ordine"));
							bean.setProdottoCodice(rs.getString("prodotto"));
							bean.setNome_prodotto(rs.getString("nomeprodotto"));
							bean.setPrezzo(rs.getDouble("prezzo"));
							bean.setQuantità(rs.getInt("quantita"));
							
							
							composizione.add(bean);
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
				
				return composizione;
			}
		
}
=======
import com.sun.istack.internal.logging.Logger;

public class ComposizioneDAO {

	static Logger log=Logger.getLogger("ComposizioneMDebugger", null);
	
	public ComposizioneDAO() {
		// TODO Auto-generated constructor stub
	}

	
}

