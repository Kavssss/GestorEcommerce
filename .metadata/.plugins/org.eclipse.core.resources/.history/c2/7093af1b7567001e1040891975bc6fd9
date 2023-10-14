package backend.entities.shopeeEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backend.utils.DataUtils;

public class VendaShopeeEntity {
		
	private Long id;
	private LocalDate data;
	private String conta;
	private String cliente;
	private List<ItemShopeeEntity> itens = new ArrayList<>();
	private String status;
	
	public VendaShopeeEntity() {
	}
		
	public VendaShopeeEntity(List<String> s) {
		if (s.get(0).length() > 10)
			this.data = DataUtils.stringExcelToLocalDate(s.get(0));
		else
			this.data = DataUtils.stringToLocalDate(s.get(0));
		this.conta = s.get(1);
		this.cliente = s.get(5);
		addItens(s.get(2), s.get(3), s.get(4), s.get(6));
		this.status = s.get(7);
	}
	
	public VendaShopeeEntity(String id, List<String> s) {
		this.id = Long.parseLong(id);
		if (s.get(0).length() > 10)
			this.data = DataUtils.stringExcelToLocalDate(s.get(0));
		else
			this.data = DataUtils.stringToLocalDate(s.get(0));
		this.conta = s.get(1);
		this.cliente = s.get(5);
		addItens(s.get(2), s.get(3), s.get(4), s.get(6));
		this.status = s.get(7);
	}

	public void addItens(String qtde, String modelo, String variacao, String valorUnitario) {
		variacao = variacao.replaceAll(" ", "");
		List<String> variacoes = Arrays.asList(variacao.split("-"));
		Set<String> variacoesUnicas = new HashSet<>(variacoes);

		if (variacoesUnicas.size() > 1)
			for (String vu : variacoesUnicas) {
				Long copias = variacoes.stream().map(v -> v.equals(vu)).count();
				itens.add(new ItemShopeeEntity(copias.toString(), modelo, vu, valorUnitario));
			}
		else
			itens.add(new ItemShopeeEntity(qtde, modelo, variacoes.get(0), valorUnitario));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<ItemShopeeEntity> getItens() {
		return itens;
	}

	public void setItens(List<ItemShopeeEntity> itens) {
		this.itens = itens;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
