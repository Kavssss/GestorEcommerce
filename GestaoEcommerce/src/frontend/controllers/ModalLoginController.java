package frontend.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import frontend.utils.LoadScene;
import frontend.utils.enums.Usuario;
import frontend.views.utils.Alerts;
import frontend.views.utils.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ModalLoginController implements Initializable {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnNovo;

    @FXML
    private Label labelInvalido;

    @FXML
    private RadioButton rbAdmin;

    @FXML
    private RadioButton rbPadrao;

    @FXML
    private TextField txtFrete;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;
    
    private ToggleGroup toggleGroup = new ToggleGroup();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        rbAdmin.setToggleGroup(toggleGroup);
        rbPadrao.setToggleGroup(toggleGroup);
	}

    @FXML
    void onCancelarAction(ActionEvent event) {
    	LoadScene.getModalStage().close();
    }

    @FXML
    void onEntrarAction(ActionEvent event) {
    	if (toggleGroup.getSelectedToggle() == null)
    		Alerts.showAlert(null, "Selecione o tipo de usuário", null, AlertType.INFORMATION);
    	
    	LoadScene.getModalStage().close();
    }

    @FXML
    void onNovoAction(ActionEvent event) {
    	LoadScene.callCadastroModal(LoadScene.getStage(), getClass());
    	LoadScene.getModalStage().close();
    }

    @FXML
    void onPadraoAction(ActionEvent event) {
    	Constraints.setUsuario(Usuario.PADRAO);
    }
    
    @FXML
    void onAdminAction(ActionEvent event) {
    	Constraints.setUsuario(Usuario.ADMINISTRADOR);
    }

}
