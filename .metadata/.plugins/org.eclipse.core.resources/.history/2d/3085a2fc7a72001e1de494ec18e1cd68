package backend.repositories.vendaGeral;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import backend.entities.geralEntity.VendaGeralFormatadaEntity;

public interface VendaGeralRepository {

	
	List<VendaGeralFormatadaEntity> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem, 
			String cliente, String status) throws SQLException;
	
	List<String> findItens() throws SQLException;
	
	void insertTbItem(String codItem) throws SQLException;
}
