package appSaude.controle;

import java.io.IOException;

import appSaude.modelo.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ControleCalcularIMC {

	@FXML
	private Button botaoCalcularIMC;

	@FXML
	private Button botaoVoltar;

	@FXML
	private TextField campoPeso;

	@FXML
	private TextField campoAltura;

	private double peso;
	private double altura;
	private static double imc;
	private static boolean calcularIMC = false;

	@FXML
	private void calcularIMC() {
	
		try {
			peso = Double.parseDouble(campoPeso.getText().replaceAll(",", "."));
			altura = Double.parseDouble(campoAltura.getText().replaceAll(",", "."));
			if (peso > 0 && altura > 0) {
				imc = peso / (altura * altura);
				calcularIMC = true;
				mudarTela("/appSaude/visao/VisaoResultado.fxml");
			} else {
				Alerta.mostrarAlerta("Erro", null, "Erro ao cálcular!\nCampo(s) inválido.\n", AlertType.ERROR);
			}
		} catch (NumberFormatException | NullPointerException erro) {
			Alerta.mostrarAlerta("Erro", null, "Erro ao cálcular!\nCampo(s) inválido.\n" + erro.getMessage(), AlertType.ERROR);
		}
	}

	private synchronized void mudarTela(String tela) {
		try {
			FXMLLoader arquivoFXML = new FXMLLoader(getClass().getResource(tela));
			GridPane raiz = arquivoFXML.load();
			Scene telaAtual = AppSaude.getTelaAtual();
			GridPane raizPrincipal = (GridPane) ((GridPane) telaAtual.getRoot());
			raizPrincipal.getChildren().clear();
			raizPrincipal.getChildren().addAll(raiz.getChildren());

		} catch (IOException erro) {
			Alerta.mostrarAlerta("Erro", null, "Erro ao carregar a tela!\n" + erro.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	public void voltar() {
		mudarTela("/appSaude/visao/VisaoTelaInicial.fxml");
	}

	public static double getImc() {
		return imc;
	}	
	
	public static boolean setCalcularIMC(boolean isCalcularIMC) {
		return calcularIMC = isCalcularIMC;
	}
	
	public static boolean isCalcularIMC() {
		return calcularIMC;
	}
}
