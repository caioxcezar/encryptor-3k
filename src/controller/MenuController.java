package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public void encriptarArquivo() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/Encriptar.fxml"));
        stage.setTitle("criptografar");
        stage.setScene(new Scene(root, 640, 380));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void desencriptarArquivo() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/Desencriptar.fxml"));
        stage.setTitle("Descriptografar");
        stage.setScene(new Scene(root, 640, 380));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
    public void abrirConta() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/ContaInfo.fxml"));
        stage.setTitle("Informação da Conta");
        stage.setScene(new Scene(root, 640, 380));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
