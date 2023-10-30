package backend.repositories.vendaGeral;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import backend.dto.VendaGeralDTO;

public interface VendaGeralRepository {

	
	List<VendaGeralDTO> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem, 
			String cliente, String status) throws SQLException;
	
	Integer[] countVendas(Integer ano);
	
	List<String> findItens() throws SQLException;
	
	void insertTbItem(String codItem) throws SQLException;
	
	Double[] findValorTotal(Integer ano);
	
	Integer[] countByStatus(String status);
	
}
