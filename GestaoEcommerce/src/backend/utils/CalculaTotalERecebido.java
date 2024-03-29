package backend.utils;

public class CalculaTotalERecebido {

	public static Double calculaTotal(Integer qtde, Double unitario) {
		return (Double) unitario * qtde;
	}

	public static Double calculaTotal(String qtde, String unitario) {
		return (Double) Double.valueOf(unitario) * Integer.valueOf(qtde);
	}

	public static Double calculaRecebidoShopee(Double total, Integer qtde) {
		Double result = total - total * Taxas.getTaxaShopee() - Taxas.getCustoFixoShopee() * qtde;
		return result < 0 ? 0D : result;
	}

	public static Double calculaRecebidoShopee(Double total, String qtde) {
		return (Double) total - total * Taxas.getTaxaShopee() - Taxas.getCustoFixoShopee() * Integer.valueOf(qtde);
	}

	public static Double calculaRecebidoML(Double unitario, Integer qtde, String tipoAnuncio) {
		return calculaRecebidoMercadoLivre(tipoAnuncio, String.valueOf(qtde), String.valueOf(unitario));
	}

	public static Double calculaRecebidoML(String unitario, String qtde, String tipoAnuncio) {
		return calculaRecebidoMercadoLivre(tipoAnuncio, qtde, unitario);
	}

	private static Double calculaRecebidoMercadoLivre(String tipoAnuncio, String quantidade, String unitario) {
		Double valorUnitario = Double.valueOf(unitario);
		Integer qtde = Integer.valueOf(quantidade);
		Double valorTotal = valorUnitario * qtde;
		Double valorSemFrete = valorSemFreteML(valorTotal, qtde);

		if (valorUnitario < 79)
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO)
					|| tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO_FG))
				return (Double) valorSemFrete - valorTotal * Taxas.getTaxaClassicoML() - Taxas.getCustoFixoML();
		if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM)
				|| tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM_FG))
			return (Double) valorSemFrete - valorTotal * Taxas.getTaxaPremiumML() - Taxas.getCustoFixoML();
		else if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO)
				|| tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO_FG))
			return (Double) valorSemFrete - valorTotal * Taxas.getTaxaClassicoML();
		if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM)
				|| tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM_FG))
			return (Double) valorSemFrete - valorTotal * Taxas.getTaxaPremiumML();
		return null;
	}

	public static Double valorSemFreteML(Double valorTotal, Integer qtde) {
		return valorTotal - Taxas.getFreteML() * qtde;
	}

}
