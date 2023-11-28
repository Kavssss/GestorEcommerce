package backend.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.dto.VendaShopeeDTO;
import backend.entities.shopeeEntity.VendaShopeeEntity;
import backend.services.VendaShopeeService;

public class VendaShopeeController {

	VendaShopeeService service = new VendaShopeeService();

	/**
	 * Recupera todas as vendas
	 * 
	 * @return Todas as vendas
	 * @throws SQLException
	 */
	public List<VendaShopeeDTO> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		return service.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status);
	}

	/**
	 * Insere venda com 1 item no banco de dados
	 * 
	 * @throws SQLException
	 */
	public void insertVenda(Date data, String cliente, String status, String codItem, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		service.insertVenda(data, cliente, status, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	public void insertVenda(Date data, String cliente, String status) throws SQLException {
		service.insertVenda(data, cliente, status);
	}
	
	/**
	 * Insere item extra na última venda do banco de dados
	 * 
	 * @throws SQLException
	 */
	public void insertItemVenda(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido,
			Boolean isEmMassa) throws SQLException {
		service.insertItemVenda(codItem, qtde, valorUnitario, valorTotal, valorRecebido, isEmMassa);
	}
	
	public VendaShopeeEntity findById(Long id) throws SQLException {
		return service.findById(id);
	}
	
	public void editVenda(Long idVenda, Long idDado, Date data, String cliente, String status, String codItem, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		service.editVenda(idVenda, idDado, data, cliente, status, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	public void deleteVenda(Long idVenda, Long idDado) throws SQLException {
		service.deleteVenda(idVenda, idDado);
	}
	
	public List<Double> findValorTotalPorMes(Integer ano, Integer mes1, Integer mes2) {
		return service.findValorTotalPorMes(ano, mes1, mes2);
	}
	
}
