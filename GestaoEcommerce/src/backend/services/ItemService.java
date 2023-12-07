package backend.services;

import java.sql.SQLException;
import java.util.List;

import backend.dto.ItemDTO;
import backend.entities.ItemEntity;
import backend.repositories.item.ItemRepository;
import backend.repositories.item.ItemRepositoryImpl;

public class ItemService {

	ItemRepository repository = new ItemRepositoryImpl();
	
	public void insertItem(String codItem, String modelo, String variacao, String descricao, Boolean isEmMassa) throws SQLException {
		repository.insertItem(codItem, modelo, variacao, descricao, isEmMassa);
	}
	
	public void editItem(Long id, String codItem, String modelo, String variacao, String descricao) throws SQLException {
		repository.editItem(id, codItem, modelo, variacao, descricao);
	}
	
	public void disableProduto(Long id) throws SQLException {
		repository.disableProduto(id);
	}
	
	public void enableProduto(Long id) throws SQLException {
		repository.enableProduto(id);
	}
	
	public ItemEntity findById(Long id) throws SQLException {
		return repository.findById(id);
	}
	
	public List<ItemDTO> findProdutos(String codItem, String modelo, String variacao, String descricao) throws SQLException {
		return repository.findProdutos(codItem, modelo, variacao, descricao);
	}
	
}
