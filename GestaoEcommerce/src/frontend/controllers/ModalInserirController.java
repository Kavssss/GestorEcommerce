package frontend.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.VendaMercadoLivreController;
import backend.controllers.VendaShopeeController;
import frontend.utils.Constants;
import frontend.utils.DataUtils;
import frontend.utils.LoadScene;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

public class ModalInserirController implements Initializable {

	VendaShopeeController shopeeController = new VendaShopeeController();
	VendaMercadoLivreController mercadoLivreController = new VendaMercadoLivreController();
	
	private Integer linhasVisiveis = 1;
	
    @FXML
    private Button btnAddItem;

    @FXML
    private Button btnCancelar;

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
    private TextField txtCodItem1;

    @FXML
    private TextField txtCodItem2;

    @FXML
    private TextField txtCodItem3;

    @FXML
    private TextField txtCodItem4;

    @FXML
    private TextField txtCodItem5;

    @FXML
    private TextField txtData;

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

    private List<TextField> codItemList = Arrays.asList(txtCodItem1, txtCodItem2, txtCodItem3, txtCodItem4, txtCodItem5);
    private List<TextField> qtdeList = Arrays.asList(txtQtde1, txtQtde2, txtQtde3, txtQtde4, txtQtde5);
    private List<TextField> valorUnitarioList = Arrays.asList(txtValorUnitario1, txtValorUnitario2, txtValorUnitario3,
    		txtValorUnitario4, txtValorUnitario5);
    private List<TextField> valorTotalList = Arrays.asList(txtValorTotal1, txtValorTotal2, txtValorTotal3, txtValorTotal4,
    		txtValorTotal5);
    private List<TextField> valorRecebidoList = Arrays.asList(txtValorRecebido1, txtValorRecebido2, txtValorRecebido3,
    		txtValorRecebido4, txtValorRecebido5);
    private List<Button> btnXList = Arrays.asList(btnX2, btnX3, btnX4, btnX5);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	setNumberFields();
    	for (int i = 2; i <= 5; i++)
    		setVisibilityInsertItem(i, Boolean.FALSE);
    	cbCanal.setItems(FXCollections.observableArrayList(Arrays.asList(
    			Constants.LOJA.SHOPEE, Constants.LOJA.MERCADO_LIVRE)));
    	cbSetItems(cbTipoAnuncio1);
    	cbSetItems(cbTipoAnuncio2);
    	cbSetItems(cbTipoAnuncio3);
    	cbSetItems(cbTipoAnuncio4);
    	cbSetItems(cbTipoAnuncio5);
    }
    
    private void cbSetItems(ComboBox<String> cb) {
    	cb.setItems(FXCollections.observableArrayList(Arrays.asList(Constants.TIPO_ANUNCIO.CLASSICO,
    			Constants.TIPO_ANUNCIO.CLASSICO_FG,Constants.TIPO_ANUNCIO.PREMIUM, Constants.TIPO_ANUNCIO.PREMIUM_FG)));
    }
    
    @FXML
    void onAddItemAction(ActionEvent event) {
    	String canal = cbCanal.getSelectionModel().getSelectedItem();
    	if (Objects.nonNull(canal)) {
	    	switch(linhasVisiveis) {
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
    void onCancelarAction(ActionEvent event) {
    	LoadScene.getModalStage().close();
    }

    @FXML
    void onDataReleased(KeyEvent event) {
    	DataUtils.formataData(txtData, event);
    }

    @FXML
    void onSalvarAction(ActionEvent event) {
//    	String canal = cbCanal.getSelectionModel().getSelectedItem();
//		Date data = !txtData.getText().isBlank() ? DataUtils.stringToDate(txtData.getText()) : null;
//		String cliente = !txtCliente.getText().isBlank() ? "%"+txtCliente.getText()+"%" : null;
//		
//		String codItem = !txtCodItem1.getText().isBlank() ? "%"+txtCodItem1.getText()+"%" : null;
//		String tipoAnuncio = cbTipoAnuncio1.getSelectionModel().getSelectedItem();
//		Integer qtde = !txtQtde1.getText().isBlank() ? Integer.parseInt(txtQtde1.getText()) : null;
//		Double valorUnitario = !txtValorUnitario1.getText().isBlank() ? Double.valueOf(txtValorUnitario1.getText()) : null;
//		Double valorTotal = !txtValorTotal1.getText().isBlank() ? Double.valueOf(txtValorTotal1.getText()) : null;
//		Double valorRecebido = !txtValorRecebido1.getText().isBlank() ? Double.valueOf(txtValorRecebido1.getText()) : null;
//		
//		if (Objects.isNull(canal) || Objects.isNull(data) || Objects.isNull(cliente) || Objects.isNull(codItem)
//				|| Objects.isNull(qtde) || Objects.isNull(valorUnitario)) {
//			Alerts.showAlert("Campos não preenchidos", "ERRO", Constants.MESSAGE.CAMPOS_NAO_PREENCHIDOS, AlertType.INFORMATION);
//			return;
//		}
//		
//		if (canal.equals(Constants.LOJA.SHOPEE)) {
//			try {
//				shopeeController.insertVenda(data, cliente, codItem, qtde, valorUnitario, valorTotal, valorRecebido);
//			} catch (SQLException e) {
//				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
//			}
//		}
//		else if (canal.equals(Constants.LOJA.MERCADO_LIVRE)) {
//			
//		}
    }

    private void setPositionFields(String canal) {
    	if (canal.equals(Constants.LOJA.SHOPEE)) {
    		setTextFieldPositionField(codItemList, 142D);
    		setTextFieldPositionField(qtdeList, 272D);
    		setTextFieldPositionField(valorUnitarioList, 382D);
    		setTextFieldPositionField(valorTotalList, 542D);
    		setTextFieldPositionField(valorRecebidoList, 702D);    		
    		setButtonPositionField(btnXList, 852D);
    		
    		setVisibilityTipoAnuncio(Boolean.FALSE);
    		setLinhasVisiveis(1);
    	}
    	else if (canal.equals(Constants.LOJA.MERCADO_LIVRE)) {
    		setTextFieldPositionField(codItemList, 67D);
    		setTextFieldPositionField(qtdeList, 377D);
    		setTextFieldPositionField(valorUnitarioList, 477D);
    		setTextFieldPositionField(valorTotalList, 627D);
    		setTextFieldPositionField(valorRecebidoList, 777D);   		
    		setButtonPositionField(btnXList, 927D);
    		
    		cbTipoAnuncio1.setVisible(Boolean.TRUE);
    		setLinhasVisiveis(1);
    	}
    }
    
	private void setTextFieldPositionField(List<TextField> fields, Double position) {
    	for (TextField field : fields) {
    		Insets curMargins = StackPane.getMargin(field);
    		StackPane.setMargin(field, new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));    		
    	}
    }
	
	private void setButtonPositionField(List<Button> fields, Double position) {
    	for (Button field : fields) {
    		Insets curMargins = StackPane.getMargin(field);
    		StackPane.setMargin(field, new Insets(curMargins.getTop(), curMargins.getRight(), curMargins.getBottom(), position));    		
    	}
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
//		Constraints.setTextFieldDouble(txtValorUnitario1);
//		Constraints.setTextFieldDouble(txtValorUnitario2);
//		Constraints.setTextFieldDouble(txtValorUnitario3);
//		Constraints.setTextFieldDouble(txtValorUnitario4);
//		Constraints.setTextFieldDouble(txtValorUnitario5);
    }
    
    private void setVisibilityInsertItem(Integer row, Boolean visible) {
    	switch(row) {
    	case 2:
    		txtCodItem2.setVisible(visible);
    		txtQtde2.setVisible(visible);
    		txtValorUnitario2.setVisible(visible);
    		txtValorTotal2.setVisible(visible);
    		txtValorRecebido2.setVisible(visible);
    		btnX2.setVisible(visible);
    		break;
    	case 3:
    		txtCodItem3.setVisible(visible);
    		txtQtde3.setVisible(visible);
    		txtValorUnitario3.setVisible(visible);
    		txtValorTotal3.setVisible(visible);
    		txtValorRecebido3.setVisible(visible);
    		btnX3.setVisible(visible);
    		break;
    	case 4:
    		txtCodItem4.setVisible(visible);
    		txtQtde4.setVisible(visible);
    		txtValorUnitario4.setVisible(visible);
    		txtValorTotal4.setVisible(visible);
    		txtValorRecebido4.setVisible(visible);
    		btnX4.setVisible(visible);
    		break;
    	case 5:
    		txtCodItem5.setVisible(visible);
    		txtQtde5.setVisible(visible);
    		txtValorUnitario5.setVisible(visible);
    		txtValorTotal5.setVisible(visible);
    		txtValorRecebido5.setVisible(visible);
    		btnX5.setVisible(visible);
    		break;
    	}
    }

    private void setLinhasVisiveis(Integer linhasVisiveis) {
		this.linhasVisiveis = linhasVisiveis;
	}
    
}
