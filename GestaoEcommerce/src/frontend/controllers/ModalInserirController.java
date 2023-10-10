package frontend.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class ModalInserirController implements Initializable {

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
    private ComboBox<String> cbConta;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Constraints.setTextFieldNumber(txtQtde1);
    	Constraints.setTextFieldNumber(txtQtde2);
    	Constraints.setTextFieldNumber(txtQtde3);
    	Constraints.setTextFieldNumber(txtQtde4);
    	Constraints.setTextFieldNumber(txtQtde5);
    	List<String> options = Arrays.asList("Shopee", "Mercado Livre");
    	cbCanal.setItems(FXCollections.observableArrayList(options));
    	options = Arrays.asList("Principal", "Secundária");
    	cbConta.setItems(FXCollections.observableArrayList(options));
    }
    
}
