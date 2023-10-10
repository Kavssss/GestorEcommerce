package frontend.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadScene {
	
	private static Stage stage;
	private static Scene indexScene;
	private static Scene vendasScene;
	
	public void load(Stage stage) throws IOException {
		setStage(stage);
		stage.setTitle("Morais Couros");
		stage.setResizable(false);
		
		Parent indexFxml = FXMLLoader.load(getClass().getResource(Constants.VIEWS.INDEX));
		Parent vendasFxml = FXMLLoader.load(getClass().getResource(Constants.VIEWS.VENDAS));
		
		indexScene = new Scene(indexFxml);
		vendasScene = new Scene(vendasFxml);
					
		stage.setScene(indexScene);
		stage.show();
    }
	
	public static void changeScene(String newScene) {
		switch(newScene) {
			case Constants.VIEWS.INDEX:
				getStage().setScene(indexScene);
				break;
			case Constants.VIEWS.VENDAS:
				getStage().setScene(vendasScene);
				break;
			default:
				System.out.println("ERROR");
				break;
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		LoadScene.stage = stage;
	}
	
}
