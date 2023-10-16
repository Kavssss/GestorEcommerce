package frontend.utils;

public interface Constants {
	
	interface LOJA {
		String SHOPEE = "Shopee";
		String MERCADO_LIVRE = "Mercado Livre";
		String GERAL = "Geral";
	}
	
	interface CONTA_ANUNCIO {
		String CONTA = "Conta";
		String TIPO_ANUNCIO = "Tipo de Anúncio";
		
		String PRINCIPAL = "Principal";
		String SECUNDARIA = "Secundária";
		String CLASSICO = "Classico";
		String PREMIUM = "Premium";
		String CLASSICO_FG = "Clássico - Frete Grátis";
		String PREMIUM_FG = "Premium - Frete Grátis";
	}
	
	interface STATUS {
		String PENDENTE = "Pendente";
		String CONCLUIDO = "Concluído";
		String DEVOLUCAO = "Devolução";
		String CANCELADO = "Cancelado";
	}
	
	interface VIEWS {		
		String INDEX = "/frontend/views/Index.fxml";
		String VENDAS = "/frontend/views/ViewVendas.fxml";
		String PRODUTOS = "/frontend/views/ViewProdutos.fxml";
		String RELATORIOS = "/frontend/views/ViewRelatorios.fxml";
	}
	
	interface MODAL {
		String INSERIR_VENDA = "/frontend/views/ModalInserir.fxml";		
	}
	
}