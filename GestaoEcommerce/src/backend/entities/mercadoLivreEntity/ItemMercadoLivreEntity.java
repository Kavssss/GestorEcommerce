package backend.entities.mercadoLivreEntity;

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
	private Boolean isFreteGratis;
	private Double totalSemFrete;
	
	public ItemMercadoLivreEntity(Long idItem, String codItem, Integer qtde, Double valorUnitario, Double valorTotal,
			Double valorRecebido, String tipoAnuncio, Boolean isFreteGratis, Double totalSemFrete) {
		super(idItem, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
		this.tipoAnuncio = tipoAnuncio;
		this.isFreteGratis = isFreteGratis;
		this.totalSemFrete = totalSemFrete;
	}
	
}

