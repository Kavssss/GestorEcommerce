package backend.dto;

import java.util.Date;

import backend.entities.geralEntity.VendaGeralEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaGeralDTO extends VendaGeralEntity {

	private String codItem;
	private Integer qtde;
	private Double valorUnitario;
	private Double valorTotal;
	private Double valorRecebido;
	
	public VendaGeralDTO(Long id, Date data, String cliente, String status, String canal, String codItem,
			Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido) {
		super(id, data, cliente, status, canal);
		this.codItem = codItem;
		this.qtde = qtde;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.valorRecebido = valorRecebido;
	}
	
}