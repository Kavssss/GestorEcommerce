package backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDTO {

	private Long id;
	private String codItem;
	private String modelo;
	private String variacao;
	private String descricao;
	private String isAtivo;

}
