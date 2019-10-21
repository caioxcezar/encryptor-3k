package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MensagemController implements Initializable {
    @FXML
    private Label lblMsg, lblTitulo;
    @FXML
    private Button btnOk;

    public void setMensagem(String mensagem) {
        this.lblMsg.setText(mensagem);
    }

    public void setLblTitulo(String lblTitulo) {
        this.lblTitulo.setText(lblTitulo);
    }

    public void fecharTela() {
        Stage stage = (Stage) btnOk.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setError();
    }

    private void setError() {
        this.lblMsg.setText("Ocoreu um erro");
    }
}
