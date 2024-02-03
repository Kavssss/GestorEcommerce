package frontend.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.ProdutoController;
import backend.entities.ProdutoEntity;
import backend.utils.Categoria;
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

	ProdutoController controller = new ProdutoController();

	@FXML
	private Button btnX;

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
	public void initialize(URL url, ResourceBundle rb) {
		if (txtCodItem.getText() == null)
			btnDesativar.setVisible(false);
		else
			btnDesativar.setVisible(true);
	}

	@FXML
	void onXAction(ActionEvent event) {
		LoadScene.getModalStage().close();
	}

	@FXML
	void onDesativarAction(ActionEvent event) {
		Long idItem = !txtIdItem.getText().isBlank() ? Long.valueOf(txtIdItem.getText()) : null;

		try {
			if (btnDesativar.getText().equals("ATIVAR"))
				controller.setFlagProduto(idItem, 1);
			else if (btnDesativar.getText().equals("DESATIVAR"))
				controller.setFlagProduto(idItem, 0);
		} catch (SQLException e) {
			Alerts.showAlert("ERRO", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	@FXML
	void onSalvarAction(ActionEvent event) {
		Long idItem = !txtIdItem.getText().isBlank() ? Long.valueOf(txtIdItem.getText()) : null;
		String codItem = !txtCodItem.getText().isBlank() ? txtCodItem.getText() : null;
		Categoria categoria = Categoria.obterPorValor(null); //Colocar o valor que vem aqui
		String modelo = !txtModelo.getText().isBlank() ? txtModelo.getText() : null;
		String variacao = !txtVariacao.getText().isBlank() ? txtVariacao.getText() : null;
		String descricao = !txtDescricao.getText().isBlank() ? txtDescricao.getText() : null;
		Integer estoque = null; //Colocar valor aqui

		if (Objects.isNull(codItem) || Objects.isNull(descricao) || Objects.isNull(categoria)) {
			Alerts.showAlert(null, Constants.MESSAGE.CAMPOS_NAO_PREENCHIDOS, AlertType.INFORMATION);
			return;
		}

		try {
			if (Objects.isNull(idItem)) {
				controller.insertProduto(codItem, categoria, modelo, variacao, descricao, estoque, Boolean.FALSE);
			} else {
				controller.editProduto(idItem, codItem, modelo, variacao, descricao, estoque);
			}
			LoadScene.getModalStage().close();
		} catch (SQLException e) {
			Alerts.showAlert(null, e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}
	
	// ajustar os novos campos
	public void updateFieldsEdit(Long id) {
		try {
			ProdutoEntity produto = controller.findById(id);
			txtIdItem.setText(produto.getId().toString());
			txtCodItem.setText(produto.getCodItem());
			txtModelo.setText(produto.getCodItem().split("-")[0]);
			txtVariacao.setText(produto.getCodItem().split("-")[1]);
			txtDescricao.setText(produto.getDescricao());
			btnDesativar.setText(produto.getIsAtivo() ? "DESATIVAR" : "ATIVAR");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
