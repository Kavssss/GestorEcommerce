package backend.entities.shopeeEntity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaShopeeEntity {
		
	private Long id;
	private Date data;
	private String cliente;
	private List<ItemShopeeEntity> itens = new ArrayList<>();
	private String status;
	
	public VendaShopeeEntity(Long id, Date data, String cliente, String status) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.status = status;
	}

	public void addItem(ItemShopeeEntity item) {
		this.itens.add(item);
	}
	
	public void addItem(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido) {
		this.itens.add(new ItemShopeeEntity(codItem, qtde, valorUnitario, valorTotal, valorRecebido));
	}

}
