package backend.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;
import backend.repositories.vendaShopee.VendaShopeeRepository;
import backend.repositories.vendaShopee.VendaShopeeRepositoryImpl;

public class VendaShopeeService {

	VendaShopeeRepository repository = new VendaShopeeRepositoryImpl();

	public List<VendaShopeeFormatadaEntity> findVendas(Date dataInicio, Date dataFim, Integer qtde, String codItem,
			String cliente, String status) throws SQLException {
		return repository.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status);
	}

	public void insertVenda(Date data, String cliente, String codItem, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		repository.insertVenda(data, cliente, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}

}
