package backend.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.mercadoLivreEntity.VendaMercadoLivreFormatadaEntity;
import backend.repositories.vendaML.VendaMercadoLivreRepository;
import backend.repositories.vendaML.VendaMercadoLivreRepositoryImpl;

public class VendaMercadoLivreService {

	VendaMercadoLivreRepository repository = new VendaMercadoLivreRepositoryImpl();
	
	public List<VendaMercadoLivreFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String tipoAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
		return repository.findVendas(dataInicio, dataFim, tipoAnuncio, qtde, codItem, cliente, status);
	}
	
}
