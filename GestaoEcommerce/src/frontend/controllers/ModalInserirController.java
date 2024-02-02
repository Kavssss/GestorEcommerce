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
import frontend.utils.LoadScene;
import frontend.views.utils.Alerts;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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
    private TextField txtNomeCliente;
	
	@FXML
    private TextField txtUsuarioCliente;
	
	@FXML
    private TextField txtCpfCliente;

	@FXML
    private DatePicker dpData;

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
	
	@FXML
    private TextField txtCustoFrete1;

    @FXML
    private TextField txtCustoFrete2;

    @FXML
    private TextField txtCustoFrete3;

    @FXML
    private TextField txtCustoFrete4;

    @FXML
    private TextField txtCustoFrete5;
	
	@FXML
    private Label labelTipoAnuncio;
	
	@FXML
    private Label labelCodItem;
	
	@FXML
    private Label labelQtde;
	
	@FXML
    private Label labelValorUnitario;
	
	@FXML
    private Label labelValorTotal;
	
	@FXML
    private Label labelValorRecebido;
	
	@FXML
    private Label labelCustoFrete;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		LoadScene.getModalStage().setWidth(LoadScene.getModalWidth());
		LoadScene.getModalStage().setHeight(LoadScene.getModalHeight());
		Constraints.setReal(txtCustoFrete1);
		Constraints.setReal(txtValorUnitario1);
		setNumberFields();
		for (int i = 2; i <= 5; i++)
			setVisibilityInsertItem(i, Boolean.FALSE);
		cbCanal.setItems(
				FXCollections.observableArrayList(Arrays.asList(Constants.LOJA.SHOPEE, Constants.LOJA.MERCADO_LIVRE)));
		cbStatus.setItems(FXCollections.observableArrayList(Arrays.asList(Constants.STATUS.PENDENTE,
				Constants.STATUS.CONCLUIDO, Constants.STATUS.DEVOLUCAO, Constants.STATUS.CANCELADO)));
		cbStatus.setValue(Constants.STATUS.PENDENTE);
		fillCbTipoAnuncio(cbTipoAnuncio1);
		fillCbTipoAnuncio(cbTipoAnuncio2);
		fillCbTipoAnuncio(cbTipoAnuncio3);
		fillCbTipoAnuncio(cbTipoAnuncio4);
		fillCbTipoAnuncio(cbTipoAnuncio5);
		List<String> itens = cbItemSetItems();
		cbItem1.setItems(FXCollections.observableArrayList(itens));
		cbItem2.setItems(FXCollections.observableArrayList(itens));
		cbItem3.setItems(FXCollections.observableArrayList(itens));
		cbItem4.setItems(FXCollections.observableArrayList(itens));
		cbItem5.setItems(FXCollections.observableArrayList(itens));
		btnApagar.setVisible(Boolean.FALSE);
	}

	private void fillCbTipoAnuncio(ComboBox<String> cb) {
		cb.setItems(FXCollections
				.observableArrayList(Arrays.asList(Constants.TIPO_ANUNCIO.CLASSICO, Constants.TIPO_ANUNCIO.PREMIUM)));
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
					txtCustoFrete2.setVisible(Boolean.TRUE);
				break;
			case 2:
				setVisibilityInsertItem(3, Boolean.TRUE);
				btnX2.setVisible(false);
				setLinhasVisiveis(3);
				if (canal.equals(Constants.LOJA.MERCADO_LIVRE))
					cbTipoAnuncio3.setVisible(Boolean.TRUE);
					txtCustoFrete3.setVisible(Boolean.TRUE);
				break;
			case 3:
				setVisibilityInsertItem(4, Boolean.TRUE);
				btnX3.setVisible(false);
				setLinhasVisiveis(4);
				if (canal.equals(Constants.LOJA.MERCADO_LIVRE))
					cbTipoAnuncio4.setVisible(Boolean.TRUE);
					txtCustoFrete4.setVisible(Boolean.TRUE);
				break;
			case 4:
				setVisibilityInsertItem(5, Boolean.TRUE);
				btnX4.setVisible(false);
				setLinhasVisiveis(5);
				if (canal.equals(Constants.LOJA.MERCADO_LIVRE))
					cbTipoAnuncio5.setVisible(Boolean.TRUE);
					txtCustoFrete5.setVisible(Boolean.TRUE);
				break;
			}
		}
	}

	@FXML
	void onBtnX2Action(ActionEvent event) {
		setVisibilityInsertItem(2, Boolean.FALSE);
		setLinhasVisiveis(1);
		cbTipoAnuncio2.setVisible(Boolean.FALSE);
		txtCustoFrete2.setVisible(Boolean.FALSE);
	}

	@FXML
	void onBtnX3Action(ActionEvent event) {
		setVisibilityInsertItem(3, Boolean.FALSE);
		btnX2.setVisible(Boolean.TRUE);
		setLinhasVisiveis(2);
		cbTipoAnuncio3.setVisible(Boolean.FALSE);
		txtCustoFrete3.setVisible(Boolean.FALSE);
	}

	@FXML
	void onBtnX4Action(ActionEvent event) {
		setVisibilityInsertItem(4, Boolean.FALSE);
		btnX3.setVisible(Boolean.TRUE);
		setLinhasVisiveis(3);
		cbTipoAnuncio4.setVisible(Boolean.FALSE);
		txtCustoFrete4.setVisible(Boolean.FALSE);
	}

	@FXML
	void onBtnX5Action(ActionEvent event) {
		setVisibilityInsertItem(5, Boolean.FALSE);
		btnX4.setVisible(Boolean.TRUE);
		setLinhasVisiveis(4);
		cbTipoAnuncio5.setVisible(Boolean.FALSE);
		txtCustoFrete5.setVisible(Boolean.FALSE);
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
		Date data = Objects.nonNull(dpData.getValue()) ? Date.valueOf(dpData.getValue()) : null;
		String nomeCliente = !txtNomeCliente.getText().isBlank() ? txtNomeCliente.getText() : null;
		String usuarioCliente = !txtUsuarioCliente.getText().isBlank() ? txtUsuarioCliente.getText() : null;
		String cpfCliente = !txtCpfCliente.getText().isBlank() ? txtCpfCliente.getText() : null;
		String status = cbStatus.getSelectionModel().getSelectedItem();

		String codItem = cbItem1.getSelectionModel().getSelectedItem();
		String tipoAnuncio = cbTipoAnuncio1.getSelectionModel().getSelectedItem();
		Double custoFrete = !txtCustoFrete1.getText().isBlank() ? Double.valueOf(txtCustoFrete1.getText()) : null;
		Integer qtde = !txtQtde1.getText().isBlank() ? Integer.parseInt(txtQtde1.getText()) : null;
		Double valorUnitario = !txtValorUnitario1.getText().isBlank() ? Double.valueOf(txtValorUnitario1.getText())
				: null;
		Double valorTotal = qtde * valorUnitario;
		Double valorRecebido = canal.equals(Constants.LOJA.SHOPEE)
				? CalculaTotalERecebido.calculaRecebidoShopee(valorTotal, qtde)
				: CalculaTotalERecebido.calculaRecebidoML(valorUnitario, qtde, tipoAnuncio, custoFrete);

		if (Objects.isNull(nomeCliente) && Objects.isNull(usuarioCliente)) {
			Alerts.showAlert("Campos não preenchidos", "ERRO", Constants.MESSAGE.NOME_OU_USUARIO_NAO_PREENCHIDO,
					AlertType.INFORMATION);
			return;
		}
		
		if (Objects.isNull(canal) || Objects.isNull(data) || Objects.isNull(status) || Objects.isNull(codItem)
				|| Objects.isNull(qtde) || Objects.isNull(valorUnitario)
				|| (canal.equals(Constants.LOJA.MERCADO_LIVRE) && Objects.isNull(tipoAnuncio))) {
			Alerts.showAlert("Campos não preenchidos", "ERRO", Constants.MESSAGE.CAMPOS_NAO_PREENCHIDOS, AlertType.INFORMATION);
			return;
		}

		if (canal.equals(Constants.LOJA.SHOPEE)) {
			try {
				if (Objects.isNull(idVenda)) {
					shopeeController.insertVenda(data, nomeCliente, status, codItem, qtde, valorUnitario, valorTotal,
							valorRecebido);
				} else {
					shopeeController.editVenda(idVenda, idDado, data, nomeCliente, status, codItem, qtde, valorUnitario,
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
					mercadoLivreController.insertVenda(data, nomeCliente, status, codItem, tipoAnuncio, custoFrete, qtde,
							valorUnitario, valorTotal, valorRecebido);
				else {
					mercadoLivreController.editVenda(idVenda, idDado, data, nomeCliente, status, codItem, tipoAnuncio, custoFrete,
							qtde, valorUnitario, valorTotal, valorRecebido);
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
		Alerts.showAlert("Sucesso", "INSERIDO COM SUCESSO", null, AlertType.INFORMATION);
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
		Double custoFrete = !txtCustoFrete1.getText().isBlank() ? Double.valueOf(txtCustoFrete1.getText()) : null;
		Integer qtde = !txtQtde.getText().isBlank() ? Integer.parseInt(txtQtde.getText()) : null;
		Double valorUnitario = !txtValorUnitario.getText().isBlank() ? Double.valueOf(txtValorUnitario.getText())
				: null;
		Double valorTotal = qtde * valorUnitario;
		Double valorRecebido = CalculaTotalERecebido.calculaRecebidoML(valorUnitario, qtde, tipoAnuncio, custoFrete);
		try {
			mercadoLivreController.insertItemVenda(codItem, tipoAnuncio, custoFrete, qtde, valorUnitario, valorTotal,
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
			setFieldsSizeAndPosition(labelCodItem, cbItem1, cbItem2, cbItem3, cbItem4, cbItem5, 7, 6);
			setFieldsSizeAndPosition(labelQtde, txtQtde1, txtQtde2, txtQtde3, txtQtde4, txtQtde5, 15, 4);
			setFieldsSizeAndPosition(labelValorUnitario, txtValorUnitario1, txtValorUnitario2, txtValorUnitario3, txtValorUnitario4,
					txtValorUnitario5, 21, 6);
			setFieldsSizeAndPosition(labelValorTotal, txtValorTotal1, txtValorTotal2, txtValorTotal3, txtValorTotal4,
					txtValorTotal5, 29, 6);
			setFieldsSizeAndPosition(labelValorRecebido, txtValorRecebido1, txtValorRecebido2, txtValorRecebido3, txtValorRecebido4,
					txtValorRecebido5, 37, 6);
			setFieldsSizeAndPosition(null, null, btnX2, btnX3, btnX4, btnX5, 44, 1);

			hideFieldsML();
		} else if (canal.equals(Constants.LOJA.MERCADO_LIVRE)) {
			setFieldsSizeAndPosition(labelCodItem, cbItem1, cbItem2, cbItem3, cbItem4, cbItem5, 3, 6);
			setFieldsSizeAndPosition(labelQtde, txtQtde1, txtQtde2, txtQtde3, txtQtde4, txtQtde5, 20, 3);
			setFieldsSizeAndPosition(labelValorUnitario, txtValorUnitario1, txtValorUnitario2, txtValorUnitario3, txtValorUnitario4,
					txtValorUnitario5, 29, 5);
			setFieldsSizeAndPosition(labelValorTotal, txtValorTotal1, txtValorTotal2, txtValorTotal3, txtValorTotal4,
					txtValorTotal5, 35, 5);
			setFieldsSizeAndPosition(labelValorRecebido, txtValorRecebido1, txtValorRecebido2, txtValorRecebido3, txtValorRecebido4,
					txtValorRecebido5, 41, 5);
			setFieldsSizeAndPosition(null, null, btnX2, btnX3, btnX4, btnX5, 47, 1);

			showFieldsML();
		}
		setLinhasVisiveis(1);
	}
	
	private void setFieldsSizeAndPosition(Node label, Node field1, Node field2, Node field3, Node field4, Node field5,
			Integer position, Integer size) {
		if (Objects.nonNull(label))
			GridPane.setColumnIndex(label, position);
		
		if (Objects.nonNull(field1)) {
			GridPane.setColumnIndex(field1, position);
			GridPane.setColumnSpan(field1, size);
		}
		GridPane.setColumnIndex(field2, position);
		GridPane.setColumnSpan(field2, size);
		GridPane.setColumnIndex(field3, position);
		GridPane.setColumnSpan(field3, size);
		GridPane.setColumnIndex(field4, position);
		GridPane.setColumnSpan(field4, size);
		GridPane.setColumnIndex(field5, position);
		GridPane.setColumnSpan(field5, size);
	}

	private void hideFieldsML() {
		labelTipoAnuncio.setVisible(false);
		cbTipoAnuncio1.setVisible(false);
		cbTipoAnuncio2.setVisible(false);
		cbTipoAnuncio3.setVisible(false);
		cbTipoAnuncio4.setVisible(false);
		cbTipoAnuncio5.setVisible(false);
		labelCustoFrete.setVisible(false);
		txtCustoFrete1.setVisible(false);
		txtCustoFrete2.setVisible(false);
		txtCustoFrete3.setVisible(false);
		txtCustoFrete4.setVisible(false);
		txtCustoFrete5.setVisible(false);
	}
	
	private void showFieldsML() {
		labelTipoAnuncio.setVisible(true);
		cbTipoAnuncio1.setVisible(true);
		labelCustoFrete.setVisible(true);
		txtCustoFrete1.setVisible(true);
	}

	private void setNumberFields() {
		Constraints.setTextFieldNumber(txtQtde1);
		Constraints.setTextFieldNumber(txtQtde2);
		Constraints.setTextFieldNumber(txtQtde3);
		Constraints.setTextFieldNumber(txtQtde4);
		Constraints.setTextFieldNumber(txtQtde5);
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
//			txtData.setText(DataUtils.dateToString(vendaShopee.getData()));
			txtNomeCliente.setText(vendaShopee.getCliente());
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
			txtNomeCliente.setText(vendaML.getCliente());
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
