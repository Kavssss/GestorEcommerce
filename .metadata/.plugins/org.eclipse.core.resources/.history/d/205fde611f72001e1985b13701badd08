package backend.controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.mercadoLivreEntity.VendaMercadoLivreFormatadaEntity;
import backend.services.VendaMercadoLivreService;

public class VendaMercadoLivreController {

	VendaMercadoLivreService service = new VendaMercadoLivreService();
	
	public List<VendaMercadoLivreFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String tipoAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
		return service.findVendas(dataInicio, dataFim, tipoAnuncio, qtde, codItem, cliente, status);
	}
	
}
