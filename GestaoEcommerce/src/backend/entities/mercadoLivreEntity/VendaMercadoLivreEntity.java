package backend.entities.mercadoLivreEntity;

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
public class VendaMercadoLivreEntity {
	
	private Long id;
	private Date data;
	private String cliente;
	private String telefone;
	private List<ItemMercadoLivreEntity> itens = new ArrayList<>();
	private String status;

	public VendaMercadoLivreEntity(Long id, Date data, String cliente, String telefone, String status) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.telefone = telefone;
		this.status = status;
	}

	public void addItem(ItemMercadoLivreEntity item) {
		this.itens.add(item);
	}
	
	public void addItens(String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido,
			String tipoAnuncio, Boolean isFreteGratis, Double totalSemFrete) {
		this.itens.add(new ItemMercadoLivreEntity(codItem, qtde, valorUnitario, valorTotal, valorRecebido, tipoAnuncio,
				isFreteGratis, totalSemFrete));
	}
		
}

