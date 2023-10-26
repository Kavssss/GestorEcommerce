package backend.controllers;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import backend.dto.VendaGeralDTO;
import backend.services.VendaGeralService;

public class VendaGeralController {
	
	VendaGeralService service = new VendaGeralService();
	
	/**
	 * Recupera todas as vendas com base nos parâmetros passados
	 * @return VendaGeralDTO
	 * @throws SQLException 
	 */
	public List<VendaGeralDTO> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		return service.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status);
	}
	
	/**
	 * Recupera todos os códigos dos itens
	 * 
	 * @return Códigos itens
	 * @throws SQLException
	 */
	public List<String> findItens() throws SQLException {
		return service.findItens();
	}
	
}
