package backend.repositories.item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import backend.dto.ItemDTO;
import backend.entities.ItemEntity;
import frontend.views.utils.Alerts;
import javafx.scene.control.Alert.AlertType;
import models.DAO;
import models.DbException;

public class ItemRepositoryImpl extends DAO implements ItemRepository {

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	@Override
	public ItemEntity findById(Long id) throws SQLException {
		String sql = "SELECT * FROM TB_ITEM WHERE ID_ITEM = ?";
		try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql);
   		 	preparedStatement.setLong(1, id);
		 	System.out.println(preparedStatement.toString());
   		 	resultSet = preparedStatement.executeQuery();
   		 	return resultSetToItem(resultSet);
        } catch (SQLException e) {
       	 	throw new DbException(e.getMessage());
        }
	}
	
	private ItemEntity resultSetToItem(ResultSet resultSet) throws SQLException {
		ItemEntity item = null;
		if (resultSet.next())
			item = new ItemEntity(
					resultSet.getLong("ID_ITEM"),
					resultSet.getString("COD_ITEM"),
					resultSet.getString("DESCRICAO"),
					resultSet.getBoolean("IS_ATIVO"));
    	this.desconectar(this.conexao);
    	return item != null ? item : null;
    }

	@Override
	public void insertItem(String codItem, String descricao) throws SQLException {
		StringBuilder sql = new StringBuilder("INSERT INTO TB_ITEM(COD_ITEM, MODELO, VARIACAO, DESCRICAO) ");
		sql.append(" VALUES(?, ?, ?, ?)");
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql.toString());
			preparedStatement.setString(1, codItem);
			preparedStatement.setString(2, codItem.split("-")[0]);
			preparedStatement.setString(3, codItem.split("-")[1]);
			preparedStatement.setString(4, descricao);
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void editItem(Long id, String codItem, String descricao) throws SQLException {
		StringBuilder sql = new StringBuilder("UPDATE TB_ITEM SET COD_ITEM = ?, MODELO = ?, VARIACAO = ?, DESCRICAO = ?");
		sql.append(" WHERE ID_VENDA = ?");
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql.toString());
			preparedStatement.setString(1, codItem);
			preparedStatement.setString(2, codItem.split("-")[0]);
			preparedStatement.setString(3, codItem.split("-")[1]);
			preparedStatement.setString(4, descricao);
			preparedStatement.setLong(5, id);
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<ItemDTO> findProdutos(String codItem, String modelo, String variacao, String descricao)
			throws SQLException {
		List<String> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM TB_ITEM ");
		sql.append(" WHERE 1 = 1 ");
		if(Objects.nonNull(codItem)) {
			sql.append(" COD_ITEM LIKE ? ");
			params.add(codItem);
		}
		if(Objects.nonNull(modelo)) {
			sql.append(" MODELO LIKE ? ");
			params.add(modelo);
		}
		if(Objects.nonNull(variacao)) {
			sql.append(" VARIACAO LIKE ? ");
			params.add(variacao);
		}
		if(Objects.nonNull(descricao)) {
			sql.append(" DESCRICAO LIKE ? ");
			params.add(descricao);
		}
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql.toString());
			if (params.size() != 0)
	   		 	for (int i = 1; i <= params.size(); i++)
	   		 		preparedStatement.setString(i, params.get(i));
		 	System.out.println(preparedStatement.toString());
		 	resultSet = preparedStatement.executeQuery();
			return resultSetToVenda(resultSet);
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private List<ItemDTO> resultSetToVenda(ResultSet resultSet) throws SQLException {
    	List<ItemDTO> itens = new ArrayList<>();
		while (resultSet.next()) {
			ItemDTO item = new ItemDTO(
					resultSet.getLong("ID_ITEM"),
					resultSet.getString("COD_ITEM"),
					resultSet.getString("MODELO"),
					resultSet.getString("VARIACAO"),
					resultSet.getString("DESCRICAO"),
					resultSet.getString("IS_ATIVO").equals("0") ? "Inativo" : "Ativo");
			itens.add(item);
    	}
    	this.desconectar(this.conexao);
    	return itens;
    }

	@Override
	public void disableProduto(Long id) throws SQLException {
		String sql = "UPDATE TB_ITEM SET IS_ATIVO = 0 WHERE ID_ITEM = ?";
		try {
   		 	this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql);
	 		preparedStatement.setLong(1, id);
	 		System.out.println(preparedStatement.toString());
	 		preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);
   		 	
	 		Alerts.showAlert("Sucesso", null, "Produto desativado.", AlertType.INFORMATION);
        } catch (SQLException e) {
        	Alerts.showAlert("Erro", "ERRO", "Não foi possível desativar o produto.", AlertType.ERROR);
       	 	throw new DbException(e.getMessage());
        }
	}

	@Override
	public void enableProduto(Long id) throws SQLException {
		String sql = "UPDATE TB_ITEM SET IS_ATIVO = 1 WHERE ID_ITEM = ?";
		try {
   		 	this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql);
	 		preparedStatement.setLong(1, id);
	 		System.out.println(preparedStatement.toString());
	 		preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);
   		 	
	 		Alerts.showAlert("Sucesso", null, "Produto ativado.", AlertType.INFORMATION);
        } catch (SQLException e) {
        	Alerts.showAlert("Erro", "ERRO", "Não foi possível ativar o produto.", AlertType.ERROR);
       	 	throw new DbException(e.getMessage());
        }
		
	}

}
