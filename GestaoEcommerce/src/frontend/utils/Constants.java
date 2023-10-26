package frontend.utils;

public interface Constants {
	
	interface LOJA {
		String SHOPEE = "Shopee";
		String MERCADO_LIVRE = "Mercado Livre";
		String GERAL = "Geral";
	}
	
	interface TIPO_ANUNCIO {
		String TIPO_ANUNCIO = "Tipo de Anúncio";
		
		String CLASSICO = "Clássico";
		String PREMIUM = "Premium";
		String CLASSICO_FG = "Clássico - Frete Grátis";
		String PREMIUM_FG = "Premium - Frete Grátis";
	}
	
	interface STATUS {
		String PENDENTE = "Pendente";
		String CONCLUIDO = "Concluído";
		String DEVOLUCAO = "Devolução";
		String CANCELADO = "Cancelado";
		String TODOS = "Todos";
	}
	
	interface VIEWS {		
		String INDEX = "/frontend/views/Index.fxml";
		String VENDAS = "/frontend/views/ViewVendas.fxml";
		String PRODUTOS = "/frontend/views/ViewProdutos.fxml";
		String RELATORIOS = "/frontend/views/ViewRelatorios.fxml";
	}
	
	interface MODAL {
		String INSERIR_VENDA = "/frontend/views/ModalInserir.fxml";
		String INSERIR_PRODUTO = "/frontend/views/ModalProduto.fxml";
	}
	
	interface MESSAGE {
		String CAMPOS_NAO_PREENCHIDOS = "Preencha todos os campos para prosseguir";
		String CANAL_NAO_PREENCHIDO = "Campo CANAL não selecionado";
		String IRREVERSIVEL = "Essa ação é irreversível";
	}
	
}
