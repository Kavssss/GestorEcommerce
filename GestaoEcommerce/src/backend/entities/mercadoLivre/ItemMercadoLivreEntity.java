package backend.entities.mercadoLivre;

import backend.entities.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemMercadoLivreEntity extends ItemEntity {

	private String tipoAnuncio;
	private Double custoFrete;
	private Double totalSemFrete;

	public ItemMercadoLivreEntity(Long idItem, String codItem, Integer qtde, Double valorUnitario, Double valorTotal,
			Double valorRecebido, String tipoAnuncio, Double custoFrete, Double totalSemFrete) {
		super(idItem, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
		this.tipoAnuncio = tipoAnuncio;
		this.custoFrete = custoFrete;
		this.totalSemFrete = totalSemFrete;
	}

}
