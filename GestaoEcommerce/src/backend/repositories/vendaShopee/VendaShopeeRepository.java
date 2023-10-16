package backend.repositories.vendaShopee;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;

public interface VendaShopeeRepository  {
    
    List<VendaShopeeFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String contaAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException;
	
}

