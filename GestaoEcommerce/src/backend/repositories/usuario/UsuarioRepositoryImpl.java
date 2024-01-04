package backend.repositories.usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import frontend.utils.enums.TipoUsuario;
import frontend.views.utils.Alerts;
import javafx.scene.control.Alert.AlertType;
import models.DAO;
import models.DbException;

public class UsuarioRepositoryImpl extends DAO implements UsuarioRepository {
	
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	@Override
	public Boolean findUsuarioByName(String nome) {
		String sql = "SELECT 1 FROM TB_USUARIO WHERE NM_USUARIO = ?";
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
	public void insertNewUser(String user, String senha, TipoUsuario tipo) {
		String sql = "INSERT INTO TB_USUARIO(NM_USUARIO, SENHA_USUARIO, TP_USUARIO) VALUES (?, ?, ?)";
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, senha);
			preparedStatement.setInt(3, tipo.ordinal());
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			this.desconectar(this.conexao);

			Alerts.showAlert("Sucesso", "Novo usuário cadastrado com sucesso!", null, AlertType.INFORMATION);
		} catch (SQLException e) {
			Alerts.showAlert("Erro", null, "Não foi possível cadastrar o usuário.", AlertType.ERROR);
			throw new DbException(e.getMessage());
		}
	}
	
}
