package backend.entities.mercadoLivreEntity;

import backend.entities.ItemEntity;
import backend.utils.Constants;
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
	
	public ItemMercadoLivreEntity(String codItem, String tipoAnuncio, String isFreteGratis, String qtde, String valorUnitario) {
		this.codItem = codItem;
		this.tipoAnuncio = tipoAnuncio;
		this.isFreteGratis = isFreteGratis.equals("S") ? Boolean.TRUE : Boolean.FALSE;
		this.qtde = Double.valueOf(qtde).intValue();
		this.valorUnitario = Double.valueOf(valorUnitario);
		this.valorTotal = this.valorUnitario * this.qtde;
		this.totalSemFrete = this.isFreteGratis ? valorTotal - Constants.TAXA_ML.FRETE * this.qtde : valorTotal;
		this.valorRecebido = valorRecebido(tipoAnuncio);
	}
	
	private Double valorRecebido(String tipoAnuncio) {
		if (!isFreteGratis)
			return totalSemFrete - comissao(tipoAnuncio) - Constants.TAXA_ML.CUSTO_FIXO * qtde;
		else
			return totalSemFrete - comissao(tipoAnuncio);	
	}
	
	private Double comissao(String tipoAnuncio) {
		if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO) || tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO_FG))
			return valorTotal * Constants.TAXA_ML.TAXA_CLASSICO;
		else
			return valorTotal * Constants.TAXA_ML.TAXA_PREMIUM;
	}
	
}

