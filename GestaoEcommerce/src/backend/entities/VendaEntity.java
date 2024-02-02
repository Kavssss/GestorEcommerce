package backend.entities;

import backend.utils.TipoAnuncioProduto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaEntity {

	private Long idVenda;
	private Long idProduto;
	private Long idPedido;
	
	private TipoAnuncioProduto tipoAnuncio;
	private Double custoFrete;
	private Integer qtd;
	private Double valorUnitario;
	private Double valorTotal;
	private Double valorRepasse;
	
}
