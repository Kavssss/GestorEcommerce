package util;

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
		this.stage = stage;
		stage.setTitle("Morais Couros");
		stage.setResizable(false);
		
		Parent indexFxml = FXMLLoader.load(getClass().getResource(Paths.VIEWS.INDEX));
		Parent vendasFxml = FXMLLoader.load(getClass().getResource(Paths.VIEWS.VENDAS));
		indexScene = new Scene(indexFxml);
		vendasScene = new Scene(vendasFxml);
					
		stage.setScene(indexScene);
		stage.show();
    }
	
	public static void changeScene(String newScene) {
		switch(newScene) {
			case Paths.VIEWS.INDEX:
				stage.setScene(indexScene);
				break;
			case Paths.VIEWS.VENDAS:
				stage.setScene(vendasScene);
				break;
			default:
				System.out.println("ERROR");
				break;
		}
	}
	
}
