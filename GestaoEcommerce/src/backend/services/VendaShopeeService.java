package backend.services;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;
import backend.repositories.vendaShopee.VendaShopeeRepository;
import backend.repositories.vendaShopee.VendaShopeeRepositoryImpl;

public class VendaShopeeService {

	VendaShopeeRepository repository = new VendaShopeeRepositoryImpl();
	
	public List<VendaShopeeFormatadaEntity> findVendas(Date dataInicio, Date dataFim, String contaAnuncio,
			Integer qtde, String codItem, String cliente, String status) throws SQLException {
		return repository.findVendas(dataInicio, dataFim, contaAnuncio, qtde, codItem, cliente, status);
	}
	
//	public String findTotalVendasPorMes(Integer mes, Integer ano) {
//		List<String> result = new ArrayList<>();
//		List<VendaShopee> vendas = repository.findVendasPorMes(mes, ano);		
//		result.add(String.valueOf(vendas.stream().mapToDouble(venda -> venda.getValorTotal()).sum()));
//		result.add(String.valueOf(vendas.stream().mapToInt(venda -> venda.getPedido()).sum()));
//		result.add(String.valueOf(Double.valueOf(result.get(0)) / Integer.valueOf(result.get(1))));		
//		StringBuilder retorno = new StringBuilder("Vendas do mês ").append(DataUtils.formataMes(mes));
//		return formataTotalQtdeMedia(result, retorno);
//	}
//	
//	public String findTotalVendasPorDia(Integer dia, Integer mes, Integer ano) {
//		List<String> result = new ArrayList<>(); 
//		List<VendaShopee> vendas = repository.findVendasPorDia(dia, mes, ano);		
//		result.add(String.valueOf(vendas.stream().mapToDouble(venda -> venda.getValorTotal()).sum()));
//		result.add(String.valueOf(vendas.stream().mapToInt(venda -> venda.getPedido()).sum()));
//		result.add(String.valueOf(Double.valueOf(result.get(0)) / Integer.valueOf(result.get(1))));		
//		StringBuilder retorno = new StringBuilder("Vendas do dia ").append(DataUtils.formataData(dia, mes, ano));
//		return formataTotalQtdeMedia(result, retorno);
//	}
//	
//	public String findTotalVendasPorCliente(String cliente) {
//		List<String> result = new ArrayList<>(); 
//		List<VendaShopee> vendas = repository.findVendasPorCliente(cliente);
//		result.add(String.valueOf(vendas.stream().mapToDouble(venda -> venda.getValorTotal()).sum()));
//		result.add(String.valueOf(vendas.stream().mapToInt(venda -> venda.getPedido()).sum()));
//		result.add(String.valueOf(Double.valueOf(result.get(0)) / Integer.valueOf(result.get(1))));		
//		StringBuilder retorno = new StringBuilder("Pedidos do(a) cliente ").append(cliente);
//		return formataTotalQtdeMedia(result, retorno);
//	}
//	
//	public String findVendasPorCliente(String cliente) {
//		return descricaoVendas(repository.findVendasPorCliente(cliente));
//	}
	
//	private String formataTotalQtdeMedia(List<String> list, StringBuilder sb) {		
//		sb.append("\nValor total: ").append(list.get(0));
//		sb.append("\nQuantidade de pedidos: ").append(list.get(1));
//		sb.append("\nMédia de valor por pedido: ").append(list.get(2));
//		return sb.toString();
//	}
	
//	private String descricaoVendas(List<VendaShopee> vendas) {
//		StringBuilder desc = new StringBuilder();
//		desc.append("   DATA    -   CONTA   - QTDE - ");
//		desc.append("   MODELO   ").append("  -  ").append("  VARIAÇÃO  ").append("  -  ").append("    CLIENTE    ").append("  -  ");
//		desc.append(" VALOR UNIT. ").append("  -  ").append(" VALOR TOTAL ").append("  -  ").append(" VALOR RECEBIDO ").append("  -  ");
//		desc.append("   STATUS   ").append("\n");
//		vendas.forEach(venda -> {
//			desc.append(venda.getData().toString()).append(" - ").append(venda.getConta()).append(" - ").append(venda.getQtde().toString());
//			desc.append("  -  ").append(venda.getModelo()).append("  -  ").append(listToString(venda.getVariacao())).append("  -  ");
//			desc.append(venda.getCliente()).append("  -  ").append(venda.getValorUnitario().toString()).append("  -  ").append(venda.getValorTotal().toString());
//			desc.append("  -  ").append(venda.getValorRecebido().toString()).append("  -  ").append(venda.getStatus()).append("\n");
//		});
//		return desc.toString();
//	}
	
//	private String listToString(List<String> list) {
//    	StringBuilder sb = new StringBuilder();
//    	if (list.size() > 1) {
//    		for (String s : list)
//    			sb.append(s).append("-");
//    		sb.replace(sb.length() - 1, sb.length(), "");
//    	} else {
//    		sb.append(list.get(0));
//    	}
//    	return sb.toString();
//    }
	
//	private Integer calculaQtdePedidos(List<VendaShopee> vendas) {
//		return vendas.stream()
//			    .map(VendaShopee::getId)
//			    .collect(Collectors.toSet())
//			    .size();
//	}
//	
//	private Integer calculaQtdeItens(List<VendaShopee> vendas) {
//		return vendas.stream()
//			    .flatMap(venda -> venda.getItens().stream())
//			    .mapToInt(ItemShopee::getQtde)
//			    .sum();
//	}
//	
//	private Double calculaValorTotal(List<VendaShopee> vendas) {
//		return vendas.stream()
//				.flatMap(venda -> venda.getItens().stream())
//				.mapToDouble(ItemShopee::getValorTotal)
//				.sum();
//	}
//	
//	private Double calculaValorRecebido(List<VendaShopee> vendas) {
//		return vendas.stream()
//				.flatMap(venda -> venda.getItens().stream())
//				.mapToDouble(ItemShopee::getValorRecebido)
//				.sum();
//	}
	
}




