package frontend.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import backend.entities.mercadoLivreEntity.VendaMercadoLivreEntity;
import backend.entities.shopeeEntity.VendaShopeeEntity;
import frontend.utils.Constants;
import frontend.utils.DataUtils;
import frontend.utils.StatusVenda;
import frontend.utils.TipoAnuncioMercadoLivre;
import frontend.utils.TipoContaShopee;
import frontend.views.utils.Alerts;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewVendasController implements Initializable {

	private TipoAnuncioMercadoLivre anuncioML = null;
	private TipoContaShopee contaShopee = null;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private StatusVenda status;

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
	private ComboBox<String> cbContaAnuncio;

	@FXML
	private TableView<?> tbGeral;

	@FXML
	private TableView<VendaMercadoLivreEntity> tbMercadoLivre;

	@FXML
	private TableView<VendaShopeeEntity> tbShopee;

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
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldNumber(txtQtde);
		Constraints.setTextFieldNumberBar(txtDataInicio);
		Constraints.setTextFieldMaxLength(txtDataInicio, 10);
		Constraints.setTextFieldNumberBar(txtDataFim);
		Constraints.setTextFieldMaxLength(txtDataFim, 10);
		List<String> options = Arrays.asList(Constants.LOJA.SHOPEE, Constants.LOJA.MERCADO_LIVRE,
				Constants.LOJA.TODOS);
		cbCanal.setItems(FXCollections.observableArrayList(options));
		cbContaAnuncio.setPromptText(null);
		options = Arrays.asList("", Constants.STATUS.PENDENTE, Constants.STATUS.CONCLUIDO,
				Constants.STATUS.DEVOLUCAO, Constants.STATUS.CANCELADO);
		cbStatus.setItems(FXCollections.observableArrayList(options));
	}

	@FXML
	void onCanalAction() {
		List<String> options;
		if (cbCanal.getSelectionModel().getSelectedItem().equals(Constants.LOJA.SHOPEE)) {
			cbContaAnuncio.setPromptText(Constants.CONTA_ANUNCIO.CONTA);
			options = Arrays.asList("", Constants.CONTA_ANUNCIO.PRINCIPAL, Constants.CONTA_ANUNCIO.SECUNDARIA);
			cbContaAnuncio.setItems(FXCollections.observableArrayList(options));
		} else if (cbCanal.getSelectionModel().getSelectedItem().equals(Constants.LOJA.MERCADO_LIVRE)) {
			cbContaAnuncio.setPromptText("Tipo de Anúncio");
			options = Arrays.asList("", Constants.CONTA_ANUNCIO.CLASSICO, Constants.CONTA_ANUNCIO.CLASSICO_FG,
					Constants.CONTA_ANUNCIO.PREMIUM, Constants.CONTA_ANUNCIO.PREMIUM_FG);
			cbContaAnuncio.setItems(FXCollections.observableArrayList(options));
		} else {
			cbContaAnuncio.setPromptText(null);
			cbContaAnuncio.setItems(null);
		}
	}

	@FXML
	void onContaAnuncioAction() {
		String selection = cbContaAnuncio.getSelectionModel().getSelectedItem();
		if (selection.equals(Constants.CONTA_ANUNCIO.CLASSICO))
			anuncioML = TipoAnuncioMercadoLivre.CLASSICO;
		if (selection.equals(Constants.CONTA_ANUNCIO.PREMIUM))
			anuncioML = TipoAnuncioMercadoLivre.PREMIUM;
		if (selection.equals(Constants.CONTA_ANUNCIO.CLASSICO_FG))
			anuncioML = TipoAnuncioMercadoLivre.CLASSICO_FG;
		if (selection.equals(Constants.CONTA_ANUNCIO.PREMIUM_FG))
			anuncioML = TipoAnuncioMercadoLivre.PREMIUM_FG;
		if (selection.equals(Constants.CONTA_ANUNCIO.PRINCIPAL))
			contaShopee = TipoContaShopee.PRINCIPAL;
		if (selection.equals(Constants.CONTA_ANUNCIO.SECUNDARIA))
			contaShopee = TipoContaShopee.SECUNDARIA;
	}
	
	@FXML
	void onStatusAction() {
		String selection = cbStatus.getSelectionModel().getSelectedItem();
		if (selection.equals(Constants.STATUS.PENDENTE))
			status = StatusVenda.PENDENTE;
		if (selection.equals(Constants.STATUS.CONCLUIDO))
			status = StatusVenda.CONCLUIDO;
		if (selection.equals(Constants.STATUS.DEVOLUCAO))
			status = StatusVenda.DEVOLUCAO;
		if (selection.equals(Constants.STATUS.CANCELADO))
			status = StatusVenda.CANCELADO;
	}

	@FXML
	void onDataInicioReleased(KeyEvent event) {
		formataData(txtDataInicio, event);
	}

	@FXML
	void onDataFimReleased(KeyEvent event) {
		formataData(txtDataFim, event);
	}

	private void formataData(TextField textField, KeyEvent event) {
		String text = textField.getText();
		if (event.getCode() != KeyCode.BACK_SPACE) {
			textField.setText(DataUtils.addBarraData(text));
			if (text.length() == 3) {
				String formattedDate = new StringBuilder(text.substring(0, 2)).append("/").append(text.substring(2))
						.toString();
				txtDataInicio.setText(formattedDate);
			}
			if (text.length() == 6) {
				String formattedDate = new StringBuilder(text.substring(0, 5)).append("/").append(text.substring(5))
						.toString();
				textField.setText(formattedDate);
			}
			textField.positionCaret(textField.getLength());
		}
	}

	@FXML
	void onInserirAction(ActionEvent event) {
		callModalInserirVenda(Constraints.currentStage(event));
	}

	private void callModalInserirVenda(Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.MODAL.INSERIR_VENDA));
			Pane pane = loader.load();
			Stage modalStage = new Stage();
			modalStage.setTitle("Inserir venda");
			modalStage.setScene(new Scene(pane));
			modalStage.setResizable(false);
			modalStage.initOwner(parentStage);
			modalStage.initModality(Modality.WINDOW_MODAL);
			modalStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR", e.getMessage(), AlertType.ERROR);
		}
	}
	
	@FXML
	void onBuscarAction() {
		
	}
	
	@FXML
	void onLimparAction() {
		cbCanal.setValue("");
		cbCanal.setPromptText("Canal de Venda");
		txtDataInicio.setText("");
		txtDataFim.setText("");
		cbContaAnuncio.setValue("");
		txtQtde.setText("");
		txtCodItem.setText("");
		txtCliente.setText("");
		cbStatus.setValue("");
		cbStatus.setPromptText("Status");
	}
	

}
