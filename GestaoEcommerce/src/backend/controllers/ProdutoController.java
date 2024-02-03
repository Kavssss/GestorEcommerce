package backend.controllers;

import java.sql.SQLException;
import java.util.List;

import backend.entities.ProdutoEntity;
import backend.services.ProdutoService;
import backend.utils.Categoria;

public class ProdutoController {

	ProdutoService service = new ProdutoService();

	public void insertProduto(String codItem, Categoria categoria, String modelo, String variacao, String descricao, Integer estoque,
			Boolean isEmMassa) throws SQLException {
		service.insertProduto(codItem, categoria, modelo, variacao, descricao, estoque, isEmMassa);
	}

	public void editProduto(Long id, String codItem, String modelo, String variacao, String descricao, Integer estoque)
			throws SQLException {
		service.editProduto(id, codItem, modelo, variacao, descricao, estoque);
	}

	public void setFlagProduto(Long id, Integer flag) throws SQLException {
		service.setFlagProduto(id, flag);
	}

	public ProdutoEntity findById(Long id) throws SQLException {
		return service.findById(id);
	}

	public List<ProdutoEntity> findProdutos(String codItem, Categoria categoria, String modelo, String variacao, String descricao,
			Integer estoque) throws SQLException {
		return service.findProdutos(codItem, categoria, modelo, variacao, descricao, estoque);
	}

}
