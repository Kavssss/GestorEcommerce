package backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemEntity {

	protected Long id;
	protected String codItem;
	protected Integer qtde;
	protected Double valorUnitario;
	protected Double valorTotal;
	protected Double valorRecebido;
	private String descricao;
	private Boolean isAtivo = Boolean.TRUE;

	public ItemEntity(Long id, String codItem, Integer qtde, Double valorUnitario, Double valorTotal,
			Double valorRecebido) {
		this.id = id;
		this.codItem = codItem;
		this.qtde = qtde;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.valorRecebido = valorRecebido;
	}

	public ItemEntity(Long id, String codItem, String descricao, Boolean isAtivo) {
		this.id = id;
		this.codItem = codItem;
		this.descricao = descricao;
		this.isAtivo = isAtivo;
	}

}
