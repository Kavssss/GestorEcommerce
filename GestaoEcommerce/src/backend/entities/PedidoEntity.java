package backend.entities;

import java.sql.Date;
import java.util.List;

import backend.utils.CanalVenda;
import backend.utils.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoEntity {

	private Long id;
	private Long idCliente;
	
	private Date data;
	private CanalVenda canalVenda;
	private StatusPedido statusPedido;
	private List<VendaEntity> vendas;
	
}
