package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	protected Connection conexao = null;
	//private String url;
	//private String user;
	//private String password;
	
	private String params;

	public DAO() {
		try {
			//url = "jdbc:mysql://localhost:3306/bd_vendas";
			//user = "root";
			//password = "123456";
			
			params = "jdbc:mysql://localhost:3306/bd_vendas?" +
					 "user=root&password=123456";
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	}

	/**
	 * Faz conexão ao Banco de Dados
	 * 
	 * @return Connection
	 */
	public Connection conectar() {
		try {
			//conexao = DriverManager.getConnection(url, user, password);
			
			conexao = DriverManager.getConnection(params);
			return conexao;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	/**
	 * Desconecta com o Banco de Dados
	 * 
	 * @param c - Connection
	 * @return void
	 */
	public void desconectar(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
