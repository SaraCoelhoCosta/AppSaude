package appSaude.controle;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AppSaude extends Application {

	private static Stage janela;
	private static Scene telaAtual;

	@Override
	public void start(Stage primaryStage) throws Exception {
		janela = primaryStage;

		String arquivoCSS = getClass().getResource("/appSaude/visao/Aparencia.css").toExternalForm();
		URL arquivoFXML = getClass().getResource("/appSaude/visao/VisaoTelaInicial.fxml");
		GridPane raiz = FXMLLoader.load(arquivoFXML);

		telaAtual = new Scene(raiz, 320, 250); // Define qual a tela e o tamanho.
		telaAtual.getStylesheets().add(arquivoCSS);
		telaAtual.getStylesheets().add("https://fonts.googleapis.com/css2?family=Acme");
		
		janela.setResizable(false); // Não permite alterar o tamanho da tela.
		janela.setTitle("Calculadora da Saúde"); // Define o título do programa.
		janela.setScene(telaAtual); // Chama a tela.
		janela.show(); // Exibe a tela.
	}
	
	public static Scene getTelaAtual() {
		return telaAtual;
	}
	
	public static Stage getJanela() {
		return janela;
	}
	
	public static void fechar(Stage primary) {
		primary.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
