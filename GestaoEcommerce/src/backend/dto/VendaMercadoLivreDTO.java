package backend.dto;

import java.sql.Date;

import backend.entities.mercadoLivreEntity.VendaMercadoLivreEntity;
import frontend.utils.DataUtils;
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
	private String valorUnitario;
	private String valorTotal;
	private String valorRecebido;
	private String tipoAnuncio;
	private Boolean isFreteGratis;
	private String totalSemFrete;
	private String fData = DataUtils.dateToString(this.getData());

	public VendaMercadoLivreDTO(Long idVenda, Date data, String cliente, String status, Long idDado, String codItem,
			Integer qtde, String valorUnitario, String valorTotal, String valorRecebido, String tipoAnuncio,
			Boolean isFreteGratis, String totalSemFrete) {
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
