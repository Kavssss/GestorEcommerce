package frontend.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.ItemController;
import backend.entities.ItemEntity;
import frontend.utils.Constants;
import frontend.utils.LoadScene;
import frontend.views.utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModalProdutoController implements Initializable {

	ItemController itemController = new ItemController();
	
    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnDesativar;

    @FXML
    private Button btnSalvar;
    
    @FXML
    private TextField txtIdItem;

    @FXML
    private TextField txtCodItem;

    @FXML
    private TextField txtDescricao;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtVariacao;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
    
    @FXML
    void onCancelarAction(ActionEvent event) {
    	LoadScene.getModalStage().close();
    }

    @FXML
    void onDesativarAction(ActionEvent event) {
    	Long idItem = !txtIdItem.getText().isBlank() ? Long.valueOf(txtIdItem.getText()) : null;
    	
		try {
			if (btnDesativar.getText().equals("ATIVAR")) 
				itemController.enableProduto(idItem);
			else if (btnDesativar.getText().equals("DESATIVAR")) 
				itemController.disableProduto(idItem);
		} catch (SQLException e) {
			Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
    }

    @FXML
    void onSalvarAction(ActionEvent event) {
    	Long idItem = !txtIdItem.getText().isBlank() ? Long.valueOf(txtIdItem.getText()) : null;
    	String codItem = !txtCodItem.getText().isBlank() ? txtCodItem.getText() : null;
    	String descricao = !txtDescricao.getText().isBlank() ? txtDescricao.getText() : null;
    	
    	if (Objects.isNull(codItem) || Objects.isNull(descricao)) {
			Alerts.showAlert("Campos não preenchidos", "ERRO", Constants.MESSAGE.CAMPOS_NAO_PREENCHIDOS, AlertType.INFORMATION);
			return;
		}
    	
    	try {
			if (Objects.isNull(idItem))
				itemController.insertItem(codItem, descricao);
			else
				itemController.editItem(idItem, codItem, descricao);
		} catch (SQLException e) {
			Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
    }
    
    public void updateFieldsEdit(Long id) {
    	try {
			ItemEntity item = itemController.findById(id);
			txtIdItem.setText(item.getId().toString());
			txtCodItem.setText(item.getCodItem());
			txtModelo.setText(item.getCodItem().split("-")[0]);
			txtVariacao.setText(item.getCodItem().split("-")[1]);
			txtDescricao.setText(item.getDescricao());
			btnDesativar.setText(item.getIsAtivo() ? "DESATIVAR" : "ATIVAR");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
