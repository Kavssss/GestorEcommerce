package backend.entities.mercadoLivreEntity;

import backend.utils.Constants;

public class ItemMercadoLivreEntity {

	private static final String SIM = "S";
	
	private String codItem;
	private String tipoAnuncio;
	private Boolean isFreteGratis;
	private Integer qtde;
	private String modelo;
	private String variacao;
	private Double valorUnitario;
	private Double valorTotal;
	private Double totalSemFrete;
	private Double valorRecebido;
	
	public ItemMercadoLivreEntity() {
	}
	
	public ItemMercadoLivreEntity(String tipoAnuncio, String isFreteGratis, String qtde, String modelo, String variacao, String valorUnitario) {
		this.codItem = modelo.concat("-").concat(variacao);
		this.tipoAnuncio = tipoAnuncio;
		this.isFreteGratis = isFreteGratis.equals(SIM) ? Boolean.TRUE : Boolean.FALSE;
		this.qtde = Double.valueOf(qtde).intValue();
		this.modelo = modelo;
		this.variacao = variacao;
		this.valorUnitario = Double.valueOf(valorUnitario);
		this.valorTotal = this.valorUnitario * this.qtde;
		this.totalSemFrete = this.isFreteGratis ? valorTotal - Constants.TAXA_ML.FRETE * this.qtde : valorTotal;
		this.valorRecebido = valorRecebido(tipoAnuncio);
	}
	
	private Double valorRecebido(String tipoAnuncio) {
		if (!isFreteGratis)
			return totalSemFrete - comissao(tipoAnuncio) - Constants.TAXA_ML.CUSTO_FIXO * qtde;
		else
			return totalSemFrete - comissao(tipoAnuncio);	
	}
	
	private Double comissao(String tipoAnuncio) {
		if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO))
			return valorTotal * Constants.TAXA_ML.TAXA_CLASSICO;
		else
			return valorTotal * Constants.TAXA_ML.TAXA_PREMIUM;
	}

	public String getCodItem() {
		return codItem;
	}

	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}

	public String getTipoAnuncio() {
		return tipoAnuncio;
	}

	public void setTipoAnuncio(String tipoAnuncio) {
		this.tipoAnuncio = tipoAnuncio;
	}

	public Boolean getIsFreteGratis() {
		return isFreteGratis;
	}

	public void setIsFreteGratis(Boolean isFreteGratis) {
		this.isFreteGratis = isFreteGratis;
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

	public Double getTotalSemFrete() {
		return totalSemFrete;
	}

	public void setTotalSemFrete(Double totalSemFrete) {
		this.totalSemFrete = totalSemFrete;
	}

	public Double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(Double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}
	
}

