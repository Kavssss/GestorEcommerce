package backend.repositories.vendaGeral;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import backend.entities.geralEntity.VendaGeralFormatadaEntity;

public interface VendaGeralRepository {

	
	List<VendaGeralFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String contaAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException;
	
}
