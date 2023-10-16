package backend.entities.geralEntity;

import java.util.ArrayList;
import java.util.Date;
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
		this.canal = canal.equals(CanalVenda.SHOPEE) ? CanalVenda.SHOPEE : CanalVenda.MERCADO_LIVRE;
	}
	
	public void addItem(ItemGeralEntity item) {
		this.itens.add(item);
	}
	
	public void addItem(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido) {
		this.itens.add(new ItemGeralEntity(codItem, qtde, valorUnitario, valorTotal, valorRecebido));
	}
	
}