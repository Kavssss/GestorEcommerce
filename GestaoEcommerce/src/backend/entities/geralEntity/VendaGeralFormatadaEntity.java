package backend.entities.geralEntity;

import java.util.Date;

import backend.utils.CanalVenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaGeralFormatadaEntity extends VendaGeralEntity {

	private String codItem;
	private Integer qtde;
	private Double valorUnitario;
	private Double valorTotal;
	private Double valorRecebido;
	
	public VendaGeralFormatadaEntity(Long id, Date data, String cliente, String status, CanalVenda canal, String codItem,
			Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido) {
		super(id, data, cliente, status, canal);
		this.codItem = codItem;
		this.qtde = qtde;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.valorRecebido = valorRecebido;
	}
	
}
