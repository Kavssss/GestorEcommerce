package frontend.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.VendaGeralController;
import backend.controllers.VendaMercadoLivreController;
import backend.controllers.VendaShopeeController;
import backend.entities.mercadoLivre.ItemMercadoLivreEntity;
import backend.entities.mercadoLivre.VendaMercadoLivreEntity;
import backend.entities.shopee.ItemShopeeEntity;
import backend.entities.shopee.VendaShopeeEntity;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import models.DbException;

public class ModalInserirController implements Initializable {

	VendaShopeeController shopeeController = new VendaShopeeController();
	VendaMercadoLivreController mercadoLivreController = new VendaMercadoLivreController();
	VendaGeralController geralController = new VendaGeralController();

	ViewVendasController viewVendasController = new ViewVendasController();

	private Integer linhasVisiveis = 1;

	@FXML
	private Button btnAddItem;

	@FXML
	private Button btnApagar;

	@FXML
	private Button btnX;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnX2;

	@FXML
	private Button btnX3;

	@FXML
	private Button btnX4;

	@FXML
	private Button btnX5;

	@FXML
	private ComboBox<String> cbCanal;

	@FXML
	private ComboBox<String> cbItem1;

	@FXML
	private ComboBox<String> cbItem2;

	@FXML
	private ComboBox<String> cbItem3;

	@FXML
	private ComboBox<String> cbItem4;

	@FXML
	private ComboBox<String> cbItem5;

	@FXML
	private ComboBox<String> cbStatus;

	@FXML
	private ComboBox<String> cbTipoAnuncio1;

	@FXML
	private ComboBox<String> cbTipoAnuncio2;

	@FXML
	private ComboBox<String> cbTipoAnuncio3;

	@FXML
	private ComboBox<String> cbTipoAnuncio4;

	@FXML
	private ComboBox<String> cbTipoAnuncio5;

	@FXML
	private TextField txtCliente;

	@FXML
	private TextField txtData;

	@FXML
	private TextField txtIdDado;

	@FXML
	private TextField txtIdVenda;

	@FXML
	private TextField txtQtde1;

	@FXML
	private TextField txtQtde2;

	@FXML
	private TextField txtQtde3;

	@FXML
	private TextField txtQtde4;

	@FXML
	private TextField txtQtde5;

	@FXML
	private TextField txtValorRecebido1;

	@FXML
	private TextField txtValorRecebido2;

	@FXML
	private TextField txtValorRecebido3;

	@FXML
	private TextField txtValorRecebido4;

	@FXML
	private TextField txtValorRecebido5;

	@FXML
	private TextField txtValorTotal1;

	@FXML
	private TextField txtValorTotal2;

	@FXML
	private TextField txtValorTotal3;

	@FXML
	private TextField txtValorTotal4;

	@FXML
	private TextField txtValorTotal5;

	@FXML
	private TextField txtValorUnitario1;

	@FXML
	private TextField txtValorUnitario2;

	@FXML
	private TextField txtValorUnitario3;

	@FXML
	private TextField txtValorUnitario4;

