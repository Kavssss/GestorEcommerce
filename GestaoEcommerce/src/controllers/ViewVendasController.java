package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.LoadScene;
import views.utils.Constraints;

public class ViewVendasController implements Initializable {

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
    private ComboBox<String> cbCanal;

    @FXML
    private ComboBox<String> cbConta;

    @FXML
    private TableView<?> tbGeral;

    @FXML
    private TableView<?> tbMercadoLivre;

    @FXML
    private TableView<?> tbShopee;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtCodItem;

    @FXML
    private TextField txtDataFim;

    @FXML
    private TextField txtDataInicio;

    @FXML
    private TextField txtQtde;

    @FXML
    private TextField txtStatus;
    
    @FXML
    private ObservableList<String> obsList;

    @FXML
    void onVendasEntered(MouseEvent event) {
    	Constraints.hoverEffect(btnVendas);
    }
    
    @FXML
    void onVendasExited(MouseEvent event) {
    	Constraints.unhoverEffect(btnVendas);
    }
    
    @FXML
    void onProdutosEntered(MouseEvent event) {
    	Constraints.hoverEffect(btnProdutos);
    }
    
    @FXML
    void onProdutosExited(MouseEvent event) {
    	Constraints.unhoverEffect(btnProdutos);
    }
    
    @FXML
    void onRelatoriosEntered(MouseEvent event) {
    	Constraints.hoverEffect(btnRelatorios);
    }
    
    @FXML
    void onRelatoriosExited(MouseEvent event) {
    	Constraints.unhoverEffect(btnRelatorios);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	Constraints.setTextFieldInteger(txtQtde);
    	List<String> options = Arrays.asList("Shopee", "Mercado Livre", "Todos");
    	cbCanal.setItems(FXCollections.observableArrayList(options));
    	options = Arrays.asList("Principal");
    	cbConta.setItems(FXCollections.observableArrayList(options));
    }
    
    public void carregaTela(Stage stage) throws IOException {
    	Parent root = new FXMLLoader(getClass().getResource("../views/Index.fxml")).load();		
		stage.setScene(new Scene(root));
		stage.setTitle("Morais Couros");
		stage.setResizable(false);			
		stage.show();
    }
    
    @FXML
    void onInserirAction(ActionEvent event) {
    	LoadScene.callModalInserirVenda(Constraints.currentStage(event));
    }
    
}
