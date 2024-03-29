package frontend.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class ExportPDF {

//	public static void exportPdf(Scene scene, String outputPDF) throws IOException {
//		Stage stage = new Stage();
//		stage.setScene(scene);
//		stage.show();
//
//		// Capture a screenshot of the JavaFX scene
//		WritableImage screenshot = scene.snapshot(null);
//		BufferedImage bufferedImage = SwingFXUtils.fromFXImage(screenshot, null);
//
//		// Create a PDF document
//		PDDocument document = new PDDocument();
//		PDPage page = new PDPage(PDRectangle.A4);
//		document.addPage(page);
//
//		try (PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, false)) {
//			contentStream.drawImage(LosslessFactory.createFromImage(document, bufferedImage), 0, 0);
//		}
//
//		// Save the PDF
//		document.save(outputPDF);
//		document.close();
//
//		// Close the JavaFX stage
//		stage.close();
//	}

//		PDDocument document = new PDDocument();
//		PDPage page = new PDPage(PDRectangle.A4);
//		document.addPage(page);
//		try {
//			PDPageContentStream contentStream = new PDPageContentStream(document, page);
//			float margin = 50;
//			float yStart = page.getMediaBox().getHeight() - margin;
//			float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
//			float yPosition = yStart;
//			int rows = table.getItems().size();
//			int cols = table.getColumns().size();
//			float rowHeight = 20f;
//			float tableHeight = rowHeight * rows;
//			float tableYBottom = yStart - tableHeight;
//
//			// Cabeçalho da tabela
//			float tableYPosition = yStart;
//			for (TableColumn<?, ?> column : table.getColumns()) {
//				float width = tableWidth / (float) cols;
//				contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//				contentStream.beginText();
//				contentStream.newLineAtOffset(margin, tableYPosition);
//				contentStream.showText(column.getText());
//				contentStream.endText();
//				tableYPosition -= rowHeight;
//			}
//
//			// Dados da tabela
//			for (int i = 0; i < rows; i++) {
//				tableYPosition = yStart - (i * rowHeight);
//				ObservableList<TableColumn<ItemDTO, ?>> columns = table.getColumns();
//				for (int j = 0; j < cols; j++) {
//					TableColumn<?, ?> column = columns.get(j);
//					float width = tableWidth / (float) cols;
//					contentStream.setFont(PDType1Font.HELVETICA, 12);
//					contentStream.beginText();
//					contentStream.newLineAtOffset(margin + j * width, tableYPosition);
//					contentStream.showText(column.getCellData(i).toString());
//					contentStream.endText();
//				}
//			}
//
//			contentStream.close();
//
//			// Salve o documento em um arquivo
//			FileChooser fileChooser = new FileChooser();
//			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
//			File file = fileChooser.showSaveDialog(LoadScene.getStage());
//
//			if (file != null) {
//				document.save(file);
//				document.close();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
