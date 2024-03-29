package frontend.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import backend.controllers.VendaGeralController;
import backend.controllers.VendaMercadoLivreController;
import backend.controllers.VendaShopeeController;
import frontend.utils.Constants;
import frontend.utils.DataUtils;
import frontend.utils.LoadScene;
import frontend.utils.enums.TipoOperacao;
import frontend.views.utils.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class DashboardController implements Initializable {

	VendaGeralController geralController = new VendaGeralController();
	VendaShopeeController shopeeController = new VendaShopeeController();
	VendaMercadoLivreController mercadoLivreController = new VendaMercadoLivreController();

	@FXML
	private BarChart<Double, String> barChart;

	@FXML
	private Button btnDashboard;

	@FXML
	private Button btnPorAno;

	@FXML
	private Button btnPorMes;

	@FXML
	private Button btnPorSemestre;

	@FXML
	private Button btnProdutos;

	@FXML
	private Button btnVendas;

	@FXML
	private ComboBox<Integer> cbAno;

	@FXML
	private ComboBox<String> cbMes;

	@FXML
	private ComboBox<String> cbSemestre;

	@FXML
	private Label labelPieChart;

	@FXML
	private Label labelPor;

	@FXML
	private LineChart<String, Double> lineChart;

	@FXML
	private PieChart pieChart;

	@FXML
	private Label txtCancelamentos;

	@FXML
	private TextField txtDataInicio;

	@FXML
	private Label txtDevolucoes;

	@FXML
	private Label txtValorTotal;

	@FXML
	private Label txtVendas;

	@FXML
	private CategoryAxis xAxisLineChart;

	@FXML
	private NumberAxis yAxisLineChart;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cbAno.setItems(FXCollections.observableArrayList(Arrays.asList(2023, 2022, 2021, 2020)));
		cbSemestre.setItems(FXCollections.observableArrayList(DataUtils.getListSemestres()));
		cbMes.setItems(FXCollections.observableArrayList(DataUtils.getListMeses()));
		cbAno.setValue(Constants.ANO._2023);
		labelPor.setText("Por Ano");
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

	@FXML
	void onSemestreAction(ActionEvent event) {
		atualizaDados();
	}

	@FXML
	void onMesAction(ActionEvent event) {
		atualizaDados();
	}

	private void zeraComboBoxes() {
		cbAno.setValue(null);
		cbSemestre.setValue(null);
		cbMes.setValue(null);
	}

	@SuppressWarnings("unchecked")
	public void atualizaDados() {
		Integer ano = cbAno.getSelectionModel().getSelectedItem();
		String semestre = cbSemestre.getSelectionModel().getSelectedItem();
		Integer mes = DataUtils.converteMesToInt(cbMes.getSelectionModel().getSelectedItem());

		if (Objects.isNull(ano))
			return;

		Integer[] vendas;
		Double[] valorVendas;
		Integer[] cancelados;
		Integer[] devolvidos;
		List<Double> valorPorMesShopee;
		List<Double> valorPorMesML;

		if (Objects.nonNull(mes)) {
			vendas = geralController.countVendasPorAno(ano, mes, mes);
			valorVendas = geralController.findValorTotalPorAno(ano, mes, mes);
			cancelados = geralController.countByStatus(Constants.STATUS.CANCELADO, ano, mes, mes);
			devolvidos = geralController.countByStatus(Constants.STATUS.DEVOLUCAO, ano, mes, mes);
			valorPorMesShopee = shopeeController.findValorTotalPorMes(ano, mes, mes);
			valorPorMesML = mercadoLivreController.findValorTotalPorMes(ano, mes, mes);
			prepareLineChart(valorPorMesShopee, valorPorMesML, TipoOperacao.MENSAL);
		} else if (Objects.nonNull(semestre)) {
			if (semestre.equals(Constants.SEMESTRE.PRIMEIRO)) {
				vendas = geralController.countVendasPorAno(ano, 1, 6);
				valorVendas = geralController.findValorTotalPorAno(ano, 1, 6);
				cancelados = geralController.countByStatus(Constants.STATUS.CANCELADO, ano, 1, 6);
				devolvidos = geralController.countByStatus(Constants.STATUS.DEVOLUCAO, ano, 1, 6);
				valorPorMesShopee = shopeeController.findValorTotalPorMes(ano, 1, 6);
				valorPorMesML = mercadoLivreController.findValorTotalPorMes(ano, 1, 6);
				prepareLineChart(valorPorMesShopee, valorPorMesML, TipoOperacao.SEMESTRAL_1);
			} else if (semestre.equals(Constants.SEMESTRE.SEGUNDO)) {
				vendas = geralController.countVendasPorAno(ano, 7, 12);
				valorVendas = geralController.findValorTotalPorAno(ano, 7, 12);
				cancelados = geralController.countByStatus(Constants.STATUS.CANCELADO, ano, 7, 12);
				devolvidos = geralController.countByStatus(Constants.STATUS.DEVOLUCAO, ano, 7, 12);
				valorPorMesShopee = shopeeController.findValorTotalPorMes(ano, 7, 12);
				valorPorMesML = mercadoLivreController.findValorTotalPorMes(ano, 7, 12);
				prepareLineChart(valorPorMesShopee, valorPorMesML, TipoOperacao.SEMESTRAL_2);
			} else {
				Alerts.showAlert("Campo não preenchido", "Selecione o semestre", null, AlertType.INFORMATION);
				return;
			}
		} else {
			vendas = geralController.countVendasPorAno(ano, 1, 12);
			valorVendas = geralController.findValorTotalPorAno(ano, 1, 12);
			cancelados = geralController.countByStatus(Constants.STATUS.CANCELADO, ano, 1, 12);
			devolvidos = geralController.countByStatus(Constants.STATUS.DEVOLUCAO, ano, 1, 12);
			valorPorMesShopee = shopeeController.findValorTotalPorMes(ano, 1, 12);
			valorPorMesML = mercadoLivreController.findValorTotalPorMes(ano, 1, 12);
			prepareLineChart(valorPorMesShopee, valorPorMesML, TipoOperacao.ANUAL);
		}

		txtVendas.setText(vendas[0].toString());

		if (valorVendas[0] > 99999)
			txtValorTotal.setFont(new Font(txtValorTotal.getFont().getName(), 16));
		else
			txtValorTotal.setFont(new Font(txtValorTotal.getFont().getName(), 17));

		txtValorTotal.setText("R$ " + formataReais(valorVendas[0]));
		txtCancelamentos.setText(cancelados[0].toString());
		txtDevolucoes.setText(devolvidos[0].toString());

		ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
				new PieChart.Data(
						Constants.LOJA.SHOPEE + " ("
								+ String.format("%.2f", calculaPercent(valorVendas[1], valorVendas[0])) + "%)",
						calculaPercent(valorVendas[1], valorVendas[0])),
				new PieChart.Data(
						Constants.LOJA.MERCADO_LIVRE + " ("
								+ String.format("%.2f", calculaPercent(valorVendas[2], valorVendas[0])) + "%)",
						calculaPercent(valorVendas[2], valorVendas[0])));
		pieChart.setData(pieData);

		labelPieChart.setText(Constants.LOJA.SHOPEE + "\nR$ " + formataReais(valorVendas[1]) + "\n" + vendas[1]
				+ " vendas" + "\n" + devolvidos[1] + " devoluções" + "\n" + cancelados[1] + " cancelamentos\n\n"
				+ Constants.LOJA.MERCADO_LIVRE + "\nR$ " + formataReais(valorVendas[2]) + "\n" + vendas[2] + " vendas"
				+ "\n" + devolvidos[2] + " devoluções" + "\n" + cancelados[2] + " cancelamentos");

		barChart.getData().clear();
		XYChart.Series<Double, String> barShopee = new XYChart.Series<>();
		barShopee.setName(Constants.LOJA.SHOPEE);
		barShopee.getData().add(new XYChart.Data<>(calculaPercent(valorVendas[1], valorVendas[0]),
				Constants.LOJA.SHOPEE + "\n" + vendas[1] + " vendas"));

		XYChart.Series<Double, String> barML = new XYChart.Series<>();
		barML.setName(Constants.LOJA.MERCADO_LIVRE);
		barML.getData().add(new XYChart.Data<>(calculaPercent(valorVendas[2], valorVendas[0]),
				Constants.LOJA.MERCADO_LIVRE + "\n" + vendas[2] + " vendas"));

		barChart.getData().addAll(barShopee, barML);
	}

	@SuppressWarnings("unchecked")
	private void prepareLineChart(List<Double> shopee, List<Double> mercadoLivre, TipoOperacao to) {
		lineChart.getData().clear();

		XYChart.Series<String, Double> lineShopee = new XYChart.Series<>();
		lineShopee.setName(Constants.LOJA.SHOPEE);
		setLineChartValues(lineShopee, shopee, to);
		XYChart.Series<String, Double> lineML = new XYChart.Series<>();
		lineML.setName(Constants.LOJA.MERCADO_LIVRE);
		setLineChartValues(lineML, mercadoLivre, to);

		lineChart.getData().addAll(lineShopee, lineML);
	}

	private void setLineChartValues(XYChart.Series<String, Double> line, List<Double> values, TipoOperacao to) {
		if (to.equals(TipoOperacao.MENSAL)) {
			List<String> listDias = DataUtils
					.getListDias(DataUtils.converteMesToInt(cbMes.getSelectionModel().getSelectedItem()));
			for (int i = 0; i < listDias.size(); i++)
				line.getData().add(new XYChart.Data<>(listDias.get(i), values.get(i)));
		} else if (to.equals(TipoOperacao.SEMESTRAL_1)) {
			setJaneiroJunhoLineChart(line, values, 0);
		} else if (to.equals(TipoOperacao.SEMESTRAL_2)) {
			setJulhoDezembroLineChart(line, values, 0);
		} else if (to.equals(TipoOperacao.ANUAL)) {
			setJaneiroJunhoLineChart(line, values, 0);
			setJulhoDezembroLineChart(line, values, 6);
		}

	}

	private void setJaneiroJunhoLineChart(XYChart.Series<String, Double> line, List<Double> values, Integer index) {
		line.getData().add(new XYChart.Data<>(Constants.MES.JAN, values.get(index)));
		line.getData().add(new XYChart.Data<>(Constants.MES.FEV, values.get(index + 1)));
		line.getData().add(new XYChart.Data<>(Constants.MES.MAR, values.get(index + 2)));
		line.getData().add(new XYChart.Data<>(Constants.MES.ABR, values.get(index + 3)));
		line.getData().add(new XYChart.Data<>(Constants.MES.MAI, values.get(index + 4)));
		line.getData().add(new XYChart.Data<>(Constants.MES.JUN, values.get(index + 5)));
	}

	private void setJulhoDezembroLineChart(XYChart.Series<String, Double> line, List<Double> values, Integer index) {
		line.getData().add(new XYChart.Data<>(Constants.MES.JUL, values.get(index)));
		line.getData().add(new XYChart.Data<>(Constants.MES.AGO, values.get(index + 1)));
		line.getData().add(new XYChart.Data<>(Constants.MES.SET, values.get(index + 2)));
		line.getData().add(new XYChart.Data<>(Constants.MES.OUT, values.get(index + 3)));
		line.getData().add(new XYChart.Data<>(Constants.MES.NOV, values.get(index + 4)));
		line.getData().add(new XYChart.Data<>(Constants.MES.DEZ, values.get(index + 5)));
	}

	private Double calculaPercent(Double divisor, Double dividendo) {
		if (dividendo != 0)
			return Double.valueOf(divisor) / Double.valueOf(dividendo) * 100;
		return 0D;
	}

	@FXML
	void onPorAnoAction(ActionEvent event) {
		cbSemestre.setVisible(Boolean.FALSE);
		cbMes.setVisible(Boolean.FALSE);
		zeraComboBoxes();
		cbAno.setValue(Constants.ANO._2023);
		labelPor.setText("Por Ano");
		atualizaDados();
	}

	@FXML
	void onPorMesAction(ActionEvent event) {
		cbSemestre.setVisible(Boolean.FALSE);
		cbMes.setVisible(Boolean.TRUE);
		zeraComboBoxes();
		cbMes.setValue(Constants.MES.JANEIRO);
		cbAno.setValue(Constants.ANO._2023);
		labelPor.setText("Por Mês");
		atualizaDados();
	}

	@FXML
	void onPorSemestreAction(ActionEvent event) {
		cbSemestre.setVisible(Boolean.TRUE);
		cbMes.setVisible(Boolean.FALSE);
		zeraComboBoxes();
		cbSemestre.setValue(Constants.SEMESTRE.PRIMEIRO);
		cbAno.setValue(Constants.ANO._2023);
		labelPor.setText("Por Semestre");
		atualizaDados();
	}

	private String formataReais(Double valor) {
		return new DecimalFormat("#,##0.00").format(valor);
	}

}
