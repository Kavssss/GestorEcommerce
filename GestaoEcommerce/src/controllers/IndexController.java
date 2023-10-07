package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import utils.LoadScene;
import utils.Paths;
import views.utils.Constraints;

public class IndexController {
	
    @FXML
    private Button btnProdutos;

    @FXML
    private Button btnRelatorios;

    @FXML
    private Button btnVendas;

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
    
    @FXML
    void onVendasAction(ActionEvent event) {
    	LoadScene.changeScene(Paths.VIEWS.VENDAS);
    }
    
    @FXML
    void onProdutosAction(ActionEvent event) {

    }

    @FXML
    void onRelatoriosAction(ActionEvent event) {

    }
    
}
