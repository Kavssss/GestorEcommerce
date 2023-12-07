package backend.dto;

import java.sql.Date;

import backend.entities.geralEntity.VendaGeralEntity;
import frontend.utils.DataUtils;
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
	private String valorUnitario;
	private String valorTotal;
	private String valorRecebido;
	private String fData = DataUtils.dateToString(this.getData());
	
	public VendaGeralDTO(Long id, Date data, String cliente, String status, String canal, String codItem,
			Integer qtde, String valorUnitario, String valorTotal, String valorRecebido) {
		super(id, data, cliente, status, canal);
		this.codItem = codItem;
		this.qtde = qtde;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.valorRecebido = valorRecebido;
	}
	
}
