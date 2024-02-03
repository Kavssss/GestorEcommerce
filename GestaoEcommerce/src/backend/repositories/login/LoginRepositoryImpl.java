package backend.repositories.login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import backend.utils.TipoLogin;
import frontend.views.utils.Alerts;
import javafx.scene.control.Alert.AlertType;
import models.DAO;
import models.DbException;

public class LoginRepositoryImpl extends DAO implements LoginRepository {
	
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	@Override
	public Boolean isDuplicateUser(String nome) {
		String sql = "SELECT 1 FROM tb_login WHERE nome_login = ?";
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql);
			preparedStatement.setString(1, nome);
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			Boolean retorno = resultSet.next();
			this.desconectar(this.conexao);
			return retorno;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public Boolean isValidLogin(String usuario, String senha) {
		String sql = "SELECT 1 FROM tb_login WHERE nome_login = ? AND senha_login = ?";
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql);
			preparedStatement.setString(1, usuario);
			preparedStatement.setString(2, senha);
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			Boolean retorno = resultSet.next();
			this.desconectar(this.conexao);
			return retorno;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	@Override
	public void insertNewUser(String user, String senha, TipoLogin tipo) {
		String sql = "INSERT INTO tb_login(nome_login, senha_login, tp_login) VALUES (?, ?, ?)";
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, senha);
			preparedStatement.setInt(3, tipo.ordinal());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			this.desconectar(this.conexao);

			Alerts.showAlert("Novo usuário cadastrado com sucesso!", null, AlertType.INFORMATION);
		} catch (SQLException e) {
			Alerts.showAlert(null, "Não foi possível cadastrar o usuário.", AlertType.ERROR);
			throw new DbException(e.getMessage());
		}
	}
	
}
