package backend.repositories.vendaML;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.mercadoLivreEntity.VendaMercadoLivreFormatadaEntity;

public interface VendaMercadoLivreRepository {

	List<VendaMercadoLivreFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String contaAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException;
	
}
