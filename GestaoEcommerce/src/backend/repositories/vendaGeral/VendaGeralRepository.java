package backend.repositories.vendaGeral;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import backend.dto.VendaGeralDTO;

public interface VendaGeralRepository {

	
	List<VendaGeralDTO> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem, 
			String cliente, String status) throws SQLException;
	
	Integer[] countVendasPorAno(Integer ano, Integer mes1, Integer mes2);
	
	List<String> findItens() throws SQLException;
	
	List<String> findItensAtivos() throws SQLException;
	
	void insertTbItem(String codItem) throws SQLException;
	
	Double[] findValorTotalPorAno(Integer ano, Integer mes1, Integer mes2);
	
	Integer[] countByStatus(String status, Integer ano, Integer mes1, Integer mes2);
	
}
