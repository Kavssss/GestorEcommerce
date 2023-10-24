package backend.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.mercadoLivreEntity.VendaMercadoLivreEntity;
import backend.entities.mercadoLivreEntity.VendaMercadoLivreFormatadaEntity;
import backend.services.VendaMercadoLivreService;

public class VendaMercadoLivreController {

	VendaMercadoLivreService service = new VendaMercadoLivreService();
	
	/**
	 * Recupera todas as vendas
	 * 
	 * @return Todas as vendas
	 * @throws SQLException
	 */
	public List<VendaMercadoLivreFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String tipoAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
		return service.findVendas(dataInicio, dataFim, tipoAnuncio, qtde, codItem, cliente, status);
	}
	
	/**
	 * Insere venda com 1 item no banco de dados
	 * 
	 * @throws SQLException
	 */
	public void insertVenda(Date data, String cliente, String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		service.insertVenda(data, cliente, codItem, tipoAnuncio, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	/**
	 * Insere item extra na última venda do banco de dados
	 * 
	 * @throws SQLException
	 */
	public void insertItemVenda(String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario, Double valorTotal,
    		Double valorRecebido) throws SQLException {
		service.insertItemVenda(codItem, tipoAnuncio, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	public VendaMercadoLivreEntity findById(Long id) throws SQLException {
		return service.findById(id);
	}
	
}
