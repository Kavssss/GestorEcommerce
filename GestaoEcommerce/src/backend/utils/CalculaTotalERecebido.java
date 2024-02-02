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

	public static Double calculaRecebidoML(Double unitario, Integer qtde, String tipoAnuncio, Double custoFrete) {
		return calculaRecebidoMercadoLivre(tipoAnuncio, qtde, unitario, custoFrete);
	}

	public static Double calculaRecebidoML(String unitario, String qtde, String tipoAnuncio, String custoFrete) {
		return calculaRecebidoMercadoLivre(tipoAnuncio, Integer.valueOf(qtde), Double.valueOf(unitario), Double.valueOf(custoFrete));
	}

	private static Double calculaRecebidoMercadoLivre(String tipoAnuncio, Integer quantidade, Double unitario, Double custoFrete) {
		
		Double valorTotal = unitario * quantidade;
		Double totalSemFrete = valorTotal - custoFrete * quantidade; // valorSemFreteML(valorTotal, qtde);

		if (unitario < 79) {
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO))
				return (Double) totalSemFrete - valorTotal * Taxas.getTaxaClassicoML() - Taxas.getCustoFixoML();
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM))
				return (Double) totalSemFrete - valorTotal * Taxas.getTaxaPremiumML() - Taxas.getCustoFixoML();
		} else {
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.CLASSICO))
				return (Double) totalSemFrete - valorTotal * Taxas.getTaxaClassicoML();
			if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO_ML.PREMIUM))
				return (Double) totalSemFrete - valorTotal * Taxas.getTaxaPremiumML();
		}
		return null;
	}

//	public static Double valorSemFreteML(Double valorTotal, Integer qtde) {
//		return valorTotal - Taxas.getFreteML() * qtde;
//	}

}
