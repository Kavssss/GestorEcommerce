package backend.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.dto.VendaMercadoLivreDTO;
import backend.entities.mercadoLivre.VendaMercadoLivreEntity;
import backend.repositories.vendaML.VendaMercadoLivreRepository;
import backend.repositories.vendaML.VendaMercadoLivreRepositoryImpl;

public class VendaMercadoLivreService {

	VendaMercadoLivreRepository repository = new VendaMercadoLivreRepositoryImpl();

	public List<VendaMercadoLivreDTO> findVendas(Date dataInicio, Date dataFim, String tipoAnuncio, Integer qtde,
			String codItem, String cliente, String status) throws SQLException {
		return repository.findVendas(dataInicio, dataFim, tipoAnuncio, qtde, codItem, cliente, status);
	}

	public void insertVenda(Date data, String cliente, String status, String codItem, String tipoAnuncio, Integer qtde,
			Double valorUnitario, Double valorTotal, Double valorRecebido) throws SQLException {
		repository.insertVenda(data, cliente, status, codItem, tipoAnuncio, qtde, valorUnitario, valorTotal,
				valorRecebido);
	}

	public void insertVenda(Date data, String cliente, String status) throws SQLException {
		repository.insertVenda(data, cliente, status);
	}

	public void insertItemVenda(String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario,
			Double valorTotal, Double valorRecebido) throws SQLException {
		repository.insertItemVenda(codItem, tipoAnuncio, qtde, valorUnitario, valorTotal, valorRecebido);
	}

	public VendaMercadoLivreEntity findById(Long id) throws SQLException {
		return repository.findById(id);
	}

	public void editVenda(Long idVenda, Long idDado, Date data, String cliente, String status, String codItem,
			String tipoAnuncio, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido)
			throws SQLException {
		repository.editVenda(idVenda, idDado, data, cliente, status, codItem, tipoAnuncio, qtde, valorUnitario,
				valorTotal, valorRecebido);
	}

	public void deleteVenda(Long idVenda, Long idDado) throws SQLException {
		repository.deleteVenda(idVenda, idDado);
	}

	public List<Double> findValorTotalPorMes(Integer ano, Integer mes1, Integer mes2) {
		return repository.findValorTotalPorMes(ano, mes1, mes2);
	}

}
