package utils;

import controller.MensagemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FxUtils {
    public static void mensagemLoad(String titulo, String mensagem) {
        try {
            FXMLLoader loader = new FXMLLoader(FxUtils.class.getResource("../view/MensagemTela.fxml"));
            Stage stage = new Stage();
            Parent root = loader.load();
            MensagemController controller = loader.getController();
            controller.setMensagem(mensagem);
            controller.setLblTitulo(titulo);
            stage.setScene(new Scene(root, 640, 380));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
