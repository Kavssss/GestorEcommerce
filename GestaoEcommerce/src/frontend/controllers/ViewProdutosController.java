package frontend.controllers;

import java.sql.SQLException;
import java.util.List;

import backend.controllers.ItemController;
import backend.dto.ItemDTO;
import frontend.utils.Constants;
import frontend.utils.LoadScene;
import frontend.views.utils.Alerts;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewProdutosController {

	ItemController itemController = new ItemController();
	
    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnExportar;

    @FXML
    private Button btnInserir;

    @FXML
    private Button btnLimpar;

    @FXML
    private Button btnProdutos;

    @FXML
    private Button btnRelatorios;

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
    void onBuscarAction(ActionEvent event) {
    	String codItem = !txtCodItem.getText().isBlank() ? "%"+txtCodItem.getText()+"%" : null;
    	String modelo = !txtModelo.getText().isBlank() ? "%"+txtModelo.getText()+"%" : null;
    	String variacao = !txtVariacao.getText().isBlank() ? "%"+txtVariacao.getText()+"%" : null;
    	String descricao = !txtDescricao.getText().isBlank() ? "%"+txtDescricao.getText()+"%" : null;
    	
    	try {
    		montaTabela(itemController.findProdutos(codItem, modelo, variacao, descricao));
		} catch (SQLException e) {
			Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
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

}
