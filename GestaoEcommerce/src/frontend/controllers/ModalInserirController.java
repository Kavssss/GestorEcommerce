package frontend.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import frontend.utils.Constants;
import frontend.utils.LoadScene;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ModalInserirController implements Initializable {

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
    private ComboBox<String> cbContaAnuncio;

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
    private TextField txtValotTotal1;
    
    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	setNumberFields();
    	for (int i = 2; i <= 5; i++)
    		setVisibilityInsertItem(i, false);
    	List<String> options = Arrays.asList("Shopee", "Mercado Livre");
    	cbCanal.setItems(FXCollections.observableArrayList(options));
    	options = Arrays.asList("Principal", "Secundária");
    	cbContaAnuncio.setItems(FXCollections.observableArrayList(options));
    }
    
    @FXML
    void onCanalAction(ActionEvent event) {
    	List<String> options;
		String selection = cbCanal.getSelectionModel().getSelectedItem();
		if (selection != null && selection.equals(Constants.LOJA.SHOPEE)) {
			cbContaAnuncio.setPromptText(Constants.CONTA_ANUNCIO.CONTA);
			options = Arrays.asList("", Constants.CONTA_ANUNCIO.PRINCIPAL, Constants.CONTA_ANUNCIO.SECUNDARIA);
			cbContaAnuncio.setItems(FXCollections.observableArrayList(options));
		} else if (selection != null && selection.equals(Constants.LOJA.MERCADO_LIVRE)) {
			cbContaAnuncio.setPromptText(Constants.CONTA_ANUNCIO.TIPO_ANUNCIO);
			options = Arrays.asList("", Constants.CONTA_ANUNCIO.CLASSICO, Constants.CONTA_ANUNCIO.CLASSICO_FG,
					Constants.CONTA_ANUNCIO.PREMIUM, Constants.CONTA_ANUNCIO.PREMIUM_FG);
			cbContaAnuncio.setItems(FXCollections.observableArrayList(options));
		} else {
			cbContaAnuncio.setPromptText("-");
			cbContaAnuncio.setItems(null);
		}
    }
    
    @FXML
    void onAddItemAction(ActionEvent event) {
    	switch(linhasVisiveis) {
    	case 1:
    		setVisibilityInsertItem(2, true);
    		linhasVisiveis = 2;
    		break;
    	case 2:
    		setVisibilityInsertItem(3, true);
    		btnX2.setVisible(false);
    		linhasVisiveis = 3;
    		break;
    	case 3:
    		setVisibilityInsertItem(4, true);
    		btnX3.setVisible(false);
    		linhasVisiveis = 4;
    		break;
    	case 4:
    		setVisibilityInsertItem(5, true);
    		btnX4.setVisible(false);
    		linhasVisiveis = 5;
    		break;
    	}
    }
    
    @FXML
    void onBtnX2Action(ActionEvent event) {
    	setVisibilityInsertItem(2, false);
    }

    @FXML
    void onBtnX3Action(ActionEvent event) {
    	setVisibilityInsertItem(3, false);
		btnX2.setVisible(true);
    }

    @FXML
    void onBtnX4Action(ActionEvent event) {
    	setVisibilityInsertItem(4, false);
		btnX3.setVisible(true);
    }

    @FXML
    void onBtnX5Action(ActionEvent event) {
    	setVisibilityInsertItem(5, false);
		btnX4.setVisible(true);
    }
    
    @FXML
    void onCancelarAction(ActionEvent event) {
    	LoadScene.getModalStage().close();
    }
    
    @FXML
    void onSalvarAction(ActionEvent event) {

    }
    
    private void setNumberFields() {
    	Constraints.setTextFieldNumber(txtQtde1);
    	Constraints.setTextFieldNumber(txtQtde2);
    	Constraints.setTextFieldNumber(txtQtde3);
    	Constraints.setTextFieldNumber(txtQtde4);
    	Constraints.setTextFieldNumber(txtQtde5);
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
    
}
