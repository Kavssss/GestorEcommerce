package backend.controllers;

import java.sql.SQLException;
import java.util.List;

import backend.dto.ItemDTO;
import backend.entities.ItemEntity;
import backend.services.ItemService;

public class ItemController {

	ItemService service = new ItemService();
	
	public void insertItem(String codItem, String modelo, String variacao, String descricao, Boolean isEmMassa) throws SQLException {
		service.insertItem(codItem, modelo, variacao, descricao, isEmMassa);
	}
	
	public void editItem(Long id, String codItem, String modelo, String variacao, String descricao) throws SQLException {
		service.editItem(id, codItem, modelo, variacao, descricao);
	}
	
	public void disableProduto(Long id) throws SQLException {
		service.disableProduto(id);
	}
	
	public void enableProduto(Long id) throws SQLException {
		service.enableProduto(id);
	}
	
	public ItemEntity findById(Long id) throws SQLException {
		return service.findById(id);
	}
	
	public List<ItemDTO> findProdutos(String codItem, String modelo, String variacao, String descricao) throws SQLException {
		return service.findProdutos(codItem, modelo, variacao, descricao);
	}
	
}
