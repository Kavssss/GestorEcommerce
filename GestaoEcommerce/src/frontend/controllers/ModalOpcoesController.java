package frontend.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.utils.Taxas;
import frontend.utils.Constants;
import frontend.utils.LoadScene;
import frontend.views.utils.Alerts;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModalOpcoesController implements Initializable {

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnSalvar;

	@FXML
	private ComboBox<String> cbCanal;

	@FXML
	private TextField txtTaxa;

	@FXML
	private TextField txtCustoFixo;

	@FXML
	private TextField txtFrete;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Constraints.setTextFieldDouble(txtTaxa);
		Constraints.setTextFieldDouble(txtCustoFixo);
		Constraints.setTextFieldDouble(txtFrete);
		cbCanal.setItems(FXCollections.observableArrayList(Arrays.asList(Constants.LOJA.SHOPEE,
				Constants.LOJA.MERCADO_LIVRE_CLASSICO, Constants.LOJA.MERCADO_LIVRE_PREMIUM)));
	}

	@FXML
	void onCanalAction(ActionEvent event) {
		String selection = cbCanal.getSelectionModel().getSelectedItem();
		if (selection != null && (selection.equals(Constants.LOJA.MERCADO_LIVRE_CLASSICO)
				|| selection.equals(Constants.LOJA.MERCADO_LIVRE_PREMIUM)))
			txtFrete.setVisible(Boolean.TRUE);
		else if (selection != null && selection.equals(Constants.LOJA.SHOPEE))
			txtFrete.setVisible(Boolean.FALSE);
	}

	@FXML
	void onCancelarAction(ActionEvent event) {
		LoadScene.getModalStage().close();
	}

	@FXML
	void onSalvarAction(ActionEvent event) {
		String canal = cbCanal.getSelectionModel().getSelectedItem();
		Double taxa = !txtTaxa.getText().isBlank() ? Double.valueOf(txtTaxa.getText()) / 100 : null;
		Double custoFixo = !txtCustoFixo.getText().isBlank() ? Double.valueOf(txtCustoFixo.getText()) : null;
		Double frete = !txtFrete.getText().isBlank() ? Double.valueOf(txtFrete.getText()) : null;

		if (Objects.isNull(canal) || Objects.isNull(custoFixo) || Objects.isNull(taxa)
				|| (txtFrete.isVisible() && Objects.isNull(frete))) {
			Alerts.showAlert("Informações incompletas", "Preencha todos os campos", null, AlertType.ERROR);
			return;
		}

		if (canal.equals(Constants.LOJA.SHOPEE))
			Taxas.buildTaxasShopee(taxa, custoFixo);
		else if (canal.equals(Constants.LOJA.MERCADO_LIVRE_CLASSICO))
			Taxas.buildTaxasMLClassico(taxa, custoFixo, frete);
		else if (canal.equals(Constants.LOJA.MERCADO_LIVRE_PREMIUM))
			Taxas.buildTaxasMLPremium(taxa, custoFixo, frete);

		Alerts.showAlert("Sucesso", "Taxas atualizadas com sucesso", null, AlertType.INFORMATION);
		LoadScene.getModalStage().close();
	}

}
