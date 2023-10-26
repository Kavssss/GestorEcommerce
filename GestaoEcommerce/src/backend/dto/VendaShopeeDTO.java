package backend.dto;

import java.sql.Date;

import backend.entities.shopeeEntity.VendaShopeeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaShopeeDTO extends VendaShopeeEntity {
	
	private String codItem;
	private Integer qtde;
	private Double valorUnitario;
	private Double valorTotal;
	private Double valorRecebido;

	public VendaShopeeDTO(Long id, Date data, String cliente, String status, Long idDado, String codItem,
			Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido) {
		super(id, data, cliente, status, idDado);
		this.codItem = codItem;
		this.qtde = qtde;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.valorRecebido = valorRecebido;
	}
	
}