package backend.dto;

import java.sql.Date;

import backend.entities.shopeeEntity.VendaShopeeEntity;
import frontend.utils.DataUtils;
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
	private String valorUnitario;
	private String valorTotal;
	private String valorRecebido;
	private String fData = DataUtils.dateToString(this.getData());

	public VendaShopeeDTO(Long id, Date data, String cliente, String status, Long idDado, String codItem,
			Integer qtde, String valorUnitario, String valorTotal, String valorRecebido) {
		super(id, data, cliente, status, idDado);
		this.codItem = codItem;
		this.qtde = qtde;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.valorRecebido = valorRecebido;
	}
	
}
