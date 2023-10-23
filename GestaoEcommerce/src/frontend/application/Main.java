package frontend.application;

import java.util.Locale;

import frontend.utils.LoadScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		Locale.setDefault(new Locale("pt", "BR"));
		try {
			LoadScene.load(stage, getClass());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
