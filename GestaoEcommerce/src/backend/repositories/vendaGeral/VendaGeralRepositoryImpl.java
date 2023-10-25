package backend.repositories.vendaGeral;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import backend.entities.geralEntity.ItemGeralEntity;
import backend.entities.geralEntity.VendaGeralEntity;
import backend.entities.geralEntity.VendaGeralFormatadaEntity;
import frontend.utils.Constants;
import models.DAO;
import models.DbException;

public class VendaGeralRepositoryImpl extends DAO implements VendaGeralRepository {

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	@Override
	public List<VendaGeralFormatadaEntity> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT vs.*, ds.*, 'S' as CANAL ");
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
		sql.append(" UNION ");
		sql.append(" SELECT vml.ID_VENDA, vml.DATA_VENDA, vml.CLIENTE, vml.STATUS, dml.ID_VENDA, dml.COD_ITEM, ");
		sql.append(" dml.QTDE, dml.VALOR_UNITARIO, dml.VALOR_TOTAL, dml.VALOR_RECEBIDO, dml.ID_DADO, 'ML' as CANAL ");
		sql.append(" FROM TB_VENDA_ML vml ");
		sql.append(" INNER JOIN TB_DADOS_VENDA_ML dml ON dml.ID_VENDA = vml.ID_VENDA ");
		sql.append(" INNER JOIN TB_ITEM i ON i.COD_ITEM = dml.COD_ITEM ");
		sql.append(" WHERE 1 = 1 ");
		if (Objects.nonNull(dataInicio) && Objects.nonNull(dataFim)) {
			sql.append(" AND (vml.DATA_VENDA BETWEEN ? AND ?) ");
			params.add(dataInicio);
			params.add(dataFim);
		}
		if (Objects.nonNull(qtde)) {
			sql.append(" AND dml.QTDE = ? ");
			params.add(qtde);
		}
		if (Objects.nonNull(codItem)) {
			sql.append(" AND i.COD_ITEM LIKE ? ");
			params.add(codItem);
		}
		if (Objects.nonNull(cliente)) {
			sql.append(" AND vml.CLIENTE LIKE ? ");
			params.add(cliente);
		}
		if (Objects.nonNull(status)) {
			sql.append(" AND vml.STATUS = ? ");
			params.add(status);
		}
		sql.append(" ORDER BY 2");
		try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql.toString());
   		 	System.out.println(preparedStatement.toString());
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
   		 	resultSet = preparedStatement.executeQuery();
   		 	return resultSetToVenda(resultSet);
        } catch (SQLException e) {
       	 	throw new DbException(e.getMessage());
        }
	}
	
	private List<VendaGeralFormatadaEntity> resultSetToVenda(ResultSet resultSet) throws SQLException {
    	List<VendaGeralEntity> vendas = new ArrayList<>();
    	Long lastId = 0L;
    	String lastCanal = "";
		while (resultSet.next()) {
			ItemGeralEntity item = new ItemGeralEntity(
					resultSet.getString("COD_ITEM"),
					resultSet.getInt("QTDE"),
					resultSet.getDouble("VALOR_UNITARIO"),
					resultSet.getDouble("VALOR_TOTAL"),
					resultSet.getDouble("VALOR_RECEBIDO"));
			if ((lastCanal.equals(resultSet.getString("CANAL")) && !lastId.equals(resultSet.getLong("ID_VENDA")))
					|| !lastCanal.equals(resultSet.getString("CANAL")))
				vendas.add(new VendaGeralEntity(
						resultSet.getLong("ID_VENDA"),
						resultSet.getDate("DATA_VENDA"),
						resultSet.getString("CLIENTE"),
						resultSet.getString("STATUS"),
						resultSet.getString("CANAL")));			
			vendas.get(vendas.size() - 1).addItem(item);
			lastId = resultSet.getLong("ID_VENDA");
			lastCanal = resultSet.getString("CANAL");
    	}
    	this.desconectar(this.conexao);
    	return buildVendaFormatada(vendas);
    }

	private List<VendaGeralFormatadaEntity> buildVendaFormatada(List<VendaGeralEntity> vendas) {
    	List<VendaGeralFormatadaEntity> list = new ArrayList<>();    	
    	for (VendaGeralEntity venda : vendas) {
    		for (int i = 0; i < venda.getItens().size(); i++) {
    			ItemGeralEntity item = venda.getItens().get(i);
//    			if (i > 0) {
//    	    		VendaGeralFormatadaEntity vendaItem = new VendaGeralFormatadaEntity(
//		    				item.getCodItem(),
//			    			item.getQtde(),
//			    			item.getValorUnitario(),
//			    			item.getValorTotal(),
//			    			item.getValorRecebido());
//    	    		list.add(vendaItem);
//    	    		continue;
//    	    	}
    	    	VendaGeralFormatadaEntity vendaItem = new VendaGeralFormatadaEntity(
    	    			venda.getId(),
    	    			venda.getData(),
    	    			venda.getCliente(),
    	    			venda.getStatus(),
    	    			venda.getCanal(),
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
	public List<String> findItens() throws SQLException {
		String sql = "SELECT COD_ITEM from TB_ITEM";
		try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql);
		 	System.out.println(preparedStatement.toString());
   		 	resultSet = preparedStatement.executeQuery();
   		 	List<String> result = new ArrayList<>();
   		 	while (resultSet.next())
   		 		result.add(resultSet.getString(1));
   		 	this.desconectar(this.conexao);
   		 	return result;
        } catch (SQLException e) {
       	 	throw new DbException(e.getMessage());
        }
	}
	
	@Override
	public void insertTbItem(String codItem) throws SQLException {
		String sql = "INSERT INTO TB_ITEM VALUES(?, ?, ?)";
		try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(sql);
   		 	preparedStatement.setString(1, codItem);
   		 	preparedStatement.setString(2, codItem.split("-")[0]);
   		 	preparedStatement.setString(3, codItem.split("-")[1]);
		 	System.out.println(preparedStatement.toString());
   		 	preparedStatement.executeUpdate();
   		 	this.desconectar(this.conexao);
        } catch (SQLException e) {
       	 	throw new DbException(e.getMessage());
        }
    }
	
}
