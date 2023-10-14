package backend.entities.shopeeEntity;

import backend.entities.ItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItemShopeeEntity extends ItemEntity {
	
	public ItemShopeeEntity(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido) {
		super(codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}
	
}
