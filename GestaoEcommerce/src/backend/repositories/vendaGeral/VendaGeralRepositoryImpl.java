package backend.repositories.vendaGeral;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import backend.entities.geralEntity.ItemGeralEntity;
import backend.entities.geralEntity.VendaGeralEntity;
import backend.entities.geralEntity.VendaGeralFormatadaEntity;
import models.DAO;
import models.DbException;

public class VendaGeralRepositoryImpl extends DAO implements VendaGeralRepository {

	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
	@Override
	public List<VendaGeralFormatadaEntity> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT vs.ID_VENDA, vs.DATA_VENDA, vs.CLIENTE, ds.QTDE, i.COD_ITEM, ");
		sql.append(" ds.VALOR_UNITARIO, ds.VALOR_TOTAL, ds.VALOR_RECEBIDO, vs.STATUS, 'S' as CANAL ");
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
		if (Objects.nonNull(status)) {
			sql.append(" AND vs.STATUS = ? ");
			params.add(status);
		}
		sql.append(" UNION ");
		sql.append(" SELECT vml.ID_VENDA, vml.DATA_VENDA, vml.CLIENTE, dml.QTDE, i.COD_ITEM, ");
		sql.append(" dml.VALOR_UNITARIO, dml.VALOR_TOTAL, dml.VALOR_RECEBIDO, vml.STATUS, 'ML' as CANAL ");
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
    	String lastCliente = "";
		while (resultSet.next()) {
			ItemGeralEntity item = new ItemGeralEntity(
					resultSet.getString(5),
					resultSet.getInt(4),
					resultSet.getDouble(6),
					resultSet.getDouble(7),
					resultSet.getDouble(8));
			if (!lastId.equals(resultSet.getLong(1)) && !lastCliente.equals(resultSet.getString(3))) {
				vendas.add(new VendaGeralEntity(
						resultSet.getLong(1),
						resultSet.getDate(2),
						resultSet.getString(3),
						resultSet.getString(9),
						resultSet.getString(10)));
			}
			vendas.get(vendas.size() - 1).addItem(item);
			lastId = resultSet.getLong(1);
			lastCliente = resultSet.getString(3);
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
	
}