package frontend.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.ProdutoController;
import backend.dto.ItemDTO;
import backend.utils.Categoria;
import frontend.utils.Constants;
import frontend.utils.LoadScene;
import frontend.views.utils.Alerts;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ViewProdutosController implements Initializable {

	ProdutoController controller = new ProdutoController();

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnBaixarModelo;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnInserirEmMassa;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnDashboard;

	@FXML
	private Button btnVendas;

	@FXML
	private TableColumn<ItemDTO, String> columnCodItem;

	@FXML
	private TableColumn<ItemDTO, String> columnDescricao;

	@FXML
	private TableColumn<ItemDTO, String> columnModelo;

	@FXML
	private TableColumn<ItemDTO, String> columnSituacao;

	@FXML
	private TableColumn<ItemDTO, String> columnVariacao;

	@FXML
	private TableView<ItemDTO> tbProduto;

	@FXML
	private TextField txtCodItem;

	@FXML
	private TextField txtDescricao;

	@FXML
	private TextField txtModelo;

	@FXML
	private TextField txtVariacao;

	@FXML
	void onVendasAction(ActionEvent event) {
		LoadScene.changeScene(Constants.VIEWS.VENDAS);
	}

	@FXML
	void onDashboardAction(ActionEvent event) {
		LoadScene.changeScene(Constants.VIEWS.DASHBOARD);
	}

	@FXML
	void onBuscarAction(ActionEvent event) {
		String codItem = !txtCodItem.getText().isBlank() ? "%" + txtCodItem.getText() + "%" : null;
		Categoria categoria = Categoria.obterPorValor(null);
		String modelo = !txtModelo.getText().isBlank() ? "%" + txtModelo.getText() + "%" : null;
		String variacao = !txtVariacao.getText().isBlank() ? "%" + txtVariacao.getText() + "%" : null;
		String descricao = !txtDescricao.getText().isBlank() ? "%" + txtDescricao.getText() + "%" : null;
		Integer estoque = null;

		try {
			montaTabela(controller.findProdutos(codItem, categoria, modelo, variacao, descricao, estoque));
		} catch (SQLException e) {
			Alerts.showAlert(null, e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	private void montaTabela(List<ItemDTO> vendas) {
		columnCodItem.setCellValueFactory(new PropertyValueFactory<>("codItem"));
		columnModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		columnVariacao.setCellValueFactory(new PropertyValueFactory<>("variacao"));
		columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		columnSituacao.setCellValueFactory(new PropertyValueFactory<>("isAtivo"));

		tbProduto.setItems(FXCollections.observableArrayList(vendas));
	}

	@FXML
	void onInserirAction(ActionEvent event) {
		callModalInserirProduto(Constraints.currentStage(event));
	}

	private void callModalInserirProduto(Stage parentStage) {
		LoadScene.callInsertProdutoModal(parentStage, getClass());
	}

	@FXML
	void onLimparAction(ActionEvent event) {
		txtCodItem.setText("");
		txtModelo.setText("");
		txtVariacao.setText("");
		txtDescricao.setText("");
		tbProduto.getItems().clear();
	}

	@FXML
	void onTbProdutoMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			ItemDTO selectedItem = tbProduto.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				callModalEditar(Constraints.currentStage(event), selectedItem.getId());
			}
		}
	}

	private void callModalEditar(Stage parentStage, Long id) {
		LoadScene.callEditProdutoModal(parentStage, getClass(), id);
	}

	@FXML
	void onInserirEmMassaAction(ActionEvent event) {
//		Alerts.showAlert(null, "Selecione o arquivo do tipo CSV (Separado por vírgulas)", AlertType.INFORMATION);
//		
//		FileChooser fileChooser = new FileChooser();
//		fileChooser.setTitle("Selecione um arquivo");
//		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo Separado por Vírgulas(*.csv)",
//				"*.csv");
//		fileChooser.getExtensionFilters().add(extFilter);
//
//		File file = fileChooser.showOpenDialog(Constraints.currentStage(event));
//
//		if (file == null)
//			return;
//
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader(file));
//
//			String line;
//			Boolean skip = Boolean.TRUE;
//
//			while (Objects.nonNull((line = reader.readLine()))) {
//				if (skip) {
//					skip = !skip;
//					continue;
//				}
//
//				String[] s = line.split(";");
//				String codItem = s[0];
//				String modelo = s[1];
//				String variacao = s[2];
//				String descricao = s[3];
//				itemController.insertItem(codItem, modelo, variacao, descricao, Boolean.TRUE);
//			}
//			Alerts.showAlert("Sucesso", null, "Operação realizada com sucesso.", AlertType.INFORMATION);
//			reader.close();
//		} catch (IOException | SQLException e) {
//			e.printStackTrace();
//		}
	}

	@FXML
	void onBaixarModeloAction(ActionEvent event) {
		Alerts.modeloProdutoAlert();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		LoadScene.getStage().setWidth(LoadScene.getWidth());
		LoadScene.getStage().setHeight(LoadScene.getHeight());
	}

}
