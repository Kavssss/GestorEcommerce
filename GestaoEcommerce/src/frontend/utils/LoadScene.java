package frontend.utils;

import java.io.IOException;

import frontend.controllers.ModalInserirController;
import frontend.views.utils.Alerts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadScene {
	
	private static Stage stage;
	private static Stage modalStage;
	private static Scene indexScene;
	private static Scene vendasScene;
	
	public static void load(Stage stage, Class<?> currentClass) throws IOException {
		setStage(stage);
		stage.setTitle("Morais Couros");
		stage.setResizable(false);
		
		Parent indexFxml = FXMLLoader.load(currentClass.getResource(Constants.VIEWS.INDEX));
		Parent vendasFxml = FXMLLoader.load(currentClass.getResource(Constants.VIEWS.VENDAS));
		
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
	
	public static void callModal(Stage parentStage, Class<?> currentClass) {
		try {
			FXMLLoader loader = new FXMLLoader(currentClass.getResource(Constants.MODAL.INSERIR_VENDA));
			Pane pane = loader.load();
			Stage modalStage = new Stage();
			modalStage.setTitle("Inserir venda");
			modalStage.setScene(new Scene(pane));
			modalStage.setResizable(Boolean.FALSE);
			modalStage.initOwner(parentStage);
			modalStage.initModality(Modality.WINDOW_MODAL);
			setModalStage(modalStage);
			modalStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR", e.getMessage(), AlertType.ERROR);
		}
	}
	
	public static void callEditModal(Stage parentStage, Class<?> currentClass, Long id, String canal) {
		try {
			FXMLLoader loader = new FXMLLoader(currentClass.getResource(Constants.MODAL.INSERIR_VENDA));
			Pane pane = loader.load();
			ModalInserirController controller = loader.getController();
			controller.updateFieldsEdit(id, canal);
			Stage modalStage = new Stage();
			modalStage.setTitle("Editar venda");
			modalStage.setScene(new Scene(pane));
			modalStage.setResizable(Boolean.FALSE);
			modalStage.initOwner(parentStage);
			modalStage.initModality(Modality.WINDOW_MODAL);
			setModalStage(modalStage);
			modalStage.showAndWait();
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		LoadScene.stage = stage;
	}

	public static Stage getModalStage() {
		return modalStage;
	}

	public static void setModalStage(Stage modalStage) {
		LoadScene.modalStage = modalStage;
	}
	
}
