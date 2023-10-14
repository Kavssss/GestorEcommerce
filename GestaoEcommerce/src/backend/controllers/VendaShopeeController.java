package backend.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;
import backend.services.VendaShopeeService;

public class VendaShopeeController {

	VendaShopeeService service = new VendaShopeeService();
	
	/**
	 * Recupera todas as vendas
	 * @return Todas as vendas
	 * @throws SQLException 
	 */
	public List<VendaShopeeFormatadaEntity> findAll() throws SQLException {
		return service.findAll();
	}
	
	
	
	/**
	 * Recupera as vendas da tabela Excel a partir da data passada como parâmetro e insere no banco de dados
	 * @param data no formato "aaaa-MM-dd"
	 * @return Mensagem de sucesso ou erro ao inserir
	 */
//	public void inserirAPartirDe(String data) throws SQLException {
//		service.inserirAPartirDe(DataUtils.stringToLocalDate(data));
//	}
	
	/**
	 * Recupera as vendas da tabela Excel a na data passada como parâmetro e insere no banco de dados
	 * @param data no formato "aaaa-MM-dd"
	 * @return Mensagem de sucesso ou erro ao inserir
	 * @throws SQLException 
	 */
//	public void inserirEm(String data) throws SQLException {
//		service.inserirEm(DataUtils.stringToLocalDate(data));
//	}
	
	/**
	 * Recupera o valor total das vendas referentes ao mês passado por parâmetro
	 * @param mes (1 = jan, 2 = fev ...)
	 * @param ano (2023, 2022 ...)
	 * @return Soma das vendas do mês
	 */
//	public String findTotalVendasPorMes(Integer mes, Integer ano) {
//		return service.findTotalVendasPorMes(mes, ano);
//	}
	
	/**
	 * Recupera o valor total das vendas referentes ao dia passado por parâmetro
	 * @param dia  -  1 a 31
	 * @param mes  -  1 a 12
	 * @param ano  -  2023, 2022, 2021...
	 * @return Soma das vendas do mês
	 */
//	public String findTotalVendasPorDia(Integer dia, Integer mes, Integer ano) {
//		return service.findTotalVendasPorDia(dia, mes, ano);
//	}
	
	/**
	 * Recupera o valor total das vendas por cliente
	 * @param cliente  -  nome do usuário no e-comerce
	 * @return Soma das vendas do cliente
	 */
//	public String findTotalVendasPorCliente(String cliente) {
//		return service.findTotalVendasPorCliente(cliente);
//	}
	
	/**
	 * Recupera as vendas por cliente
	 * @param cliente  -  nome do usuário no e-comerce
	 * @return Dados das vendas por cliente
	 */
//	public String findVendasPorCliente(String cliente) {
//		return service.findVendasPorCliente(cliente);
//	}
	
}

