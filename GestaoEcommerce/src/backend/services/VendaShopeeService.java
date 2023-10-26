package backend.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.dto.VendaShopeeDTO;
import backend.entities.shopeeEntity.VendaShopeeEntity;
import backend.repositories.vendaShopee.VendaShopeeRepository;
import backend.repositories.vendaShopee.VendaShopeeRepositoryImpl;

public class VendaShopeeService {

	VendaShopeeRepository repository = new VendaShopeeRepositoryImpl();

	public List<VendaShopeeDTO> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		return repository.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status);
	}

	public void insertVenda(Date data, String cliente, String status, String codItem, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		repository.insertVenda(data, cliente, status, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	public void insertItemVenda(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido)
			throws SQLException {
		repository.insertItemVenda(codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}

	public VendaShopeeEntity findById(Long id) throws SQLException {
		return repository.findById(id);
	}
	
	public void editVenda(Long idVenda, Long idDado, Date data, String cliente, String status, String codItem, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		repository.editVenda(idVenda, idDado, data, cliente, status, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	public void deleteVenda(Long idVenda, Long idDado) throws SQLException {
		repository.deleteVenda(idVenda, idDado);
	}
	
}
