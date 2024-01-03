package frontend.utils;

public interface Constants {

	interface LOJA {
		String SHOPEE = "Shopee";
		String MERCADO_LIVRE = "Mercado Livre";
		String MERCADO_LIVRE_CLASSICO = "Mercado Livre - Clássico";
		String MERCADO_LIVRE_PREMIUM = "Mercado Livre - Premium";
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
		String DASHBOARD = "/frontend/views/Dashboard.fxml";
	}

	interface MODAL {
		String INSERIR_VENDA = "/frontend/views/ModalInserir.fxml";
		String INSERIR_PRODUTO = "/frontend/views/ModalProduto.fxml";
		String OPCOES = "/frontend/views/Opcoes.fxml";
		String LOGIN = "/frontend/views/ModalLogin.fxml";
		String CADASTRO = "/frontend/views/ModalCadastro.fxml";
	}

	interface MESSAGE {
		String CAMPOS_NAO_PREENCHIDOS = "Preencha todos os campos para prosseguir";
		String CANAL_NAO_PREENCHIDO = "Campo CANAL não selecionado";
		String IRREVERSIVEL = "Essa ação é irreversível";
	}

	interface MES {
		String JAN = "Jan";
		String JANEIRO = "Janeiro";
		String FEV = "Fev";
		String FEVEREIRO = "Fevereiro";
		String MAR = "Mar";
		String MARCO = "Março";
		String ABR = "Abr";
		String ABRIL = "Abril";
		String MAI = "Mai";
		String MAIO = "Maio";
		String JUN = "Jun";
		String JUNHO = "Junho";
		String JUL = "Jul";
		String JULHO = "Julho";
		String AGO = "Ago";
		String AGOSTO = "Agosto";
		String SET = "Set";
		String SETEMBRO = "Setembro";
		String OUT = "Out";
		String OUTUBRO = "Outubro";
		String NOV = "Nov";
		String NOVEMBRO = "Novembro";
		String DEZ = "Dez";
		String DEZEMBRO = "Dezembro";
	}

	interface SEMESTRE {
		String PRIMEIRO = "1º Semestre";
		String SEGUNDO = "2º Semestre";
	}

	interface ANO {
		Integer _2023 = 2023;
		Integer _2022 = 2022;
		Integer _2021 = 2021;
		Integer _2020 = 2020;
	}

}
