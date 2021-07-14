package appSaude.modelo;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {
	
	public static void mostrarAlerta(String title, String header, String content, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
	}
}