	@FXML
	private TextField txtValorUnitario5;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setNumberFields();
		for (int i = 2; i <= 5; i++)
			setVisibilityInsertItem(i, Boolean.FALSE);
		cbCanal.setItems(
				FXCollections.observableArrayList(Arrays.asList(Constants.LOJA.SHOPEE, Constants.LOJA.MERCADO_LIVRE)));
		cbStatus.setItems(FXCollections.observableArrayList(Arrays.asList(Constants.STATUS.PENDENTE,
				Constants.STATUS.CONCLUIDO, Constants.STATUS.DEVOLUCAO, Constants.STATUS.CANCELADO)));
		cbStatus.setValue(Constants.STATUS.PENDENTE);
		cbTipoAnuncioSetItems(cbTipoAnuncio1);
		cbTipoAnuncioSetItems(cbTipoAnuncio2);
		cbTipoAnuncioSetItems(cbTipoAnuncio3);
		cbTipoAnuncioSetItems(cbTipoAnuncio4);
		cbTipoAnuncioSetItems(cbTipoAnuncio5);
		List<String> itens = cbItemSetItems();
		cbItem1.setItems(FXCollections.observableArrayList(itens));
		cbItem2.setItems(FXCollections.observableArrayList(itens));
		cbItem3.setItems(FXCollections.observableArrayList(itens));
		cbItem4.setItems(FXCollections.observableArrayList(itens));
		cbItem5.setItems(FXCollections.observableArrayList(itens));
		btnApagar.setVisible(Boolean.FALSE);
	}

	private void cbTipoAnuncioSetItems(ComboBox<String> cb) {
		cb.setItems(FXCollections
				.observableArrayList(Arrays.asList(Constants.TIPO_ANUNCIO.CLASSICO, Constants.TIPO_ANUNCIO.CLASSICO_FG,
						Constants.TIPO_ANUNCIO.PREMIUM, Constants.TIPO_ANUNCIO.PREMIUM_FG)));
	}

	private List<String> cbItemSetItems() {
		try {
			return geralController.findItensAtivos();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@FXML
	void onAddItemAction(ActionEvent event) {
		String canal = cbCanal.getSelectionModel().getSelectedItem();
		if (Objects.nonNull(canal)) {
			switch (linhasVisiveis) {
			case 1:
				setVisibilityInsertItem(2, Boolean.TRUE);
				setLinhasVisiveis(2);
				if (canal.equals(Constants.LOJA.MERCADO_LIVRE))
					cbTipoAnuncio2.setVisible(Boolean.TRUE);
				break;
			case 2:
				setVisibilityInsertItem(3, Boolean.TRUE);
				btnX2.setVisible(false);
				setLinhasVisiveis(3);
				if (canal.equals(Constants.LOJA.MERCADO_LIVRE))
					cbTipoAnuncio3.setVisible(Boolean.TRUE);
				break;
			case 3:
				setVisibilityInsertItem(4, Boolean.TRUE);
				btnX3.setVisible(false);
				setLinhasVisiveis(4);
				if (canal.equals(Constants.LOJA.MERCADO_LIVRE))
					cbTipoAnuncio4.setVisible(Boolean.TRUE);
				break;
			case 4:
				setVisibilityInsertItem(5, Boolean.TRUE);
				btnX4.setVisible(false);
				setLinhasVisiveis(5);
				if (canal.equals(Constants.LOJA.MERCADO_LIVRE))
					cbTipoAnuncio5.setVisible(Boolean.TRUE);
				break;
			}
		}
	}

	@FXML
	void onBtnX2Action(ActionEvent event) {
		setVisibilityInsertItem(2, Boolean.FALSE);
		setLinhasVisiveis(1);
		cbTipoAnuncio2.setVisible(Boolean.FALSE);
	}

	@FXML
	void onBtnX3Action(ActionEvent event) {
		setVisibilityInsertItem(3, Boolean.FALSE);
		btnX2.setVisible(Boolean.TRUE);
		setLinhasVisiveis(2);
		cbTipoAnuncio3.setVisible(Boolean.FALSE);
	}

	@FXML
	void onBtnX4Action(ActionEvent event) {
		setVisibilityInsertItem(4, Boolean.FALSE);
		btnX3.setVisible(Boolean.TRUE);
		setLinhasVisiveis(3);
		cbTipoAnuncio4.setVisible(Boolean.FALSE);
	}

	@FXML
	void onBtnX5Action(ActionEvent event) {
		setVisibilityInsertItem(5, Boolean.FALSE);
		btnX4.setVisible(Boolean.TRUE);
		setLinhasVisiveis(4);
		cbTipoAnuncio5.setVisible(Boolean.FALSE);
	}

	@FXML
	void onCanalAction(ActionEvent event) {
		String selection = cbCanal.getSelectionModel().getSelectedItem();
		if (selection != null)
			setPositionFields(selection);
	}

	@FXML
	void onXAction(ActionEvent event) {
		LoadScene.getModalStage().close();
	}

	@FXML
	void onDataReleased(KeyEvent event) {
		DataUtils.formataData(txtData, event);
	}

	@FXML
	void onValorUnitario1Released(KeyEvent event) {
		if (Objects.nonNull(txtQtde1) && txtValorUnitario1.getText().length() > 3) {
			setTextValorTotal(txtQtde1.getText(), txtValorUnitario1.getText(), txtValorTotal1);
			setTextValorRecebido(txtQtde1.getText(), txtValorUnitario1.getText(),
					cbTipoAnuncio1.getSelectionModel().getSelectedItem(), txtValorRecebido1);
		}
	}

	@FXML
	void onValorUnitario2Released(KeyEvent event) {
		if (Objects.nonNull(txtQtde2) && txtValorUnitario2.getText().length() > 3) {
			setTextValorTotal(txtQtde2.getText(), txtValorUnitario2.getText(), txtValorTotal2);
			setTextValorRecebido(txtQtde2.getText(), txtValorUnitario2.getText(),
					cbTipoAnuncio2.getSelectionModel().getSelectedItem(), txtValorRecebido2);
		}
	}

	@FXML
	void onValorUnitario3Released(KeyEvent event) {
		if (Objects.nonNull(txtQtde3) && txtValorUnitario3.getText().length() > 3) {
			setTextValorTotal(txtQtde3.getText(), txtValorUnitario3.getText(), txtValorTotal3);
			setTextValorRecebido(txtQtde3.getText(), txtValorUnitario3.getText(),
					cbTipoAnuncio3.getSelectionModel().getSelectedItem(), txtValorRecebido3);
		}
	}

	@FXML
	void onValorUnitario4Released(KeyEvent event) {
		if (Objects.nonNull(txtQtde4) && txtValorUnitario4.getText().length() > 3) {
			setTextValorTotal(txtQtde4.getText(), txtValorUnitario4.getText(), txtValorTotal4);
			setTextValorRecebido(txtQtde4.getText(), txtValorUnitario4.getText(),
					cbTipoAnuncio4.getSelectionModel().getSelectedItem(), txtValorRecebido4);
		}
	}

	@FXML
	void onValorUnitario5Released(KeyEvent event) {
		if (Objects.nonNull(txtQtde5) && txtValorUnitario5.getText().length() > 3) {
			setTextValorTotal(txtQtde5.getText(), txtValorUnitario5.getText(), txtValorTotal5);
			setTextValorRecebido(txtQtde5.getText(), txtValorUnitario5.getText(),
					cbTipoAnuncio5.getSelectionModel().getSelectedItem(), txtValorRecebido5);
		}
	}

	private void setTextValorTotal(String qtde, String unitario, TextField field) {
		field.setText(String.format("R$ %.2f", CalculaTotalERecebido.calculaTotal(qtde, unitario)).replace(".", ","));
	}

	private void setTextValorRecebido(String qtde, String unitario, String tipoAnuncio, TextField field) {
		Double total = CalculaTotalERecebido.calculaTotal(qtde, unitario);
		Double recebido;
		if (cbCanal.getSelectionModel().getSelectedItem().equals(Constants.LOJA.SHOPEE)) {
			recebido = CalculaTotalERecebido.calculaRecebidoShopee(total, qtde);
			field.setText(String.format("R$ %.2f", recebido).replace(".", ","));
		} else if (cbCanal.getSelectionModel().getSelectedItem().equals(Constants.LOJA.MERCADO_LIVRE)) {
			recebido = CalculaTotalERecebido.calculaRecebidoML(unitario, qtde, tipoAnuncio);
			field.setText(String.format("R$ %.2f", recebido).replace(".", ","));
		}
	}

	@FXML
	void onApagarAction(ActionEvent event) {
		String canal = cbCanal.getSelectionModel().getSelectedItem();
		Long idVenda = !txtIdVenda.getText().isBlank() ? Long.valueOf(txtIdVenda.getText()) : null;
		Long idDado = !txtIdDado.getText().isBlank() ? Long.valueOf(txtIdDado.getText()) : null;

		if (Objects.isNull(canal)) {
			Alerts.showAlert("Canal não encontrado", "ERRO", Constants.MESSAGE.CANAL_NAO_PREENCHIDO,
					AlertType.INFORMATION);
			return;
		}

		try {
			if (Alerts.confirmationAlert("Excluir", Constants.MESSAGE.IRREVERSIVEL))
				if (canal.equals(Constants.LOJA.SHOPEE))
					shopeeController.deleteVenda(idVenda, idDado);
				else if (canal.equals(Constants.LOJA.MERCADO_LIVRE))
					mercadoLivreController.deleteVenda(idVenda, idDado);
		} catch (SQLException e) {
			Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
		LoadScene.getModalStage().close();
		buscaAutomatica();
	}

	@FXML
	void onSalvarAction(ActionEvent event) {
		Long idVenda = !txtIdVenda.getText().isBlank() ? Long.valueOf(txtIdVenda.getText()) : null;
		Long idDado = !txtIdDado.getText().isBlank() ? Long.valueOf(txtIdDado.getText()) : null;
		String canal = cbCanal.getSelectionModel().getSelectedItem();
		Date data = !txtData.getText().isBlank() ? DataUtils.stringToDate(txtData.getText()) : null;
		String cliente = !txtCliente.getText().isBlank() ? txtCliente.getText() : null;
		String status = cbStatus.getSelectionModel().getSelectedItem();

		String codItem = cbItem1.getSelectionModel().getSelectedItem();
		String tipoAnuncio = cbTipoAnuncio1.getSelectionModel().getSelectedItem();
		Integer qtde = !txtQtde1.getText().isBlank() ? Integer.parseInt(txtQtde1.getText()) : null;
		Double valorUnitario = !txtValorUnitario1.getText().isBlank() ? Double.valueOf(txtValorUnitario1.getText())
				: null;
		Double valorTotal = CalculaTotalERecebido.calculaTotal(qtde, valorUnitario);
		Double valorRecebido = canal.equals(Constants.LOJA.SHOPEE)
				? CalculaTotalERecebido.calculaRecebidoShopee(valorTotal, qtde)
				: CalculaTotalERecebido.calculaRecebidoML(valorUnitario, qtde, tipoAnuncio);

		if (Objects.isNull(canal) || Objects.isNull(data) || Objects.isNull(cliente) || Objects.isNull(status)
				|| Objects.isNull(codItem) || Objects.isNull(qtde) || Objects.isNull(valorUnitario)
				|| (canal.equals(Constants.LOJA.MERCADO_LIVRE) && Objects.isNull(tipoAnuncio))) {
			Alerts.showAlert("Campos não preenchidos", "ERRO", Constants.MESSAGE.CAMPOS_NAO_PREENCHIDOS,
					AlertType.INFORMATION);
			return;
		}

		if (canal.equals(Constants.LOJA.SHOPEE)) {
			try {
				if (Objects.isNull(idVenda)) {
					shopeeController.insertVenda(data, cliente, status, codItem, qtde, valorUnitario, valorTotal,
							valorRecebido);
				} else {
					shopeeController.editVenda(idVenda, idDado, data, cliente, status, codItem, qtde, valorUnitario,
							valorTotal, valorRecebido);
					return;
				}
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
				e.printStackTrace();
			}
			if (linhasVisiveis > 1)
				inserirOutrosItensShopee(cbItem2, txtQtde2, txtValorUnitario2);
			if (linhasVisiveis > 2)
				inserirOutrosItensShopee(cbItem3, txtQtde3, txtValorUnitario3);
			if (linhasVisiveis > 3)
				inserirOutrosItensShopee(cbItem4, txtQtde4, txtValorUnitario4);
			if (linhasVisiveis > 4)
				inserirOutrosItensShopee(cbItem5, txtQtde5, txtValorUnitario5);
		} else if (canal.equals(Constants.LOJA.MERCADO_LIVRE)) {
			try {
				if (Objects.isNull(idVenda))
					mercadoLivreController.insertVenda(data, cliente, status, codItem, tipoAnuncio, qtde, valorUnitario,
							valorTotal, valorRecebido);
				else {
					mercadoLivreController.editVenda(idVenda, idDado, data, cliente, status, codItem, tipoAnuncio, qtde,
							valorUnitario, valorTotal, valorRecebido);
					return;
				}
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
				e.printStackTrace();
			}
			if (linhasVisiveis > 1)
				inserirOutrosItensML(cbItem2, cbTipoAnuncio2, txtQtde2, txtValorUnitario2);
			if (linhasVisiveis > 2)
				inserirOutrosItensML(cbItem3, cbTipoAnuncio3, txtQtde3, txtValorUnitario3);
			if (linhasVisiveis > 3)
				inserirOutrosItensML(cbItem4, cbTipoAnuncio4, txtQtde4, txtValorUnitario4);
			if (linhasVisiveis > 4)
				inserirOutrosItensML(cbItem5, cbTipoAnuncio5, txtQtde5, txtValorUnitario5);
		}
		LoadScene.getModalStage().close();
		buscaAutomatica();
	}

	private void inserirOutrosItensShopee(ComboBox<String> cbItem, TextField txtQtde, TextField txtValorUnitario) {
		String codItem = cbItem.getSelectionModel().getSelectedItem();
		Integer qtde = !txtQtde.getText().isBlank() ? Integer.parseInt(txtQtde.getText()) : null;
		Double valorUnitario = !txtValorUnitario.getText().isBlank() ? Double.valueOf(txtValorUnitario.getText())
				: null;
		Double valorTotal = CalculaTotalERecebido.calculaTotal(qtde, valorUnitario);
		Double valorRecebido = CalculaTotalERecebido.calculaRecebidoShopee(valorTotal, qtde);
		try {
			shopeeController.insertItemVenda(codItem, qtde, valorUnitario, valorTotal, valorRecebido, Boolean.FALSE);
		} catch (SQLException e) {
			Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	private void inserirOutrosItensML(ComboBox<String> cbItem, ComboBox<String> cbTipoAnuncio, TextField txtQtde,
			TextField txtValorUnitario) {
		String codItem = cbItem.getSelectionModel().getSelectedItem();
		String tipoAnuncio = cbTipoAnuncio.getSelectionModel().getSelectedItem();
		Integer qtde = !txtQtde.getText().isBlank() ? Integer.parseInt(txtQtde.getText()) : null;
		Double valorUnitario = !txtValorUnitario.getText().isBlank() ? Double.valueOf(txtValorUnitario.getText())
				: null;
		Double valorTotal = CalculaTotalERecebido.calculaTotal(qtde, valorUnitario);
		Double valorRecebido = CalculaTotalERecebido.calculaRecebidoML(valorUnitario, qtde, tipoAnuncio);
		try {
			mercadoLivreController.insertItemVenda(codItem, tipoAnuncio, qtde, valorUnitario, valorTotal,
					valorRecebido);
		} catch (SQLException e) {
			Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	private void setPositionFields(String canal) {
		if (canal == null)
			return;

		if (canal.equals(Constants.LOJA.SHOPEE)) {
			setComboBoxPosition(cbItem1, cbItem2, cbItem3, cbItem4, cbItem5, 142D);
			setTextFieldsPosition(txtQtde1, txtQtde2, txtQtde3, txtQtde4, txtQtde5, 272D);
			setTextFieldsPosition(txtValorUnitario1, txtValorUnitario2, txtValorUnitario3, txtValorUnitario4,
					txtValorUnitario5, 382D);
			setTextFieldsPosition(txtValorTotal1, txtValorTotal2, txtValorTotal3, txtValorTotal4, txtValorTotal5, 542D);
			setTextFieldsPosition(txtValorRecebido1, txtValorRecebido2, txtValorRecebido3, txtValorRecebido4,
					txtValorRecebido5, 702D);
			setButtonsPosition(btnX2, btnX3, btnX4, btnX5, 852D);

			setVisibilityTipoAnuncio(Boolean.FALSE);
			setLinhasVisiveis(1);
		} else if (canal.equals(Constants.LOJA.MERCADO_LIVRE)) {
			setComboBoxPosition(cbItem1, cbItem2, cbItem3, cbItem4, cbItem5, 67D);
			setTextFieldsPosition(txtQtde1, txtQtde2, txtQtde3, txtQtde4, txtQtde5, 377D);
			setTextFieldsPosition(txtValorUnitario1, txtValorUnitario2, txtValorUnitario3, txtValorUnitario4,
					txtValorUnitario5, 477D);
			setTextFieldsPosition(txtValorTotal1, txtValorTotal2, txtValorTotal3, txtValorTotal4, txtValorTotal5, 627D);
			setTextFieldsPosition(txtValorRecebido1, txtValorRecebido2, txtValorRecebido3, txtValorRecebido4,
					txtValorRecebido5, 777D);
			setButtonsPosition(btnX2, btnX3, btnX4, btnX5, 927D);

			cbTipoAnuncio1.setVisible(Boolean.TRUE);
			setLinhasVisiveis(1);
		}
	}

	private void setTextFieldsPosition(TextField field1, TextField field2, TextField field3, TextField field4,
			TextField field5, Double position) {
		Insets curMargins = StackPane.getMargin(field1);
		StackPane.setMargin(field1,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(field2);
		StackPane.setMargin(field2,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(field3);
		StackPane.setMargin(field3,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(field4);
		StackPane.setMargin(field4,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(field5);
		StackPane.setMargin(field5,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
	}

	private void setButtonsPosition(Button button1, Button button2, Button button3, Button button4, Double position) {
		Insets curMargins = StackPane.getMargin(button1);
		StackPane.setMargin(button1,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(button2);
		StackPane.setMargin(button2,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(button3);
		StackPane.setMargin(button3,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(button4);
		StackPane.setMargin(button4,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
	}

	private void setComboBoxPosition(ComboBox<String> field1, ComboBox<String> field2, ComboBox<String> field3,
			ComboBox<String> field4, ComboBox<String> field5, Double position) {
		Insets curMargins = StackPane.getMargin(field1);
		StackPane.setMargin(field1,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(field2);
		StackPane.setMargin(field2,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(field3);
		StackPane.setMargin(field3,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(field4);
		StackPane.setMargin(field4,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
		curMargins = StackPane.getMargin(field5);
		StackPane.setMargin(field5,
				new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));
	}

	private void setVisibilityTipoAnuncio(Boolean bool) {
		cbTipoAnuncio1.setVisible(bool);
		cbTipoAnuncio2.setVisible(bool);
		cbTipoAnuncio3.setVisible(bool);
		cbTipoAnuncio4.setVisible(bool);
		cbTipoAnuncio5.setVisible(bool);
	}

	private void setNumberFields() {
		Constraints.setTextFieldNumber(txtQtde1);
		Constraints.setTextFieldNumber(txtQtde2);
		Constraints.setTextFieldNumber(txtQtde3);
		Constraints.setTextFieldNumber(txtQtde4);
		Constraints.setTextFieldNumber(txtQtde5);
		Constraints.setTextFieldNumberBar(txtData);
		Constraints.setTextFieldMaxLength(txtData, 10);
	}

	private void setVisibilityInsertItem(Integer row, Boolean visible) {
		switch (row) {
		case 2:
			cbItem2.setVisible(visible);
			txtQtde2.setVisible(visible);
			txtValorUnitario2.setVisible(visible);
			txtValorTotal2.setVisible(visible);
			txtValorRecebido2.setVisible(visible);
			btnX2.setVisible(visible);
			break;
		case 3:
			cbItem3.setVisible(visible);
			txtQtde3.setVisible(visible);
			txtValorUnitario3.setVisible(visible);
			txtValorTotal3.setVisible(visible);
			txtValorRecebido3.setVisible(visible);
			btnX3.setVisible(visible);
			break;
		case 4:
			cbItem4.setVisible(visible);
			txtQtde4.setVisible(visible);
			txtValorUnitario4.setVisible(visible);
			txtValorTotal4.setVisible(visible);
			txtValorRecebido4.setVisible(visible);
			btnX4.setVisible(visible);
			break;
		case 5:
			cbItem5.setVisible(visible);
			txtQtde5.setVisible(visible);
			txtValorUnitario5.setVisible(visible);
			txtValorTotal5.setVisible(visible);
			txtValorRecebido5.setVisible(visible);
			btnX5.setVisible(visible);
			break;
		}
	}

	public void updateFieldsEdit(Long id, String canal) {
		btnApagar.setVisible(Boolean.TRUE);
		if (canal.equals("S")) {
			updateFieldsEditShopee(id);
		} else if (canal.equals("ML")) {
			updateFieldsEditMercadoLivre(id);
		}
	}

	public void updateFieldsEditShopee(Long id) {
		try {
			VendaShopeeEntity vendaShopee = shopeeController.findById(id);
			ItemShopeeEntity itemShopee = vendaShopee.getItens().get(0);
			txtIdVenda.setText(vendaShopee.getId().toString());
			txtIdDado.setText(vendaShopee.getIdDado().toString());
			cbCanal.setValue(Constants.LOJA.SHOPEE);
			cbCanal.setEditable(Boolean.FALSE);
			setPositionFields(cbCanal.getSelectionModel().getSelectedItem());
			txtData.setText(DataUtils.dateToString(vendaShopee.getData()));
			txtCliente.setText(vendaShopee.getCliente());
			cbItem1.setValue(itemShopee.getCodItem());
			txtQtde1.setText(itemShopee.getQtde().toString());
			txtValorUnitario1.setText(itemShopee.getValorUnitario().toString());
			txtValorTotal1.setText(itemShopee.getValorTotal().toString());
			txtValorRecebido1.setText(itemShopee.getValorRecebido().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateFieldsEditMercadoLivre(Long id) {
		try {
			VendaMercadoLivreEntity vendaML = mercadoLivreController.findById(id);
			ItemMercadoLivreEntity itemML = vendaML.getItens().get(0);
			txtIdVenda.setText(vendaML.getId().toString());
			txtIdDado.setText(vendaML.getIdDado().toString());
			cbCanal.setValue(Constants.LOJA.MERCADO_LIVRE);
			cbCanal.setEditable(Boolean.FALSE);
			setPositionFields(cbCanal.getSelectionModel().getSelectedItem());
			txtData.setText(DataUtils.dateToString(vendaML.getData()));
			txtCliente.setText(vendaML.getCliente());
			cbItem1.setValue(itemML.getCodItem());
			cbTipoAnuncio1.setValue(itemML.getTipoAnuncio());
			txtQtde1.setText(itemML.getQtde().toString());
			txtValorUnitario1.setText(itemML.getValorUnitario().toString());
			txtValorTotal1.setText(itemML.getValorTotal().toString());
			txtValorRecebido1.setText(itemML.getValorRecebido().toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setLinhasVisiveis(Integer linhasVisiveis) {
		this.linhasVisiveis = linhasVisiveis;
	}

	private void buscaAutomatica() {
//		viewVendasController.buscaAutomatica();
	}

}
