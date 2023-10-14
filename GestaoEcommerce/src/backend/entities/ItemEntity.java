package backend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemEntity {

	protected String codItem;
	protected Integer qtde;
	protected Double valorUnitario;
	protected Double valorTotal;
	protected Double valorRecebido;
	
}
