package backend.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.mercadoLivreEntity.VendaMercadoLivreEntity;
import backend.entities.mercadoLivreEntity.VendaMercadoLivreFormatadaEntity;
import backend.repositories.vendaML.VendaMercadoLivreRepository;
import backend.repositories.vendaML.VendaMercadoLivreRepositoryImpl;

public class VendaMercadoLivreService {

	VendaMercadoLivreRepository repository = new VendaMercadoLivreRepositoryImpl();
	
	public List<VendaMercadoLivreFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String tipoAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
		return repository.findVendas(dataInicio, dataFim, tipoAnuncio, qtde, codItem, cliente, status);
	}
	
	public void insertVenda(Date data, String cliente, String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		repository.insertVenda(data, cliente, codItem, tipoAnuncio, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	public void insertItemVenda(String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario, Double valorTotal,
    		Double valorRecebido) throws SQLException {
		repository.insertItemVenda(codItem, tipoAnuncio, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
	public VendaMercadoLivreEntity findById(Long id) throws SQLException {
		return repository.findById(id);
	}
	
}
