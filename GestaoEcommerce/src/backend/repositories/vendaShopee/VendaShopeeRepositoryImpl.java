package backend.repositories.vendaShopee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import backend.entities.shopeeEntity.ItemShopeeEntity;
import backend.entities.shopeeEntity.VendaShopeeEntity;
import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;
import models.DAO;
import models.DbException;

public class VendaShopeeRepositoryImpl extends DAO implements VendaShopeeRepository {

	PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
	public List<VendaShopeeFormatadaEntity> findAll() throws SQLException {
    	try {
    		this.conectar();
   		 	preparedStatement = this.conexao.prepareStatement(getQuerySearch());
   		 	resultSet = preparedStatement.executeQuery();
   		 	return resultSetToVenda(resultSet);
        } catch (SQLException e) {
       	 	throw new DbException(e.getMessage());
        }
	}

    @Override
    public List<VendaShopeeFormatadaEntity> findVendasPorPeriodo(Integer dia, Integer mes, Integer ano) {
    	StringBuilder sql = new StringBuilder(getQuerySearch());
    	if (Objects.nonNull(ano))
    		sql.append(" AND year(venda.DATA_VENDA) = ?");
    	if (Objects.nonNull(mes))
    		sql.append(" AND month(venda.DATA_VENDA) = ? ");
    	if (Objects.nonNull(dia))
    		sql.append(" AND day(venda.DATA_VENDA) = ? ");
    	try {
    		this.conectar();            
            preparedStatement = this.conexao.prepareStatement(sql.toString());
            setPstFindPeriodo(preparedStatement, ano, mes, dia);
            resultSet = preparedStatement.executeQuery();
            return resultSetToVenda(resultSet);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    @Override
    public List<VendaShopeeFormatadaEntity> findVendasPorCliente(String cliente) {
    	StringBuilder sql = new StringBuilder(getQuerySearch());
    	sql.append(" AND venda.CLIENTE LIKE %?%");
    	try {
    		this.conectar();            
            preparedStatement = this.conexao.prepareStatement(sql.toString());
            preparedStatement.setString(1, cliente);
            resultSet = preparedStatement.executeQuery();
            return resultSetToVenda(resultSet);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    
    private void setPstFindPeriodo(PreparedStatement preparedStatement, Integer ano, Integer mes, Integer dia) throws SQLException {
    	if (Objects.nonNull(ano))
    		preparedStatement.setString(1, ano.toString());
	    	if (Objects.nonNull(mes))
	            preparedStatement.setString(2, mes.toString());
		    	if (Objects.nonNull(dia))
		            preparedStatement.setString(3, dia.toString());
	    	else
	    		if (Objects.nonNull(dia))
		            preparedStatement.setString(2, dia.toString());
    	else
    		if (Objects.nonNull(mes))
	            preparedStatement.setString(1, mes.toString());
		    	if (Objects.nonNull(dia))
		            preparedStatement.setString(2, dia.toString());
	    	else
	    		if (Objects.nonNull(dia))
		            preparedStatement.setString(1, dia.toString());
    }
    
    private String getQuerySearch() {
    	StringBuilder sql = new StringBuilder("SELECT venda.ID_VENDA, venda.DATA_VENDA, venda.CONTA, dados.QTDE, item.COD_ITEM, ");
    	sql.append(" venda.CLIENTE, dados.VALOR_UNITARIO, dados.VALOR_TOTAL, dados.VALOR_RECEBIDO, venda.STATUS ");
    	sql.append(" FROM TB_VENDA_SHOPEE venda ");
    	sql.append(" INNER JOIN TB_DADOS_VENDA_SHOPEE dados ON dados.ID_VENDA = venda.ID_VENDA ");
    	sql.append(" INNER JOIN TB_ITEM item ON item.COD_ITEM = dados.COD_ITEM ");
    	sql.append(" WHERE 1 = 1 ");
    	return sql.toString();
    }

//    private void insertTbVenda(VendaShopeeEntity venda) throws SQLException {
//    	StringBuilder sql = new StringBuilder("INSERT INTO TB_VENDA_SHOPEE(");
//    	sql.append("DATA_VENDA, CONTA, CLIENTE, STATUS) VALUES");
//		sql.append("(");
//    	sql.append(colocaAspas(venda.getData().toString())).append(", ");
//    	sql.append(colocaAspas(venda.getConta())).append(", ");
//    	sql.append(colocaAspas(venda.getCliente())).append(", ");
//    	sql.append(colocaAspas(venda.getStatus()));
//    	sql.append(")");
//    	executeQueryUpdate(sql.toString());
//    }
//
//    private void insertTbItem(VendaShopeeEntity venda) throws SQLException {
//    	StringBuilder sql = new StringBuilder("INSERT INTO TB_ITEM VALUES");
//    	Integer sizeQuery = sql.toString().length();
//		for (ItemShopee item : venda.getItens()) {
//    		if (!verificaItemExistente(item.getCodItem())) {
//				sql.append("(");
//				sql.append(colocaAspas(item.getCodItem())).append(", ");
//		    	sql.append(colocaAspas(item.getModelo())).append(", ");
//		    	sql.append(colocaAspas(item.getVariacao()));
//		    	sql.append("), ");
//    		}
//		}
//		if (sql.toString().length() > sizeQuery) {
//			removeVirgula(sql);
//			executeQueryUpdate(sql.toString());
//		}
//    }
//    
//    private void insertTbDadosVenda(VendaShopeeEntity venda) throws SQLException {
//    	StringBuilder sql = new StringBuilder("INSERT INTO TB_DADOS_VENDA_SHOPEE VALUES ");
//    	for (ItemShopee item : venda.getItens()) {
//    		sql.append("(");
//    		sql.append(colocaAspas(findLastId(Constants.SHOPEE))).append(", ");
//	    	sql.append(colocaAspas(item.getCodItem())).append(", ");
//	    	sql.append(colocaAspas(item.getQtde().toString())).append(", ");
//	    	sql.append(colocaAspas(item.getValorUnitario().toString())).append(", ");
//	    	sql.append(colocaAspas(item.getValorTotal().toString())).append(", ");
//	    	sql.append(colocaAspas(item.getValorRecebido().toString()));
//	    	sql.append("), ");
//    	}
//    	removeVirgula(sql);
//    	executeQueryUpdate(sql.toString());
//    }

    private List<VendaShopeeFormatadaEntity> resultSetToVenda(ResultSet resultSet) throws SQLException {
    	List<VendaShopeeEntity> vendas = new ArrayList<>();
    	Long lastId = 0L;
		while (resultSet.next()) {
			ItemShopeeEntity item = new ItemShopeeEntity(
					resultSet.getString(5),
					resultSet.getInt(4),
					resultSet.getDouble(7),
					resultSet.getDouble(8),
					resultSet.getDouble(9));
			if (!lastId.equals(resultSet.getLong(1))) {
				vendas.add(new VendaShopeeEntity(
						resultSet.getLong(1),
						resultSet.getDate(2),
						resultSet.getString(3),
						resultSet.getString(6),
						resultSet.getString(10)));
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
    			if (i > 0) {
    	    		VendaShopeeFormatadaEntity vendaItem = new VendaShopeeFormatadaEntity(
		    				item.getCodItem(),
			    			item.getQtde(),
			    			item.getValorUnitario(),
			    			item.getValorTotal(),
			    			item.getValorRecebido());
    	    		list.add(vendaItem);
    	    		continue;
    	    	}
    	    	VendaShopeeFormatadaEntity vendaItem = new VendaShopeeFormatadaEntity(
    	    			venda.getId(),
    	    			venda.getData(),
    	    			venda.getConta(),
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

