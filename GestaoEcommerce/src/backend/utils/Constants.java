package backend.utils;

public interface Constants {
	
	interface TAXA_ML {
		Double TAXA_CLASSICO = 0.14;
		Double TAXA_PREMIUM = 0.19;
		Double CUSTO_FIXO = 6.0;
		Double FRETE = 18.95;
	}
	
	interface TAXA_SHOPEE {
		Double TAXA = 0.2;
		Double CUSTO_FIXO = 3.0;
	}
	
	interface TIPO_ANUNCIO_ML {
		String CLASSICO = "Classico";
		String PREMIUM = "Premium";
		String CLASSICO_FG = "Clássico - Frete Grátis";
		String PREMIUM_FG = "Premium - Frete Grátis";
	}
	
}
