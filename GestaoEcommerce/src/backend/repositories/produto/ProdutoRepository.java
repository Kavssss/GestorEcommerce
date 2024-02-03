package backend.repositories.produto;

import java.sql.SQLException;
import java.util.List;

import backend.entities.ProdutoEntity;
import backend.utils.Categoria;

public interface ProdutoRepository {

	void insertProduto(String codItem, Categoria categoria, String modelo, String variacao, String descricao, Integer estoque,
			Boolean isEmMassa) throws SQLException;

	void editProduto(Long id, String codItem, String modelo, String variacao, String descricao, Integer estoque) throws SQLException;

	void setFlagProduto(Long id, Integer flag) throws SQLException;

	ProdutoEntity findById(Long id) throws SQLException;

	List<ProdutoEntity> findProdutos(String codItem, Categoria categoria, String modelo, String variacao, String descricao, Integer estoque)
			throws SQLException;

}
