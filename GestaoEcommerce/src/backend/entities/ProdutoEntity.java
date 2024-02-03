package backend.entities;

import backend.utils.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoEntity {

	private Long id;
	private String codItem;
	private Categoria categoria;
	private String modelo;
	private String variacao;
	private String descricao;
	private Integer estoque;
	private Boolean isAtivo;

	public ProdutoEntity(Long id, String codItem, Integer categoria, String modelo, String variacao, String descricao,
			Integer estoque, Boolean isAtivo) {
		this.id = id;
		this.codItem = codItem;
		this.categoria = Categoria.obterPorValor(categoria);
		this.modelo = modelo;
		this.variacao = variacao;
		this.descricao = descricao;
		this.estoque = estoque;
		this.isAtivo = isAtivo;
	}

}
