package utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadScene {
	
	private static Stage stage;
	private static Scene indexScene;
	private static Scene vendasScene;
	
	private static Scene modalInserirVenda;
	
	public void load(Stage stage) throws IOException {
		setStage(stage);
		stage.setTitle("Morais Couros");
		stage.setResizable(false);
		
		Parent indexFxml = FXMLLoader.load(getClass().getResource(Paths.VIEWS.INDEX));
		Parent vendasFxml = FXMLLoader.load(getClass().getResource(Paths.VIEWS.VENDAS));
		Parent modalInserirVendaFXML = FXMLLoader.load(getClass().getResource(Paths.MODAL.INSERIR_VENDA));
		
		indexScene = new Scene(indexFxml);
		vendasScene = new Scene(vendasFxml);
		modalInserirVenda = new Scene(modalInserirVendaFXML);
					
		stage.setScene(indexScene);
		stage.show();
    }
	
	public static void changeScene(String newScene) {
		switch(newScene) {
			case Paths.VIEWS.INDEX:
				getStage().setScene(indexScene);
				break;
			case Paths.VIEWS.VENDAS:
				getStage().setScene(vendasScene);
				break;
			default:
				System.out.println("ERROR");
				break;
		}
	}
	
	public static void callModalInserirVenda(Stage parentStage) {
		Stage modalStage = new Stage();
		modalStage.setTitle("Inserir venda");
		modalStage.setScene(modalInserirVenda);
		modalStage.setResizable(false);
		modalStage.initOwner(parentStage);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.showAndWait();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		LoadScene.stage = stage;
	}
	
}
