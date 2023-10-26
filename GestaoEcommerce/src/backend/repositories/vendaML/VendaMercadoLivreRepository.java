package backend.repositories.vendaML;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.dto.VendaMercadoLivreDTO;
import backend.entities.mercadoLivreEntity.VendaMercadoLivreEntity;

public interface VendaMercadoLivreRepository {

	List<VendaMercadoLivreDTO> findVendas(Date dataInicio, Date dataFim, String tipoAnuncio, Integer qtde,
			String codItem, String cliente, String status) throws SQLException;

	void insertVenda(Date data, String cliente, String status, String codItem, String tipoAnuncio, Integer qtde,
			Double valorUnitario, Double valorTotal, Double valorRecebido) throws SQLException;

	void insertItemVenda(String codItem, String tipoAnuncio, Integer qtde, Double valorUnitario, Double valorTotal,
			Double valorRecebido) throws SQLException;

	VendaMercadoLivreEntity findById(Long id) throws SQLException;

	void editVenda(Long idVenda, Long idDado, Date data, String cliente, String status, String codItem,
			String tipoAnuncio, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido)
			throws SQLException;

	void deleteVenda(Long idVenda, Long idDado) throws SQLException;
	
}
