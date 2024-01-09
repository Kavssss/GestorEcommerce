package frontend.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import frontend.utils.Constants;
import frontend.utils.LoadScene;
import frontend.views.utils.Constraints;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class IndexController implements Initializable {
	
	@FXML
	private Button btnDashboard;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnTaxas;

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
	void onTaxasAction(ActionEvent event) {
		LoadScene.callOpcoesModal(Constraints.currentStage(event), getClass());
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Stage primaryStage = LoadScene.getStage();
		primaryStage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            	LoadScene.callLoginModal(LoadScene.getStage(), getClass());
            }
        });
		LoadScene.windowSizes();
	}
	
	

}
