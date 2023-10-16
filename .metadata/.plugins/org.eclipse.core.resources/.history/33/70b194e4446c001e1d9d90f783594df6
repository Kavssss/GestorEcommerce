package backend.services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import backend.entities.geralEntity.VendaGeralFormatadaEntity;
import backend.repositories.vendaGeral.VendaGeralRepository;
import backend.repositories.vendaGeral.VendaGeralRepositoryImpl;

public class VendaGeralService {

	VendaGeralRepository repository = new VendaGeralRepositoryImpl();
	
	public List<VendaGeralFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String contaAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
		return repository.findVendas(dataInicio, dataFim, contaAnuncio, qtde, codItem, cliente, status);
	}
	
}
