package model;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
import beans.ComposizioneB;
import beans.OrdineB;

public class ComposizioneDAO {
	private static final String TABLE_NAME="composizione";
	static Logger log=Logger.getLogger("ComposizioneDAODebugger");
	public ComposizioneDAO() {
		// TODO Auto-generated constructor stub
	}
	public void doSave(ComposizioneB composizione) throws SQLException {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		log.info("doSave -> verifico pre-condizioni");
		if(composizione==null || composizione.getN_fattura()==null || composizione.getN_fattura().equals("")
				|| composizione.getProdottoCodice()==null || composizione.getProdottoCodice().equals("")
				|| composizione.getNome_prodotto()==null || composizione.getNome_prodotto().equals("")
				|| composizione.getPrezzo()<1 ||  composizione.getQuantità()<1)
				
			return;
		
		String insertSQL="insert into " + ComposizioneDAO.TABLE_NAME
				+ " (nome_prodotto, prezzo, quantita, prodottoCodice, ordinen_fattura) "
				+ "values (?, ?, ?, ?, ?)";

		try {
			connection=DriverManagerConnectionPool.getConnection();
			preparedStatement=connection.prepareStatement(insertSQL);
			
			
			
			preparedStatement.setString(1, composizione.getNome_prodotto());
			preparedStatement.setDouble(2, composizione.getPrezzo());
			preparedStatement.setInt(3, composizione.getQuantità());
			preparedStatement.setString(4, composizione.getProdottoCodice());
			preparedStatement.setString(5, composizione.getN_fattura());
			
			
			
			
		

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
				
				log.info("doRetrieveByOrdine -> verifico pre-condizioni");
				if(ordine==null || ordine.getN_fattura()==null || ordine.getN_fattura().equals(""))
					return null;
				
				Connection connection=null;
				PreparedStatement preparedStatement=null;
				
				String selectSQL="select * from " + ComposizioneDAO.TABLE_NAME + " where ordinen_fattura=?";
				

				try {
					connection=DriverManagerConnectionPool.getConnection();
					preparedStatement=connection.prepareStatement(selectSQL);
					preparedStatement.setString(1, ordine.getN_fattura());

					ResultSet rs=preparedStatement.executeQuery();

					while(rs.next()) {
							ComposizioneB bean=new ComposizioneB();
						
							bean.setN_fattura(rs.getString("ordinen_fattura"));
							bean.setProdottoCodice(rs.getString("prodottoCodice"));
							bean.setNome_prodotto(rs.getString("nome_prodotto"));
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
