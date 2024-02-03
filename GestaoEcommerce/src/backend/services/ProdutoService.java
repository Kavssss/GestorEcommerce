package backend.services;

import java.sql.SQLException;
import java.util.List;

import backend.entities.ProdutoEntity;
import backend.repositories.produto.ProdutoRepository;
import backend.repositories.produto.ProdutoRepositoryImpl;
import backend.utils.Categoria;

public class ProdutoService {

	ProdutoRepository repository = new ProdutoRepositoryImpl();

	public void insertProduto(String codItem, Categoria categoria, String modelo, String variacao, String descricao, Integer estoque,
			Boolean isEmMassa) throws SQLException {
		repository.insertProduto(codItem, categoria, modelo, variacao, descricao, estoque, isEmMassa);
	}

	public void editProduto(Long id, String codItem, String modelo, String variacao, String descricao, Integer estoque)
			throws SQLException {
		repository.editProduto(id, codItem, modelo, variacao, descricao, estoque);
	}

	public void setFlagProduto(Long id, Integer flag) throws SQLException {
		repository.setFlagProduto(id, flag);
	}

	public ProdutoEntity findById(Long id) throws SQLException {
		return repository.findById(id);
	}

	public List<ProdutoEntity> findProdutos(String codItem, Categoria categoria, String modelo, String variacao, String descricao,
			Integer estoque) throws SQLException {
		return repository.findProdutos(codItem, categoria, modelo, variacao, descricao, estoque);
	}

}
