package backend.repositories.vendaShopee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import backend.entities.shopeeEntity.VendaShopeeEntity;
import backend.utils.Constants;
import models.DAO;
import models.DbException;

public class VendaShopeeRepositoryImpl extends DAO implements VendaShopeeRepository {

	PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
	public List<VendaShopeeEntity> findAll() throws SQLException {
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
    public List<VendaShopeeEntity> findVendasPorPeriodo(Integer dia, Integer mes, Integer ano) {
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
    public List<VendaShopeeEntity> findVendasPorCliente(String cliente) {
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
    	StringBuilder sql = new StringBuilder("SELECT venda.ID_VENDA, venda.DATA_VENDA, venda.CONTA, dados.QTDE, item.MODELO, ");
    	sql.append(" item.VARIACAO, venda.CLIENTE, dados.VALOR_UNITARIO, dados.VALOR_TOTAL, dados.VALOR_RECEBIDO, venda.STATUS ");
    	sql.append(" FROM TB_VENDA_SHOPEE venda ");
    	sql.append(" INNER JOIN TB_DADOS_VENDA_SHOPEE dados ON dados.ID_VENDA = venda.ID_VENDA ");
    	sql.append(" INNER JOIN TB_ITEM item ON item.COD_ITEM = dados.COD_ITEM ");
    	sql.append(" WHERE 1 = 1 ");
    	return sql.toString();
    }

    private void insertTbVenda(VendaShopeeEntity venda) throws SQLException {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_VENDA_SHOPEE(");
    	sql.append("DATA_VENDA, CONTA, CLIENTE, STATUS) VALUES");
		sql.append("(");
    	sql.append(colocaAspas(venda.getData().toString())).append(", ");
    	sql.append(colocaAspas(venda.getConta())).append(", ");
    	sql.append(colocaAspas(venda.getCliente())).append(", ");
    	sql.append(colocaAspas(venda.getStatus()));
    	sql.append(")");
    	executeQueryUpdate(sql.toString());
    }

    private void insertTbItem(VendaShopeeEntity venda) throws SQLException {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_ITEM VALUES");
    	Integer sizeQuery = sql.toString().length();
		for (ItemShopee item : venda.getItens()) {
    		if (!verificaItemExistente(item.getCodItem())) {
				sql.append("(");
				sql.append(colocaAspas(item.getCodItem())).append(", ");
		    	sql.append(colocaAspas(item.getModelo())).append(", ");
		    	sql.append(colocaAspas(item.getVariacao()));
		    	sql.append("), ");
    		}
		}
		if (sql.toString().length() > sizeQuery) {
			removeVirgula(sql);
			executeQueryUpdate(sql.toString());
		}
    }
    
    private void insertTbDadosVenda(VendaShopeeEntity venda) throws SQLException {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_DADOS_VENDA_SHOPEE VALUES ");
    	for (ItemShopee item : venda.getItens()) {
    		sql.append("(");
    		sql.append(colocaAspas(findLastId(Constants.SHOPEE))).append(", ");
	    	sql.append(colocaAspas(item.getCodItem())).append(", ");
	    	sql.append(colocaAspas(item.getQtde().toString())).append(", ");
	    	sql.append(colocaAspas(item.getValorUnitario().toString())).append(", ");
	    	sql.append(colocaAspas(item.getValorTotal().toString())).append(", ");
	    	sql.append(colocaAspas(item.getValorRecebido().toString()));
	    	sql.append("), ");
    	}
    	removeVirgula(sql);
    	executeQueryUpdate(sql.toString());
    }

    private List<VendaShopeeEntity> resultSetToVenda(ResultSet resultSet) throws SQLException {
    	List<VendaShopeeEntity> vendas = new ArrayList<>();
		if (resultSet.next()) {
    		List<String> list = new ArrayList<>();
    		for (int i = 2; i < 11; i++) 
	        	list.add(resultSet.getString(i));
        	if (!vendas.isEmpty() && vendas.get(vendas.size() - 1).getId().equals(resultSet.getLong(1)))
				vendas.get(vendas.size() - 1).addItens(list.get(2), list.get(3), list.get(4), list.get(6));
    		else
    			vendas.add(new VendaShopeeEntity(resultSet.getString(1), list));
    	}
    	this.desconectar(this.conexao);
    	return vendas;
    }

    private List<VendaShopeeEntity> excelToObjects(List<List<String>> matrizDeString) {
    	List<VendaShopeeEntity> vendasShopee = new ArrayList<>();
    	for (List<String> linha : matrizDeString) {   		
    		if (linha.get(8).isBlank()) 
    			vendasShopee.get(vendasShopee.size() - 1).addItens(
    					linha.get(2), linha.get(3), linha.get(4), linha.get(6));
    		else
    			vendasShopee.add(new VendaShopeeEntity(linha));
    	}
    	return vendasShopee;
    }
  
}

