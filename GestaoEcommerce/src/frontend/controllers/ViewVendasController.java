package frontend.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import backend.controllers.VendaGeralController;
import backend.controllers.VendaMercadoLivreController;
import backend.controllers.VendaShopeeController;
import backend.entities.geralEntity.VendaGeralFormatadaEntity;
import backend.entities.mercadoLivreEntity.VendaMercadoLivreEntity;
import backend.entities.mercadoLivreEntity.VendaMercadoLivreFormatadaEntity;
import backend.entities.shopeeEntity.VendaShopeeFormatadaEntity;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ViewVendasController implements Initializable {

	VendaGeralController geralController = new VendaGeralController();
	VendaShopeeController shopeeController = new VendaShopeeController();
	VendaMercadoLivreController mercadoLivreController = new VendaMercadoLivreController();

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnExportar;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnRelatorios;

	@FXML
	private Button btnVendas;

	@FXML
	private ComboBox<String> cbCanal;

	@FXML
	private ComboBox<String> cbTipoAnuncio;

	@FXML
	private TableView<VendaGeralFormatadaEntity> tbGeral;	
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, String> columnDataTbGeral;
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, Number> columnQtdTbGeral;
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, String> columnItemTbGeral;
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, String> columnClienteTbGeral;
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, Number> columnUnitTbGeral;
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, Number> columnTotalTbGeral;
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, Number> columnRecebidoTbGeral;
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, String> columnStatusTbGeral;
	@FXML
	private TableColumn<VendaGeralFormatadaEntity, String> columnCanalTbGeral;

	@FXML
	private TableView<VendaMercadoLivreEntity> tbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, String> columnDataTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, String> columnAnuncioTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, Number> columnQtdTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, String> columnItemTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, String> columnClienteTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, Number> columnUnitTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, Number> columnTotalTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, Number> columnRecebidoTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreEntity, String> columnStatusTbMercadoLivre;

	@FXML
	private TableView<VendaShopeeFormatadaEntity> tbShopee;
	@FXML
	private TableColumn<VendaShopeeFormatadaEntity, String> columnDataTbShopee;
	@FXML
	private TableColumn<VendaShopeeFormatadaEntity, Number> columnQtdTbShopee;
	@FXML
	private TableColumn<VendaShopeeFormatadaEntity, String> columnItemTbShopee;
	@FXML
	private TableColumn<VendaShopeeFormatadaEntity, String> columnClienteTbShopee;
	@FXML
	private TableColumn<VendaShopeeFormatadaEntity, Number> columnUnitTbShopee;
	@FXML
	private TableColumn<VendaShopeeFormatadaEntity, Number> columnTotalTbShopee;
	@FXML
	private TableColumn<VendaShopeeFormatadaEntity, Number> columnRecebidoTbShopee;
	@FXML
	private TableColumn<VendaShopeeFormatadaEntity, String> columnStatusTbShopee;

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
		callModalInserirVenda(Constraints.currentStage(event));
	}

	private void callModalInserirVenda(Stage parentStage) {
		LoadScene.callModal(parentStage, getClass());
	}
	
	@FXML
	void onBuscarAction() {
		String canal = cbCanal.getSelectionModel().getSelectedItem();
		Date dataInicio = !txtDataInicio.getText().isBlank() ? DataUtils.stringToDate(txtDataInicio.getText()) : null;
		Date dataFim = !txtDataFim.getText().isBlank() ? DataUtils.stringToDate(txtDataFim.getText()) : null;
		String tipoAnuncio = cbTipoAnuncio.getSelectionModel().getSelectedItem();
		Integer qtde = !txtQtde.getText().isBlank() ? Integer.parseInt(txtQtde.getText()) : null;
		String codItem = !txtCodItem.getText().isBlank() ? "%"+txtCodItem.getText()+"%" : null; 
		String cliente = !txtCliente.getText().isBlank() ? "%"+txtCliente.getText()+"%" : null;
		String status = cbStatus.getSelectionModel().getSelectedItem();
		
		if (dataInicio == null && dataFim != null) {
			Insets curMargins = StackPane.getMargin(txtErroData);
			StackPane.setMargin(txtErroData, new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), 260));
			txtErroData.setVisible(true);
			return;
		}
		
		if (dataInicio != null && dataFim == null) {
			Insets curMargins = StackPane.getMargin(txtErroData);
			StackPane.setMargin(txtErroData, new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), 458));
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
				montaTabelaML(mercadoLivreController.findVendas(dataInicio, dataFim, tipoAnuncio, qtde, codItem, cliente, status));
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			}
		}
	}
	
	private void montaTabelaGeral(List<VendaGeralFormatadaEntity> vendas) {
		columnDataTbGeral.setCellValueFactory(new PropertyValueFactory<>("data"));
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
	
	private void montaTabelaShopee(List<VendaShopeeFormatadaEntity> vendas) {
		columnDataTbShopee.setCellValueFactory(new PropertyValueFactory<>("data"));
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
	
	private void montaTabelaML(List<VendaMercadoLivreFormatadaEntity> vendas) {
		columnDataTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("data"));
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
		options = Arrays.asList(Constants.STATUS.TODOS, Constants.STATUS.PENDENTE, Constants.STATUS.CONCLUIDO, Constants.STATUS.DEVOLUCAO,
				Constants.STATUS.CANCELADO);
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
	
}
