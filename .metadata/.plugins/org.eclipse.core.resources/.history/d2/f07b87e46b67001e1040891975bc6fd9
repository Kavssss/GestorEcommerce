package backend.entities.mercadoLivreEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backend.utils.DataUtils;

public class VendaMercadoLivreEntity {
	
	private Long id;
	private LocalDate data;
	private String cliente;
	private String telefone;
	private List<ItemMercadoLivreEntity> itens = new ArrayList<>();
	private String status;
	
	public VendaMercadoLivreEntity() {
	}

	public VendaMercadoLivreEntity(List<String> s) {
		this.data = DataUtils.stringExcelToLocalDate(s.get(0));
		this.cliente = s.get(6);
		this.telefone = s.get(7);
		addItens(s.get(1), s.get(2), s.get(3), s.get(4), s.get(5), s.get(8));
		this.status = s.get(9);
	}
	
	public VendaMercadoLivreEntity(String id, List<String> s) {
		this.id = Long.parseLong(id);
		this.data = DataUtils.stringExcelToLocalDate(s.get(0));
		this.cliente = s.get(6);
		this.telefone = s.get(7);
		addItens(s.get(1), s.get(2), s.get(3), s.get(4), s.get(5), s.get(8));
		this.status = s.get(9);
	}

	public void addItens(String tipoAnuncio, String isFreteGratis, String qtde, String modelo, String variacao, String valorUnitario) {
		variacao = variacao.replaceAll(" ", "");
		List<String> variacoes = Arrays.asList(variacao.split("-"));
		Set<String> variacoesUnicas = new HashSet<>(variacoes);

		if (variacoesUnicas.size() > 1)
			for (String vu : variacoesUnicas) {
				Long copias = variacoes.stream().map(v -> v.equals(vu)).count();
				itens.add(new ItemMercadoLivreEntity(tipoAnuncio, isFreteGratis, copias.toString(), modelo, vu, valorUnitario));
			}
		else
			itens.add(new ItemMercadoLivreEntity(tipoAnuncio, isFreteGratis, qtde, modelo, variacoes.get(0), valorUnitario));
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<ItemMercadoLivreEntity> getItens() {
		return itens;
	}

	public void setItens(List<ItemMercadoLivreEntity> itens) {
		this.itens = itens;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
}

