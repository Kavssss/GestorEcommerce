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
import models.DAO;
import models.DbException;

public class VendaShopeeRepositoryImpl extends DAO implements VendaShopeeRepository {

	PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
	public List<VendaShopeeFormatadaEntity> findVendas(Date dataInicio, Date dataFim,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
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
    
    @Override
    public void insertVenda(Date data, String cliente, String codItem, Integer qtde, Double valorUnitario, Double valorTotal,
    		Double valorRecebido) throws SQLException {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_VENDA_SHOPEE( ");
		sql.append(" DATA_VENDA, CLIENTE, STATUS) ");
		sql.append(" VALUES(?, ?, \"PENDENTE\");");
	}

    private String insertTbVenda() {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_VENDA_SHOPEE( ");
		sql.append(" DATA_VENDA, CLIENTE, STATUS) ");
		sql.append(" VALUES(?, ?, ?);");
		return sql.toString();
    }

    private String insertTbItem() {
		return "INSERT INTO TB_ITEM VALUES(?, ?, ?);";
    }
    
    private String insertTbDadosVenda() {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_DADOS_VENDA_SHOPEE VALUES( ");
		sql.append(" ?, ?, ?, ?, ?, ?);");
		return sql.toString();
    }

    private List<VendaShopeeFormatadaEntity> resultSetToVenda(ResultSet resultSet) throws SQLException {
    	List<VendaShopeeEntity> vendas = new ArrayList<>();
    	Long lastId = 0L;
		while (resultSet.next()) {
			ItemShopeeEntity item = new ItemShopeeEntity(
					resultSet.getString(6),
					resultSet.getInt(7),
					resultSet.getDouble(8),
					resultSet.getDouble(9),
					resultSet.getDouble(10));
			if (!lastId.equals(resultSet.getLong(1))) {
				vendas.add(new VendaShopeeEntity(
						resultSet.getLong(1),
						resultSet.getDate(2),
						resultSet.getString(3),
						resultSet.getString(4)));
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
  
}

