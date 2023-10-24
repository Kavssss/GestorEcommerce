package backend.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.shopeeEntity.VendaShopeeEntity;
import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;
import backend.services.VendaShopeeService;

public class VendaShopeeController {

	VendaShopeeService service = new VendaShopeeService();

	/**
	 * Recupera todas as vendas
	 * 
	 * @return Todas as vendas
	 * @throws SQLException
	 */
	public List<VendaShopeeFormatadaEntity> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		return service.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status);
	}

	/**
	 * Insere venda com 1 item no banco de dados
	 * 
	 * @throws SQLException
	 */
	public void insertVenda(Date data, String cliente, String codItem, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		service.insertVenda(data, cliente, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	/**
	 * Insere item extra na última venda do banco de dados
	 * 
	 * @throws SQLException
	 */
	public void insertItemVenda(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido)
			throws SQLException {
		service.insertItemVenda(codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	public VendaShopeeEntity findById(Long id) throws SQLException {
		return service.findById(id);
	}
	
}
