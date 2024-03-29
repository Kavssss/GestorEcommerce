package backend.entities.geral;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import backend.utils.CanalVenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaGeralEntity {

	private Long id;
	private Date data;
	private String cliente;
	private List<ItemGeralEntity> itens = new ArrayList<>();
	private String status;
	private String canal;

	public VendaGeralEntity(Long id, Date data, String cliente, String status, CanalVenda canal) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.status = status;
		this.canal = canal.toString();
	}

	public VendaGeralEntity(Long id, Date data, String cliente, String status, String canal) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.status = status;
		this.canal = canal.equals(CanalVenda.S) ? CanalVenda.S : CanalVenda.ML;
	}

	public void addItem(ItemGeralEntity item) {
		this.itens.add(item);
	}

	public void addItem(Long idItem, String codItem, Integer qtde, Double valorUnitario, Double valorTotal,
			Double valorRecebido) {
		this.itens.add(new ItemGeralEntity(idItem, codItem, qtde, valorUnitario, valorTotal, valorRecebido));
	}

}
