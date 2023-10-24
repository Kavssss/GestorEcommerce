package backend.entities.mercadoLivreEntity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaMercadoLivreFormatadaEntity extends VendaMercadoLivreEntity {
	
	private String codItem;
	private Integer qtde;
	private Double valorUnitario;
	private Double valorTotal;
	private Double valorRecebido;
	private String tipoAnuncio;
	private Boolean isFreteGratis;
	private Double totalSemFrete;

	public VendaMercadoLivreFormatadaEntity(Long id, Date data, String cliente, String status,
			String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido,
			String tipoAnuncio, Boolean isFreteGratis, Double totalSemFrete) {
		super(id, data, cliente, status);
		this.codItem = codItem;
		this.qtde = qtde;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.valorRecebido = valorRecebido;
		this.tipoAnuncio = tipoAnuncio;
		this.isFreteGratis = isFreteGratis;
		this.totalSemFrete = totalSemFrete;
	}
	
}
