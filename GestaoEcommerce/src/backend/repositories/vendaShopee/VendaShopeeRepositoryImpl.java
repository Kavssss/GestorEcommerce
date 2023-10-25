package backend.repositories.vendaShopee;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import backend.entities.shopeeEntity.ItemShopeeEntity;
import backend.entities.shopeeEntity.VendaShopeeEntity;
import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;
import frontend.utils.Constants;
import frontend.views.utils.Alerts;
import javafx.scene.control.Alert.AlertType;
import models.DAO;
import models.DbException;

public class VendaShopeeRepositoryImpl extends DAO implements VendaShopeeRepository {

	PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
	public List<VendaShopeeFormatadaEntity> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
    	List<Object> params = new ArrayList<>();
    	StringBuilder sql = new StringBuilder("SELECT vs.*, ds.* ");
		sql.append(" FROM TB_VENDA_SHOPEE vs ");
		sql.append(" INNER JOIN TB_DADOS_VENDA_SHOPEE ds ON ds.ID_VENDA = vs.ID_VENDA ");
		sql.append(" INNER JOIN TB_ITEM i ON i.COD_ITEM = ds.COD_ITEM ");
		sql.append(" WHERE 1 = 1 ");
		if (Objects.nonNull(dataInicio) && Objects.nonNull(dataFim)) {
			sql.append(" AND (vs.DATA_VENDA BETWEEN ? AND ?) ");
			params.add(dataInicio);
			params.add(dataFim);
		}
		if (Objects.nonNull(qtde)) {
			sql.append(" AND ds.QTDE = ? ");
			params.add(qtde);
		}
		if (Objects.nonNull(codItem)) {
			sql.append(" AND i.COD_ITEM LIKE ? ");
			params.add(codItem);
		}
		if (Objects.nonNull(cliente)) {
			sql.append(" AND vs.CLIENTE LIKE ? ");
			params.add(cliente);
		}
		if (Objects.nonNull(status) && !status.equals(Constants.STATUS.TODOS)) {
			sql.append(" AND vs.STATUS = ? ");
			params.add(status);
		}
		sql.append(" ORDER BY 2");
    	try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql.toString());
		 	if (params.size() != 0)
	   		 	for (int i = 1; i <= params.size(); i++) {
	   		 		Object param = params.get(i-1);	   		 		
	   		 		if (param instanceof Integer)
		   		        preparedStatement.setInt(i, (Integer) param);
		   		    else if (param instanceof String)
		   		        preparedStatement.setString(i, (String) param);
		   		    else if (param instanceof Date)
		   		        preparedStatement.setDate(i, (java.sql.Date) param);
		   		}
		 	System.out.println(preparedStatement.toString());
   		 	resultSet = preparedStatement.executeQuery();
   		 	return resultSetToVenda(resultSet);
        } catch (SQLException e) {
       	 	throw new DbException(e.getMessage());
        }
	}
    
    @Override
    public void insertVenda(Date data, String cliente, String status, String codItem, Integer qtde, Double valorUnitario,
    		Double valorTotal, Double valorRecebido) throws SQLException {
    	try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(insertTbVenda());
   		 	preparedStatement.setDate(1, data);
   		 	preparedStatement.setString(2, cliente);
   		 	preparedStatement.setString(3, status);
   		 	System.out.println(preparedStatement.toString());
   		 	preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);
   		 	
   		 	Long lastId = findLastId();
   		 	
   		 	this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(insertTbDadosVenda());
   		 	preparedStatement.setLong(1, lastId);
	 		preparedStatement.setString(2, codItem);
	 		preparedStatement.setInt(3, qtde);
	 		preparedStatement.setDouble(4, valorUnitario);
	 		preparedStatement.setDouble(5, valorTotal);
	 		preparedStatement.setDouble(6, valorRecebido);
	 		System.out.println(preparedStatement.toString());
	 		preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);   		 	
   		 	
	 		Alerts.showAlert("Sucesso", "INSERIDO COM SUCESSO", "Venda cadastrada.", AlertType.INFORMATION);
        } catch (SQLException e) {
        	Alerts.showAlert("Erro", "ERRO", "Não foi possível cadastrar a venda.", AlertType.ERROR);
       	 	throw new DbException(e.getMessage());
        }
	}
    
    @Override
    public void insertItemVenda(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido)
			throws SQLException {
    	Long lastId = findLastId();
    	try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(insertTbDadosVenda());
   		 	preparedStatement.setLong(1, lastId);
	 		preparedStatement.setString(2, codItem);
	 		preparedStatement.setInt(3, qtde);
	 		preparedStatement.setDouble(4, valorUnitario);
	 		preparedStatement.setDouble(5, valorTotal);
	 		preparedStatement.setDouble(6, valorRecebido);
	 		System.out.println(preparedStatement.toString());
	 		preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);   		 	
   		 	
	 		Alerts.showAlert("Sucesso", "INSERIDO COM SUCESSO", "Venda cadastrada.", AlertType.INFORMATION);
    	} catch (SQLException e) {
    		Alerts.showAlert("Erro", "ERRO", "Não foi possível cadastrar a venda.", AlertType.ERROR);
       	 	throw new DbException(e.getMessage());
    	}
    }

    private String insertTbVenda() {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_VENDA_SHOPEE( ");
		sql.append(" DATA_VENDA, CLIENTE, STATUS) ");
		sql.append(" VALUES(?, ?, ?)");
		return sql.toString();
    }
    
    private String insertTbDadosVenda() {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_DADOS_VENDA_SHOPEE VALUES( ");
		sql.append(" ?, ?, ?, ?, ?, ?)");
		return sql.toString();
    }
    
    private Long findLastId() {
    	String sql = "SELECT MAX(ID_VENDA) FROM TB_VENDA_SHOPEE";
    	try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql);
		 	System.out.println(preparedStatement.toString());
   		 	resultSet = preparedStatement.executeQuery();
   		 	resultSet.next();
   		 	Long value = resultSet.getLong(1);
   		 	this.desconectar(this.conexao);
   		 	return value;
        } catch (SQLException e) {
       	 	throw new DbException(e.getMessage());
        }
    }

    private List<VendaShopeeFormatadaEntity> resultSetToVenda(ResultSet resultSet) throws SQLException {
    	List<VendaShopeeEntity> vendas = new ArrayList<>();
    	Long lastId = 0L;
		while (resultSet.next()) {
			ItemShopeeEntity item = new ItemShopeeEntity(
					resultSet.getString("COD_ITEM"),
					resultSet.getInt("QTDE"),
					resultSet.getDouble("VALOR_UNITARIO"),
					resultSet.getDouble("VALOR_TOTAL"),
					resultSet.getDouble("VALOR_RECEBIDO"));
			if (!lastId.equals(resultSet.getLong(1))) {
				vendas.add(new VendaShopeeEntity(
						resultSet.getLong("ID_VENDA"),
						resultSet.getDate("DATA_VENDA"),
						resultSet.getString("CLIENTE"),
						resultSet.getString("STATUS"),
						resultSet.getLong("ID_DADO")));
			}
			vendas.get(vendas.size()-1).addItem(item);
			lastId = resultSet.getLong(1);
    	}
    	this.desconectar(this.conexao);
    	return buildVendaFormatada(vendas);
    }
    
    private List<VendaShopeeFormatadaEntity> buildVendaFormatada(List<VendaShopeeEntity> vendas) {
    	List<VendaShopeeFormatadaEntity> list = new ArrayList<>();
    	
    	for (VendaShopeeEntity venda : vendas) {
    		for (int i = 0; i < venda.getItens().size(); i++) {
    			ItemShopeeEntity item = venda.getItens().get(i);
//    			if (i > 0) {
//    	    		VendaShopeeFormatadaEntity vendaItem = new VendaShopeeFormatadaEntity(
//		    				item.getCodItem(),
//			    			item.getQtde(),
//			    			item.getValorUnitario(),
//			    			item.getValorTotal(),
//			    			item.getValorRecebido());
//    	    		list.add(vendaItem);
//    	    		continue;
//    	    	}
    	    	VendaShopeeFormatadaEntity vendaItem = new VendaShopeeFormatadaEntity(
    	    			venda.getId(),
    	    			venda.getData(),
    	    			venda.getCliente(),
    	    			venda.getStatus(),
    	    			venda.getIdDado(),
    	    			item.getCodItem(),
    	    			item.getQtde(),
    	    			item.getValorUnitario(),
    	    			item.getValorTotal(),
    	    			item.getValorRecebido());
    	    	list.add(vendaItem);
    	    }
    	}
    	return list;
    }

	@Override
	public VendaShopeeEntity findById(Long id) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT vs.*, ds.* ");
		sql.append(" FROM TB_VENDA_SHOPEE vs ");
		sql.append(" INNER JOIN TB_DADOS_VENDA_SHOPEE ds ON ds.ID_VENDA = vs.ID_VENDA ");
		sql.append(" INNER JOIN TB_ITEM i ON i.COD_ITEM = ds.COD_ITEM ");
		sql.append(" WHERE vs.ID_VENDA = ?");
		try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql.toString());
   		 	preparedStatement.setLong(1, id);
		 	System.out.println(preparedStatement.toString());
   		 	resultSet = preparedStatement.executeQuery();
   		 	return resultSetToVendaEdit(resultSet);
        } catch (SQLException e) {
       	 	throw new DbException(e.getMessage());
        }
	}
	
	private VendaShopeeEntity resultSetToVendaEdit(ResultSet resultSet) throws SQLException {
		resultSet.next();
		VendaShopeeEntity venda = new VendaShopeeEntity(
					resultSet.getLong("ID_VENDA"),
					resultSet.getDate("DATA_VENDA"),
					resultSet.getString("CLIENTE"),
					resultSet.getString("STATUS"),
					resultSet.getLong("ID_DADO"));
		venda.addItem(new ItemShopeeEntity(
				resultSet.getString("COD_ITEM"),
				resultSet.getInt("QTDE"),
				resultSet.getDouble("VALOR_UNITARIO"),
				resultSet.getDouble("VALOR_TOTAL"),
				resultSet.getDouble("VALOR_RECEBIDO")));
    	while (resultSet.next()) {
    		ItemShopeeEntity item = new ItemShopeeEntity(
    				resultSet.getString("COD_ITEM"),
    				resultSet.getInt("QTDE"),
    				resultSet.getDouble("VALOR_UNITARIO"),
    				resultSet.getDouble("VALOR_TOTAL"),
    				resultSet.getDouble("VALOR_RECEBIDO"));
    		venda.addItem(item);
    	}			
    	this.desconectar(this.conexao);
    	return venda;
    }

	@Override
	public void editVenda(Long idVenda, Long idDado, Date data, String cliente, String status, String codItem, Integer qtde,
			Double valorUnitario, Double valorTotal, Double valorRecebido) throws SQLException {
		try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(updateTbVenda());
   		 	preparedStatement.setDate(1, data);
   		 	preparedStatement.setString(2, cliente);
   		 	preparedStatement.setString(3, status);
   		 	preparedStatement.setLong(4, idVenda);
   		 	System.out.println(preparedStatement.toString());
   		 	preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);
   		 	this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(updateTbDadosVenda());
	 		preparedStatement.setString(1, codItem);
	 		preparedStatement.setInt(2, qtde);
	 		preparedStatement.setDouble(3, valorUnitario);
	 		preparedStatement.setDouble(4, valorTotal);
	 		preparedStatement.setDouble(5, valorRecebido);
	 		preparedStatement.setLong(6, idDado);
	 		System.out.println(preparedStatement.toString());
	 		preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);
   		 	
	 		Alerts.showAlert("Sucesso", "EDITADO COM SUCESSO", "Venda editada.", AlertType.INFORMATION);
        } catch (SQLException e) {
        	Alerts.showAlert("Erro", "ERRO", "Não foi possível editar a venda.", AlertType.ERROR);
       	 	throw new DbException(e.getMessage());
        }
	}
	
	private String updateTbVenda() {
		StringBuilder sql = new StringBuilder("UPDATE TB_VENDA_SHOPEE ");
		sql.append(" SET DATA_VENDA = ?, CLIENTE = ?, STATUS = ? ");
		sql.append(" WHERE ID_VENDA = ?");
		return sql.toString();
	}
	
    private String updateTbDadosVenda() {
    	StringBuilder sql = new StringBuilder("UPDATE TB_DADOS_VENDA_SHOPEE ");
		sql.append(" SET COD_ITEM = ?, QTDE = ?, VALOR_UNITARIO = ?, VALOR_TOTAL = ?, VALOR_RECEBIDO = ? ");
		sql.append(" WHERE ID_DADO = ? ");
		return sql.toString();
    }

	@Override
	public void deleteVenda(Long idVenda, Long idDado) throws SQLException {		
		try {
   		 	this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(deleteTbDadosVenda());
	 		preparedStatement.setLong(1, idDado);
	 		System.out.println(preparedStatement.toString());
	 		preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);
   		 	this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(deleteTbVenda());
   		 	preparedStatement.setLong(1, idVenda);
   		 	System.out.println(preparedStatement.toString());
   		 	preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);
   		 	
	 		Alerts.showAlert("Sucesso", null, "Venda excluída.", AlertType.INFORMATION);
        } catch (SQLException e) {
        	Alerts.showAlert("Erro", "ERRO", "Não foi possível excluir a venda.", AlertType.ERROR);
       	 	throw new DbException(e.getMessage());
        }
	}
	
	private String deleteTbVenda() {
		return "DELETE FROM TB_VENDA_SHOPEE WHERE ID_VENDA = ?";
	}
	
	private String deleteTbDadosVenda() {
		return "DELETE FROM TB_DADOS_VENDA_SHOPEE WHERE ID_DADO = ?";
	}
  
}

