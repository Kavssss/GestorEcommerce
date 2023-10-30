package frontend.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import backend.controllers.VendaGeralController;
import frontend.utils.Constants;
import frontend.utils.LoadScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DashboardController implements Initializable {

	VendaGeralController geralController = new VendaGeralController();
	
	@FXML
    private Button btnDashboard;

    @FXML
    private Button btnProdutos;

    @FXML
    private Button btnVendas;

    @FXML
    private ComboBox<Integer> cbAno;

    @FXML
    private PieChart grPorCanal;

    @FXML
    private LineChart<?, ?> grPorMes;

    @FXML
    private TextField txtDataInicio;

    @FXML
    private Label txtVendas;
    
    @FXML
    private Label txtValorTotal;
    
    @FXML
    private Label txtCancelamentos;
    
    @FXML
    private Label txtDevolucoes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	cbAno.setItems(FXCollections.observableArrayList(Arrays.asList(2023, 2022, 2021, 2020)));
    	cbAno.setValue(2023);
    	atualizaDados();
    }
    
    @FXML
    void onProdutosAction(ActionEvent event) {
    	LoadScene.changeScene(Constants.VIEWS.PRODUTOS);
    }
    
    @FXML
    void onVendasAction(ActionEvent event) {
    	LoadScene.changeScene(Constants.VIEWS.VENDAS);
    }

	@FXML
    void onAnoAction(ActionEvent event) {
		atualizaDados();
    }
	
	private void atualizaDados() {
		Integer[] vendas = geralController.countVendas(cbAno.getSelectionModel().getSelectedItem());
		Double[] valorVendas = geralController.findValorTotal(cbAno.getSelectionModel().getSelectedItem());
		Integer[] cancelados = geralController.countByStatus(Constants.STATUS.CANCELADO);
		Integer[] devolvidos = geralController.countByStatus(Constants.STATUS.DEVOLUCAO);
		
		txtVendas.setText(vendas[0].toString());
		txtValorTotal.setText(valorVendas[0].toString());
		txtCancelamentos.setText(cancelados[0].toString());
		txtDevolucoes.setText(devolvidos[0].toString());
		
		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
    			new PieChart.Data(Constants.LOJA.SHOPEE, calculaPerCent(vendas[1], vendas[0])),
    			new PieChart.Data(Constants.LOJA.MERCADO_LIVRE, calculaPerCent(vendas[2], vendas[0])));
    	grPorCanal.setData(pieData);
	}
	
	private Double calculaPerCent(Integer divisor, Integer dividendo) {
		if (dividendo != 0)
			return Double.valueOf(divisor) / Double.valueOf(dividendo) * 100;
		return 0D;
	}

}
