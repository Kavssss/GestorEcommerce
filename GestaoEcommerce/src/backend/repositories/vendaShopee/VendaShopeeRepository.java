package backend.repositories.vendaShopee;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.shopeeEntity.VendaShopeeEntity;
import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;

public interface VendaShopeeRepository {

	List<VendaShopeeFormatadaEntity> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException;

	void insertVenda(Date data, String cliente, String status, String codItem, Integer qtde, Double valorUnitario, Double valorTotal,
			Double valorRecebido) throws SQLException;

	void insertItemVenda(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido)
			throws SQLException;

	VendaShopeeEntity findById(Long id) throws SQLException;
	
	
	void editVenda(Long idVenda, Long idDado, Date data, String cliente, String status, String codItem, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException;
	
	void deleteVenda(Long idVenda, Long idDado) throws SQLException;
}
