package backend.repositories.item;

import java.sql.SQLException;
import java.util.List;

import backend.dto.ItemDTO;
import backend.entities.ItemEntity;

public interface ItemRepository {

	void insertItem(String codItem, String modelo, String variacao, String descricao, Boolean isEmMassa)
			throws SQLException;

	void editItem(Long id, String codItem, String modelo, String variacao, String descricao) throws SQLException;

	void disableProduto(Long id) throws SQLException;

	void enableProduto(Long id) throws SQLException;

	ItemEntity findById(Long id) throws SQLException;

	List<ItemDTO> findProdutos(String codItem, String modelo, String variacao, String descricao) throws SQLException;

}
