package backend.dto;

import java.sql.Date;

import backend.entities.mercadoLivreEntity.VendaMercadoLivreEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VendaMercadoLivreDTO extends VendaMercadoLivreEntity {
	
	private String codItem;
	private Integer qtde;
	private Double valorUnitario;
	private Double valorTotal;
	private Double valorRecebido;
	private String tipoAnuncio;
	private Boolean isFreteGratis;
	private Double totalSemFrete;

	public VendaMercadoLivreDTO(Long idVenda, Date data, String cliente, String status, Long idDado,
			String codItem, Integer qtde, Double valorUnitario, Double valorTotal, Double valorRecebido,
			String tipoAnuncio, Boolean isFreteGratis, Double totalSemFrete) {
		super(idVenda, data, cliente, status, idDado);
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