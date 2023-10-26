package backend.services;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import backend.dto.VendaGeralDTO;
import backend.repositories.vendaGeral.VendaGeralRepository;
import backend.repositories.vendaGeral.VendaGeralRepositoryImpl;

public class VendaGeralService {

	VendaGeralRepository repository = new VendaGeralRepositoryImpl();
	
	public List<VendaGeralDTO> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		return repository.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status);
	}
	
	public List<String> findItens() throws SQLException {
		return repository.findItens();
	}
	
}
