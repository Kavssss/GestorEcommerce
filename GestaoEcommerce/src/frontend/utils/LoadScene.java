package frontend.utils;

import java.io.IOException;

import frontend.controllers.ModalInserirController;
import frontend.controllers.ModalProdutoController;
import frontend.views.utils.Alerts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadScene {

	private static Stage stage;
	private static Stage modalStage;
	private static Stage loginModalStage;
	
	private static Scene indexScene;
	private static Scene vendasScene;
	private static Scene produtosScene;
	private static Scene dashboardScene;

	public static void load(Stage stage, Class<?> currentClass) throws IOException {
		setStage(stage);
		stage.setTitle("Gestor Eccomerce");
		Image icon = new Image(currentClass.getResourceAsStream("../images/logo.png"));
		stage.getIcons().add(icon);
		stage.setResizable(false);

		Parent indexFxml = FXMLLoader.load(currentClass.getResource(Constants.VIEWS.INDEX));
		Parent vendasFxml = FXMLLoader.load(currentClass.getResource(Constants.VIEWS.VENDAS));
		Parent produtosFxml = FXMLLoader.load(currentClass.getResource(Constants.VIEWS.PRODUTOS));
		Parent dashboardFxml = FXMLLoader.load(currentClass.getResource(Constants.VIEWS.DASHBOARD));

		indexScene = new Scene(indexFxml);
		vendasScene = new Scene(vendasFxml);
		produtosScene = new Scene(produtosFxml);
		dashboardScene = new Scene(dashboardFxml);

		stage.setScene(indexScene);
		stage.show();
	}

	public static void changeScene(String newScene) {
		switch (newScene) {
		case Constants.VIEWS.INDEX:
			getStage().setScene(indexScene);
			break;
		case Constants.VIEWS.VENDAS:
			getStage().setScene(vendasScene);
			break;
		case Constants.VIEWS.PRODUTOS:
			getStage().setScene(produtosScene);
			break;
		case Constants.VIEWS.DASHBOARD:
			getStage().setScene(dashboardScene);
			break;
		default:
			System.out.println("ERROR");
			break;
		}
	}

	public static void callInsertVendaModal(Stage parentStage, Class<?> currentClass) {
		try {
			buildModal(parentStage, currentClass, Constants.MODAL.INSERIR_VENDA, "Inserir venda", false);
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR", e.getMessage(), AlertType.ERROR);
		}
	}
	
	public static void callLoginModal(Stage parentStage, Class<?> currentClass) {
		try {
			buildModal(parentStage, currentClass, Constants.MODAL.LOGIN, "Login", true);
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR", e.getMessage(), AlertType.ERROR);
		}
	}
	
	public static void callCadastroModal(Stage parentStage, Class<?> currentClass) {
		try {
			buildModal(parentStage, currentClass, Constants.MODAL.CADASTRO, "Cadastrar", false);
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR", e.getMessage(), AlertType.ERROR);
		}
	}

	private static void buildModal(Stage parentStage, Class<?> currentClass, String pathModal, String title, Boolean isLogin)
			throws IOException {
		FXMLLoader loader = new FXMLLoader(currentClass.getResource(pathModal));
		Pane pane = loader.load();
		Stage modalStage = new Stage();
//		Image icon = new Image(currentClass.getResourceAsStream("../images/logo.png"));
//		modalStage.getIcons().add(icon);
		modalStage.setTitle(title);
		modalStage.setScene(new Scene(pane));
		modalStage.setResizable(Boolean.FALSE);
		modalStage.initOwner(parentStage);
		modalStage.initModality(Modality.WINDOW_MODAL);
		modalStage.initStyle(StageStyle.UNDECORATED);
		setModalStage(modalStage);
		if (isLogin)
			setLoginModalStage(modalStage);
		modalStage.showAndWait();
	}

	public static void callEditVendaModal(Stage parentStage, Class<?> currentClass, Long id, String canal) {
		try {
			FXMLLoader loader = new FXMLLoader(currentClass.getResource(Constants.MODAL.INSERIR_VENDA));
			Pane pane = loader.load();
			ModalInserirController controller = loader.getController();
			controller.updateFieldsEdit(id, canal);
			Stage modalStage = new Stage();
			Image icon = new Image(currentClass.getResourceAsStream("../images/logo.png"));
			modalStage.getIcons().add(icon);
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

	public static void callInsertProdutoModal(Stage parentStage, Class<?> currentClass) {
		try {
			buildModal(parentStage, currentClass, Constants.MODAL.INSERIR_PRODUTO, "Inserir produto", false);
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR", e.getMessage(), AlertType.ERROR);
		}
	}

	public static void callEditProdutoModal(Stage parentStage, Class<?> currentClass, Long id) {
		try {
			FXMLLoader loader = new FXMLLoader(currentClass.getResource(Constants.MODAL.INSERIR_PRODUTO));
			Pane pane = loader.load();
			ModalProdutoController controller = loader.getController();
			controller.updateFieldsEdit(id);
			Stage modalStage = new Stage();
			Image icon = new Image(currentClass.getResourceAsStream("../images/logo.png"));
			modalStage.getIcons().add(icon);
			modalStage.setTitle("Editar produto");
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

	public static void callOpcoesModal(Stage parentStage, Class<?> currentClass) {
		try {
			buildModal(parentStage, currentClass, Constants.MODAL.OPCOES, "Opções", false);
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "ERROR", e.getMessage(), AlertType.ERROR);
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

	public static Stage getLoginModalStage() {
		return loginModalStage;
	}

	public static void setLoginModalStage(Stage loginModal) {
		LoadScene.loginModalStage = loginModal;
	}
	
}
