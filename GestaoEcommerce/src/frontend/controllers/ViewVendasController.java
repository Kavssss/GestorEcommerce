package frontend.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.VendaGeralController;
import backend.controllers.VendaMercadoLivreController;
import backend.controllers.VendaShopeeController;
import backend.dto.VendaGeralDTO;
import backend.dto.VendaMercadoLivreDTO;
import backend.dto.VendaShopeeDTO;
import backend.utils.CalculaTotalERecebido;
import frontend.utils.Constants;
import frontend.utils.DataUtils;
import frontend.utils.LoadScene;
import frontend.views.utils.Alerts;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewVendasController implements Initializable {

	VendaGeralController geralController = new VendaGeralController();
	VendaShopeeController shopeeController = new VendaShopeeController();
	VendaMercadoLivreController mercadoLivreController = new VendaMercadoLivreController();

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnBaixarModelo;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnInserirEmMassa;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnDashboard;

	@FXML
	private Button btnOpcoes;

	@FXML
	private Button btnVendas;

	@FXML
	private Label labelLegenda;

	@FXML
	private ComboBox<String> cbCanal;

	@FXML
	private ComboBox<String> cbTipoAnuncio;

	@FXML
	private TableView<VendaGeralDTO> tbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnDataTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, Number> columnQtdTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnItemTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnClienteTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, Number> columnUnitTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, Number> columnTotalTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, Number> columnRecebidoTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnStatusTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnCanalTbGeral;

	@FXML
	private TableView<VendaMercadoLivreDTO> tbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnDataTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnAnuncioTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, Number> columnQtdTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnItemTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnClienteTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, Number> columnUnitTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, Number> columnTotalTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, Number> columnRecebidoTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnStatusTbMercadoLivre;

	@FXML
	private TableView<VendaShopeeDTO> tbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, String> columnDataTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, Number> columnQtdTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, String> columnItemTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, String> columnClienteTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, Number> columnUnitTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, Number> columnTotalTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, Number> columnRecebidoTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, String> columnStatusTbShopee;

	@FXML
	private TextField txtCliente;

	@FXML
	private TextField txtCodItem;

	@FXML
	private TextField txtDataFim;

	@FXML
	private TextField txtDataInicio;

	@FXML
	private TextField txtQtde;

	@FXML
	private ComboBox<String> cbStatus;

	@FXML
	private Label txtErroData;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		labelLegenda.setVisible(Boolean.FALSE);
		setNumberFields();
		setItensComboBox();
	}

	@FXML
	void onCanalAction() {
		List<String> options;
		String selection = cbCanal.getSelectionModel().getSelectedItem();
		if (selection != null && selection.equals(Constants.LOJA.MERCADO_LIVRE)) {
			cbTipoAnuncio.setPromptText(Constants.TIPO_ANUNCIO.TIPO_ANUNCIO);
			options = Arrays.asList(Constants.TIPO_ANUNCIO.CLASSICO, Constants.TIPO_ANUNCIO.CLASSICO_FG,
					Constants.TIPO_ANUNCIO.PREMIUM, Constants.TIPO_ANUNCIO.PREMIUM_FG);
			cbTipoAnuncio.setItems(FXCollections.observableArrayList(options));
		} else {
			cbTipoAnuncio.setPromptText("-");
			cbTipoAnuncio.setItems(null);
		}
	}

	@FXML
	void onDataInicioReleased(KeyEvent event) {
		DataUtils.formataData(txtDataInicio, event);
	}

	@FXML
	void onDataFimReleased(KeyEvent event) {
		DataUtils.formataData(txtDataFim, event);
	}

	@FXML
	void onInserirAction(ActionEvent event) {
		LoadScene.callInsertVendaModal(Constraints.currentStage(event), getClass());
	}

	@FXML
	void onBuscarAction() {
		String canal = cbCanal.getSelectionModel().getSelectedItem();
		Date dataInicio = !txtDataInicio.getText().isBlank() ? DataUtils.stringToDate(txtDataInicio.getText()) : null;
		Date dataFim = !txtDataFim.getText().isBlank() ? DataUtils.stringToDate(txtDataFim.getText()) : null;
		String tipoAnuncio = cbTipoAnuncio.getSelectionModel().getSelectedItem();
		Integer qtde = !txtQtde.getText().isBlank() ? Integer.parseInt(txtQtde.getText()) : null;
		String codItem = !txtCodItem.getText().isBlank() ? "%" + txtCodItem.getText() + "%" : null;
		String cliente = !txtCliente.getText().isBlank() ? "%" + txtCliente.getText() + "%" : null;
		String status = cbStatus.getSelectionModel().getSelectedItem();

		if (dataInicio == null && dataFim != null) {
			Insets curMargins = StackPane.getMargin(txtErroData);
			StackPane.setMargin(txtErroData,
					new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), 260));
			txtErroData.setVisible(true);
			return;
		}

		if (dataInicio != null && dataFim == null) {
			Insets curMargins = StackPane.getMargin(txtErroData);
			StackPane.setMargin(txtErroData,
					new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), 458));
			txtErroData.setVisible(true);
			return;
		}

		if ((dataInicio == null && dataFim == null) || (dataInicio != null && dataFim != null)) {
			txtErroData.setVisible(false);
		}

		if (canal == null || canal.equals(Constants.LOJA.GERAL)) {
			try {
				montaTabelaGeral(geralController.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status));
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			}
		} else if (canal.equals(Constants.LOJA.SHOPEE)) {
			try {
				montaTabelaShopee(shopeeController.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status));
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			}
		} else if (canal.equals(Constants.LOJA.MERCADO_LIVRE)) {
			try {
				montaTabelaML(mercadoLivreController.findVendas(dataInicio, dataFim, tipoAnuncio, qtde, codItem,
						cliente, status));
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			}
		}
		if (tbGeral.isVisible())
			labelLegenda.setVisible(Boolean.TRUE);
		else
			labelLegenda.setVisible(Boolean.FALSE);
	}

	private void montaTabelaGeral(List<VendaGeralDTO> vendas) {
		columnDataTbGeral.setCellValueFactory(new PropertyValueFactory<>("fData"));
		columnClienteTbGeral.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		columnQtdTbGeral.setCellValueFactory(new PropertyValueFactory<>("qtde"));
		columnItemTbGeral.setCellValueFactory(new PropertyValueFactory<>("codItem"));
		columnUnitTbGeral.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		columnTotalTbGeral.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		columnRecebidoTbGeral.setCellValueFactory(new PropertyValueFactory<>("valorRecebido"));
		columnStatusTbGeral.setCellValueFactory(new PropertyValueFactory<>("status"));
		columnCanalTbGeral.setCellValueFactory(new PropertyValueFactory<>("canal"));
		setVisibilityTables(true, false, false);

		tbGeral.setItems(FXCollections.observableArrayList(vendas));
	}

	private void montaTabelaShopee(List<VendaShopeeDTO> vendas) {
		columnDataTbShopee.setCellValueFactory(new PropertyValueFactory<>("fData"));
		columnClienteTbShopee.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		columnQtdTbShopee.setCellValueFactory(new PropertyValueFactory<>("qtde"));
		columnItemTbShopee.setCellValueFactory(new PropertyValueFactory<>("codItem"));
		columnUnitTbShopee.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		columnTotalTbShopee.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		columnRecebidoTbShopee.setCellValueFactory(new PropertyValueFactory<>("valorRecebido"));
		columnStatusTbShopee.setCellValueFactory(new PropertyValueFactory<>("status"));
		setVisibilityTables(false, true, false);

		tbShopee.setItems(FXCollections.observableArrayList(vendas));
	}

	private void montaTabelaML(List<VendaMercadoLivreDTO> vendas) {
		columnDataTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("fData"));
		columnAnuncioTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("tipoAnuncio"));
		columnQtdTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("qtde"));
		columnItemTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("codItem"));
		columnClienteTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		columnUnitTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		columnTotalTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		columnRecebidoTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("valorRecebido"));
		columnStatusTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("status"));
		setVisibilityTables(false, false, true);

		tbMercadoLivre.setItems(FXCollections.observableArrayList(vendas));
	}

	@FXML
	void onLimparAction() {
		cbCanal.getSelectionModel().clearSelection();
		cbCanal.setPromptText("Canal de Venda");
		txtDataInicio.setText("");
		txtDataFim.setText("");
		cbTipoAnuncio.getSelectionModel().clearSelection();
		txtQtde.setText("");
		txtCodItem.setText("");
		txtCliente.setText("");
		cbStatus.getSelectionModel().clearSelection();
		cbStatus.setPromptText("Status");
		setVisibilityTables(false, false, false);
		txtErroData.setVisible(false);
		labelLegenda.setVisible(Boolean.FALSE);
	}

	@FXML
	void onTbGeralMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			VendaGeralDTO selectedItem = tbGeral.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				callModalEditarVenda(Constraints.currentStage(event), selectedItem.getId(), selectedItem.getCanal());
			}
		}
	}

	@FXML
	void onTbMercadoLivreClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			VendaMercadoLivreDTO selectedItem = tbMercadoLivre.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				callModalEditarVenda(Constraints.currentStage(event), selectedItem.getId(), "ML");
			}
		}
	}

	@FXML
	void onTbShopeeClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			VendaShopeeDTO selectedItem = tbShopee.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				callModalEditarVenda(Constraints.currentStage(event), selectedItem.getId(), "S");
			}
		}
	}

	private void callModalEditarVenda(Stage parentStage, Long id, String canal) {
		LoadScene.callEditVendaModal(parentStage, getClass(), id, canal);
	}

	private void setNumberFields() {
		Constraints.setTextFieldNumber(txtQtde);
		Constraints.setTextFieldNumberBar(txtDataInicio);
		Constraints.setTextFieldMaxLength(txtDataInicio, 10);
		Constraints.setTextFieldNumberBar(txtDataFim);
		Constraints.setTextFieldMaxLength(txtDataFim, 10);
	}

	private void setItensComboBox() {
		List<String> options = Arrays.asList(Constants.LOJA.SHOPEE, Constants.LOJA.MERCADO_LIVRE, Constants.LOJA.GERAL);
		cbCanal.setItems(FXCollections.observableArrayList(options));
		cbTipoAnuncio.setPromptText("-");
		options = Arrays.asList(Constants.STATUS.TODOS, Constants.STATUS.PENDENTE, Constants.STATUS.CONCLUIDO,
				Constants.STATUS.DEVOLUCAO, Constants.STATUS.CANCELADO);
		cbStatus.setItems(FXCollections.observableArrayList(options));
	}

	private void setVisibilityTables(Boolean g, Boolean s, Boolean ml) {
		tbGeral.setVisible(g);
		tbShopee.setVisible(s);
		tbMercadoLivre.setVisible(ml);
		if (!g)
			tbGeral.getItems().clear();
		if (!s)
			tbShopee.getItems().clear();
		if (!ml)
			tbMercadoLivre.getItems().clear();
	}

	@SuppressWarnings("resource")
	@FXML
	void onInserirEmMassaAction(ActionEvent event) {
		Alerts.showAlert("Inserção em massa", null, "Selecione o arquivo do tipo CSV (Separado por vírgulas)",
				AlertType.INFORMATION);
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecione um arquivo");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo Separado por Vírgulas(*.csv)",
				"*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(Constraints.currentStage(event));

		if (file == null)
			return;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file, Charset.forName("UTF-8")));

			String line;
			int skip = 0;
			String canal = null;

			while (Objects.nonNull((line = reader.readLine()))) {
				if (skip < 3) {
					if (skip == 2)
						canal = line.split(";").length == 18 ? Constants.LOJA.SHOPEE : Constants.LOJA.MERCADO_LIVRE;
					skip++;
					continue;
				}

				String[] s = line.split(";");
				if (s.length != 0) {
					if (canal.equals(Constants.LOJA.SHOPEE)) {
						Date data = DataUtils.stringToDate(s[0]);
						String cliente = s[1];
						String status = s[2];

						shopeeController.insertVenda(data, cliente, status);

						for (int i = 3; i < s.length; i += 3) {
							Integer qtde = Integer.valueOf(s[i]);
							String codItem = s[i + 1];
							Double valorUnitario = Double
									.valueOf(s[i + 2].replace("R$", "").replaceAll(" ", "").replace(",", "."));
							Double valorTotal = CalculaTotalERecebido.calculaTotal(qtde, valorUnitario);
							Double valorRecebido = CalculaTotalERecebido.calculaRecebidoShopee(valorTotal, qtde);

							shopeeController.insertItemVenda(codItem, qtde, valorUnitario, valorTotal, valorRecebido,
									Boolean.TRUE);
						}
					} else if (canal.equals(Constants.LOJA.MERCADO_LIVRE)) {

						Date data = DataUtils.stringToDate(s[0]);
						String cliente = s[1];
						String status = s[2];

						mercadoLivreController.insertVenda(data, cliente, status);

						for (int i = 3; i < s.length; i += 5) {
							String tipoAnuncio = s[i];
							Boolean isFreteGratis = s[i + 1].equals("S") ? Boolean.TRUE : Boolean.FALSE;
							Integer qtde = Integer.valueOf(s[i + 2]);
							String codItem = s[i + 3];
							Double valorUnitario = Double
									.valueOf(s[i + 4].replace("R$", "").replaceAll(" ", "").replace(",", "."));
							Double valorTotal = CalculaTotalERecebido.calculaTotal(qtde, valorUnitario);
							Double valorRecebido = CalculaTotalERecebido.calculaRecebidoShopee(valorTotal, qtde);

							if (isFreteGratis)
								if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO.CLASSICO))
									tipoAnuncio = Constants.TIPO_ANUNCIO.CLASSICO_FG;
								else if (tipoAnuncio.equals(Constants.TIPO_ANUNCIO.PREMIUM))
									tipoAnuncio = Constants.TIPO_ANUNCIO.PREMIUM_FG;

							mercadoLivreController.insertItemVenda(codItem, tipoAnuncio, qtde, valorUnitario,
									valorTotal, valorRecebido); // , Boolean.TRUE);
						}
					}
				} else {
					Alerts.showAlert("ERRO", "Arquivo vazio!", null, AlertType.INFORMATION);
					return;
				}
			}
			reader.close();
			Alerts.showAlert("Sucesso", "INSERÇÃO EM MASSA EXECUTADA COM SUCESSO", null, AlertType.INFORMATION);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void onProdutosAction(ActionEvent event) {
		LoadScene.changeScene(Constants.VIEWS.PRODUTOS);
	}

	@FXML
	void onDashboardAction(ActionEvent event) {
		LoadScene.changeScene(Constants.VIEWS.DASHBOARD);
	}

	@FXML
	void onOpcoesAction(ActionEvent event) {
		LoadScene.callOpcoesModal(Constraints.currentStage(event), getClass());
	}

	@FXML
	void onBaixarModeloAction(ActionEvent event) {
		Alerts.tipoArquivoAlert();
	}

}
