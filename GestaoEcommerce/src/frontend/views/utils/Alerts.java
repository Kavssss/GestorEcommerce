package frontend.views.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

public class Alerts {

	private static Boolean isConfirmed = Boolean.FALSE;
	private static File file;
	private static Boolean isDownload = Boolean.FALSE;

	public static void showAlert(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.initStyle(StageStyle.UNDECORATED);
		alert.showAndWait();
	}

	public static Boolean confirmationAlert(String title, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setContentText(content);

		alert.showAndWait().ifPresent(response -> {
			if (response == alert.getButtonTypes().get(0))
				isConfirmed = Boolean.TRUE;
			else if (response == alert.getButtonTypes().get(1))
				isConfirmed = Boolean.FALSE;
		});
		return isConfirmed;
	}

	public static void tipoArquivoAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Escolha o tipo de arquivo");
		alert.setHeaderText("Deseja baixar o arquivo CSV de modelo?");

		ButtonType btnShopee = new ButtonType("Shopee");
		ButtonType btnMercadoLivre = new ButtonType("Mercado Livre");
		ButtonType btnCancel = new ButtonType("Cancelar");

		alert.getButtonTypes().setAll(btnShopee, btnMercadoLivre, btnCancel);

		alert.showAndWait().ifPresent(response -> {
			if (response == alert.getButtonTypes().get(0)) {
				file = new File("src/backend/planilhasModelo/shopee.CSV");
				isDownload = Boolean.TRUE;
			} else if (response == alert.getButtonTypes().get(1)) {
				file = new File("src/backend/planilhasModelo/mercadoLivre.CSV");
				isDownload = Boolean.TRUE;
			} else
				return;
		});

		if (isDownload) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Salvar Arquivo");
			fileChooser.setInitialFileName("Planilha modelo");
			fileChooser.getExtensionFilters()
					.add(new FileChooser.ExtensionFilter("Arquivo Separado por Vírgulas(*.csv)", "*.csv"));
			File arquivoDestino = fileChooser.showSaveDialog(null);

			if (arquivoDestino != null) {
				try {
					Files.copy(file.toPath(), arquivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			showAlert("Sucesso", "Arquivo baixado", null, AlertType.INFORMATION);
		}
	}

	public static void modeloProdutoAlert() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Baixar modelo");
		alert.setHeaderText("Deseja baixar o arquivo CSV de modelo?");

		alert.showAndWait().ifPresent(response -> {
			if (response == alert.getButtonTypes().get(0)) {
				file = new File("src/backend/planilhasModelo/produtos.CSV");
				isDownload = Boolean.TRUE;
			} else
				return;
		});

		if (isDownload) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Salvar Arquivo");
			fileChooser.setInitialFileName("Planilha modelo");
			fileChooser.getExtensionFilters()
					.add(new FileChooser.ExtensionFilter("Arquivo Separado por Vírgulas(*.csv)", "*.csv"));
			File arquivoDestino = fileChooser.showSaveDialog(null);

			if (arquivoDestino != null) {
				try {
					Files.copy(file.toPath(), arquivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			showAlert("Sucesso", "Arquivo baixado", null, AlertType.INFORMATION);
		}
	}

}
