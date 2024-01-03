package backend.entities.geralEntity;

import backend.entities.ItemEntity;

public class ItemGeralEntity extends ItemEntity {

	public ItemGeralEntity(Long idItem, String codItem, Integer qtde, Double valorUnitario, Double valorTotal,
			Double valorRecebido) {
		super(idItem, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
	}

}
