package frontend.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import backend.controllers.VendaGeralController;
import backend.controllers.VendaMercadoLivreController;
import backend.controllers.VendaShopeeController;
import backend.dto.VendaGeralDTO;
import backend.dto.VendaMercadoLivreDTO;
import backend.dto.VendaShopeeDTO;
import backend.entities.shopee.VendaShopeeEntity;
import backend.repositories.vendaShopee.VendaShopeeRepository;
import backend.repositories.vendaShopee.VendaShopeeRepositoryImpl;
import backend.services.VendaShopeeService;
import frontend.utils.Constants;
import frontend.utils.DataUtils;
import frontend.utils.LoadScene;
import frontend.utils.enums.StatusVenda;
import frontend.views.utils.Alerts;
import frontend.views.utils.Constraints;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.DbException;

public class ViewVendasController implements Initializable {

	VendaGeralController geralController = new VendaGeralController();
	VendaShopeeController shopeeController = new VendaShopeeController();
	VendaMercadoLivreController mercadoLivreController = new VendaMercadoLivreController();

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnBaixarModelo;

	@FXML
	private Button btnInserir;

	@FXML
	private Button btnInserirEmMassa;

	@FXML
	private Button btnLimpar;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnDashboard;

	@FXML
	private Button btnTaxas;

	@FXML
	private Button btnVendas;

	@FXML
	private ComboBox<String> cbCanal;

	@FXML
	private ComboBox<String> cbTipoAnuncio;

	@FXML
	private TableView<VendaGeralDTO> tbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnDataTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, Number> columnQtdTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnItemTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnClienteTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, Number> columnUnitTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, Number> columnTotalTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, Number> columnRecebidoTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnStatusTbGeral;
	@FXML
	private TableColumn<VendaGeralDTO, String> columnCanalTbGeral;

	@FXML
	private TableView<VendaMercadoLivreDTO> tbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnDataTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnAnuncioTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, Number> columnQtdTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnItemTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnClienteTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, Number> columnUnitTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, Number> columnTotalTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, Number> columnRecebidoTbMercadoLivre;
	@FXML
	private TableColumn<VendaMercadoLivreDTO, String> columnStatusTbMercadoLivre;

	@FXML
	private TableView<VendaShopeeDTO> tbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, String> columnDataTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, Number> columnQtdTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, String> columnItemTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, String> columnClienteTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, Number> columnUnitTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, Number> columnTotalTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, Number> columnRecebidoTbShopee;
	@FXML
	private TableColumn<VendaShopeeDTO, String> columnStatusTbShopee;

	@FXML
	private TextField txtCliente;

	@FXML
	private TextField txtCodItem;

	@FXML
    private DatePicker dpDataFim;

    @FXML
    private DatePicker dpDataInicio;

	@FXML
	private TextField txtQtde;

	@FXML
	private ComboBox<String> cbStatus;
	
	@FXML
    private Label labelError;
	
