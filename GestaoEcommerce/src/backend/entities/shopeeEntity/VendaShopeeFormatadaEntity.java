package backend.entities.shopeeEntity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaShopeeFormatadaEntity extends VendaShopeeEntity {
	
	private String codItem;
	private Integer qtde;
	private Double valorUnitario;
	private Double valorTotal;
	private Double valorRecebido;
	
	public VendaShopeeFormatadaEntity(Long id, Date data, String cliente, String status, String codItem,
			Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido) {
		super(id, data, cliente, status);
		this.codItem = codItem;
		this.qtde = qtde;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.valorRecebido = valorRecebido;
	}
	
}
