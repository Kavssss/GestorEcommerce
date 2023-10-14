package backend.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import backend.entities.geralEntity.VendaGeralFormatadaEntity;
import backend.services.VendaGeralService;

public class VendaGeralController {
	
	VendaGeralService service = new VendaGeralService();
	
	/**
	 * Recupera todas as vendas
	 * @return Todas as vendas
	 * @throws SQLException 
	 */
	public List<VendaGeralFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String contaAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
		return service.findVendas(dataInicio, dataFim, contaAnuncio, qtde, codItem, cliente, status);
	}
	
}
