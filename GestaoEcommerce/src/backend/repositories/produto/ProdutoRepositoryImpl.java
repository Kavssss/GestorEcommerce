package backend.repositories.produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import backend.entities.ProdutoEntity;
import backend.utils.Categoria;
import frontend.views.utils.Alerts;
import javafx.scene.control.Alert.AlertType;
import models.DAO;
import models.DbException;

public class ProdutoRepositoryImpl extends DAO implements ProdutoRepository {

	PreparedStatement preparedStatement;
	ResultSet resultSet;

	@Override
	public ProdutoEntity findById(Long id) throws SQLException {
		String sql = "SELECT * FROM tb_produto WHERE id_produto = ?";
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			ProdutoEntity produto = null;
			if (resultSet.next())
				produto = new ProdutoEntity(resultSet.getLong("id_produto"), resultSet.getString("cod_item"), resultSet.getInt("categoria"),
						resultSet.getString("modelo"), resultSet.getString("variacao"), resultSet.getString("descricao"),
						resultSet.getInt("estoque"), resultSet.getBoolean("fl_ativo"));
			this.desconectar(this.conexao);
			return produto;
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	@Override
	public void insertProduto(String codItem, Categoria categoria, String modelo, String variacao, String descricao, Integer estoque,
			Boolean isEmMassa) throws SQLException {
		if (findProdutos("%" + codItem + "%", null, null, null, null, null).isEmpty()) {
			StringBuilder sql = new StringBuilder("INSERT INTO tb_produto(cod_item, categoria, modelo, variacao, descricao, ");
			sql.append(" estoque, fl_ativo) VALUES(?, ?, ?, ?, ?, ?, 1)");
			try {
				this.conectar();
				preparedStatement = this.conexao.prepareStatement(sql.toString());
				preparedStatement.setString(1, codItem);
				preparedStatement.setInt(2, categoria.ordinal());
				preparedStatement.setString(3, modelo);
				preparedStatement.setString(4, variacao);
				preparedStatement.setString(5, descricao);
				preparedStatement.setInt(6, estoque);
				preparedStatement.executeUpdate();
				if (!isEmMassa)
					Alerts.showAlert("Produto cadastrado.", null, AlertType.INFORMATION);
			} catch (SQLException e) {
				throw new DbException(e);
			}
		} else {
			Alerts.showAlert("Produto duplicado", "O código " + codItem + " já está cadastrado.",
					AlertType.INFORMATION);
		}
	}

	@Override
	public void editProduto(Long id, String codItem, String modelo, String variacao, String descricao, Integer estoque)
			throws SQLException {
		StringBuilder sql = new StringBuilder("UPDATE tb_produto SET cod_item = ?, modelo = ?, variacao = ?, descricao = ?, ");
		sql.append(" estoque = ? WHERE id_produto = ?");
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql.toString());
			preparedStatement.setString(1, codItem);
			preparedStatement.setString(2, modelo);
			preparedStatement.setString(3, variacao);
			preparedStatement.setString(4, descricao);
			preparedStatement.setInt(5, estoque);
			preparedStatement.setLong(6, id);
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			Alerts.showAlert(null, "Produto " + codItem + " salvo com sucesso.", AlertType.INFORMATION);
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	@Override
	public List<ProdutoEntity> findProdutos(String codItem, Categoria categoria, String modelo, String variacao, String descricao,
			Integer estoque) throws SQLException {
		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM tb_produto ");
		sql.append(" WHERE 1 = 1 ");
		if (Objects.nonNull(codItem)) {
			sql.append(" AND cod_item LIKE ? ");
			params.add(codItem);
		}
		if (Objects.nonNull(categoria)) {
			sql.append(" AND categoria = ? ");
			params.add(categoria.ordinal());
		}
		if (Objects.nonNull(modelo)) {
			sql.append(" AND modelo LIKE ? ");
			params.add(modelo);
		}
		if (Objects.nonNull(variacao)) {
			sql.append(" AND variacao LIKE ? ");
			params.add(variacao);
		}
		if (Objects.nonNull(descricao)) {
			sql.append(" AND descricao LIKE ? ");
			params.add(descricao);
		}
		if (Objects.nonNull(estoque)) {
			sql.append(" AND estoque < ? ");
			params.add(estoque);
		}
		sql.append(" ORDER BY categoria, cod_item");
		
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql.toString());
			if (params.size() != 0)
				for (int i = 1; i <= params.size(); i++) {
					Object obj = params.get(i - 1); 
					if (obj instanceof String)
						preparedStatement.setString(i, (String) obj);
					else if (obj instanceof Integer)
						preparedStatement.setInt(i, (Integer) obj);
					else if (obj instanceof Categoria)
						preparedStatement.setInt(i, ((Categoria) obj).ordinal());
				}
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			return resultSetToVenda(resultSet);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private List<ProdutoEntity> resultSetToVenda(ResultSet resultSet) throws SQLException {
		List<ProdutoEntity> produtos = new ArrayList<>();
		while (resultSet.next()) {
			ProdutoEntity produto = new ProdutoEntity(resultSet.getLong("id_produto"), resultSet.getString("cod_item"),
					resultSet.getInt("categoria"), resultSet.getString("modelo"), resultSet.getString("variacao"),
					resultSet.getString("descricao"), resultSet.getInt("estoque"), resultSet.getBoolean("fl_ativo"));
			produtos.add(produto);
		}
		this.desconectar(this.conexao);
		return produtos;
	}

	@Override
	public void setFlagProduto(Long id, Integer flag) throws SQLException {
		String sql = "UPDATE tb_produto SET fl_ativo = ? WHERE id_produto = ?";
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql);
			preparedStatement.setInt(1, flag);
			preparedStatement.setLong(2, id);
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			this.desconectar(this.conexao);

			if (flag == 0)
				Alerts.showAlert(null, "Produto desativado.", AlertType.INFORMATION);
			else
				Alerts.showAlert(null, "Produto ativado.", AlertType.INFORMATION);
		} catch (SQLException e) {
			Alerts.showAlert("ERRO", "Não foi possível desativar o produto.", AlertType.ERROR);
			throw new DbException(e.getMessage());
		}
	}

}
