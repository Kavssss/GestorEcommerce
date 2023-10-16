package backend.repositories.vendaShopee;

import java.sql.SQLException;
import java.util.List;

import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;

public interface VendaShopeeRepository  {
    
    List<VendaShopeeFormatadaEntity> findAll() throws SQLException;
    
    List<VendaShopeeFormatadaEntity> findVendasPorPeriodo(Integer dia, Integer mes, Integer ano);
    
    List<VendaShopeeFormatadaEntity> findVendasPorCliente(String cliente);
	
}

