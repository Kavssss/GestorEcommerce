package backend.repositories.vendaML;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import backend.entities.mercadoLivreEntity.ItemMercadoLivreEntity;
import backend.entities.mercadoLivreEntity.VendaMercadoLivreEntity;
import backend.entities.mercadoLivreEntity.VendaMercadoLivreFormatadaEntity;
import backend.utils.CalculaTotalERecebido;
import backend.utils.Constants;
import backend.utils.TextUtils;
import frontend.views.utils.Alerts;
import javafx.scene.control.Alert.AlertType;
import models.DAO;
import models.DbException;

public class VendaMercadoLivreRepositoryImpl extends DAO implements VendaMercadoLivreRepository {

	PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
	public List<VendaMercadoLivreFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String tipoAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
    	List<Object> params = new ArrayList<>();
    	StringBuilder sql = new StringBuilder("SELECT vml.*, dml.* ");
		sql.append(" FROM TB_VENDA_ML vml ");
		sql.append(" INNER JOIN TB_DADOS_VENDA_ML dml ON dml.ID_VENDA = vml.ID_VENDA ");
		sql.append(" INNER JOIN TB_ITEM i ON i.COD_ITEM = dml.COD_ITEM ");
		sql.append(" WHERE 1 = 1 ");
		if (Objects.nonNull(dataInicio) && Objects.nonNull(dataFim)) {
			sql.append(" AND (vml.DATA_VENDA BETWEEN ? AND ?) ");
			params.add(dataInicio);
			params.add(dataFim);
		}
		if (Objects.nonNull(tipoAnuncio)) {
			System.out.println(tipoAnuncio);
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO_FG)
					|| tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM_FG))
				sql.append(" AND dml.IS_FRETE_GRATIS = 1 ");
			else
				sql.append(" AND dml.IS_FRETE_GRATIS = 0 ");
			sql.append(" AND dml.TIPO_ANUNCIO = ? ");
			params.add(tipoAnuncio.replace(" - Frete Grátis", ""));
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
		if (Objects.nonNull(status) && !status.equals("Todos")) {
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
    
    private List<VendaMercadoLivreFormatadaEntity> resultSetToVenda(ResultSet resultSet) throws SQLException {
    	List<VendaMercadoLivreEntity> vendas = new ArrayList<>();
    	Long lastId = 0L;
		while (resultSet.next()) {
			ItemMercadoLivreEntity item = new ItemMercadoLivreEntity(
					resultSet.getString("COD_ITEM"),
					resultSet.getInt("QTDE"),
					resultSet.getDouble("VALOR_UNITARIO"),
					resultSet.getDouble("VALOR_TOTAL"),
					resultSet.getDouble("VALOR_RECEBIDO"),
					resultSet.getString("TIPO_ANUNCIO"),
					resultSet.getBoolean("IS_FRETE_GRATIS"),
					resultSet.getDouble("TOTAL_SEM_FRETE"));
			if (!lastId.equals(resultSet.getLong("ID_VENDA"))) {
				vendas.add(new VendaMercadoLivreEntity(
						resultSet.getLong("ID_VENDA"),
						resultSet.getDate("DATA_VENDA"),
						resultSet.getString("CLIENTE"),
						resultSet.getString("STATUS")));
			}
			vendas.get(vendas.size() - 1).addItem(item);
			lastId = resultSet.getLong("ID_VENDA");
    	}
    	this.desconectar(this.conexao);
    	return buildVendaFormatada(vendas);
    }
    
    private List<VendaMercadoLivreFormatadaEntity> buildVendaFormatada(List<VendaMercadoLivreEntity> vendas) {
    	List<VendaMercadoLivreFormatadaEntity> list = new ArrayList<>();
    	
    	for (VendaMercadoLivreEntity venda : vendas) {
    		for (int i = 0; i < venda.getItens().size(); i++) {
    			ItemMercadoLivreEntity item = venda.getItens().get(i);
//    			if (i > 0) {
//    	    		VendaMercadoLivreFormatadaEntity vendaItem = new VendaMercadoLivreFormatadaEntity(
//		    				item.getCodItem(),
//			    			item.getQtde(),
//			    			item.getValorUnitario(),
//			    			item.getValorTotal(),
//			    			item.getValorRecebido());
//    	    		list.add(vendaItem);
//    	    		continue;
//    	    	}
    			VendaMercadoLivreFormatadaEntity vendaItem = new VendaMercadoLivreFormatadaEntity(
    	    			venda.getId(),
    	    			venda.getData(),
    	    			venda.getCliente(),
    	    			venda.getStatus(),
    	    			item.getCodItem(),
    	    			item.getQtde(),
    	    			item.getValorUnitario(),
    	    			item.getValorTotal(),
    	    			item.getValorRecebido(),
    					item.getTipoAnuncio(),
    					item.getIsFreteGratis(),
    					item.getTotalSemFrete());
    	    	list.add(vendaItem);
    	    }
    	}
    	return list;
    }
    
    @Override
    public void insertVenda(Date data, String cliente, String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		try {
			this.conectar();
		 	preparedStatement = this.conexao.prepareStatement(insertTbVenda());
		 	preparedStatement.setDate(1, data);
		 	preparedStatement.setString(2, cliente);
		 	System.out.println(preparedStatement.toString());
		 	preparedStatement.executeUpdate();
		 	this.desconectar(this.conexao);
		 	
		 	Long lastId = findLastId();
		 	
		 	this.conectar();
		 	preparedStatement = this.conexao.prepareStatement(insertTbDadosVenda());
		 	preparedStatement.setLong(1, lastId);
	 		preparedStatement.setString(2, codItem);
	 		preparedStatement.setString(3, (String) tipoAndFrete(tipoAnuncio).get(0));
	 		preparedStatement.setBoolean(4, (Boolean) tipoAndFrete(tipoAnuncio).get(1));
	 		preparedStatement.setInt(5, qtde);
	 		preparedStatement.setDouble(6, valorUnitario);
	 		preparedStatement.setDouble(7, valorTotal);
	 		preparedStatement.setDouble(8, CalculaTotalERecebido.valorSemFreteML(valorTotal, qtde));
	 		preparedStatement.setDouble(9, valorRecebido);
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
    public void insertItemVenda(String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario, Double valorTotal,
    		Double valorRecebido) throws SQLException {
    	Long lastId = findLastId();
    	try {
    		this.conectar();
		 	preparedStatement = this.conexao.prepareStatement(insertTbDadosVenda());
		 	preparedStatement.setLong(1, lastId);
	 		preparedStatement.setString(2, codItem);
	 		preparedStatement.setString(3, (String) tipoAndFrete(tipoAnuncio).get(0));
	 		preparedStatement.setBoolean(4, (Boolean) tipoAndFrete(tipoAnuncio).get(1));
	 		preparedStatement.setInt(5, qtde);
	 		preparedStatement.setDouble(6, valorUnitario);
	 		preparedStatement.setDouble(7, valorTotal);
	 		preparedStatement.setDouble(8, CalculaTotalERecebido.valorSemFreteML(valorTotal, qtde));
	 		preparedStatement.setDouble(9, valorRecebido);
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
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_VENDA_ML( ");
		sql.append(" DATA_VENDA, CLIENTE, STATUS) ");
		sql.append(" VALUES(?, ?, 'PENDENTE')");
		return sql.toString();
    }
    
    private String insertTbDadosVenda() {
    	StringBuilder sql = new StringBuilder("INSERT INTO TB_DADOS_VENDA_ML VALUES( ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sql.toString();
    }
    
    private Long findLastId() {
    	String sql = "SELECT MAX(ID_VENDA) FROM TB_VENDA_ML";
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
    
    private List<Object> tipoAndFrete(String tipoAnuncio) {
    	tipoAnuncio = TextUtils.removeSpaces(tipoAnuncio);
    	if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO) || tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM))
    		return Arrays.asList(tipoAnuncio, Boolean.FALSE);
    	if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO_FG) || tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM_FG))
    		return Arrays.asList(tipoAnuncio.split("-")[0], Boolean.TRUE);
    	return null;
    }

    @Override
	public VendaMercadoLivreEntity findById(Long id) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT vml.ID_VENDA, vml.DATA_VENDA, vml.CLIENTE, vml.STATUS, dml.ID_VENDA, ");
		sql.append(" dml.COD_ITEM,  dml.QTDE, dml.VALOR_UNITARIO, dml.VALOR_TOTAL, dml.VALOR_RECEBIDO ");
		sql.append(" FROM TB_VENDA_ML vml ");
		sql.append(" INNER JOIN TB_DADOS_VENDA_ML dml ON dml.ID_VENDA = vml.ID_VENDA ");
		sql.append(" INNER JOIN TB_ITEM i ON i.COD_ITEM = dml.COD_ITEM ");
		sql.append(" WHERE vml.ID_VENDA = ?");
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
    
    private VendaMercadoLivreEntity resultSetToVendaEdit(ResultSet resultSet) throws SQLException {
		resultSet.next();
		VendaMercadoLivreEntity venda = new VendaMercadoLivreEntity(
					resultSet.getLong(1),
					resultSet.getDate(2),
					resultSet.getString(3),
					resultSet.getString(4));
		venda.addItem(new ItemMercadoLivreEntity(
				resultSet.getString(6),
				resultSet.getInt(7),
				resultSet.getDouble(8),
				resultSet.getDouble(9),
				resultSet.getDouble(10),
				resultSet.getString(11),
				resultSet.getBoolean(12),
				resultSet.getDouble(13)));
    	while (resultSet.next()) {
    		ItemMercadoLivreEntity item = new ItemMercadoLivreEntity(
    				resultSet.getString(6),
    				resultSet.getInt(7),
    				resultSet.getDouble(8),
    				resultSet.getDouble(9),
    				resultSet.getDouble(10),
    				resultSet.getString(11),
    				resultSet.getBoolean(12),
    				resultSet.getDouble(13));
    		venda.addItem(item);
    	}			
    	this.desconectar(this.conexao);
    	return venda;
    }
	
}
