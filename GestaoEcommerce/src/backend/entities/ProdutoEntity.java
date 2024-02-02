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
	private Boolean isAtivo;
	private Integer estoque;
	
}
