package appSaude.controle;

import java.io.IOException;

import appSaude.modelo.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ControleCalcularPesoIdeal {

	@FXML
	private Button botaoCalcularPesoIdeal;

	@FXML
	private Button botaoVoltar;

	@FXML
	private TextField campoAltura;

	@FXML
	private ComboBox<String> comboBoxSexo;

	private double altura;
	private String sexo;
	private static double pesoIdeal;
	private static boolean calcularPesoIdeal = false;

	@FXML
	private void calcularPesoIdeal() {
		
		try {
			sexo = comboBoxSexo.getSelectionModel().getSelectedItem();
			altura = Double.parseDouble(campoAltura.getText().replaceAll(",", "."));

			if (altura > 0 && sexo.equals("Feminino")) {
				pesoIdeal = (altura * 62.1) - 44.7;
				calcularPesoIdeal = true;
				mudarTela("/appSaude/visao/VisaoResultado.fxml");
			} else if (altura > 0 && sexo.equals("Masculino")) {
				calcularPesoIdeal = true;
				pesoIdeal = (altura * 72.7) - 58;
				mudarTela("/appSaude/visao/VisaoResultado.fxml");
			} else {
				Alerta.mostrarAlerta("Erro", null, "Erro ao cálcular!\nCampo(s) inválido.\n", AlertType.ERROR);
			}
		} catch (NumberFormatException | NullPointerException erro) {
			Alerta.mostrarAlerta("Erro", null, "Erro ao cálcular!\nCampo(s) inválido.\n" + erro.getMessage(),
					AlertType.ERROR);
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

	public static double getPesoIdeal() {
		return pesoIdeal;
	}
	
	public static boolean setCalcularPesoIdeal(boolean isCalcularPesoIdeal) {
		return calcularPesoIdeal = isCalcularPesoIdeal;
	}
	
	public static boolean isCalcularPesoIdeal() {
		return calcularPesoIdeal;
	}
}
