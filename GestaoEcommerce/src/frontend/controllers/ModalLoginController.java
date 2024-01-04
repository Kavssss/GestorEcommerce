package frontend.controllers;

import java.util.Objects;

import frontend.utils.LoadScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ModalLoginController {

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

    @FXML
    void onXAction(ActionEvent event) {
    	LoadScene.getLoginModalStage().close();
    }

    @FXML
    void onEntrarAction(ActionEvent event) {
    	String nome = !txtUsuario.getText().isBlank() ? txtUsuario.getText() : null;
    	String senha = !txtSenha.getText().isBlank() ? txtSenha.getText() : null;
    	
    	if (Objects.isNull(nome)) {
			labelError.setText("Usuário não pode ser vazio!");
    		labelError.setVisible(true);
    		return;
		}
    	if (Objects.isNull(senha)) {
			labelError.setText("Senha não pode ser vazio!");
    		labelError.setVisible(true);
    		return;
		}
    	
    	LoadScene.getModalStage().close();
    }

    @FXML
    void onNovoAction(ActionEvent event) {
    	LoadScene.callCadastroModal(LoadScene.getStage(), getClass());
    }

}
