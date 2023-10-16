package backend.controllers;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import backend.entities.geralEntity.VendaGeralFormatadaEntity;
import backend.services.VendaGeralService;

public class VendaGeralController {
	
	VendaGeralService service = new VendaGeralService();
	
	/**
	 * Recupera todas as vendas com base nos parâmetros passados
	 * @return VendaGeralFormatadaEntity
	 * @throws SQLException 
	 */
	public List<VendaGeralFormatadaEntity> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		return service.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status);
	}
	
}