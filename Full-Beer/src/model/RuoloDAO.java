package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;


import beans.RuoloB;
import beans.UtenteB;

public class RuoloDAO {
	
	private static final String TABLE_NAME="ruolo";
	static Logger log=Logger.getLogger("RuoloDAODebugger");

	public RuoloDAO() {
		
			}

		//permette di salvare un ruolo
		public void doSave(RuoloB ruolo) throws SQLException {
		
			log.info("RuoloDAO -> doSave");
			Connection connection=null;
			PreparedStatement preparedStatement=null;
		
			log.info("RuoloModel -> verifica correttezza di ruolo da salvare");
			
			if(ruolo==null || ruolo.getUsername()==null || ruolo.getUsername().equals("")
						   || ruolo.getRuolo()==null || ruolo.getRuolo().equals(""))

						return ;
			
			String insertSQL="insert into " + RuoloDAO.TABLE_NAME+ "(tipo, username) values (?, ?)";
		
			try{
					connection=DriverManagerConnectionPool.getConnection();
					preparedStatement=connection.prepareStatement(insertSQL);
					
					preparedStatement.setString(1,ruolo.getRuolo());
					preparedStatement.setString(2,ruolo.getUsername());
					
					preparedStatement.executeUpdate();
					connection.commit();
					
			}finally{
				
					try{
							if(preparedStatement!=null)
								preparedStatement.close();
							
					}
					
						finally{
							
							DriverManagerConnectionPool.releaseConnection(connection);
							
							}
							
			}	
					log.info("RuoloDao-> doSaveTerminato");	
						
					}
				
				
				
				
				
				
			

		//HashMap associa ad ogni chiave(nel nostro caso String) un valore (il ruolo)

		public static Map<String, RuoloB> doRetrieveByUtente(UtenteB utente) throws SQLException{
	
			log.info("RuoloDAO -> doRetrieveByUtente");
			LinkedHashMap<String, RuoloB> ruoli =new LinkedHashMap<String, RuoloB>();

			log.info("RuoloDAO-> verifica correttezza username dell' utente");
			
			if(utente==null || utente.getUsername()==null || utente.getUsername().equals(""))
			
					return null;
			
			Connection connection=null;
			PreparedStatement preparedStatement=null;
			
			String selectSQL="select * from " + RuoloDAO.TABLE_NAME + " where username=?";
			
				try{
					 	connection=DriverManagerConnectionPool.getConnection();
					 	preparedStatement=connection.prepareStatement(selectSQL);
					 	preparedStatement.setString(1,utente.getUsername());
					 	
					 	ResultSet rs= preparedStatement.executeQuery();
					 	
					 	while(rs.next()){
					 						RuoloB bean= new RuoloB();
					 						
					 						bean.setRuolo(rs.getString("tipo"));
					 						bean.setUsername(rs.getString("username"));
					 						
					 						
											ruoli.put("" + bean.getRuolo(),bean);
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
										
									log.info("Ho ottenuto i ruoli, ruoli vuota: " + ruoli.isEmpty());
							
										return ruoli;
		}
	}