package frontend.controllers;

import frontend.utils.Constants;
import frontend.utils.LoadScene;
import frontend.views.utils.Constraints;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class IndexController {

	@FXML
	private Button btnDashboard;
	
    @FXML
    private Button btnProdutos;
    
    @FXML
	private Button btnOpcoes;

    @FXML
    private Button btnVendas;
    
    @FXML
    void onVendasAction(ActionEvent event) {
    	LoadScene.changeScene(Constants.VIEWS.VENDAS);
    }
    
    @FXML
    void onProdutosAction(ActionEvent event) {
    	LoadScene.changeScene(Constants.VIEWS.PRODUTOS);
    }

    @FXML
    void onDashboardAction(ActionEvent event) {
    	LoadScene.changeScene(Constants.VIEWS.DASHBOARD);
    }
    
    @FXML
    void onOpcoesAction(ActionEvent event) {
		LoadScene.callOpcoesModal(Constraints.currentStage(event), getClass());
    }
    
}
