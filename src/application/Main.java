package application;

import javafx.application.Application;
import javafx.stage.Stage;
import util.LoadScene;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			new LoadScene().load(stage);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
