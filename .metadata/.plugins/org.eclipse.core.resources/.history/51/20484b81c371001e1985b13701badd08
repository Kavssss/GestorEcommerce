package backend.utils;

public class CalculaTotalERecebido {

	public static Double calculaTotal(Integer qtde, Double unitario) {
		return (Double) unitario * qtde;
	}
	
	public static Double calculaRecebido(String canal, Integer qtde, Double valorUnitario, String tipoAnuncio) {
    	Double valorTotal = calculaTotal(qtde, valorUnitario);
    	if (canal.equals(CanalVenda.SHOPEE))
    		return calculaRecebidoShopee(valorTotal);
    	if (canal.equals(CanalVenda.MERCADO_LIVRE))
    		return calculaRecebidoMercadoLivre(tipoAnuncio, valorUnitario, valorTotal);
    	return null;
    }
	
	private static Double calculaRecebidoShopee(Double valorTotal) {
		return (Double) valorTotal - (valorTotal * Constants.TAXA_SHOPEE.TAXA) - Constants.TAXA_SHOPEE.CUSTO_FIXO;
	}
	
	private static Double calculaRecebidoMercadoLivre(String tipoAnuncio, Double valorUnitario, Double valorTotal) {
		if (valorUnitario < 79)
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO))
				return (Double) valorTotal - (valorTotal * Constants.TAXA_ML.TAXA_CLASSICO) - Constants.TAXA_ML.CUSTO_FIXO;
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO_FG))
				return (Double) valorTotal - (valorTotal * Constants.TAXA_ML.TAXA_CLASSICO) - Constants.TAXA_ML.FRETE - Constants.TAXA_ML.CUSTO_FIXO;
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM))
				return (Double) valorTotal - (valorTotal * Constants.TAXA_ML.TAXA_PREMIUM) - Constants.TAXA_ML.CUSTO_FIXO;
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM_FG))
				return (Double) valorTotal - (valorTotal * Constants.TAXA_ML.TAXA_PREMIUM) - Constants.TAXA_ML.FRETE - Constants.TAXA_ML.CUSTO_FIXO;
		else
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO))
				return (Double) valorTotal - (valorTotal * Constants.TAXA_ML.TAXA_CLASSICO);
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO_FG))
				return (Double) valorTotal - (valorTotal * Constants.TAXA_ML.TAXA_CLASSICO) - Constants.TAXA_ML.FRETE;
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM))
				return (Double) valorTotal - (valorTotal * Constants.TAXA_ML.TAXA_PREMIUM);
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM_FG))
				return (Double) valorTotal - (valorTotal * Constants.TAXA_ML.TAXA_PREMIUM) - Constants.TAXA_ML.FRETE;
		return null;
	}
	
}
