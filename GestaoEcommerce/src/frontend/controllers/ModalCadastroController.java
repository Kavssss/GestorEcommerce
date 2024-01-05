package frontend.controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.UsuarioController;
import frontend.utils.LoadScene;
import frontend.utils.enums.TipoUsuario;
import frontend.views.utils.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class ModalCadastroController implements Initializable {

	UsuarioController controller = new UsuarioController();
	
	@FXML
    private Button btnSalvar;
	
	@FXML
    private Button btnX;
	
	@FXML
    private Label labelError;

    @FXML
    private RadioButton rbAdmin;

    @FXML
    private RadioButton rbPadrao;

    @FXML
    private PasswordField txtConfirmarSenha;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;
    
    private ToggleGroup toggleGroup = new ToggleGroup();
    
    @Override
	public void initialize(URL url, ResourceBundle rb) {
        rbAdmin.setToggleGroup(toggleGroup);
        rbPadrao.setToggleGroup(toggleGroup);
        Constraints.setNoSpaceTextField(txtSenha);
        Constraints.setNoSpaceTextField(txtConfirmarSenha);
	}

    @FXML
    void onSalvarAction(ActionEvent event) {
    	labelError.setVisible(false);
    	String usuario = !txtUsuario.getText().isBlank() ? txtUsuario.getText() : null;
    	String senha = !txtSenha.getText().isBlank() ? txtSenha.getText().replaceAll(" ", "") : null;
    	String confirmaSenha = !txtConfirmarSenha.getText().isBlank() ? txtConfirmarSenha.getText() : null;
    	TipoUsuario tipo;
    	
    	if (Objects.isNull(toggleGroup.getSelectedToggle())) {
    		displayErrorMessage("Tipo de usuário não selecionado!");
			return;
    	}
    	if (Objects.isNull(usuario)) {
    		displayErrorMessage("Usuário não pode ser vazio!");
    		return;
    	}
    	if (Objects.isNull(senha)) {
    		displayErrorMessage("Senha não pode ser vazio!");
    		return;
		}
    	if (Objects.isNull(confirmaSenha) || !senha.equals(confirmaSenha)) {
    		displayErrorMessage("Senhas não batem!");
    		return;
    	}
    	if (userExists(usuario)) {
    		displayErrorMessage("Usuário já existe!");
    		return;
    	}
    	if (!Constraints.validatePassword(senha)) {
    		displayErrorMessage("Senha com pelo menos 4 caracteres!");
    		return;
    	}
    	
    	tipo = toggleGroup.getSelectedToggle().equals(rbPadrao) ? TipoUsuario.PADRAO : TipoUsuario.ADMINISTRADOR;
    	controller.insertNewUser(usuario, senha, tipo);
    	LoadScene.getModalStage().close();
    }
    
    @FXML
    void onXAction(ActionEvent event) {
    	LoadScene.getModalStage().close();
    }

    private Boolean userExists(String user) {
    	return controller.isDuplicateUser(user);
    }

    private void displayErrorMessage(String msg) {
    	labelError.setText(msg);
    	labelError.setVisible(Boolean.TRUE);
    }
}
