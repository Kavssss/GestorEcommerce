package backend.repositories.vendaShopee;

import java.sql.SQLException;
import java.util.List;

import backend.entities.shopeeEntity.VendaShopeeEntity;

public interface VendaShopeeRepository  {
    
    List<VendaShopeeEntity> findAll() throws SQLException;
    
    List<VendaShopeeEntity> findVendasPorPeriodo(Integer dia, Integer mes, Integer ano);
    
    List<VendaShopeeEntity> findVendasPorCliente(String cliente);
	
}