	@FXML
    private Button btnCopyDate;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		LoadScene.getStage().setWidth(LoadScene.getWidth());
		LoadScene.getStage().setHeight(LoadScene.getHeight());
		setNumberFields();
		setItensComboBox();
	}

	@FXML
	void onCanalAction() {
		List<String> options;
		String selection = cbCanal.getSelectionModel().getSelectedItem();
		if (selection != null && selection.equals(Constants.LOJA.MERCADO_LIVRE)) {
			cbTipoAnuncio.setPromptText(Constants.TIPO_ANUNCIO.TIPO_ANUNCIO);
			options = Arrays.asList(Constants.TIPO_ANUNCIO.CLASSICO, Constants.TIPO_ANUNCIO.PREMIUM);
			cbTipoAnuncio.setItems(FXCollections.observableArrayList(options));
		} else {
			cbTipoAnuncio.setPromptText("-");
			cbTipoAnuncio.setItems(null);
		}
	}

	@FXML
	void onInserirAction(ActionEvent event) {
		LoadScene.callInsertVendaModal(Constraints.currentStage(event), getClass());
	}
	
	private void ajustaColuna(TableView<?> table, TableColumn<?, ?> column, Double percent) {
		Double value = (table.getWidth() - 22D) * percent;
		column.setMinWidth(value);
		column.setMaxWidth(value);
		column.setPrefWidth(value);
	}

	@FXML
	void onBuscarAction() {
		String canal = cbCanal.getSelectionModel().getSelectedItem();
		Date dataInicio = Objects.nonNull(dpDataInicio.getValue()) ? Date.valueOf(dpDataInicio.getValue()) : null;
		Date dataFim = Objects.nonNull(dpDataFim.getValue()) ? Date.valueOf(dpDataFim.getValue()) : null;
		String tipoAnuncio = cbTipoAnuncio.getSelectionModel().getSelectedItem();
		Integer qtde = !txtQtde.getText().isBlank() ? Integer.parseInt(txtQtde.getText()) : null;
		String codItem = !txtCodItem.getText().isBlank() ? "%" + txtCodItem.getText() + "%" : null;
		String cliente = !txtCliente.getText().isBlank() ? "%" + txtCliente.getText() + "%" : null;
		String status = cbStatus.getSelectionModel().getSelectedItem();
		
		ajustaColuna(tbGeral, columnDataTbGeral, 0.1);
		ajustaColuna(tbGeral, columnQtdTbGeral, 0.05);
		ajustaColuna(tbGeral, columnItemTbGeral, 0.1);
		ajustaColuna(tbGeral, columnClienteTbGeral, 0.3);
		ajustaColuna(tbGeral, columnUnitTbGeral, 0.1);
		ajustaColuna(tbGeral, columnTotalTbGeral, 0.1);
		ajustaColuna(tbGeral, columnRecebidoTbGeral, 0.1);
		ajustaColuna(tbGeral, columnStatusTbGeral, 0.1);
		ajustaColuna(tbGeral, columnCanalTbGeral, 0.05);
		
		ajustaColuna(tbMercadoLivre, columnDataTbMercadoLivre, 0.09);
		ajustaColuna(tbMercadoLivre, columnAnuncioTbMercadoLivre, 0.09);
		ajustaColuna(tbMercadoLivre, columnQtdTbMercadoLivre, 0.07);
		ajustaColuna(tbMercadoLivre, columnItemTbMercadoLivre, 0.09);
		ajustaColuna(tbMercadoLivre, columnClienteTbMercadoLivre, 0.3);
		ajustaColuna(tbMercadoLivre, columnUnitTbMercadoLivre, 0.09);
		ajustaColuna(tbMercadoLivre, columnTotalTbMercadoLivre, 0.09);
		ajustaColuna(tbMercadoLivre, columnRecebidoTbMercadoLivre, 0.09);
		ajustaColuna(tbMercadoLivre, columnStatusTbMercadoLivre, 0.09);
		
		if (Objects.nonNull(dataInicio) && Objects.isNull(dataFim))
			dataFim = Date.valueOf(DataUtils.MAX_DATE);
		else if (Objects.isNull(dataInicio) && Objects.nonNull(dataFim))
			dataInicio = Date.valueOf(DataUtils.MIN_DATE);
		
		System.out.println(dataInicio);
		System.out.println(dataFim);
		
		if (Objects.nonNull(dataInicio) && Objects.nonNull(dataFim))
			if (dataInicio.after(dataFim)) {
				labelError.setText("\'Data Início\' após \'Data Fim\'");
				labelError.setVisible(true);
				return;
			}

		if (canal == null || canal.equals(Constants.LOJA.GERAL)) {
			try {
				montaTabelaGeral(geralController.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status));
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			}
		} else if (canal.equals(Constants.LOJA.SHOPEE)) {
			try {
				montaTabelaShopee(shopeeController.findVendas(dataInicio, dataFim, qtde, codItem, cliente, status));
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			}
		} else if (canal.equals(Constants.LOJA.MERCADO_LIVRE)) {
			try {
				montaTabelaML(mercadoLivreController.findVendas(dataInicio, dataFim, tipoAnuncio, qtde, codItem,
						cliente, status));
			} catch (SQLException e) {
				Alerts.showAlert("SQL Exception", "ERRO", e.getMessage(), AlertType.ERROR);
			}
		}
	}

	private void montaTabelaGeral(List<VendaGeralDTO> vendas) {
		columnDataTbGeral.setCellValueFactory(new PropertyValueFactory<>("fData"));
		columnClienteTbGeral.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		columnQtdTbGeral.setCellValueFactory(new PropertyValueFactory<>("qtde"));
		columnItemTbGeral.setCellValueFactory(new PropertyValueFactory<>("codItem"));
		columnUnitTbGeral.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		columnTotalTbGeral.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		columnRecebidoTbGeral.setCellValueFactory(new PropertyValueFactory<>("valorRecebido"));
		columnStatusTbGeral.setCellValueFactory(new PropertyValueFactory<>("status"));
		columnCanalTbGeral.setCellValueFactory(new PropertyValueFactory<>("canal"));
		setVisibilityTables(true, false, false);

		tbGeral.setItems(FXCollections.observableArrayList(vendas));
	}

	private void montaTabelaShopee(List<VendaShopeeDTO> vendas) {
		columnDataTbShopee.setCellValueFactory(new PropertyValueFactory<>("fData"));
		columnClienteTbShopee.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		columnQtdTbShopee.setCellValueFactory(new PropertyValueFactory<>("qtde"));
		columnItemTbShopee.setCellValueFactory(new PropertyValueFactory<>("codItem"));
		columnUnitTbShopee.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		columnTotalTbShopee.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		columnRecebidoTbShopee.setCellValueFactory(new PropertyValueFactory<>("valorRecebido"));
		columnStatusTbShopee.setCellValueFactory(new PropertyValueFactory<>("status"));
		setVisibilityTables(false, true, false);

		tbShopee.setItems(FXCollections.observableArrayList(vendas));
	}

	private void montaTabelaML(List<VendaMercadoLivreDTO> vendas) {
		columnDataTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("fData"));
		columnAnuncioTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("tipoAnuncio"));
		columnQtdTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("qtde"));
		columnItemTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("codItem"));
		columnClienteTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		columnUnitTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
		columnTotalTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
		columnRecebidoTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("valorRecebido"));
		columnStatusTbMercadoLivre.setCellValueFactory(new PropertyValueFactory<>("status"));
		setVisibilityTables(false, false, true);

		tbMercadoLivre.setItems(FXCollections.observableArrayList(vendas));
	}

	@FXML
	void onLimparAction() {
		cbCanal.getSelectionModel().clearSelection();
		cbCanal.setPromptText("");
		cbTipoAnuncio.getSelectionModel().clearSelection();
		txtQtde.setText("");
		txtCodItem.setText("");
		txtCliente.setText("");
		cbStatus.getSelectionModel().clearSelection();
		cbStatus.setPromptText("");
		dpDataInicio.setValue(null);
		dpDataFim.setValue(null);
		btnCopyDate.setVisible(false);
		setVisibilityTables(false, false, false);
	}

	@FXML
	void onTbGeralMouseClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			VendaGeralDTO selectedItem = tbGeral.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				callModalEditarVenda(Constraints.currentStage(event), selectedItem.getId(), selectedItem.getCanal());
			}
		}
	}

	@FXML
	void onTbMercadoLivreClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			VendaMercadoLivreDTO selectedItem = tbMercadoLivre.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				callModalEditarVenda(Constraints.currentStage(event), selectedItem.getId(), "ML");
			}
		}
	}

	@FXML
	void onTbShopeeClicked(MouseEvent event) {
		if (event.getClickCount() == 2) {
			VendaShopeeDTO selectedItem = tbShopee.getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				callModalEditarVenda(Constraints.currentStage(event), selectedItem.getId(), "S");
			}
		}
	}

	private void callModalEditarVenda(Stage parentStage, Long id, String canal) {
		LoadScene.callEditVendaModal(parentStage, getClass(), id, canal);
	}

	private void setNumberFields() {
		Constraints.setTextFieldNumber(txtQtde);
		Constraints.setTextFieldMaxLength(txtQtde, 5);
	}

	private void setItensComboBox() {
		List<String> options = Arrays.asList(Constants.LOJA.SHOPEE, Constants.LOJA.MERCADO_LIVRE, Constants.LOJA.GERAL);
		cbCanal.setItems(FXCollections.observableArrayList(options));
		cbTipoAnuncio.setPromptText("-");
		options = Arrays.asList(Constants.STATUS.TODOS, Constants.STATUS.PENDENTE, Constants.STATUS.CONCLUIDO,
				Constants.STATUS.DEVOLUCAO, Constants.STATUS.CANCELADO);
		cbStatus.setItems(FXCollections.observableArrayList(options));
	}

	private void setVisibilityTables(Boolean g, Boolean s, Boolean ml) {
		tbGeral.setVisible(g);
		tbShopee.setVisible(s);
		tbMercadoLivre.setVisible(ml);
		if (!g)
			tbGeral.getItems().clear();
		if (!s)
			tbShopee.getItems().clear();
		if (!ml)
			tbMercadoLivre.getItems().clear();
	}

	@FXML
	void onInserirEmMassaAction(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecione o arquivo");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Arquivo Excel (*.xlsx)", "*.xlsx");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(Constraints.currentStage(event));

		if (file.getName().contains("Order.toship.")) {
			System.out.println("Planilha SHOPEE");
			return;
		} else if (file.getName().contains("Vendas_BR_Mercado_Livre_")) {
			System.out.println("Planilha MERCADO LIVRE");
			return;
		}

		try {
			FileInputStream fis = new FileInputStream(file);
		    XSSFWorkbook workbook = new XSSFWorkbook(fis);
		    XSSFSheet sheet = workbook.getSheetAt(0);
		    int skip = 0;
		    String lastId = "";

		    for (Row row : sheet) {
		    	if (skip == 0) {
		    		skip++;
		    		continue;
		    	}

		    	String idPedido = row.getCell(0).toString();
	        	Date date = DataUtils.stringExcelToDate(row.getCell(9).toString());
	        	String cliente = row.getCell(43).toString();
	        	String cpf = row.getCell(46).toString();
	        	Integer qtde = Integer.valueOf(row.getCell(16).toString());
	        	String item = row.getCell(12).toString();
	        	Double valorUnitario = Double.valueOf(row.getCell(15).toString());
	        	Double valorTotal = Double.valueOf(row.getCell(18).toString());
	        	Double valorRepasse = 
	        			valorTotal - Double.valueOf(row.getCell(39).toString()) - Double.valueOf(row.getCell(40).toString());
	        	String status = row.getCell(1).toString();
	        	
	        	shopeeController.insertVenda(date, cliente, status, item, qtde, valorUnitario, valorTotal, valorRepasse);
	        	
	        	System.out.println("INSERIDO");
		    }
		    
		    fis.close();
		} catch (IOException | SQLException e) {
			throw new DbException(e);
		}
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

	@FXML
	void onBaixarModeloAction(ActionEvent event) {
		Alerts.tipoArquivoAlert();
	}
	
	@FXML
	void onDataInicioAction(ActionEvent event) {
		btnCopyDate.setText(">");
		btnCopyDate.setVisible(true);
	}
	
	@FXML
    void onDataFimAction(ActionEvent event) {
		btnCopyDate.setText("<");
		btnCopyDate.setVisible(true);
    }
	
	@FXML
    void onCopyDateAction(ActionEvent event) {
		if (btnCopyDate.getText().equals("<"))
			dpDataInicio.setValue(dpDataFim.getValue());
		else
			dpDataFim.setValue(dpDataInicio.getValue());
    }

}
