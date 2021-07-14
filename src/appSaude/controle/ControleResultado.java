package appSaude.controle;

import java.io.IOException;

import appSaude.modelo.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

public class ControleResultado {

	@FXML
	private Label labelCalculo;

	@FXML
	private Button botaoConfirmar;

	private String texto;

	@FXML
	public void initialize() {

		labelCalculo.getStyleClass().remove("label-baixo-peso");
		labelCalculo.getStyleClass().remove("label-saudavel");
		labelCalculo.getStyleClass().remove("label-sobrepeso");
		labelCalculo.getStyleClass().remove("label-obesidade1");
		labelCalculo.getStyleClass().remove("label-obesidade2");
		labelCalculo.getStyleClass().remove("label-obesidade3");

		if (ControleCalcularIMC.isCalcularIMC()) {

			ControleCalcularIMC.setCalcularIMC(false);
			if (ControleCalcularIMC.getImc() < 18.5) {
				texto = String.format("Seu IMC: %.2f\n\nBaixo Peso!", ControleCalcularIMC.getImc());
				labelCalculo.getStyleClass().add("label-baixo-peso");
			} else if (ControleCalcularIMC.getImc() >= 18.5 && ControleCalcularIMC.getImc() < 25) {
				texto = String.format("Seu IMC: %.2f\n\nSaudável!", ControleCalcularIMC.getImc());
				labelCalculo.getStyleClass().add("label-saudavel");
			} else if (ControleCalcularIMC.getImc() >= 25 && ControleCalcularIMC.getImc() < 30) {
				texto = String.format("Seu IMC: %.2f\n\nSobrepeso!", ControleCalcularIMC.getImc());
				labelCalculo.getStyleClass().add("label-sobrepeso");
			} else if (ControleCalcularIMC.getImc() >= 30 && ControleCalcularIMC.getImc() < 35) {
				texto = String.format("Seu IMC: %.2f\n\nObesidade de grau 1", ControleCalcularIMC.getImc());
				labelCalculo.getStyleClass().add("label-obesidade1");
			} else if (ControleCalcularIMC.getImc() >= 35 && ControleCalcularIMC.getImc() < 40) {
				texto = String.format("Seu IMC: %.2f\n\nObesidade de grau 2", ControleCalcularIMC.getImc());
				labelCalculo.getStyleClass().add("label-obesidade2");
			} else if (ControleCalcularIMC.getImc() >= 40) {
				texto = String.format("Seu IMC: %.2f\n\nObesidade de grau 2", ControleCalcularIMC.getImc());
				labelCalculo.getStyleClass().add("label-obesidade3");
			}
			labelCalculo.setText(texto);
		} else if (ControleCalcularPesoIdeal.isCalcularPesoIdeal()) {
			ControleCalcularPesoIdeal.setCalcularPesoIdeal(false);
			texto = String.format("O peso ideal é:\n%.2f", ControleCalcularPesoIdeal.getPesoIdeal());
			labelCalculo.getStyleClass().add("label-saudavel");
			labelCalculo.setText(texto);
		}
	}

	@FXML
	public void confirmar() {
		mudarTela("/appSaude/visao/VisaoTelaInicial.fxml");
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

}
