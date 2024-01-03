package backend.repositories.vendaShopee;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.dto.VendaShopeeDTO;
import backend.entities.shopeeEntity.VendaShopeeEntity;

public interface VendaShopeeRepository {

	List<VendaShopeeDTO> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem, String cliente,
			String status) throws SQLException;

	void insertVenda(Date data, String cliente, String status, String codItem, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException;

	void insertItemVenda(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido,
			Boolean isEmMassa) throws SQLException;

	VendaShopeeEntity findById(Long id) throws SQLException;

	void editVenda(Long idVenda, Long idDado, Date data, String cliente, String status, String codItem, Integer qtde,
			Double valorUnitario, Double valorTotal, Double valorRecebido) throws SQLException;

	void deleteVenda(Long idVenda, Long idDado) throws SQLException;

	List<Double> findValorTotalPorMes(Integer ano, Integer mes1, Integer mes2);

	void insertVenda(Date data, String cliente, String status) throws SQLException;

}
