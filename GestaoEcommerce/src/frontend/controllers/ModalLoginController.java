package frontend.controllers;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.UsuarioController;
import frontend.utils.LoadScene;
import frontend.views.utils.CSS;
import frontend.views.utils.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class ModalLoginController implements Initializable {

	UsuarioController controller = new UsuarioController();
	
	@FXML
    private StackPane background;
	
	@FXML
    private Button btnX;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnNovo;

    @FXML
    private Label labelError;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUsuario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    	txtUsuario.setStyle("-fx-background-color: #80809B; -fx-background-radius: 15");
//    	txtUsuario.setStyle("-fx-background-color: #fff; -fx-background-radius: 15");
//    	background.setStyle(CSS.LIGHT_THEME.BACKGROUND);
    	Constraints.setNoSpaceTextField(txtSenha);
    	addStyle(txtUsuario, CSS.FIELD.BACKGROUND_COLOR, "#d3d3d3");
    	addStyle(txtUsuario, CSS.FIELD.BACKGROUND_RADIUS, "15");
    }
    
    private void addStyle(Node node, String field, String value) {
    	node.setStyle(node.getStyle() + ";" + field + ":" +  value);
    }

    @FXML
    void onXAction(ActionEvent event) {
    	LoadScene.getLoginModalStage().close();
    	LoadScene.setLoginModalStage(null);
    }
    
    @FXML
    void onEntrarAction(ActionEvent event) {
    	String nome = !txtUsuario.getText().isBlank() ? txtUsuario.getText() : null;
    	String senha = !txtSenha.getText().isBlank() ? txtSenha.getText() : null;
    	
    	if (Objects.isNull(nome)) {
    		displayErrorMessage("Usuário não pode ser vazio!");
    		return;
		}
    	if (Objects.isNull(senha)) {
    		displayErrorMessage("Senha não pode ser vazia!");
    		return;
		}
    	if (!loginExists(nome, senha)) {
    		displayErrorMessage("Usuário e/ou senha inválidos!");
    		return;
    	}

    	LoadScene.getLoginModalStage().close();
    }

    @FXML
    void onNovoAction(ActionEvent event) {
    	LoadScene.callCadastroModal(LoadScene.getStage(), getClass());
    }
    
    private void displayErrorMessage(String msg) {
    	labelError.setText(msg);
		labelError.setVisible(Boolean.TRUE);
    }

    private Boolean loginExists(String usuario, String senha) {
    	return controller.isValidLogin(usuario, senha);
    }

}
