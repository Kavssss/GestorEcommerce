package backend.utils;

public class Taxas {

	private static Double taxaClassicoML = 0.14;
	private static Double taxaPremiumML = 0.19;
	private static Double custoFixoML = 6.0;
	private static Double freteML = 18.95;

	private static Double taxaShopee = 0.2;
	private static Double custoFixoShopee = 3.0;

	public static void buildTaxasMLClassico(Double taxaClassicoML, Double custoFixoML, Double freteML) {
		Taxas.taxaClassicoML = taxaClassicoML;
		Taxas.custoFixoML = custoFixoML;
		Taxas.freteML = freteML;
	}

	public static void buildTaxasMLPremium(Double taxaPremiumML, Double custoFixoML, Double freteML) {
		Taxas.taxaPremiumML = taxaPremiumML;
		Taxas.custoFixoML = custoFixoML;
		Taxas.freteML = freteML;
	}

	public static void buildTaxasShopee(Double taxaShopee, Double custoFixoShopee) {
		Taxas.taxaShopee = taxaShopee;
		Taxas.custoFixoShopee = custoFixoShopee;
	}

	public static Double getTaxaClassicoML() {
		return taxaClassicoML;
	}

	public static void setTaxaClassicoML(Double taxaClassicoML) {
		Taxas.taxaClassicoML = taxaClassicoML;
	}

	public static Double getTaxaPremiumML() {
		return taxaPremiumML;
	}

	public static void setTaxaPremiumML(Double taxaPremiumML) {
		Taxas.taxaPremiumML = taxaPremiumML;
	}

	public static Double getCustoFixoML() {
		return custoFixoML;
	}

	public static void setCustoFixoML(Double custoFixoML) {
		Taxas.custoFixoML = custoFixoML;
	}

	public static Double getFreteML() {
		return freteML;
	}

	public static void setFreteML(Double freteML) {
		Taxas.freteML = freteML;
	}

	public static Double getTaxaShopee() {
		return taxaShopee;
	}

	public static void setTaxaShopee(Double taxaShopee) {
		Taxas.taxaShopee = taxaShopee;
	}

	public static Double getCustoFixoShopee() {
		return custoFixoShopee;
	}

	public static void setCustoFixoShopee(Double custoFixoShopee) {
		Taxas.custoFixoShopee = custoFixoShopee;
	}

}
