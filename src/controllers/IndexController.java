package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import util.LoadScene;
import util.Paths;

public class IndexController {
	
    @FXML
    private Button btnProdutos;

    @FXML
    private Button btnRelatorios;

    @FXML
    private Button btnVendas;

    @FXML
    void onVendasEntered(MouseEvent event) {
    	hoverEffect(btnVendas);
    }
    
    @FXML
    void onVendasExited(MouseEvent event) {
    	unhoverEffect(btnVendas);
    }
    
    @FXML
    void onProdutosEntered(MouseEvent event) {
    	hoverEffect(btnProdutos);
    }
    
    @FXML
    void onProdutosExited(MouseEvent event) {
    	unhoverEffect(btnProdutos);
    }
    
    @FXML
    void onRelatoriosEntered(MouseEvent event) {
    	hoverEffect(btnRelatorios);
    }
    
    @FXML
    void onRelatoriosExited(MouseEvent event) {
    	unhoverEffect(btnRelatorios);
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

    private void hoverEffect(Button btn) {
    	String styles = btn.getStyle();
    	btn.setStyle(styles.concat(" -fx-background-color: #00FFFF;"));
    }
    
    private void unhoverEffect(Button btn) {
    	String styles = btn.getStyle();
    	btn.setStyle(styles.replace(" -fx-background-color: #00FFFF;", ""));
    	
    }
    
}
