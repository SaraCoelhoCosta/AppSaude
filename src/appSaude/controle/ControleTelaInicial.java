package appSaude.controle;

import java.io.IOException;

import appSaude.modelo.Alerta;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ControleTelaInicial {

	@FXML
	private Button botaoTelaCalcularIMC;

	@FXML
	private Button botaoTelaCalcularPesoIdeal;

	@FXML
	private Button botaoSair;

	@FXML
	public void calcularIMC() {
		mudarTela("/appSaude/visao/VisaoCalcularIMC.fxml");
	}

	@FXML
	private void calcularPesoIdeal() {
		mudarTela("/appSaude/visao/VisaoCalcularPesoIdeal.fxml");
	}

	private synchronized void mudarTela(String tela) {
		try {
			FXMLLoader arquivoFXML = new FXMLLoader(getClass().getResource(tela));
			GridPane raiz = arquivoFXML.load();
			Scene telaAtual = AppSaude.getTelaAtual();
			GridPane raizPrincipal = (GridPane) ((GridPane) telaAtual.getRoot());
			raizPrincipal.getChildren().clear();
			raizPrincipal.getChildren().addAll(raiz.getChildren());
		} catch (IOException e) {
			Alerta.mostrarAlerta("Erro", null, "Erro ao carregar a tela!\n" + e.getMessage(), AlertType.ERROR);
		}
	}

	@FXML
	private void sair() {
		AppSaude.fechar(AppSaude.getJanela());
	}
}
