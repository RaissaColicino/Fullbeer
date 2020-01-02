package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		public 	Set<ComposizioneB> doRetrieveByOrdine(OrdineB ordine) throws SQLException{
				LinkedHashSet<ComposizioneBean> composizione=new LinkedHashSet<ComposizioneBean>();

				Connection connection=null;
				PreparedStatement preparedStatement=null;
				
				String selectSQL="select * from " + ComposizioneModel.TABLE_NAME + " where ordinen_fattura=?";
				

				try {
					connection=DriverManagerConnectionPool.getConnection();
					preparedStatement=connection.prepareStatement(selectSQL);
					preparedStatement.setString(1, ordine.getNumero());

					ResultSet rs=preparedStatement.executeQuery();

					while(rs.next()) {
							ComposizioneBean bean=new ComposizioneBean();
						
							bean.setOrdine(rs.getString("ordine"));
							bean.setProdotto(rs.getString("prodotto"));
							bean.setNomeProdotto(rs.getString("nomeprodotto"));
							bean.setPrezzoVen(rs.getDouble("prezzoven"));
							bean.setIvaVen(rs.getInt("ivaven"));
							bean.setQt(rs.getInt("qt"));
							bean.setTaglia(rs.getString("taglia"));
							
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
	}
	
}
