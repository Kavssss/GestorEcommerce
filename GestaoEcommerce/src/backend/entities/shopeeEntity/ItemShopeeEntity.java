package backend.entities.shopeeEntity;

import backend.utils.Constants;

public class ItemShopeeEntity {

	private String codItem;
	private Integer qtde;
	private String modelo;
	private String variacao;
	private Double valorUnitario;
	private Double valorTotal;
	private Double valorRecebido;
	
	public ItemShopeeEntity() {
	}
	
	public ItemShopeeEntity(String qtde, String modelo, String variacao, String valorUnitario) {
		this.codItem = modelo.concat("-").concat(variacao);
		this.qtde = Double.valueOf(qtde).intValue();
		this.modelo = modelo;
		this.variacao = variacao;		
		this.valorUnitario = Double.valueOf(valorUnitario);
		this.valorTotal = this.valorUnitario * this.qtde;
		this.valorRecebido = this.valorTotal - this.valorTotal * Constants.TAXA_SHOPEE.TAXA - Constants.TAXA_SHOPEE.CUSTO_FIXO;
	}
	
	public String getCodItem() {
		return codItem;
	}

	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getVariacao() {
		return variacao;
	}

	public void setVariacao(String variacao) {
		this.variacao = variacao;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(Double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	
}
