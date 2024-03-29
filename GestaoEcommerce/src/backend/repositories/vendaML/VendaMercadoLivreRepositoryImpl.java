package backend.repositories.vendaML;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import backend.controllers.ItemController;
import backend.dto.VendaMercadoLivreDTO;
import backend.entities.mercadoLivre.ItemMercadoLivreEntity;
import backend.entities.mercadoLivre.VendaMercadoLivreEntity;
import backend.utils.CalculaTotalERecebido;
import backend.utils.Constants;
import frontend.utils.DataUtils;
import frontend.views.utils.Alerts;
import javafx.scene.control.Alert.AlertType;
import models.DAO;
import models.DbException;

public class VendaMercadoLivreRepositoryImpl extends DAO implements VendaMercadoLivreRepository {

	ItemController itemController = new ItemController();

	PreparedStatement preparedStatement;
	ResultSet resultSet;

	@Override
	public List<VendaMercadoLivreDTO> findVendas(Date dataInicio, Date dataFim, String tipoAnuncio, Integer qtde,
			String codItem, String cliente, String status) throws SQLException {
		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT vml.*, i.COD_ITEM, dml.* ");
		sql.append(" FROM TB_VENDA_ML vml ");
		sql.append(" INNER JOIN TB_DADOS_VENDA_ML dml ON dml.ID_VENDA = vml.ID_VENDA ");
		sql.append(" INNER JOIN TB_ITEM i ON i.ID_ITEM = dml.ID_ITEM ");
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
		sql.append(" ORDER BY 2, 1");
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql.toString());
			System.out.println(preparedStatement.toString());
			if (params.size() != 0)
				for (int i = 1; i <= params.size(); i++) {
					Object param = params.get(i - 1);
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

	private List<VendaMercadoLivreDTO> resultSetToVenda(ResultSet resultSet) throws SQLException {
		List<VendaMercadoLivreEntity> vendas = new ArrayList<>();
		Long lastId = 0L;
		while (resultSet.next()) {
			ItemMercadoLivreEntity item = new ItemMercadoLivreEntity(resultSet.getLong("ID_ITEM"),
					resultSet.getString("COD_ITEM"), resultSet.getInt("QTDE"), resultSet.getDouble("VALOR_UNITARIO"),
					resultSet.getDouble("VALOR_TOTAL"), resultSet.getDouble("VALOR_RECEBIDO"),
					resultSet.getString("TIPO_ANUNCIO"), resultSet.getBoolean("IS_FRETE_GRATIS"),
					resultSet.getDouble("TOTAL_SEM_FRETE"));
			if (!lastId.equals(resultSet.getLong("ID_VENDA"))) {
				vendas.add(new VendaMercadoLivreEntity(resultSet.getLong("ID_VENDA"), resultSet.getDate("DATA_VENDA"),
						resultSet.getString("CLIENTE"), resultSet.getString("STATUS"), resultSet.getLong("ID_DADO")));
			}
			vendas.get(vendas.size() - 1).addItem(item);
			lastId = resultSet.getLong("ID_VENDA");
		}
		this.desconectar(this.conexao);
		return buildVendaFormatada(vendas);
	}

	private List<VendaMercadoLivreDTO> buildVendaFormatada(List<VendaMercadoLivreEntity> vendas) {
		List<VendaMercadoLivreDTO> list = new ArrayList<>();

		for (VendaMercadoLivreEntity venda : vendas) {
			for (int i = 0; i < venda.getItens().size(); i++) {
				ItemMercadoLivreEntity item = venda.getItens().get(i);
//    			if (i > 0) {
//    	    		VendaMercadoLivreDTO vendaItem = new VendaMercadoLivreFormatadaEntity(
//		    				item.getCodItem(),
//			    			item.getQtde(),
//			    			item.getValorUnitario(),
//			    			item.getValorTotal(),
//			    			item.getValorRecebido());
//    	    		list.add(vendaItem);
//    	    		continue;
//    	    	}
				VendaMercadoLivreDTO vendaItem = new VendaMercadoLivreDTO(venda.getId(), venda.getData(),
						venda.getCliente(), venda.getStatus(), venda.getIdDado(), item.getCodItem(), item.getQtde(),
						"R$ " + String.format("%.2f", item.getValorUnitario()),
						"R$ " + String.format("%.2f", item.getValorTotal()),
						"R$ " + String.format("%.2f", item.getValorRecebido()), item.getTipoAnuncio(),
						item.getIsFreteGratis(), "R$ " + String.format("%.2f", item.getTotalSemFrete()));
				list.add(vendaItem);
			}
		}
		return list;
	}

	@Override
	public void insertVenda(Date data, String cliente, String status, String codItem, String tipoAnuncio, Integer qtde,
			Double valorUnitario, Double valorTotal, Double valorRecebido) throws SQLException {
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
			preparedStatement.setLong(2, findItemByCodItem(codItem));
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
	public void insertVenda(Date data, String cliente, String status) throws SQLException {
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(insertTbVenda());
			preparedStatement.setDate(1, data);
			preparedStatement.setString(2, cliente);
			preparedStatement.setString(3, status);
			System.out.println(preparedStatement.toString());
			preparedStatement.executeUpdate();
			this.desconectar(this.conexao);
		} catch (SQLException e) {
			Alerts.showAlert("Erro", "ERRO", "Não foi possível cadastrar a venda.", AlertType.ERROR);
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public void insertItemVenda(String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		Long lastId = findLastId();
		Long idItem = findItemByCodItem(codItem);

		if (Objects.isNull(idItem)) {
			itemController.insertItem(codItem, codItem.split("-")[0], codItem.split("-")[1], "-", Boolean.TRUE);
			idItem = findItemByCodItem(codItem);
		}

		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(insertTbDadosVenda());
			preparedStatement.setLong(1, lastId);
			preparedStatement.setLong(2, idItem);
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

//	 		Alerts.showAlert("Sucesso", "INSERIDO COM SUCESSO", "Venda cadastrada.", AlertType.INFORMATION);
		} catch (SQLException e) {
			Alerts.showAlert("Erro", "ERRO", "Não foi possível cadastrar a venda.", AlertType.ERROR);
			throw new DbException(e.getMessage());
		}
	}

	private String insertTbVenda() {
		StringBuilder sql = new StringBuilder("INSERT INTO TB_VENDA_ML( ");
		sql.append(" DATA_VENDA, CLIENTE, STATUS) ");
		sql.append(" VALUES(?, ?, ?)");
		return sql.toString();
	}

	private String insertTbDadosVenda() {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO TB_DADOS_VENDA_ML(ID_VENDA, ID_ITEM, TIPO_ANUNCIO, IS_FRETE_GRATIS, ");
		sql.append(" QTDE, VALOR_UNITARIO, VALOR_TOTAL, TOTAL_SEM_FRETE, VALOR_RECEBIDO) VALUES( ");
		sql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		return sql.toString();
	}

	private Long findLastId() {
		String sql = "SELECT MAX(ID_VENDA) ID_VENDA FROM TB_VENDA_ML";
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql);
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Long value = resultSet.getLong("ID_VENDA");
			this.desconectar(this.conexao);
			return value;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private List<Object> tipoAndFrete(String tipoAnuncio) {
//    	tipoAnuncio = TextUtils.removeSpaces(tipoAnuncio);
		if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO)
				|| tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM))
			return Arrays.asList(tipoAnuncio, Boolean.FALSE);
		if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO_FG)
				|| tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM_FG))
			return Arrays.asList(tipoAnuncio.split("-")[0], Boolean.TRUE);
		return null;
	}

	@Override
	public VendaMercadoLivreEntity findById(Long id) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT vml.*, i.COD_ITEM, dml.* ");
		sql.append(" FROM TB_VENDA_ML vml ");
		sql.append(" INNER JOIN TB_DADOS_VENDA_ML dml ON dml.ID_VENDA = vml.ID_VENDA ");
		sql.append(" INNER JOIN TB_ITEM i ON i.ID_ITEM = dml.ID_ITEM ");
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
		VendaMercadoLivreEntity venda = new VendaMercadoLivreEntity(resultSet.getLong("ID_VENDA"),
				resultSet.getDate("DATA_VENDA"), resultSet.getString("CLIENTE"), resultSet.getString("STATUS"),
				resultSet.getLong("ID_DADO"));
		venda.addItem(new ItemMercadoLivreEntity(resultSet.getLong("ID_ITEM"), resultSet.getString("COD_ITEM"),
				resultSet.getInt("QTDE"), resultSet.getDouble("VALOR_UNITARIO"), resultSet.getDouble("VALOR_TOTAL"),
				resultSet.getDouble("VALOR_RECEBIDO"), resultSet.getString("TIPO_ANUNCIO"),
				resultSet.getBoolean("IS_FRETE_GRATIS"), resultSet.getDouble("ID_DADO")));
		while (resultSet.next()) {
			ItemMercadoLivreEntity item = new ItemMercadoLivreEntity(resultSet.getLong("ID_ITEM"),
					resultSet.getString("COD_ITEM"), resultSet.getInt("QTDE"), resultSet.getDouble("VALOR_UNITARIO"),
					resultSet.getDouble("VALOR_TOTAL"), resultSet.getDouble("VALOR_RECEBIDO"),
					resultSet.getString("TIPO_ANUNCIO"), resultSet.getBoolean("IS_FRETE_GRATIS"),
					resultSet.getDouble("ID_DADO"));
			venda.addItem(item);
		}
		this.desconectar(this.conexao);
		return venda;
	}

	@Override
	public void editVenda(Long idVenda, Long idDado, Date data, String cliente, String status, String codItem,
			String tipoAnuncio, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido)
			throws SQLException {
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
			Long idItem = findItemByCodItem(codItem);
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(updateTbDadosVenda());
			preparedStatement.setLong(1, idItem);
			preparedStatement.setString(2, (String) tipoAndFrete(tipoAnuncio).get(0));
			preparedStatement.setBoolean(3, (Boolean) tipoAndFrete(tipoAnuncio).get(1));
			preparedStatement.setDouble(4, qtde);
			preparedStatement.setDouble(5, valorUnitario);
			preparedStatement.setDouble(6, valorTotal);
			preparedStatement.setDouble(7, CalculaTotalERecebido.valorSemFreteML(valorTotal, qtde));
			preparedStatement.setDouble(8, valorRecebido);
			preparedStatement.setLong(9, idDado);
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
		StringBuilder sql = new StringBuilder("UPDATE TB_VENDA_ML ");
		sql.append(" SET DATA_VENDA = ?, CLIENTE = ?, STATUS = ? ");
		sql.append(" WHERE ID_VENDA = ?");
		return sql.toString();
	}

	private String updateTbDadosVenda() {
		StringBuilder sql = new StringBuilder("UPDATE TB_DADOS_VENDA_ML ");
		sql.append(" SET ID_ITEM = ?, TIPO_ANUNCIO = ?, IS_FRETE_GRATIS = ?, QTDE = ?, ");
		sql.append(" VALOR_UNITARIO = ?, VALOR_TOTAL = ?, TOTAL_SEM_FRETE = ?, VALOR_RECEBIDO = ? ");
		sql.append(" WHERE ID_DADO = ? ");
		return sql.toString();
	}

	private Long findItemByCodItem(String codItem) throws SQLException {
		String sql = "SELECT ID_ITEM FROM TB_ITEM WHERE COD_ITEM = ?";
		try {
			this.conectar();
			preparedStatement = this.conexao.prepareStatement(sql);
			preparedStatement.setString(1, codItem);
			System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			Long value;
			if (resultSet.next())
				value = resultSet.getLong(1);
			else
				value = null;
			this.desconectar(this.conexao);
			return value;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
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
		return "DELETE FROM TB_VENDA_ML WHERE ID_VENDA = ?";
	}

	private String deleteTbDadosVenda() {
		return "DELETE FROM TB_DADOS_VENDA_ML WHERE ID_DADO = ?";
	}

	@Override
	public List<Double> findValorTotalPorMes(Integer ano, Integer mes1, Integer mes2) {
		if (mes1.equals(mes2))
			return valorTotalPorDia(ano, mes1);
		List<Double> values = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT SUM(dml.VALOR_TOTAL) AS VALOR ");
		sql.append(" FROM TB_DADOS_VENDA_ML dml ");
		sql.append(" INNER JOIN TB_VENDA_ML vml ON vml.ID_VENDA = dml.ID_VENDA ");
		sql.append(" WHERE YEAR(vml.DATA_VENDA) = ? ");
		sql.append(" AND MONTH(vml.DATA_VENDA) = ?");
		try {
			for (int i = mes1; i <= mes2; i++) {
				this.conectar();
				preparedStatement = this.conexao.prepareStatement(sql.toString());
				preparedStatement.setInt(1, ano);
				preparedStatement.setInt(2, i);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				Double value = resultSet.getDouble("VALOR");
				values.add(Objects.nonNull(value) ? value : 0D);
				this.desconectar(this.conexao);
			}
			return values;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	private List<Double> valorTotalPorDia(Integer ano, Integer mes) {
		List<Double> values = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT SUM(dml.VALOR_TOTAL) AS VALOR ");
		sql.append(" FROM TB_DADOS_VENDA_ML dml ");
		sql.append(" INNER JOIN TB_VENDA_ML vml ON vml.ID_VENDA = dml.ID_VENDA ");
		sql.append(" WHERE YEAR(vml.DATA_VENDA) = ? ");
		sql.append(" AND MONTH(vml.DATA_VENDA) = ? ");
		sql.append(" AND DAY(vml.DATA_VENDA) = ?");
		Integer qtdeDias = DataUtils.getListDias(mes).size();
		try {
			for (int i = 1; i <= qtdeDias; i++) {
				this.conectar();
				preparedStatement = this.conexao.prepareStatement(sql.toString());
				preparedStatement.setInt(1, ano);
				preparedStatement.setInt(2, mes);
				preparedStatement.setInt(3, i);
				resultSet = preparedStatement.executeQuery();
				resultSet.next();
				Double value = resultSet.getDouble("VALOR");
				values.add(Objects.nonNull(value) ? value : 0D);
				this.desconectar(this.conexao);
			}
			return values;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
